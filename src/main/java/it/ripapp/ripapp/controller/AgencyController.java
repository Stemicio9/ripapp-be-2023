package it.ripapp.ripapp.controller;


import it.ripapp.ripapp.entityUpdate.DemiseEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.services.DemiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class AgencyController extends AbstractController {

    @Autowired
    private DemiseService demiseService;

    @Autowired
    private AgencyService agencyService;

    @GetMapping("/demises")
    @ResponseBody
    public ResponseEntity getAgencyDemises(
            @RequestParam Integer offset,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(demiseService.getAgencyDemises(userid, offset), HttpStatus.OK);
    }

    @PostMapping("/demise")
    @ResponseBody
    public ResponseEntity insertDemise(
            @RequestBody DemiseEntity demise,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(demiseService.insertDemise(userid, demise), HttpStatus.CREATED);
    }

    @DeleteMapping("/demise/{demiseid}")
    @ResponseBody
    public ResponseEntity deleteDemise(
            @PathVariable Long demiseid,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(demiseService.deleteDemiseByID(userid, demiseid), HttpStatus.OK);
    }

    @PutMapping("/demise/{demiseid}")
    @ResponseBody
    public ResponseEntity updateDemise(
            @PathVariable Long demiseid,
            @RequestBody DemiseEntity demise,
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(demiseService.updateDemise(userid,demiseid, demise), HttpStatus.CREATED);
    }

    @GetMapping("/account/search")
    @ResponseBody
    public ResponseEntity searchAccount(
            @RequestParam String query,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(agencyService.searchAccount(userid, query), HttpStatus.OK);
    }

    @GetMapping("/products")
    @ResponseBody
    public ResponseEntity getAgencyProducts(
            @RequestParam Integer offset,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(agencyService.getAgencyProducts(userid, offset), HttpStatus.OK);
    }

    @GetMapping("/all-products")
    @ResponseBody
    public ResponseEntity getAllProducts(Long userId) throws ResponseException {
        return GetResponse(agencyService.getAvailableProducts(userId), HttpStatus.OK);
    }



    @PostMapping("/product")
    @ResponseBody
    public ResponseEntity insertProduct(
            @RequestBody ProductEntity product,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(agencyService.insertProduct(userid, product), HttpStatus.CREATED);
    }

    @DeleteMapping("/product/{productid}")
    @ResponseBody
    public ResponseEntity deleteProduct(
            @PathVariable Long productid,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(agencyService.deleteProduct(userid, productid), HttpStatus.OK);
    }

    @PutMapping("/product/{productid}")
    @ResponseBody
    public ResponseEntity updateProduct(
            @PathVariable Long productid,
            @RequestBody ProductEntity product,
            @CookieValue Long userid) throws ResponseException {
        return GetResponse(agencyService.updateProduct(userid,productid, product), HttpStatus.CREATED);
    }


    //TODO usato solo per testing, va eliminato e sostituito con quello che usa i cookie; idem per quello delle agenzie
    @PostMapping("/demiseWithoutCookie")
    @ResponseBody
    public ResponseEntity insertDemiseNew(@RequestBody DemiseEntity demiseEntity) throws ResponseException {
        return GetResponse(demiseService.insertDemiseForTesting(demiseEntity), HttpStatus.CREATED);
    }



}
