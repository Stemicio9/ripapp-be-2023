package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.exceptions.ResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class AgencyController extends AbstractController {



/*
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



    @RequestMapping(value = "/products", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAgencyProducts(
            @RequestParam Integer offset,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.getAgencyProducts(userid, offset), HttpStatus.OK);
    }


    @RequestMapping(value = "/product", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity insertProduct(
            @RequestBody ProductEntity product,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.insertProduct(userid, product), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/{productid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity deleteProduct(
            @PathVariable UUID productId,
            @CookieValue UUID userid) throws ResponseException {

        return GetResponse(agencyBLL.deleteProductByID(userid, productId), HttpStatus.OK);
    }


    @RequestMapping(value = "/product/{productid}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity updateProduct(
            @PathVariable UUID productid,
            @RequestBody ProductEntity product,
            @CookieValue UUID userid) throws ResponseException {
        return GetResponse(agencyBLL.updateProduct(userid,productid, product), HttpStatus.CREATED);
    }

*/

}
