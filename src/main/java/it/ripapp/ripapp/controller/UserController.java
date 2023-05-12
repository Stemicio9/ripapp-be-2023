package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.bll.*;
import it.ripapp.ripapp.entities.FiltersEntity;
import it.ripapp.ripapp.entities.IEntity;
import it.ripapp.ripapp.entities.PhoneBookSyncEntity;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.exceptions.ResponseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api/auth")
public class UserController extends AbstractController {

    private UserBLL userBLL;
    private DemiseBLL demiseBLL;
    private AgencyBLL agencyBLL;



    @Autowired
    public UserController(TextBLL textBLL, UserBLL userBLL, DemiseBLL demiseBLL, AgencyBLL agencyBLL) {
        super(textBLL);
        this.userBLL = userBLL;
        this.demiseBLL = demiseBLL;
        this.agencyBLL = agencyBLL;
    }

    @RequestMapping(value = "/search/demises", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity searchDemisesByCity(
            @RequestBody FiltersEntity filters,
            @CookieValue UUID userid,
            @CookieValue Lang lang) throws ResponseException {

        return GetResponse(demiseBLL.getDemisesByFilters(filters, userid, lang), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/search/demises/autocomplete", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchDemisesAutocomplete(
            @RequestParam String query,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(demiseBLL.userDemisesAutocomplete(userid, query), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/demises", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity getUserLinkedDemises(
            @RequestBody FiltersEntity filters,
            @CookieValue Lang lang,
            @CookieValue UUID userid) throws ResponseException {
        return GetResponse(demiseBLL.getUserLinkedDemises(userid, filters, lang), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateAccount(
            @RequestBody AccountEntity accountEntity,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(userBLL.updateUserByID(userid, accountEntity), HttpStatus.OK);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAccountByCookie(
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse((Collection<? extends IEntity>) userBLL.getAccountByID(userid), HttpStatus.OK);
    }

    @RequestMapping(value = "/phonebook", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity syncPhonebook(
            @CookieValue UUID userid,
            @RequestBody PhoneBookSyncEntity phoneBookSyncEntity) throws ResponseException {

        return GetResponse(userBLL.syncPhoneBookChunk(userid, phoneBookSyncEntity), HttpStatus.CREATED);
    }

   /* @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getUserUnreadDemises(
            @CookieValue UUID userid,
            @CookieValue Lang lang,
            @RequestParam Integer offset) throws ResponseException {

        return GetResponse(userBLL.getUserUnreadDemises(userid, offset, lang), HttpStatus.OK);
    }

    */

    @RequestMapping(value = "/account", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteAccount(
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(userBLL.deleteAccount(userid), HttpStatus.OK);
    }



    @RequestMapping(value = "/agency/{agencyid}/phonebook", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public ResponseEntity sendPhoneBook(
            @CookieValue UUID userid,
            @PathVariable UUID agencyid,
            @RequestParam String file) throws ResponseException {

        return GetResponse(userBLL.sendPhoneBook(userid, agencyid, file), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/agency/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchAgency(
            @CookieValue UUID userid,
            @RequestParam String query) {
        return GetResponse(agencyBLL.searchAgency(userid, query), HttpStatus.OK);
    }


    @RequestMapping(value = "/user/playerid/{playerid}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity setPlayerID(
            @CookieValue UUID userid,
            @PathVariable String playerid) {
        return GetResponse(userBLL.addPlayerID(userid, playerid), HttpStatus.CREATED);
    }



}
