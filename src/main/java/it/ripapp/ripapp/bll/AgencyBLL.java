package it.ripapp.ripapp.bll;

import com.google.common.hash.Hashing;
import it.ripapp.ripapp.dal.AgencyDAL;
import it.ripapp.ripapp.dal.UserDAL;
import it.ripapp.ripapp.entities.*;
import it.ripapp.ripapp.exceptions.BadRequestException;
import it.ripapp.ripapp.exceptions.ForbiddenException;
import it.ripapp.ripapp.exceptions.ServerErrorException;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.onesignal.OSNotification;
import it.ripapp.ripapp.onesignal.OneSignal;
import info.debatty.java.stringsimilarity.Cosine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AgencyBLL {

    // TODO TEST BRANCH

    private AgencyDAL agencyDAL;
    private UserDAL userDAL;
    private OneSignal oneSignal;
    private UserBLL userBLL;

    @Autowired
    public AgencyBLL(AgencyDAL agencyDAL, UserDAL userDAL, OneSignal oneSignal, UserBLL userBLL) {
        this.agencyDAL = agencyDAL;
        this.userDAL = userDAL;
        this.oneSignal = oneSignal;
        this.userBLL = userBLL;
    }


    public List<DemiseEntity> getAgencyDemises(UUID userid, int offset){
        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);
        return agencyDAL.getDemisesByAgency(agency.getAgencyid(), offset);
    }

    public List<ProductEntity> getAgencyProducts(UUID userid, int offset){
        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);
        return agencyDAL.getProductsByAgency(agency.getAgencyid(), offset);
    }


    public Boolean insertDemise(UUID userid, DemiseEntity demise) {

        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);

        demise.setRelatives(demise.getRelatives().stream()
                .filter(r -> r.getKinship() != null)
                .peek(r -> {

                    if (r.getPhone() != null)
                        r.setPhonehash(Hashing.sha256().hashString(r.getPhone().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());

                })
                .collect(Collectors.toList()));

        if (demise.getPhonenumber() != null)
            demise.setPhonehash(Hashing.sha256().hashString(demise.getPhonenumber().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());

        demise.setTs(LocalDateTime.now(ZoneOffset.UTC));
        agencyDAL.insertDemise(agency.getAgencyid(), demise);

        matchDemiseAndUpdateMatchesAndNotify(demise);

        return true;
    }

    public Boolean insertProduct(UUID userid, ProductEntity product) {

        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);

        agencyDAL.insertProduct(agency.getAgencyid(), product);

        return true;
    }

    private Map<UUID, DemiseMatchEntity> getMatchesByContact(DemiseEntity demise){
        Map<UUID, AccountEntity> matches = userDAL.getUsersByContact(demise.getPhonehash());
        Map<UUID, DemiseMatchEntity> result = matches.values()
                .stream()
                .map(account ->
                        new DemiseMatchEntity()
                                .setDemiseid(demise.getDemiseid())
                                .setAccountid(account.getAccountid())
                                .setName(account.getPhonebookName())
                                .setType(Demisematchtype.contact)
                                .setMatchLang(account.getLang())
                                .setNotify(account.getNotif())
                )
                .collect(Collectors.toMap(DemiseMatchEntity::getAccountid, x -> x));

        return result;
    }

    private Map<UUID, DemiseMatchEntity> getMatchesByRelativeContact(DemiseEntity demise){

        Map<UUID, DemiseMatchEntity> results = new HashMap<>();

        for (DemiseRelativeEntity relative : demise.getRelatives()) {

            Map<UUID, AccountEntity> matches = userDAL.getUsersByContact(relative.getPhonehash());

            results.putAll(
                    matches.values()
                            .stream()
                            .filter(m -> !results.containsKey(m.getAccountid()))
                            .map(account ->
                                    new DemiseMatchEntity()
                                            .setDemiseid(demise.getDemiseid())
                                            .setAccountid(account.getAccountid())
                                            .setName(account.getPhonebookName())
                                            .setKinship(relative.getKinship())
                                            .setType(Demisematchtype.relative)
                                            .setMatchLang(account.getLang())
                                            .setNotify(account.getNotif())
                            )
                            .collect(Collectors.toMap(x -> x.getAccountid(), x -> x))
            );
        }

        return results;
    }


    private Map<UUID, DemiseMatchEntity> getDemiseCityMatches(DemiseEntity demise){

        Map<UUID, DemiseMatchEntity> result = new HashMap<>();

        for (CityEntity city : demise.getCities()) {
            Map<UUID, AccountEntity> matches = userDAL.getUsersByCity(city.getCityid());

            result.putAll(
                    matches.values()
                            .stream()
                            .filter(d -> !result.containsKey(d.getAccountid()))
                            .map(account ->
                                    new DemiseMatchEntity()
                                            .setDemiseid(demise.getDemiseid())
                                            .setAccountid(account.getAccountid())
                                            .setCityid(city.getCityid())
                                            .setType(Demisematchtype.city)
                                            .setMatchLang(account.getLang())
                                            .setNotify(account.getNotif())
                            )
                            .collect(Collectors.toMap(x -> x.getAccountid(), x -> x))
            );
        }

        return result;
    }

    private void matchDemiseAndUpdateMatchesAndNotify(DemiseEntity demise){

        Set<UUID> existingMatches = agencyDAL.getDemiseMatchesIDs(demise.getDemiseid());

        Map<UUID, DemiseMatchEntity> newMatches = new HashMap<>();

        Map<UUID, DemiseMatchEntity> contactMatches = getMatchesByContact(demise);

        for (Map.Entry<UUID, DemiseMatchEntity> userMatched : contactMatches.entrySet()) {
            if (!existingMatches.contains(userMatched.getKey())){

                newMatches.put(userMatched.getKey(), userMatched.getValue());

                if (userMatched.getValue().getNotify())
                    sendMatchNotification(
                            userMatched.getValue().getAccountid(),
                            userMatched.getValue().getMatchLang(),
                            userMatched.getValue().getName(),
                            null,
                            null
                    );
            }
        }


        Map<UUID, DemiseMatchEntity> relativesContactMatches = getMatchesByRelativeContact(demise);
        for (Map.Entry<UUID, DemiseMatchEntity> userMatched : relativesContactMatches.entrySet()) {

            if (!existingMatches.contains(userMatched.getKey()) && !newMatches.containsKey(userMatched.getKey())){

                newMatches.put(userMatched.getKey(), userMatched.getValue());

                if (userMatched.getValue().getNotify())
                    sendMatchNotification(
                            userMatched.getValue().getAccountid(),
                            userMatched.getValue().getMatchLang(),
                            demise.getName(),
                            userMatched.getValue().getKinship(),
                            userMatched.getValue().getName()
                    );
            }
        }



        Map<UUID, DemiseMatchEntity> cityMatches = getDemiseCityMatches(demise);
        for (Map.Entry<UUID, DemiseMatchEntity> userMatched : cityMatches.entrySet()) {

            if (!existingMatches.contains(userMatched.getKey()) && !newMatches.containsKey(userMatched.getKey())){

                newMatches.put(userMatched.getKey(), userMatched.getValue());

                if (userMatched.getValue().getNotify())
                    sendMatchNotification(
                            userMatched.getValue().getAccountid(),
                            userMatched.getValue().getMatchLang(),
                            demise.getName(),
                            null,
                            null
                    );
            }
        }

        agencyDAL.updateMatches(demise.getDemiseid(), newMatches.values());
    }


    private void sendMatchNotification(UUID receiverID, Lang lang, String demiseName, Kinship kinship, String relativeName){

        String title = demiseName;

        if (kinship != null)
            title = userBLL.computeKinshipText(kinship, lang, demiseName, relativeName);

        title = userBLL.computeNotificationTitle(kinship, lang, title);

        Integer setTo = userDAL.getCounterSet(receiverID);
        userDAL.deleteCounterSet(receiverID);

        var userPlayerIDs = userDAL.getInstanceIDs(receiverID);

        oneSignal.sendNotification(
                OSNotification.builder()
                        .setPlayerIDs(userPlayerIDs)
                        .addContent(Lang.en, title)
                        .addContent(lang, title)
                        .setContentAvailable(true)
                        .setIos_badgeType(setTo != null ? "SetTo" : "Increase")
                        .setIos_badgeCount(setTo != null ? setTo+1 : 1)
                        .addData("click_action", "FLUTTER_NOTIFICATION_CLICK")
                        .build()
        );

    }

//    private String getKinshipText(Kinship kinship, Lang lang){
//        return userDAL.getKinshipText(kinship, lang);
//    }


    public List<AccountEntity> searchAccount(UUID userid, String query) {
        if (query.length() < 3)
            return Collections.emptyList();

        var results = agencyDAL.autocompleteUserPhone(query);

//        Cosine cosine = new Cosine(3);
//        Map<String, Integer> profileInput = cosine.getProfile(query.toLowerCase());
//        results.forEach(
//                res -> res.setSimilarity(
//                        cosine.similarity(profileInput, cosine.getProfile(res.getPhone().toLowerCase()))
//                )
//        );
//        results.sort(Comparator.comparingDouble(AccountEntity::getSimilarity));

        return results;
    }


    public Boolean deleteDemiseByID(UUID userid, UUID demiseid) throws ServerErrorException, ForbiddenException {

        if (!agencyDAL.operatorOwnsDemise(userid, demiseid))
            throw new ForbiddenException("Utente non abilitato");

        if(!agencyDAL.deleteDemiseByID(demiseid))
            throw new ServerErrorException("Demise not deleted " + demiseid.toString());

        return true;
    }


    public Boolean deleteProductByID(UUID userid, UUID productId) throws ServerErrorException, ForbiddenException {

        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);

        if (!agencyDAL.operatorOwnsDemise(productId, agency.getAgencyid()))
            throw new ForbiddenException("Utente non abilitato");

        if(!agencyDAL.deleteProductByID(productId))
            throw new ServerErrorException("Demise not deleted " + productId.toString());

        return true;
    }

    public Boolean updateDemise(UUID userid, UUID demiseid, DemiseEntity demise) throws BadRequestException, ForbiddenException {

        if (!agencyDAL.operatorOwnsDemise(userid, demiseid))
            throw new ForbiddenException("Utente non abilitato");

        if (!demiseid.equals(demise.getDemiseid()))
            throw new BadRequestException("id demise non uguale all'id fornito in path");

        demise.setRelatives(demise.getRelatives().stream()
                .filter(r -> r.getKinship() != null)
                .peek(r -> {

                    if (r.getPhone() != null)
                        r.setPhonehash(Hashing.sha256().hashString(r.getPhone().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());

                })
                .collect(Collectors.toList()));

        agencyDAL.updateDemise(demiseid, demise);

        matchDemiseAndUpdateMatchesAndNotify(demise);


        return true;
    }


    public Boolean updateProduct(UUID userid, UUID productId, ProductEntity product) throws BadRequestException, ForbiddenException {

        AgencyEntity agency = agencyDAL.getAgencyByOperator(userid);

        if (!agencyDAL.agencyOwnsProduct(productId, agency.getAgencyid()))
            throw new ForbiddenException("Utente non abilitato");

        if (!productId.equals(product.getProductId()))
            throw new BadRequestException("id prodotto non uguale all'id fornito in path");

        agencyDAL.updateProduct(productId, product);

        return true;
    }

    public List<AgencyEntity> searchAgency(UUID userid, String query) {
        List<AgencyEntity> agencies = agencyDAL.getAllAgencies();


        Cosine cosine = new Cosine(2);
        Map<String, Integer> profileInput = cosine.getProfile(query.toLowerCase());

        return agencies.stream()
                .peek(
                        res -> res.setSimilarity(
                        cosine.similarity(profileInput, cosine.getProfile(res.getName().toLowerCase())) +
                                cosine.similarity(profileInput, cosine.getProfile(res.getAddress().toLowerCase()))
                ))
                .sorted(Comparator.comparingDouble(AgencyEntity::getSimilarity))
                .filter(x -> x.getSimilarity() > 0)
                .limit(15)
                .collect(Collectors.toList());
    }
}
