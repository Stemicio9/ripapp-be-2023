package it.ripapp.ripapp.controller;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.gson.JsonObject;
import it.ripapp.ripapp.authentication.model.LoginRequest;
import it.ripapp.ripapp.dto.AccountSearchEntity;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.PhoneBookSyncEntity;
import it.ripapp.ripapp.exceptions.ResponseException;

import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.services.DemiseService;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


@RestController
@RequestMapping(value = "/api/auth")
public class UserController extends AbstractController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private DemiseService demiseService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/search/demises/autocomplete")
    @ResponseBody
    public ResponseEntity searchDemisesAutocomplete(
            @RequestParam String query,
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(demiseService.userDemisesAutocomplete(userid, query), HttpStatus.OK);
    }


    @PutMapping("/account")
    @ResponseBody
    public ResponseEntity updateAccount(
            @RequestBody AccountEntity accountEntity,
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(accountService.updateUserByID(userid, accountEntity), HttpStatus.OK);
    }

    @GetMapping("/account")
    @ResponseBody
    public ResponseEntity getAccountByContext(@CookieValue String firebasecookie) throws ResponseException, Exception {
        return GetResponse(accountService.accountFromToken(firebasecookie), HttpStatus.OK);
    }


    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity login(@RequestBody LoginRequest loginRequest,
                                HttpServletResponse response) throws ResponseException, Exception {
        //  return GetResponse(accountService.getAccountByID(userid), HttpStatus.OK);
        FirebaseAuthCookieData firebaseAuthData = accountService.getUserbaseUUIDByFirebaseToken(loginRequest.getIdtoken());

        Cookie cookie1 = new Cookie("firebasecookie", firebaseAuthData.getCookie());
        Cookie cookie2 = new Cookie("userid", firebaseAuthData.getAccountid());
        response.addCookie(cookie2);
        response.addCookie(cookie1);
        return GetResponse("CIAO", HttpStatus.OK);
    }

    @PostMapping("/phonebook")
    @ResponseBody
    public ResponseEntity syncPhonebook(
            @CookieValue Long userid,
            @RequestBody Collection<PhoneBookSyncEntity> phoneBookSyncEntity) throws ResponseException {
        return GetResponse(accountService.syncPhoneBook(userid, phoneBookSyncEntity), HttpStatus.OK);
    }

    @GetMapping("/account/list")
    public Page<AccountEntity> getAllUser(@RequestParam Integer pageNumber, @RequestParam Integer pageElements) {
        AccountSearchEntity accountSearch = new AccountSearchEntity(pageNumber, pageElements);
        return accountService.findAllAccounts(accountSearch);
    }

    @DeleteMapping("/account")
    @ResponseBody
    public ResponseEntity deleteAccount(
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(accountService.deleteAccount(userid), HttpStatus.OK);
    }

    @DeleteMapping("/account/{idUser}")
    @ResponseBody
    public ResponseEntity deleteUser(
            @PathVariable Long idUser) throws FirebaseAuthException {
        AccountEntity toDelete = accountService.getAccountByID(idUser);
        return GetResponse(accountService.deleteUser(idUser), HttpStatus.OK);
    }


    @RequestMapping(value = "/agency/{agencyid}/phonebook", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity sendPhoneBook(
            @CookieValue Long userid,
            @PathVariable Long agencyid,
            @RequestParam String file) throws ResponseException {

        return GetResponse(accountService.sendPhoneBook(userid, agencyid, file), HttpStatus.CREATED);
    }

    @GetMapping("/agency/search")
    @ResponseBody
    public ResponseEntity searchAgency(
            @CookieValue Long userid,
            @RequestParam String query) throws ResponseException {
        return GetResponse(agencyService.searchAgency(userid, query), HttpStatus.OK);
    }

    @PostMapping("/user/playerid/{playerid}")
    @ResponseBody
    public ResponseEntity setPlayerID(
            @CookieValue Long userid,
            @PathVariable String playerid) throws ResponseException {
        return GetResponse(accountService.addPlayerID(userid, playerid), HttpStatus.CREATED);
    }

    @GetMapping("/publicCityList")
    public List<Object> cityList() {
        RestTemplate restTemplate = new RestTemplate();
        List<Object> result;
        result = (List<Object>) restTemplate.getForObject("https://axqvoqvbfjpaamphztgd.functions.supabase.co/comuni", Object.class);
        return result;

       /* List<String> aryLst = new ArrayList<String>();
        for (int i = 0; i < result.size(); i++) {
            aryLst.add(result.get(i).toString());
        }
        System.out.println("FORSE FUNZIONA");
        System.out.println(result);
        return aryLst;*/
    }
}
   /* List<Object> lst  =listOfTypeObject;
    ArrayList<String> aryLst = new ArrayList<String>();
    for (int i = 0; i < lst.size(); i++) {
        aryLst.add(lst.get(i).toString());
        }

    */