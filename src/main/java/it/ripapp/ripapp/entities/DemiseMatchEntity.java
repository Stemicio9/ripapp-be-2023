package it.ripapp.ripapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;

import java.time.LocalDateTime;
import java.util.UUID;

public class DemiseMatchEntity {

    private UUID accountid;
    private UUID demiseid;
    private UUID cityid;
    private String name;
    private Kinship kinship;
    private Demisematchtype type;
    private Boolean notify;
    private LocalDateTime ts;

    @JsonIgnore
    private Lang matchLang;


    public UUID getAccountid() {
        return accountid;
    }

    public DemiseMatchEntity setAccountid(UUID accountid) {
        this.accountid = accountid;
        return this;
    }

    public UUID getDemiseid() {
        return demiseid;
    }

    public DemiseMatchEntity setDemiseid(UUID demiseid) {
        this.demiseid = demiseid;
        return this;
    }

    public UUID getCityid() {
        return cityid;
    }

    public DemiseMatchEntity setCityid(UUID cityid) {
        this.cityid = cityid;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemiseMatchEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Kinship getKinship() {
        return kinship;
    }

    public DemiseMatchEntity setKinship(Kinship kinship) {
        this.kinship = kinship;
        return this;
    }

    public Demisematchtype getType() {
        return type;
    }

    public DemiseMatchEntity setType(Demisematchtype type) {
        this.type = type;
        return this;
    }

    public Boolean getNotify() {
        return notify;
    }

    public DemiseMatchEntity setNotify(Boolean notify) {
        this.notify = notify;
        return this;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public DemiseMatchEntity setTs(LocalDateTime ts) {
        this.ts = ts;
        return this;
    }

    public Lang getMatchLang() {
        return matchLang;
    }

    public DemiseMatchEntity setMatchLang(Lang matchLang) {
        this.matchLang = matchLang;
        return this;
    }
}
