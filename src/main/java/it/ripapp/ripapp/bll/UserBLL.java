package it.ripapp.ripapp.bll;

import com.google.common.hash.Hashing;
import com.google.firebase.auth.*;

import it.ripapp.ripapp.entities.*;
import it.ripapp.ripapp.entityUpdate.AccountEntity;
import it.ripapp.ripapp.exceptions.*;

import it.ripapp.ripapp.repository.AccountRepository;
import it.ripapp.ripapp.repository.AgencyRepository;
import it.ripapp.ripapp.repository.DemiseRepository;
import it.ripapp.ripapp.utilities.UserStatus;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserBLL {

    private AccountRepository userDAL;
    private AgencyRepository agencyDAL;

    private DemiseRepository demiseDAL;


    @Autowired
    public UserBLL(AccountRepository userDAL, AgencyRepository agencyDAL) {
        this.userDAL = userDAL;
        this.agencyDAL = agencyDAL;
    }

    public UserStatus getUserStatusByEmail(String email) {

        it.ripapp.ripapp.entityUpdate.AgencyEntity agency = agencyDAL.findByEmail(email);
        if (agency != null)
            return UserStatus.agency;

        AccountEntity account = userDAL.findByEmail(email);
        if (account != null)
            return account.getEnabled() ? UserStatus.active : UserStatus.disabled;

        return UserStatus.notfound;
    }

    public AccountEntity getAccountByEmail(String email) throws NotFoundException {
        AccountEntity account =  userDAL.findByEmail(email);

        if (account == null)
            throw new NotFoundException("Account not found");

        return account;
    }

    /*

    public FirebaseAuthCookieData getUserbaseUUIDByFirebaseToken(String token, Lang lang) throws ResponseException, FirebaseAuthException {


        if (token == null)
            throw new BadRequestException("token not provided");

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        String cookie = FirebaseAuth.getInstance().createSessionCookie(token, SessionCookieOptions.builder().setExpiresIn(TimeUnit.DAYS.toMillis(1)).build());

        AccountEntity account = getAccountByEmail(decodedToken.getEmail());
        updateUserLang(account.getAccountid(), lang);

        if (!account.getEnabled())
            throw new ForbiddenException("Account disabled");

        return new FirebaseAuthCookieData(account.getAccountid(), cookie);
    }



     private void updateUserLang(UUID accountid, Lang lang) {
       userDAL.updateUserLang(accountid, lang);
    }


    public FirebaseAuthCookieData createAccount(AccountEntity account, Lang lang) throws ResponseException, FirebaseAuthException {
        if (!EmailValidator.getInstance().isValid(account.getEmail()))
            throw new ForbiddenException("invalidemail");

        if (!userDAL.insertAccount(account))
            throw new ServerErrorException("Error creating account");

        updateCityMatches(account.getAccountid(), account.getCities().stream().map(CityEntity::getCityid).collect(Collectors.toList()));

        return getUserbaseUUIDByFirebaseToken(account.getIdtoken(), lang);
    }

 */

    public boolean syncPhoneBookChunk(UUID accountid, PhoneBookSyncEntity phoneBookSyncEntity){

        List<String> prefixes = userDAL.getAllPrefixes();

        List<ContactEntity> hashed = phoneBookSyncEntity.getContacts().stream()
                .filter(x -> x.getNum() != null)
                .peek(c -> {
                            boolean found = false;

                            c.setNum(c.getNum().replaceAll("[^\\+0-9]", ""));

                            for (String prefix : prefixes) {

                                if (found)
                                    break;

                                if (c.getNum().startsWith("00"+prefix))
                                {
                                    c.setNum(c.getNum().replace("00"+prefix, "").replace("+", ""));
                                    c.setPrefix("+"+prefix);
                                    found = true;
                                }
                                else if (c.getNum().startsWith("+"+prefix))
                                {
                                    c.setNum(c.getNum().replace("+"+prefix, "").replace("+", ""));
                                    c.setPrefix("+"+prefix);
                                    found = true;
                                }
                            }

                            c.setPhonehash(Hashing
                                            .sha256()
                                            .hashString(c.getNum(), StandardCharsets.UTF_8)
                                            .toString()
                            );
                        }
                )
                .collect(Collectors.toList());

        userDAL.updatePhoneBookSync(accountid, hashed, phoneBookSyncEntity.getOffset());

        if (!phoneBookSyncEntity.getHasnextchunk())
        {
            updatePhoneBookMatches(accountid);
            userDAL.deleteContactsOverIdx(accountid, phoneBookSyncEntity.getTotal());
        }

        return true;
    }

    private void updatePhoneBookMatches(UUID accountid) {
    }

  /*
    public Boolean updateAccount(UUID userid, AccountEntity accountEntity) {

        //check user esiste

        userDAL.updateUserByID(userid, accountEntity);

        updateCityMatches(userid, accountEntity.getCities().stream().map(CityEntity::getCityid).collect(Collectors.toList()));

        return true;
    }

   */

    public AccountEntity getAccountByID(UUID userid) {
        AccountEntity account = userDAL.getAccountByID(userid);

        account.setCities((List<it.ripapp.ripapp.entityUpdate.CityEntity>) userDAL.getCitiesByAccount(userid));
        account.setStatus(getUserStatusByEmail(account.getEmail()));

        return account;
    }

    /*
    public Collection<DemiseEntity> getUserUnreadDemises(UUID userid, Integer offset, Lang lang) {
        DemiseEntity demises = userDAL.getUnreadDemisesAndMarkRead(userid, offset);

        for (DemiseEntity d : demises) {
            switch (d.getMatch().getType()){

                case contact -> {
                    d.setTitle(d.getMatch().getName());
                }

                case relative -> {
                    d.setTitle(d.getSurname() + " " + d.getName());
                    d.setKinshipdesc(computeKinshipDesc(d.getMatch().getName(), d.getMatch().getKinship(), lang));
                    d.setTitle(d.getTitle() + " " + d.getKinshipdesc());
                }

                case city -> {
                    d.setTitle(d.getSurname() + " " + d.getName());
                }
            }
        }

        return (Collection<DemiseEntity>) demises;
    }

     */

    public Boolean deleteAccount(UUID id) {

        AccountEntity account = userDAL.getAccountByID(id);
        try {
            UserRecord firebaseUser = FirebaseAuth.getInstance().getUserByEmail(account.getEmail());
            FirebaseAuth.getInstance().deleteUser(firebaseUser.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        userDAL.deleteById(id);

        return true;
    }

    public Boolean sendPhoneBook(UUID userid, UUID agencyid, String file) {
        AgencyEntity agency = agencyDAL.getAgencyByID(agencyid);
        AccountEntity account = userDAL.getAccountByID(userid);

        try {
            MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "ripappbrescia@gmail.com", "Ripapp123")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .buildMailer().sendMail(
                    EmailBuilder.startingBlank()
                            .from("ripappbrescia@gmail.com")
//                            .to(agency.getEmail())
                            .to("riccardo.pozzati.1994@gmail.com")
                            .withSubject("Rubrica_"+account.getName()+"_"+account.getSurname())
                            .withPlainText("In allegato il file contenente i contatti dell'utente "+account.getName() + " " + account.getSurname())
                            .withAttachment("Rubrica_"+account.getName()+"_"+account.getSurname() +".csv", file.getBytes(), "text/attachment")
                            .buildEmail()
            );
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        return true;
    }

    public Boolean addPlayerID(UUID userid, String playerid) {
        userDAL.addInstanceID(userid, playerid);
        return true;
    }


    public String computeKinshipText(Kinship kinship, Lang lang, String demiseName, String relativeName){
        String text = userDAL.getKinshipText(kinship, lang);
        return text.replace("{0}", demiseName).replace("{1}", relativeName);
    }



    public String computeNotificationTitle(Kinship kinship, Lang lang, String demiseName) {
        String text = "";

        if (kinship != null)
            text = userDAL.getNotificationTextByKinship(kinship, lang);
        else
            text = userDAL.getNotificationText(lang);

        return text.replace("{0}", demiseName);
    }

    public Collection<? extends IEntity> updateUserByID(UUID userid, AccountEntity accountEntity) {
         userDAL.updateUserByID(userid, accountEntity);
        return null;
    }





    public String computeKinshipDesc(String relativeName, Kinship kinship, Lang lang) {
        String result = userDAL.getKinshipText(kinship, lang);
        result = result.split(",")[1].replace("{1}", relativeName).strip();
        return result;
    }
     /*


    //todo testare
    private void updatePhoneBookMatches(UUID accountid){
        var matches = userDAL.getAccountMatches(accountid);
        var contactDemises = userDAL.getDemisesByUserContacts(accountid);


        Map<UUID, DemiseMatchEntity> toInsert = new HashMap<>();

        for (Map.Entry<UUID, DemiseMatchEntity> match : contactDemises.entrySet()) {



            if (!matches.containsKey(match.getKey())){
                toInsert.put(
                        match.getKey(),
                        match.getValue().setNotify(false)
                );
            }
            else {
                var existing = matches.get(match.getKey());

                if (existing.getType().equals(Demisematchtype.city)){
                    toInsert.put(
                            match.getKey(),
                            match.getValue().setNotify(false)
                    );
                }
            }
        }

        userDAL.deleteMatches(accountid,
                matches.entrySet()
                        .stream()
                        .filter(d ->
                                (!contactDemises.containsKey(d.getKey()) && !d.getValue().getType().equals(Demisematchtype.city))
                                ||
                                (d.getValue().getType().equals(Demisematchtype.city) && toInsert.containsKey(d.getKey()))
                        )
                        .map(d -> d.getKey())
                        .collect(Collectors.toList())
        );
        userDAL.insertMatches(toInsert.values());

    }


    //todo testare
    private void updateCityMatches(UUID accountid, List<UUID> cities){
        var matches = userDAL.findById(accountid);
        DemiseEntity demisesByCities = userDAL.getDemiseIDsByCities(cities);

        Map<UUID, DemiseMatchEntity> toInsert = new HashMap<>();


        for (Map.Entry<UUID, List<UUID>> city : demisesByCities.entrySet()) {
            for (UUID demise : city.getValue()) {
                if (!matches.containsKey(demise) && !toInsert.containsKey(demise)){
                    toInsert.put(
                            demise,
                            new DemiseMatchEntity().setDemiseid(demise).setCityid(city.getKey()).setNotify(false).setAccountid(accountid).setType(Demisematchtype.city)
                    );
                }
            }
        }

        userDAL.deleteMatches(accountid,
                matches.entrySet()
                        .stream()
                        .filter(d -> !demisesByCities.containsKey(d.getKey()) && d.getValue().getType().equals(Demisematchtype.city))
                        .map(d -> d.getKey())
                        .collect(Collectors.toList())
        );

        userDAL.insertMatches(toInsert.values());

    }

    public void deleteInstanceIDs(List<UUID> instanceIDs) {
        userDAL.deleteInstanceIDs(instanceIDs);
    }


    //todo fixare sorting nome/cognome usando quelli corretti

     */
}
