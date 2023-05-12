package it.ripapp.ripapp.controller;

import it.ripapp.ripapp.bll.Lang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class PublicController extends AbstractController {

  /*

    @RequestMapping(value = "/userstatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity userstatus(
            @RequestParam String email,
            @CookieValue(defaultValue = "it") Lang lang) {
        return GetResponse(userBLL.getUserStatusByEmail(email), HttpStatus.OK);
    }


*/
}
