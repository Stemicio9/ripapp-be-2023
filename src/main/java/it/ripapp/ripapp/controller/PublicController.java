package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.EntityUpdate.*;
import it.ripapp.ripapp.bll.Lang;

import it.ripapp.ripapp.services.AccountService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            @RequestBody AccountEntity user,
            @CookieValue(defaultValue = "it") Lang lang){
       return GetResponse(accountService.saveUser(user), HttpStatus.OK);
    }


    @GetMapping("/saveUserTest")
    @ResponseBody
    public ResponseEntity saveUser(){
        AccountEntity account = new AccountEntity();
        accountService.saveUser(account);

        CityEntity city = new CityEntity();
        AgencyEntity agency = new AgencyEntity();
        ProductEntity productEntity = new ProductEntity();
        DemiseEntity demiseEntity = new DemiseEntity();
        DemiseRelative demiseRelative = new DemiseRelative();


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
