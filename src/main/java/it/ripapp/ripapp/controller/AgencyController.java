package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.bll.AgencyBLL;
import it.ripapp.ripapp.bll.TextBLL;
import it.ripapp.ripapp.entities.DemiseEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class AgencyController extends AbstractController {

    private AgencyBLL agencyBLL;

    @Autowired
    public AgencyController(TextBLL textBLL, AgencyBLL agencyBLL) {
        super(textBLL);
        this.agencyBLL = agencyBLL;
    }



    @RequestMapping(value = "/demises", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAgencyDemises(
            @RequestParam Integer offset,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.getAgencyDemises(userid, offset), HttpStatus.OK);
    }


    @RequestMapping(value = "/demise", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insertDemise(
            @RequestBody DemiseEntity demise,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.insertDemise(userid, demise), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/demise/{demiseid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteDemise(
            @PathVariable UUID demiseid,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.deleteDemiseByID(userid, demiseid), HttpStatus.OK);
    }


    @RequestMapping(value = "/demise/{demiseid}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateDemise(
            @PathVariable UUID demiseid,
            @RequestBody DemiseEntity demise,
            @CookieValue UUID userid) throws ResponseException {
        return GetResponse(agencyBLL.updateDemise(userid,demiseid, demise), HttpStatus.CREATED);
    }


    @RequestMapping(value = "/account/search", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity searchAccount(
            @RequestParam String query,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.searchAccount(userid, query), HttpStatus.OK);
    }



}
