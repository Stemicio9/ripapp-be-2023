package it.ripapp.ripapp.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import it.ripapp.ripapp.authentication.model.LoginRequest;
import it.ripapp.ripapp.dto.AccountSearchEntity;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.entityUpdate.AgencyEntity;
import it.ripapp.ripapp.entityUpdate.ProductEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.message.DeleteProductMessage;
import it.ripapp.ripapp.message.SaveAgencyMessage;
import it.ripapp.ripapp.services.AccountService;
import it.ripapp.ripapp.services.AdminService;
import it.ripapp.ripapp.services.AgencyService;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import it.ripapp.ripapp.utilities.SearchSorting;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
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
        SaveAgencyMessage message = new SaveAgencyMessage();
        AgencyEntity saved = null;
        try {
            saved = agencyService.saveAgencyEntity(agencyEntity);
            message.setAgencySaved(saved);
            message.setMessage("Agency saved successfully!");
        }
        catch (SQLIntegrityConstraintViolationException e) {
            message.setMessage(e.getMessage());
            message.setMessage("Duplicate entry");
        }
        return GetResponse(message, HttpStatus.OK);
    }

    @DeleteMapping("/agency/{agencyId}")
    @ResponseBody
    public ResponseEntity deleteAgency(@PathVariable Long agencyId) throws Exception {
        return GetResponse(agencyService.deleteAgency(agencyId), HttpStatus.OK);
    }

    @PostMapping("/productFromAdmin")
    @ResponseBody
    public ResponseEntity saveProduct(@RequestBody ProductEntity productEntity) throws Exception{
        return GetResponse(adminService.saveProductEntity(productEntity), HttpStatus.OK);
    }

    //@PostMapping("/product/fine")
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId){
        return GetResponse(adminService.deleteProduct(productId), HttpStatus.OK);}



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

    @GetMapping("/agenciesWithIndex")
    public Page<AgencyEntity> getAllAgencies(@RequestParam Integer pageNumber, @RequestParam Integer pageElements){
        AccountSearchEntity agencySearch = new AccountSearchEntity(pageNumber, pageElements);
        return agencyService.findAllAgenciesPaged(agencySearch);
    }
    @GetMapping("/productsWithIndex")
        public Page<ProductEntity> getAllProducts(@RequestParam Integer pageNumber, @RequestParam Integer pageElements){
            AccountSearchEntity agencySearch = new AccountSearchEntity(pageNumber, pageElements);
            return adminService.findAllProcuctsPaged(agencySearch);
        }


}
