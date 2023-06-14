package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.authentication.model.LoginRequest;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.services.AdminService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/auth")
public class AdminController extends AbstractController {

    @Autowired
    private AgencyService agencyService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private AccountService accountService;

    @PostMapping("/agency")
    @ResponseBody
    public ResponseEntity saveAgency(@RequestBody AgencyEntity agencyEntity) throws Exception {
        return GetResponse(agencyService.saveAgencyEntity(agencyEntity), HttpStatus.OK);
    }

    //@PostMapping("/product/fine")
    @PostMapping("/productFromAdmin")
    @ResponseBody
    public ResponseEntity saveProduct(@RequestBody ProductEntity productEntity) throws Exception {
        return GetResponse(adminService.saveProductEntity(productEntity), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{productId}")
    public void deleteProduct(@PathVariable Long productId){adminService.deleteProduct(productId);}

    @GetMapping("/agencies")
    @ResponseBody
    public ResponseEntity findAllAgencies(){
        return GetResponse(adminService.findAllAgencies(), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity findAllAccounts(@RequestParam Integer offset, @RequestParam Long userid){
        System.out.println("ci arrivo qui si??");
        return GetResponse(accountService.findAllAccounts(), HttpStatus.OK);
    }


}
