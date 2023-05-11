package it.ripapp.ripapp.dal;
/*
import com.google.common.hash.Hashing;
import it.ripapp.ripapp.entities.*;
import it.ripapp.ripapp.jooqgen.Tables;
import it.ripapp.ripapp.jooqgen.tables.records.*;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


public class AgencyDAL extends JpaRe {

    public AgencyDAL(DSLContext dsl) {
        super(dsl);
    }


    public AgencyEntity getAgencyByEmail(String email) {
        return dsl.select()
                .from(Tables.ACCOUNT)

                .innerJoin(Tables.AGENCY_OPERATOR)
                .on(Tables.AGENCY_OPERATOR.ACCOUNTID.eq(Tables.ACCOUNT.ACCOUNTID))

                .innerJoin(Tables.AGENCY)
                .on(Tables.AGENCY.AGENCYID.eq(Tables.AGENCY_OPERATOR.AGENCYID))

                .where(Tables.ACCOUNT.EMAIL.eq(email))
                .fetchOne(x -> x.into(Tables.AGENCY).into(AgencyEntity.class));
    }

    public AgencyEntity getAgencyByOperator(UUID userid) {
        return dsl.select()
                .from(Tables.AGENCY_OPERATOR)
                .leftJoin(Tables.AGENCY).on(Tables.AGENCY.AGENCYID.eq(Tables.AGENCY_OPERATOR.AGENCYID))
                .where(Tables.AGENCY_OPERATOR.ACCOUNTID.eq(userid))
                .fetchOneInto(AgencyEntity.class);
    }

    public List<DemiseEntity> getDemisesByAgency(UUID agencyid, Integer offset) {

        var demises = dsl.select()
                .from(Tables.AGENCY_DEMISE)

                .innerJoin(Tables.DEMISE)
                .on(Tables.DEMISE.DEMISEID.eq(Tables.AGENCY_DEMISE.DEMISEID))

                .where(Tables.AGENCY_DEMISE.AGENCYID.eq(agencyid))
                .offset(offset)
                .limit(20)
                .fetchMap(x -> x.getValue(Tables.DEMISE.DEMISEID), x -> x.into(DemiseEntity.class).setName(x.getValue(Tables.DEMISE.NAME)));

        Map<UUID, List<DemiseRelativeEntity>> relatives = dsl.select()
                .from(Tables.DEMISE_RELATIVE)
                .leftJoin(Tables.ACCOUNT)
                .on(Tables.ACCOUNT.ACCOUNTID.eq(Tables.DEMISE_RELATIVE.ACCOUNTID))
                .where(Tables.DEMISE_RELATIVE.DEMISEID.in(demises.keySet()))
                .fetchGroups(x -> x.getValue(Tables.DEMISE_RELATIVE.DEMISEID), x -> {
                    DemiseRelativeEntity res = x.into(DemiseRelativeEntity.class);
                    if (x.getValue(Tables.DEMISE_RELATIVE.PHONE) != null)
                        res.setPhone(x.getValue(Tables.DEMISE_RELATIVE.PHONE));
                    else
                        res.setPhone(x.getValue(Tables.ACCOUNT.PHONE));

                    return res;
                });


        Map<UUID, List<CityEntity>> cities =
                dsl.select().from(Tables.DEMISE_CITY)

                .innerJoin(Tables.CITY)
                .on(Tables.CITY.CITYID.eq(Tables.DEMISE_CITY.CITYID))

                .where(Tables.DEMISE_CITY.DEMISEID.in(demises.keySet()))
                .fetchGroups(
                        x -> x.getValue(Tables.DEMISE_CITY.DEMISEID),
                        x -> x.into(Tables.CITY).into(CityEntity.class)
                );

        return demises.values().stream()
                .peek(x -> x
                        .setRelatives(relatives.get(x.getDemiseid()))
                        .setCities(
                                cities.getOrDefault(x.getDemiseid(), Collections.emptyList())
                                        .stream()
                                        .sorted(Comparator.comparing(CityEntity::getName))
                                        .collect(Collectors.toList())
                        )
                )
                .sorted(Comparator.comparing(DemiseEntity::getTs).reversed())
                .collect(Collectors.toList());
    }

//    public Map<UUID, AccountEntity> getPhoneMatchesByAccount(UUID matchTarget) {
//        return dsl.select()
//                .from(Tables.PHONEBOOK)
//
//                .leftJoin(Tables.ACCOUNT)
//                .on(Tables.PHONEBOOK.PHONEHASH.eq(Tables.ACCOUNT.PHONEHASHED))
//
//                .where(Tables.PHONEBOOK.ACCOUNTID.eq(matchTarget))
//                .fetchMap(
//                        x -> x.getValue(Tables.ACCOUNT.ACCOUNTID), x -> x.into(Tables.ACCOUNT).into(AccountEntity.class)
//                );
//    }

//    public Map<UUID, String> getPhonebookNames(List<String> phoneHashes) {
//        return dsl.selectFrom(Tables.PHONEBOOK)
//                .where(Tables.PHONEBOOK.PHONEHASH.in(phoneHashes))
//                .fetchMap(
//                        x -> x.getAccountid(), x -> x.getName()
//                );
//    }

    public List<AccountEntity> autocompleteUserPhone(String query) {
        return dsl.selectFrom(Tables.ACCOUNT)
                .where(Tables.ACCOUNT.PHONE.likeIgnoreCase("%"+query+"%"))
                .fetchInto(AccountEntity.class);
    }

    public void insertDemise(UUID agencyid, DemiseEntity demise) {

        dsl.transaction(ctx -> {
            DSLContext temp = DSL.using(ctx);

            DemiseRecord demiseRecord = temp.newRecord(Tables.DEMISE, demise);

            try
            {
                demiseRecord.store();

            } catch (Exception e){
                e.printStackTrace();
            }

            AgencyDemiseRecord agencyDemiseRecord = temp.newRecord(Tables.AGENCY_DEMISE);
            agencyDemiseRecord.setDemiseid(demiseRecord.getDemiseid());
            agencyDemiseRecord.setAgencyid(agencyid);
            agencyDemiseRecord.store();


            Map<String, UUID> relativesAccounts = temp
                            .selectFrom(Tables.ACCOUNT)
                            .where(Tables.ACCOUNT.PHONE.in(demise.getRelatives().stream().map(x -> x.getPhone()).collect(Collectors.toSet())))
                            .fetchMap(
                                x -> x.getPhone(),
                                x -> x.getAccountid()
                            );


            if (!demise.getRelatives().isEmpty()){

                var relativesRecords = demise.getRelatives().stream().map(
                        r -> {
                            DemiseRelativeRecord record = temp.newRecord(Tables.DEMISE_RELATIVE);

                            if (relativesAccounts.containsKey(r.getPhone()))
                                record.setAccountid(relativesAccounts.get(r.getPhone()));
                            else
                                record.setPhone(r.getPhone());

                            record.setPhonehash(r.getPhonehash());
                            record.setKinship(r.getKinship());
                            record.setDemiseid(demiseRecord.getDemiseid());
                            record.setPrefix(r.getPrefix());
                            return record;
                        }
                ).collect(Collectors.toList());

                temp.batchInsert(relativesRecords).execute();
            }

            if (!demise.getCities().isEmpty()){
                var cityRecords = demise.getCities().stream().map(
                        r -> {
                            var record = temp.newRecord(Tables.DEMISE_CITY);
                            record.setDemiseid(demiseRecord.getDemiseid());
                            record.setCityid(r.getCityid());
                            return record;
                        }
                ).collect(Collectors.toList());

                temp.batchInsert(cityRecords).execute();

            }

        });


    }


    public Boolean deleteDemiseByID(UUID demiseid) {
        return dsl.deleteFrom(Tables.DEMISE).where(Tables.DEMISE.DEMISEID.eq(demiseid)).execute() > 0;
    }

    public Boolean updateDemise(UUID demiseid, DemiseEntity demise) {
        dsl.transaction(ctx -> {
            DSLContext temp = DSL.using(ctx);


            temp.update(Tables.DEMISE)
                    .set(Tables.DEMISE.NAME, demise.getName())
                    .set(Tables.DEMISE.SURNAME, demise.getSurname())
                    .set(Tables.DEMISE.CITYNAME, demise.getCityname())
                    .set(Tables.DEMISE.DATE, demise.getDate())
                    .set(Tables.DEMISE.OBITUARY, demise.getObituary())
                    .set(Tables.DEMISE.PHONENUMBER, demise.getPhonenumber())
                    .set(Tables.DEMISE.PHONEPREFIX, demise.getPhoneprefix())
                    .set(Tables.DEMISE.PHOTOURL, demise.getPhotourl())
                    .set(Tables.DEMISE.WAKEADDRESS, demise.getWakeaddress())
                    .set(Tables.DEMISE.WAKETS, demise.getWakets())
                    .set(Tables.DEMISE.FUNERALADDRESS, demise.getFuneraladdress())
                    .set(Tables.DEMISE.FUNERALTS, demise.getFuneralts())
                    .set(Tables.DEMISE.AGE, demise.getAge())
                    .where(Tables.DEMISE.DEMISEID.eq(demiseid))
                    .execute();

            temp.deleteFrom(Tables.DEMISE_RELATIVE).where(Tables.DEMISE_RELATIVE.DEMISEID.eq(demiseid)).execute();


            Map<String, UUID> relativesAccounts = temp
                    .selectFrom(Tables.ACCOUNT)
                    .where(Tables.ACCOUNT.PHONE.in(demise.getRelatives().stream().map(x -> x.getPhone()).collect(Collectors.toSet())))
                    .fetchMap(
                            x -> x.getPhone(),
                            x -> x.getAccountid()
                    );

            var relativesRecords = demise.getRelatives().stream()
                    .filter(x -> x.getKinship() != null)
                    .map(
                            r -> {
                                DemiseRelativeRecord record = temp.newRecord(Tables.DEMISE_RELATIVE);

                                if (relativesAccounts.containsKey(r.getPhone()))
                                    record.setAccountid(relativesAccounts.get(r.getPhone()));
                                else
                                {
                                    record.setPhone(r.getPhone());
                                    record.setPhonehash(Hashing.sha256().hashString(r.getPhone().replaceAll("[^0-9]", ""), StandardCharsets.UTF_8).toString());
                                }

                                record.setPrefix(r.getPrefix());
                                record.setKinship(r.getKinship());
                                record.setDemiseid(demiseid);
                                return record;
                            }
                    )
                    .collect(Collectors.toList());

            temp.batchInsert(relativesRecords).execute();


            temp.deleteFrom(Tables.DEMISE_CITY).where(Tables.DEMISE_CITY.DEMISEID.eq(demiseid)).execute();

            var citiesRecords = demise.getCities()
                    .stream()
                    .map(x -> {
                        var r = temp.newRecord(Tables.DEMISE_CITY);
                        r.setDemiseid(demiseid);
                        r.setCityid(x.getCityid());
                        return r;
                    })
                    .collect(Collectors.toList());

            temp.batchInsert(citiesRecords).execute();


        });

        return true;
    }

    public List<AgencyEntity> getAllAgencies() {
        return dsl.selectFrom(Tables.AGENCY).fetchInto(AgencyEntity.class);
    }

    public AgencyEntity getAgencyByID(UUID agencyid) {
        return dsl.selectFrom(Tables.AGENCY).where(Tables.AGENCY.AGENCYID.eq(agencyid)).fetchOneInto(AgencyEntity.class);
    }

    public void updateMatches(UUID demiseid, Collection<DemiseMatchEntity> matchesToInsert) {
        dsl.transaction(
                ctx -> {
                    var temp = DSL.using(ctx);

                    temp.deleteFrom(Tables.DEMISE_MATCH).where(Tables.DEMISE_MATCH.DEMISEID.eq(demiseid)).execute();

                    temp.batchInsert(
                            matchesToInsert.stream()
                                    .map(m -> temp.newRecord(Tables.DEMISE_MATCH, m))
                                    .collect(Collectors.toList())
                    )
                    .execute();


                }
        );
    }

    public Set<UUID> getDemiseMatchesIDs(UUID demiseid) {
        return dsl.selectFrom(Tables.DEMISE_MATCH).where(Tables.DEMISE_MATCH.DEMISEID.eq(demiseid)).fetchSet(DemiseMatchRecord::getAccountid);
    }

    public boolean operatorOwnsDemise(UUID userid, UUID demiseid) {
        return dsl.fetchExists(
                dsl.select().from(Tables.AGENCY_OPERATOR)

                        .innerJoin(Tables.AGENCY_DEMISE)
                        .on(Tables.AGENCY_DEMISE.AGENCYID.eq(Tables.AGENCY_OPERATOR.AGENCYID))

                        .where(Tables.AGENCY_OPERATOR.ACCOUNTID.eq(userid), Tables.AGENCY_DEMISE.DEMISEID.eq(demiseid))
        );
    }

    public boolean agencyOwnsProduct(UUID productId, UUID agencyId) {
        return dsl.fetchExists(
                dsl.select().from(Tables.AGENCY)

                        .innerJoin(Tables.AGENCY_PRODUCT)
                        .on(Tables.AGENCY_PRODUCT.AGENCYID.eq(Tables.AGENCY.AGENCYID))

                        .where(Tables.AGENCY.AGENCYID.eq(agencyId), Tables.AGENCY_PRODUCT.PRODUCTID.eq(productId))
        );
    }





    public void insertProduct(UUID agencyid, ProductEntity product) {

        dsl.transaction(ctx -> {
            DSLContext temp = DSL.using(ctx);

            ProductRecord productRecord = temp.newRecord(Tables.PRODUCT, product);

            try
            {
                productRecord.store();

            } catch (Exception e){
                e.printStackTrace();
            }

            AgencyProductRecord agencyProductRecord = temp.newRecord(Tables.AGENCY_PRODUCT);

            agencyProductRecord.setProductid(productRecord.getProductid());
            agencyProductRecord.setAgencyid(agencyid);
            agencyProductRecord.store();

        });
    }

    public Boolean deleteProductByID(UUID productId) {
        return dsl.deleteFrom(Tables.PRODUCT).where(Tables.PRODUCT.PRODUCTID.eq(productId)).execute() > 0;
    }


    public Boolean updateProduct(UUID productId, ProductEntity product) {
        dsl.transaction(ctx -> {
            DSLContext temp = DSL.using(ctx);


            temp.update(Tables.PRODUCT)
                    .set(Tables.PRODUCT.PRODUCTNAME, product.getProductName())
                    .set(Tables.PRODUCT.PRICE, product.getPrice())
                    .set(Tables.PRODUCT.URLIMAGE, product.getUrlImage())
                    .execute();


        });

        return true;
    }

    public List<ProductEntity> getAllProduct() {
        return dsl.selectFrom(Tables.PRODUCT).fetchInto(ProductEntity.class);
    }

    public List<ProductEntity> getProductsByAgency(UUID agencyid, Integer offset) {

        var products = dsl.select()
                .from(Tables.AGENCY_PRODUCT)

                .innerJoin(Tables.PRODUCT)
                .on(Tables.PRODUCT.PRODUCTID.eq(Tables.AGENCY_PRODUCT.PRODUCTID))

                .where(Tables.AGENCY_PRODUCT.AGENCYID.eq(agencyid))
                .offset(offset)
                .limit(20)
                .fetchMap(x -> x.getValue(Tables.PRODUCT.PRODUCTID), x -> x.into(ProductEntity.class));

        return products.values().stream().collect(Collectors.toList());

    }


}
*/
