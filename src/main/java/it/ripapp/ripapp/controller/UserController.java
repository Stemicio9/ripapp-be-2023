package it.ripapp.ripapp.controller;


import it.ripapp.ripapp.authentication.model.LoginRequest;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.PhoneBookSyncEntity;
import it.ripapp.ripapp.exceptions.ResponseException;

import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.services.DemiseService;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.UUID;


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
        FirebaseAuthCookieData firebaseAuthData =  accountService.getUserbaseUUIDByFirebaseToken(loginRequest.getIdtoken());

        response.addCookie(new Cookie("firebasecookie", firebaseAuthData.getCookie()));
        response.addCookie(new Cookie("userid", firebaseAuthData.getAccountid()));
        return GetResponse("CIAO", HttpStatus.OK);
    }

    @PostMapping("/phonebook")
    @ResponseBody
    public ResponseEntity syncPhonebook(
            @CookieValue Long userid,
            @RequestBody Collection<PhoneBookSyncEntity> phoneBookSyncEntity) throws ResponseException {
        return GetResponse(accountService.syncPhoneBook(userid, phoneBookSyncEntity), HttpStatus.OK);
    }

    @DeleteMapping("/account")
    @ResponseBody
    public ResponseEntity deleteAccount(
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(accountService.deleteAccount(userid), HttpStatus.OK);
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


}
