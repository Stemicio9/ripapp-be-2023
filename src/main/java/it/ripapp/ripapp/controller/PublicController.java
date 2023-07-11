package it.ripapp.ripapp.controller;


import it.ripapp.ripapp.bll.Lang;
import it.ripapp.ripapp.entityUpdate.*;

import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.utilities.UserStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api")
public class PublicController extends AbstractController {

    @Autowired
    private AccountService accountService;


    // TODO understand here if we want to leave this method public or not
    // it should be public if we want to allow users to register
    @PostMapping("/saveUser")
    @ResponseBody
    public ResponseEntity saveUser(
            @RequestBody AccountEntity user){
        System.out.println("ciao buonasera carissimi");
        System.out.println(user);
       return GetResponse(accountService.saveUser(user), HttpStatus.OK);
    }


    @GetMapping("/saveUserTest")
    @ResponseBody
    public ResponseEntity saveUser(){
        AccountEntity account = new AccountEntity();

        CityEntity city = new CityEntity();
        city.setName("mil");
        AgencyEntity agency = new AgencyEntity();
        agency.setEmail("ciaociaio");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName("prodotto");
        ArrayList<ProductEntity> list= new ArrayList<>();
        list.add(productEntity);
        agency.setProducts(list);
        DemiseEntity demiseEntity = new DemiseEntity();
        demiseEntity.setRelativename("relative");
        ArrayList<DemiseEntity> list2= new ArrayList<>();
        list2.add(demiseEntity);
        agency.setDemises(list2);
        DemiseRelative demiseRelative = new DemiseRelative();
        demiseRelative.setPhone("331");

        ArrayList<DemiseRelative> list3= new ArrayList<>();
        list3.add(demiseRelative);
        demiseEntity.setRelatives(list3);
        demiseEntity.setCity(city);
        account.setAgency(agency);
        account.setStatus(UserStatus.agency);



        return GetResponse(accountService.saveUser(account), HttpStatus.OK);
    }

    @GetMapping("/userstatus")
    @ResponseBody
    public ResponseEntity userstatus(
            @RequestParam String email,
            @CookieValue(defaultValue = "it") Lang lang){
        return GetResponse(accountService.getUserStatusByEmail(email), HttpStatus.OK);
    }

}
