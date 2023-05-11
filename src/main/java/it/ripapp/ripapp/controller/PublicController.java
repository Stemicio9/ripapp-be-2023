package it.ripapp.ripapp.controller;

import com.google.firebase.auth.FirebaseAuthException;
import it.ripapp.ripapp.bll.MiscBLL;
import it.ripapp.ripapp.bll.TextBLL;
import it.ripapp.ripapp.bll.UserBLL;
import it.ripapp.ripapp.entities.LoginDataEntity;
import it.ripapp.ripapp.exceptions.ResponseException;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import it.ripapp.ripapp.utilities.FirebaseAuthCookieData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
@RequestMapping(value = "/api")
public class PublicController extends AbstractController {

    private UserBLL userBLL;
    private MiscBLL miscBLL;

    @Autowired
    public PublicController(UserBLL userBLL, TextBLL textBLL, MiscBLL miscBLL) {
        super(textBLL);
        this.userBLL = userBLL;
        this.miscBLL = miscBLL;
    }


    @RequestMapping(value = "/productionready", method = RequestMethod.GET)
    public ResponseEntity productionServerIsReady(HttpServletRequest request){

        if (miscBLL.getServerStatus().equals(Serverstatus.under_maintenance))
            return ResponseEntity.ok().body(Serverstatus.under_maintenance.getLiteral());

        String body = "active";

        String appversion = request.getHeader("app_version");
        if (appversion == null)
            body = "must_upgrade";
        else {
            Set<String> versions = miscBLL.getSupportedAppVersions();

            if (!versions.contains(appversion))
                body = "must_upgrade";
        }

        return ResponseEntity.ok().body(body);
    }


    @RequestMapping(value = "/userstatus", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity userstatus(
            @RequestParam String email,
            @CookieValue(defaultValue = "it") Lang lang) {
        return GetResponse(userBLL.getUserStatusByEmail(email), HttpStatus.OK);
    }



    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity login(
            @RequestBody LoginDataEntity loginData,
            @CookieValue Lang lang,
            HttpServletResponse response) throws ResponseException, FirebaseAuthException {

        FirebaseAuthCookieData firebaseAuthData =  userBLL.getUserbaseUUIDByFirebaseToken(loginData.getIdtoken(), lang);

        response.addCookie(new Cookie("firebasecookie", firebaseAuthData.getCookie()));
        response.addCookie(new Cookie("userid", firebaseAuthData.getAccountid().toString()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity postCustomer(
            @RequestBody AccountEntity account,
            @CookieValue(defaultValue = "it") Lang lang,
            HttpServletResponse response
    ) throws ResponseException, FirebaseAuthException {
        FirebaseAuthCookieData firebaseAuthData = userBLL.createAccount(account, lang);

        response.addCookie(new Cookie("firebasecookie", firebaseAuthData.getCookie()));
        response.addCookie(new Cookie("userid", firebaseAuthData.getAccountid().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


//    @Autowired
//    OneSignal oneSignal;

//    @Autowired
//    DemiseBLL demiseBLL;
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    @ResponseBody
//    public Object test()  {
//
//        return demiseBLL.getUserLinkedDemises(UUID.fromString("36185ffb-d327-4095-ae70-844e6262e003"), new FiltersEntity().setSorting(SearchSorting.date), Lang.it);
//
//
//
//    }

//
//        oneSignal.sendNotification(
//                OSNotification.builder()
//                        .setPlayerIDs(Collections.singletonList("8cfae22c-b0df-4b0f-b69c-3db9afa9163d"))
//                        .addContent(Lang.en, "ciaociao")
//                        .addContent(Lang.it, "ciao")
//                        .setContentAvailable(true)
//                        .setIos_badgeType("SetTo")
//                        .setIos_badgeCount(1)
//                        .addData("click_action", "FLUTTER_NOTIFICATION_CLICK")
//                        .build()
//        );

//        try {
//            MailerBuilder
//                    .withSMTPServer("smtp.gmail.com", 587, "ripappbrescia@gmail.com", "Ripapp123")
//                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
//                    .buildMailer().sendMail(
//                    EmailBuilder.startingBlank()
//                            .from("ripappbrescia@gmail.com")
//                            .to("riccardo.pozzati.1994@gmail.com")
//                            .withPlainText("test")
//                            .withSubject("test")
//                            .buildEmail()
//            );
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }

//        return null;
//    }
//
//
//
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                .setPassword("lauralecchi11")
////                .setEmail("laura.lecchi@studiolegalelecchi.eu")
////        );
//
//
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                .setPassword("pietrovitali11")
////                .setEmail("pietrovitali@icloud.com")
////        );
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                .setPassword("amalfouihel11")
////                .setEmail("amal.fouihel@icloud.com")
////        );
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                .setPassword("marcocucchi11")
////                .setEmail("marco.cucchi@live.it")
////        );
////
////
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                .setPassword("robertatest11")
////                .setEmail("roberta@lybrescia.it")
////        );
////
////
////
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                        .setPassword("danieletest11")
////                        .setEmail("daniele@lybrescia.it")
////        );
////
////
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                        .setPassword("sabrinatonoli11")
////                        .setEmail("sabrina.tonoli@live.it")
////        );
////
////        FirebaseAuth.getInstance().createUser(
////                new UserRecord.CreateRequest()
////                        .setPassword("silviacucchi11")
////                        .setEmail("silvia_cucchi@virgilio.it")
////        );
//
//
//
////        OSNotification notification = OSNotification.builder()
////                .setChannelForExternalUserIds("push")
////                .setContentAvailable(true)
////                .setPlayerIDs(Collections.singletonList("c6ad46de-3fb4-4b41-875a-0bc20c280073"))
////                .addHeading(Lang.en, "Title")
////                .addContent(Lang.en, "terzo")
////                .addData("click_action", "FLUTTER_NOTIFICATION_CLICK")
////                .setIos_badgeType("Increase")
////                .setIos_badgeCount(1)
////                .build();
////
////        oneSignal.sendNotification(notification);
//
//        return null;
//
////        return ResponseEntity.ok(
//////                new AccountEntity()
//////                        .setAccountid(UUID.randomUUID()).setName("Luca")
//////                        .setSurname("Trombili").setEmail("luca@tabili.it")
//////                        .setPrefix("+39").setPhone("12234567").setNotif(true)
//////                        .setCities(Arrays.asList(UUID.randomUUID(), UUID.randomUUID()))
////                new DemiseEntity()
////                .setDemiseid(UUID.randomUUID()).setName("Franco")
////                .setSurname("surname").setPhotourl("https://images.pexels.com/photos/771742/pexels-photo-771742.jpeg")
////                .setDate(LocalDate.of(2010, 5, 20)).setCemeteryAddress("via del cimitero")
////                .setCityid(UUID.randomUUID()).setObituary("link al documento pdf")
////                .setWakets(LocalDateTime.now(ZoneOffset.UTC).plusMonths(1)).setWakeAddress("via della chiesa")
////                .setCity(new CityEntity().setCityid(UUID.randomUUID()).setName("Lucca"))
////        );
//    }





}
