package it.ripapp.ripapp.dal;

import com.google.common.hash.Hashing;
import it.ripapp.ripapp.entities.*;
import it.ripapp.ripapp.jooqgen.Tables;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.tables.records.*;
import org.jooq.*;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class UserDAL extends AbstractDAL {

    public UserDAL(DSLContext dsl) {
        super(dsl);
    }


    public AccountEntity getAccountByEmail(String email) {
        return dsl.selectFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.EMAIL.eq(email)).fetchOneInto(AccountEntity.class);
    }

    public void updateUserLang(UUID accountid, Lang lang) {
        dsl.update(Tables.ACCOUNT).set(Tables.ACCOUNT.LANG, lang).where(Tables.ACCOUNT.ACCOUNTID.eq(accountid)).execute();
    }

    public void addInstanceID(UUID accountid, String instanceid) {
        dsl.insertInto(Tables.ACCOUNT_INSTANCEID)
                .columns(Tables.ACCOUNT_INSTANCEID.ACCOUNTID, Tables.ACCOUNT_INSTANCEID.INSTANCEID)
                .values(accountid, instanceid)
                .onConflictDoNothing()
                .execute();
    }

    public boolean insertAccount(AccountEntity account) {
       dsl.transaction(ctx -> {
           DSLContext tempDsl = DSL.using(ctx);

           AccountRecord accountRecord = tempDsl.newRecord(Tables.ACCOUNT, account);
           accountRecord.setEnabled(true);
           accountRecord.setPhonehashed(Hashing.sha256().hashString(account.getPhone().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());
           if (accountRecord.store() == 0)
               throw new DataAccessException("error inserting account");

           List<AccountCityRecord> cityRecords = account.getCities().stream().map(c -> {
               var record = tempDsl.newRecord(Tables.ACCOUNT_CITY);
               record.setAccountid(accountRecord.getAccountid());
               record.setCityid(c.getCityid());
               return record;
           }).collect(Collectors.toList());

           if (Arrays.stream(tempDsl.batchInsert(cityRecords).execute()).anyMatch(res -> res == 0))
               throw new DataAccessException("error inserting cities");
        });

        return true;
    }

    public List<UUID> getCitiesIDsByAccount(UUID accountid) {
        return dsl.selectFrom(Tables.ACCOUNT_CITY).where(Tables.ACCOUNT_CITY.ACCOUNTID.eq(accountid)).fetch(AccountCityRecord::getCityid);
    }

    public List<CityEntity> getCitiesByAccount(UUID accountid) {
        return dsl.select().from(Tables.ACCOUNT_CITY)
                .leftJoin(Tables.CITY).on(Tables.CITY.CITYID.eq(Tables.ACCOUNT_CITY.CITYID))
                .where(Tables.ACCOUNT_CITY.ACCOUNTID.eq(accountid))
                .fetchInto(CityEntity.class);
    }

    public void updatePhoneBookSync(UUID accountid, List<ContactEntity> contacts, Integer offset) {

        dsl.deleteFrom(Tables.PHONEBOOK).where(Tables.PHONEBOOK.ACCOUNTID.eq(accountid), Tables.PHONEBOOK.IDX.between(offset, offset+contacts.size())).execute();

        List<PhonebookRecord> records = new ArrayList<>();
        int idx = offset;

        for (ContactEntity c : contacts) {
            PhonebookRecord record = dsl.newRecord(Tables.PHONEBOOK);
            record.setAccountid(accountid);
            record.setName(c.getName());
            record.setPhone(c.getNum());
            record.setPhonehash(c.getPhonehash());
            record.setPrefix(c.getPrefix());
            record.setIdx(idx++);
            records.add(record);
        }


        dsl.batchInsert(records).execute();
    }

    public AccountEntity getAccountByID(UUID userid) {
        return dsl.selectFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.ACCOUNTID.eq(userid)).fetchOneInto(AccountEntity.class);
    }

    public AccountEntity updateUserByID(UUID userid, AccountEntity accountEntity) {
        AccountRecord accountRecord = dsl.fetchOne(Tables.ACCOUNT, Tables.ACCOUNT.ACCOUNTID.eq(userid));

        if(accountRecord == null)
            return null;

        accountRecord.setName(accountEntity.getName());
        accountRecord.setSurname(accountEntity.getSurname());
        accountRecord.setPrefix(accountEntity.getPrefix());
        accountRecord.setPhone(accountEntity.getPhone());
        accountRecord.setPhonehashed(Hashing.sha256().hashString(accountEntity.getPhone().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());
        accountRecord.setNotif(accountEntity.getNotif());
        accountRecord.setPhotourl(accountEntity.getPhotourl());

        if (accountRecord.update() == 0)
            return null;

        dsl.deleteFrom(Tables.ACCOUNT_CITY).where(Tables.ACCOUNT_CITY.ACCOUNTID.eq(userid)).execute();

        dsl.batchInsert(
                accountEntity.getCities().stream().map(x -> {
                    var r = dsl.newRecord(Tables.ACCOUNT_CITY);
                    r.setAccountid(userid);
                    r.setCityid(x.getCityid());
                    return r;
                }).collect(Collectors.toList())
        ).execute();


        return dsl.fetchOne(Tables.ACCOUNT, Tables.ACCOUNT.ACCOUNTID.eq(userid)).into(AccountEntity.class);
    }

    public void deleteContactsOverIdx(UUID accountid, Integer total) {
        dsl.deleteFrom(Tables.PHONEBOOK).where(Tables.PHONEBOOK.ACCOUNTID.eq(accountid), Tables.PHONEBOOK.IDX.ge(total)).execute();
    }

//    public List<DemiseEntity> getUserLinkedDemises(UUID userid, FiltersEntity filters) {
//
//        Map<UUID, DemiseEntity> result = new HashMap<>();
//
//
//        Map<String, List<String>> phonebook = dsl.selectFrom(Tables.PHONEBOOK)
//                .where(Tables.PHONEBOOK.ACCOUNTID.eq(userid))
//                .fetchGroups(PhonebookRecord::getPhonehash, PhonebookRecord::getName);
//
//
//
//
//        // defunti che ho in rubrica
//
//        var contactsDemises = dsl.selectFrom(Tables.DEMISE)
//                .where(Tables.DEMISE.PHONEHASH.in(phonebook.keySet()))
//                .fetchMap(
//                        DemiseRecord::getDemiseid,
//                        x -> x.into(Tables.DEMISE).into(DemiseEntity.class).setName(phonebook.get(x.getPhonehash()).get(0))
//                );
//
//        result.putAll(contactsDemises);
//
//
//
//
//
//        //parenti che ho in rubrica
//
//
//        var accountsInPhonebok = dsl.selectFrom(Tables.ACCOUNT)
//                .where(Tables.ACCOUNT.PHONEHASHED.in(phonebook.keySet()))
//                .fetchMap(AccountRecord::getAccountid, x -> phonebook.get(x.getPhonehashed()).get(0));
//
//
//        //decessi collegati a parenti che ho in rubrica
//        var demisesRelativesWithAccount = dsl.select()
//                .from(Tables.DEMISE_RELATIVE)
//
//                .innerJoin(Tables.DEMISE)
//                .on(Tables.DEMISE.DEMISEID.eq(Tables.DEMISE_RELATIVE.DEMISEID))
//
//                .where(Tables.DEMISE_RELATIVE.DEMISEID.notIn(result.keySet()),
//                        Tables.DEMISE_RELATIVE.ACCOUNTID.in(accountsInPhonebok.keySet()).or(Tables.DEMISE_RELATIVE.PHONEHASH.in(phonebook.keySet())))
//
//                .fetchMap(
//                        x -> x.getValue(Tables.DEMISE.DEMISEID),
//                        x -> {
//                            var res = x.into(Tables.DEMISE).into(DemiseEntity.class)
//                                    .setKinship(x.getValue(Tables.DEMISE_RELATIVE.KINSHIP));
//
//                            if (accountsInPhonebok.containsKey(x.getValue(Tables.DEMISE_RELATIVE.ACCOUNTID)))
//                                res.setRelativename(accountsInPhonebok.get(x.getValue(Tables.DEMISE_RELATIVE.ACCOUNTID)));
//                            else if (phonebook.containsKey(x.getValue(Tables.DEMISE_RELATIVE.PHONEHASH)))
//                                res.setRelativename(phonebook.get(x.getValue(Tables.DEMISE_RELATIVE.PHONEHASH)).get(0));
//
//                            return res;
//                        }
//                );
//
//
//        result.putAll(demisesRelativesWithAccount);
//
//
//
//
//
//        //defunti in città seguite
//
//
//        Set<UUID> demiseIDsByCities = dsl.selectDistinct(Tables.DEMISE_CITY.DEMISEID).from(Tables.DEMISE_CITY)
//                .where(Tables.DEMISE_CITY.CITYID.in(
//                        dsl.select(Tables.ACCOUNT_CITY.CITYID).from(Tables.ACCOUNT_CITY).where(Tables.ACCOUNT_CITY.ACCOUNTID.eq(userid))
//                ))
//                .fetchSet(x -> x.getValue(Tables.DEMISE_CITY.DEMISEID));
//
//        demiseIDsByCities.removeAll(result.keySet());
//
//        var demisesByCity = dsl.selectFrom(Tables.DEMISE)
//                .where(Tables.DEMISE.DEMISEID.in(demiseIDsByCities))
//                .fetchMap(
//                        x -> x.getDemiseid(),
//                        x -> x.into(DemiseEntity.class)
//                );
//
//        result.putAll(demisesByCity);
//
//
//
//
//
//        var cities = dsl.select().from(Tables.DEMISE_CITY)
//
//                .innerJoin(Tables.CITY)
//                .on(Tables.CITY.CITYID.eq(Tables.DEMISE_CITY.CITYID))
//
//                .where(Tables.DEMISE_CITY.DEMISEID.in(result.keySet()))
//                .fetchMap(x -> x.getValue(Tables.DEMISE_CITY.DEMISEID), x -> x.into(Tables.CITY).into(CityEntity.class));
//
////        var cemeteries = dsl.selectFrom(Tables.CEMETERY)
////                .where(Tables.CEMETERY.CEMETERYID.in(result.values().stream().map(DemiseEntity::getCemeteryid).collect(Collectors.toList())))
////                .fetchMap(CemeteryRecord::getCemeteryid, x -> x.into(CemeteryEntity.class));
//
//        var logos = dsl.select()
//                .from(Tables.AGENCY_DEMISE)
//                .innerJoin(Tables.AGENCY)
//                .on(Tables.AGENCY.AGENCYID.eq(Tables.AGENCY_DEMISE.AGENCYID))
//                .fetchMap(x -> x.getValue(Tables.AGENCY_DEMISE.DEMISEID), x -> x.getValue(Tables.AGENCY.AGENCYLOGO));
//
//
//
////        var utentiCheHannoMioNumero =
////                dsl
////                        .select()
////                        .from(Tables.PHONEBOOK)
////
////                        .leftJoin(Tables.ACCOUNT).on(Tables.ACCOUNT.ACCOUNTID.eq(Tables.PHONEBOOK.ACCOUNTID))
////
////                        .where(Tables.PHONEBOOK.PHONEHASH.eq(account.getPhonehashed()))
////                        .fetchMap(
////                                x -> x.getValue(Tables.PHONEBOOK.ACCOUNTID),
////                                x -> x.into(AccountEntity.class)
////                        );
////
////        var nomiMiaRubricaUtentiCheHannoMioNumero =
////                dsl.selectFrom(Tables.PHONEBOOK)
////                        .where(Tables.PHONEBOOK.ACCOUNTID.eq(userid),
////                        Tables.PHONEBOOK.PHONEHASH.in(utentiCheHannoMioNumero.values().stream().map(x -> x.getPhonehashed()).collect(Collectors.toList()))
////                )
////                .fetchMap(x -> x.getPhonehash(), x -> x.getName());
//
//
//        //se serve questo è il match bidirezionale
////        var userPhonebook =
////                dsl.selectFrom(Tables.ACCOUNT)
////                        .where(Tables.ACCOUNT.PHONEHASHED
////                                .in(
////                                        dsl.select(Tables.PHONEBOOK.PHONEHASH).from(Tables.PHONEBOOK).where(Tables.PHONEBOOK.ACCOUNTID.eq(userid))
////                                ))
////                        .fetchSet(AccountRecord::getAccountid);
////
////        matchedUsers.retainAll(userPhonebook);
//
//
//
////        var demises = dsl.select().from(Tables.DEMISE_RELATIVE)
////                .leftJoin(Tables.DEMISE).on(Tables.DEMISE.DEMISEID.eq(Tables.DEMISE_RELATIVE.DEMISEID))
////                .innerJoin(Tables.CEMETERY)
////                .on(Tables.CEMETERY.CEMETERYID.eq(Tables.DEMISE.CEMETERYID))
////
////                .innerJoin(Tables.CITY)
////                .on(Tables.DEMISE.CITYID.eq(Tables.CITY.CITYID))
////
////                .where(Tables.DEMISE_RELATIVE.ACCOUNTID.in(utentiCheHannoMioNumero.keySet()))
////                .fetch(x -> x.into(DemiseEntity.class)
////                        .setCity(x.into(Tables.CITY).into(CityEntity.class))
////                        .setRelativename(
////                        nomiMiaRubricaUtentiCheHannoMioNumero.getOrDefault(
////                                utentiCheHannoMioNumero.get(x.getValue(Tables.DEMISE_RELATIVE.ACCOUNTID)).getPhonehashed(),
////                                utentiCheHannoMioNumero.get(x.getValue(Tables.DEMISE_RELATIVE.ACCOUNTID)).getName()
////                        )
////                ));
////
////        var demisesByCity = dsl.select().from(Tables.DEMISE)
////
////                .innerJoin(Tables.ACCOUNT_CITY)
////                .on(Tables.DEMISE.CITYID.eq(Tables.ACCOUNT_CITY.CITYID))
////
////                .innerJoin(Tables.CEMETERY)
////                .on(Tables.CEMETERY.CEMETERYID.eq(Tables.DEMISE.CEMETERYID))
////
////                .innerJoin(Tables.CITY)
////                .on(Tables.DEMISE.CITYID.eq(Tables.CITY.CITYID))
////
////                .where(Tables.ACCOUNT_CITY.ACCOUNTID.eq(userid))
////                .fetchMap(x -> x.getValue(Tables.DEMISE.DEMISEID), x -> x.into(DemiseEntity.class).setName(x.getValue(Tables.DEMISE.NAME)).setCity(x.into(Tables.CITY).into(CityEntity.class)));
////
////        Map<UUID, List<DemiseRelative>> cityRelatives = dsl.select()
////                .from(Tables.DEMISE_RELATIVE)
////                .innerJoin(Tables.ACCOUNT)
////                .on(Tables.ACCOUNT.ACCOUNTID.eq(Tables.DEMISE_RELATIVE.ACCOUNTID))
////                .fetchGroups(x -> x.getValue(Tables.DEMISE_RELATIVE.DEMISEID), x ->
////                        new DemiseRelative()
////                                .setKinship(x.getValue(Tables.DEMISE_RELATIVE.KINSHIP))
////                                .setName(x.getValue(Tables.ACCOUNT.NAME) + " " + x.getValue(Tables.ACCOUNT.SURNAME))
////                );
////
////        HashSet<DemiseEntity> result = new HashSet(demises);
////        result.addAll(demisesByCity.values()
////                .stream()
////                .peek(x -> {
////                    if (cityRelatives.containsKey(x.getDemiseid()))
////                        x.setKinship(cityRelatives.get(x.getDemiseid()).get(0).getKinship())
////                            .setRelativename(cityRelatives.get(x.getDemiseid()).get(0).getName());
////                    else
////                        x.setKinship(null).setRelativename(x.getName()+" " + x.getSurname());
////                    }
////                )
////                .collect(Collectors.toSet())
////        );
//
//        return result.values().stream()
//                .map(demiseEntity -> demiseEntity
//                        .setCity(cities.get(demiseEntity.getDemiseid()))
//                        .setAgencylogo(logos.get(demiseEntity.getDemiseid()))
//                )
//                .sorted(
//                    (a, b) ->
//                        switch (filters.getSorting()) {
//                            case date -> a.getDate().compareTo(b.getDate());
//                            case name -> a.getName().compareTo(b.getName());
//                            case surname -> a.getSurname().compareTo(b.getSurname());
//                            case timestamp -> a.getTs().compareTo(b.getTs());
//                        }
//                )
//                .skip(filters.getOffset())
//                .limit(filters.getLimit())
//                .collect(Collectors.toList());
//    }

//    public List<AccountEntity> getAccounts(Collection<UUID> userids) {
//        return dsl.selectFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.ACCOUNTID.in(userids)).fetchInto(AccountEntity.class);
//    }

    public Collection<DemiseEntity> getUnreadDemisesAndMarkRead(UUID userid, Integer offset) {

        Map<UUID, DemiseMatchEntity> matches = getAccountMatches(userid, true);

        Set<UUID> read = dsl
                .selectFrom(Tables.DEMISE_READ)
                .where(Tables.DEMISE_READ.DEMISEID.in(matches.keySet()), Tables.DEMISE_READ.ACCOUNTID.eq(userid))
                .fetchSet(DemiseReadRecord::getDemiseid);

        var unread = matches.entrySet()
                .stream()
                .filter(x -> !read.contains(x.getKey()) && x.getValue().getNotify())
                .sorted(Comparator.comparing(a -> a.getValue().getTs()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        Set<UUID> result;

        if (unread.size() > offset+20) {

            result = unread.stream().skip(offset).limit(20).map(DemiseMatchEntity::getDemiseid).collect(Collectors.toSet());

            var toMarkRead = result.stream()
                    .map(x -> {
                        DemiseReadRecord record = dsl.newRecord(Tables.DEMISE_READ);
                        record.setAccountid(userid);
                        record.setDemiseid(x);
                        return record;
                    })
                    .collect(Collectors.toSet());

            dsl.batchInsert(toMarkRead).execute();

            int toRead = unread.size() - (offset+20);

            CounterSetRecord existing = dsl.fetchOne(Tables.COUNTER_SET, Tables.COUNTER_SET.ACCOUNTID.eq(userid));

            if (existing == null){
                existing = dsl.newRecord(Tables.COUNTER_SET);
                existing.setAccountid(userid);
                existing.setUnread(toRead);
                existing.insert();
            } else {
                existing.setUnread(toRead);
                existing.update();
            }


        } else {

            var readDemises = matches.entrySet()
                    .stream()
                    .filter(x -> read.contains(x.getKey()) && x.getValue().getNotify())
                    .sorted(Comparator.comparing(a -> a.getValue().getTs()))
                    .map(x -> x.getValue())
                    .limit((offset+20)-unread.size())
                    .collect(Collectors.toList());



            var toMarkRead = unread.stream()
                    .map(x -> {
                        DemiseReadRecord record = dsl.newRecord(Tables.DEMISE_READ);
                        record.setAccountid(userid);
                        record.setDemiseid(x.getDemiseid());
                        return record;
                    })
                    .collect(Collectors.toSet());

            dsl.batchInsert(toMarkRead).execute();

            CounterSetRecord existing = dsl.fetchOne(Tables.COUNTER_SET, Tables.COUNTER_SET.ACCOUNTID.eq(userid));

            if (existing == null){
                existing = dsl.newRecord(Tables.COUNTER_SET);
                existing.setAccountid(userid);
                existing.setUnread(0);
                existing.insert();
            } else {
                existing.setUnread(0);
                existing.update();
            }

            unread.addAll(readDemises);
            result = unread.stream().sorted(Comparator.comparing(DemiseMatchEntity::getTs).reversed()).skip(offset).limit(20).map(x -> x.getDemiseid()).collect(Collectors.toSet());
        }

        return getDemisesByIDs(result)
                .stream()
                .peek(d -> d.setRead(read.contains(d.getAccountid())).setMatch(matches.get(d.getDemiseid())))
                .sorted((a, b) -> b.getMatch().getTs().compareTo(a.getMatch().getTs()))
                .collect(Collectors.toList());
    }

    public void deleteUserByID(UUID userid) {
        dsl.deleteFrom(Tables.ACCOUNT).where(Tables.ACCOUNT.ACCOUNTID.eq(userid)).execute();
    }

    public List<String> getInstanceIDs(UUID accountid) {
        return dsl
                .selectFrom(Tables.ACCOUNT_INSTANCEID)
                .where(Tables.ACCOUNT_INSTANCEID.ACCOUNTID.eq(accountid))
                .fetch(x -> x.getInstanceid());
    }

    public String getKinshipText(Kinship kinship, Lang lang) {
        return dsl.selectFrom(Tables.KINSHIP_TEXT).where(Tables.KINSHIP_TEXT.KINSHIP.eq(kinship), Tables.KINSHIP_TEXT.LANG.eq(lang)).fetchOne(KinshipTextRecord::getText);
    }

    public String getNotificationTextByKinship(Kinship kinship, Lang lang) {
        return dsl.selectFrom(Tables.NOTIFICATION_KINSHIP_TEXT)
                .where(Tables.NOTIFICATION_KINSHIP_TEXT.KINSHIP.eq(kinship), Tables.NOTIFICATION_KINSHIP_TEXT.LANG.eq(lang))
                .fetchOne(NotificationKinshipTextRecord::getText);
    }

    public String getNotificationText(Lang lang) {
        return dsl.selectFrom(Tables.NOTIFICATION_TEXT)
                .where(Tables.NOTIFICATION_TEXT.LANG.eq(lang))
                .fetchOne(NotificationTextRecord::getText);
    }

    public Map<UUID, AccountEntity> getUsersByContact(String phonehash) {
        return dsl.select()
                .from(Tables.PHONEBOOK)

                .innerJoin(Tables.ACCOUNT)
                .on(Tables.ACCOUNT.ACCOUNTID.eq(Tables.PHONEBOOK.ACCOUNTID))

                .where(Tables.PHONEBOOK.PHONEHASH.eq(phonehash))
                .fetchMap(x -> x.getValue(Tables.ACCOUNT.ACCOUNTID), x -> x.into(Tables.ACCOUNT).into(AccountEntity.class).setPhonebookName(x.getValue(Tables.PHONEBOOK.NAME)));
    }

    public Map<UUID, AccountEntity> getUsersByCity(UUID cityid) {
        return dsl.select()
                .from(Tables.ACCOUNT_CITY)
                .innerJoin(Tables.ACCOUNT)
                .on(Tables.ACCOUNT.ACCOUNTID.eq(Tables.ACCOUNT_CITY.ACCOUNTID))
                .where(Tables.ACCOUNT_CITY.CITYID.eq(cityid))
                .fetchMap(x -> x.getValue(Tables.ACCOUNT.ACCOUNTID), x -> x.into(Tables.ACCOUNT).into(AccountEntity.class));
    }

    public Integer getCounterSet(UUID accountid) {
        return dsl.selectFrom(Tables.COUNTER_SET).where(Tables.COUNTER_SET.ACCOUNTID.eq(accountid)).fetchOne(x -> x.getUnread());
    }

    public void deleteCounterSet(UUID accountid) {
        dsl.deleteFrom(Tables.COUNTER_SET).where(Tables.COUNTER_SET.ACCOUNTID.eq(accountid)).execute();
    }

    public List<String> getAllPrefixes() {
        return dsl.selectFrom(Tables.COUNTRY).fetch(x -> x.getPhonecode().toString());
    }


    public Map<UUID, DemiseMatchEntity> getAccountMatches(UUID userid) {
        return getAccountMatches(userid, false);
    }


    private Map<UUID, DemiseMatchEntity> getAccountMatches(UUID userid, boolean onlyNotif) {
        return dsl.selectFrom(Tables.DEMISE_MATCH)
                .where(Tables.DEMISE_MATCH.ACCOUNTID.eq(userid),
                        onlyNotif ? Tables.DEMISE_MATCH.NOTIFY.isTrue() : Tables.DEMISE_MATCH.NOTIFY.isNotNull()
                        )
                .fetchMap(
                        x -> x.getDemiseid(),
                        x -> x.into(DemiseMatchEntity.class)
                );
    }

    public List<DemiseEntity> getDemisesByIDs(Set<UUID> demiseIDs) {

        var demises = dsl.selectFrom(Tables.DEMISE).where(Tables.DEMISE.DEMISEID.in(demiseIDs)).fetchMap(x -> x.getDemiseid(), x -> x.into(DemiseEntity.class));


        var cities = dsl.select().from(Tables.DEMISE_CITY)

                .innerJoin(Tables.CITY)
                .on(Tables.CITY.CITYID.eq(Tables.DEMISE_CITY.CITYID))

                .where(Tables.DEMISE_CITY.DEMISEID.in(demises.keySet()))
                .fetchGroups(x -> x.getValue(Tables.DEMISE_CITY.DEMISEID), x -> x.into(Tables.CITY).into(CityEntity.class));

        var logos = dsl.select()
                .from(Tables.AGENCY_DEMISE)
                .innerJoin(Tables.AGENCY)
                .on(Tables.AGENCY.AGENCYID.eq(Tables.AGENCY_DEMISE.AGENCYID))
                .fetchMap(x -> x.getValue(Tables.AGENCY_DEMISE.DEMISEID), x -> x.getValue(Tables.AGENCY.AGENCYLOGO));

        return demises.values().stream()
                .map(demiseEntity -> demiseEntity
                        .setCities(cities.getOrDefault(demiseEntity.getDemiseid(), Collections.emptyList()))
                        .setAgencylogo(logos.get(demiseEntity.getDemiseid()))
                )
                .collect(Collectors.toList());
    }

    public Map<UUID, List<UUID>> getDemiseIDsByCities(List<UUID> cities) {
        return dsl.selectFrom(Tables.DEMISE_CITY)
                .where(Tables.DEMISE_CITY.CITYID.in(cities))
                .fetchGroups(
                        x -> x.getCityid(),
                        x -> x.getDemiseid()
                );
    }

    public void deleteMatches(UUID accountid, List<UUID> matches) {
        if(!matches.isEmpty())
            dsl.deleteFrom(Tables.DEMISE_MATCH).where(Tables.DEMISE_MATCH.ACCOUNTID.eq(accountid), Tables.DEMISE_MATCH.DEMISEID.in(matches)).execute();
    }

    public void insertMatches(Collection<DemiseMatchEntity> matches) {
        dsl.batchInsert(
                matches.stream().map(m -> dsl.newRecord(Tables.DEMISE_MATCH, m)).collect(Collectors.toList())
        ).execute();
    }

    public Map<UUID, DemiseMatchEntity> getDemisesByUserContacts(UUID accountid) {
        Map<String, List<String>> phonebook = dsl.selectFrom(Tables.PHONEBOOK)
                .where(Tables.PHONEBOOK.ACCOUNTID.eq(accountid))
                .fetchGroups(PhonebookRecord::getPhonehash, PhonebookRecord::getName);

        var demiseContacts = dsl.selectFrom(Tables.DEMISE)
                .where(Tables.DEMISE.PHONEHASH.in(phonebook.keySet()))
                .fetchMap(
                        x -> x.getDemiseid(),
                        x ->  new DemiseMatchEntity()
                                .setAccountid(accountid)
                                .setDemiseid(x.getDemiseid())
                                .setType(Demisematchtype.contact)
                                .setName(phonebook.get(x.getPhonehash()).get(0))
                );

        var demiseRelatives =
                dsl.selectFrom(Tables.DEMISE_RELATIVE)
                .where(Tables.DEMISE_RELATIVE.PHONEHASH.in(phonebook.keySet()), Tables.DEMISE_RELATIVE.DEMISEID.notIn(demiseContacts.keySet()))
                .fetchMap(
                        x -> x.getDemiseid(),
                        x -> new DemiseMatchEntity()
                                .setAccountid(accountid)
                                .setType(Demisematchtype.relative)
                                .setKinship(x.getKinship())
                                .setName(phonebook.get(x.getPhonehash()).get(0))
                                .setDemiseid(x.getDemiseid())
                );

        demiseContacts.putAll(demiseRelatives);

        var demiseRelativesWithAccount =
                dsl.select()
                .from(Tables.ACCOUNT)

                .innerJoin(Tables.DEMISE_RELATIVE)
                .on(Tables.DEMISE_RELATIVE.ACCOUNTID.eq(Tables.ACCOUNT.ACCOUNTID))

                .where(Tables.ACCOUNT.PHONEHASHED.in(phonebook.keySet()), Tables.DEMISE_RELATIVE.DEMISEID.notIn(demiseContacts.keySet()))
                .fetchMap(
                        x -> x.getValue(Tables.DEMISE_RELATIVE.DEMISEID),
                        x -> new DemiseMatchEntity()
                                .setAccountid(accountid)
                                .setType(Demisematchtype.relative)
                                .setKinship(x.getValue(Tables.DEMISE_RELATIVE.KINSHIP))
                                .setName(phonebook.get(
                                        x.getValue(Tables.DEMISE_RELATIVE.PHONEHASH) != null ?
                                                x.getValue(Tables.DEMISE_RELATIVE.PHONEHASH) : x.getValue(Tables.ACCOUNT.PHONEHASHED)
                                ).get(0))
                                .setDemiseid(x.getValue(Tables.DEMISE_RELATIVE.DEMISEID))
                );

        demiseContacts.putAll(demiseRelativesWithAccount);

        return demiseContacts;
    }

    public void deleteInstanceIDs(List<UUID> instanceIDs) {
        dsl.deleteFrom(Tables.ACCOUNT_INSTANCEID).where(Tables.ACCOUNT_INSTANCEID.INSTANCEID.in(instanceIDs)).execute();
    }
}
