package it.ripapp.ripapp.controller;


import it.ripapp.ripapp.bll.Kinship;
import com.google.firebase.auth.FirebaseAuthException;
import it.ripapp.ripapp.dto.ProductOffered;
import it.ripapp.ripapp.entityUpdate.DemiseEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.services.AdminService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.services.DemiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/auth")
public class AgencyController extends AbstractController {

    @Autowired
    private DemiseService demiseService;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private AdminService adminService;


    @GetMapping("/demises")
    @ResponseBody
    public ResponseEntity getAgencyDemises(
            @RequestParam Integer offset,
            @CookieValue Long userid) throws ResponseException {

        return GetResponse(demiseService.getAgencyDemises(userid, offset), HttpStatus.OK);
    }


    @GetMapping("/demisesIgnorante")
    public ResponseEntity getAgencyDemisesForTesting(@RequestParam Long accountId){
        System.out.println("ci passo!");
        //return GetResponse(demiseService.getAgencyDemisesIgnorante(), HttpStatus.OK);
        return GetResponse(demiseService.getAgencyDemises(accountId, 0), HttpStatus.OK);
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
            @RequestParam Long userid) throws ResponseException {

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
            //@CookieValue Long userid) throws ResponseException {
            @RequestParam Long userid) throws ResponseException { //fixme fatto come requestparam per semplicità

        return GetResponse(agencyService.getAgencyProducts(userid, offset), HttpStatus.OK);
    }

    @GetMapping("/productsOffered")
    public ResponseEntity getProductsOfferedByAgencyOnTotal(@RequestParam Long userid) throws ResponseException {
        List<ProductEntity> allProducts = agencyService.getAvailableProducts(userid);
        List<ProductEntity> productsOfferedByAgency = agencyService.getAgencyProducts(userid, 0);
        List<ProductOffered> productsOfferedOnTotal = new ArrayList<>();
        for (ProductEntity product : allProducts)
            if (productsOfferedByAgency.contains(product))
                productsOfferedOnTotal.add(new ProductOffered(product, true));
            else
                productsOfferedOnTotal.add(new ProductOffered(product, false));
        return GetResponse(productsOfferedOnTotal, HttpStatus.OK);
    }

    @PostMapping("/productsOffered")
    public void editProductsOfferedByAgencyOnTotal(@RequestParam Long userid, @RequestBody List<ProductOffered> productsOffered) {
        System.out.println("prodotti arrivati da frontend: "+ productsOffered);
        agencyService.setAgencyProducts(userid, productsOffered);
    }


    @GetMapping("/all-products")
    @ResponseBody
    public ResponseEntity getAllProducts(@RequestParam Long userId) throws ResponseException {
        List<ProductEntity> prodotti = adminService.findAllProducts();
        System.out.println(prodotti);
        return GetResponse(prodotti, HttpStatus.OK);
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

    @GetMapping("/kinships")
    public ResponseEntity getKinships(){
        return GetResponse(Kinship.values(), HttpStatus.OK);
        //todo fetch from db actually
    }



}
