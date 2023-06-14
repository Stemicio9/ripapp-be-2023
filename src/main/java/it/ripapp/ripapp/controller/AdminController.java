package it.ripapp.ripapp.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import it.ripapp.ripapp.authentication.model.LoginRequest;
import it.ripapp.ripapp.dto.AccountSearchEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.services.AdminService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import it.ripapp.ripapp.utilities.SearchSorting;
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

    @GetMapping("/agencies")
    @ResponseBody
    public ResponseEntity findAllAgencies(){
        return GetResponse(adminService.findAllAgencies(), HttpStatus.OK);
    }

    @GetMapping("/accounts")
    public ResponseEntity findAllAccounts(@RequestParam Integer offset, @RequestParam Long userid, @RequestBody AccountSearchEntity accountSearch){
        System.out.println("ci arrivo qui si??");
        System.out.println(offset);
        return GetResponse(accountService.findAllAccounts(accountSearch), HttpStatus.OK);
    }
}
