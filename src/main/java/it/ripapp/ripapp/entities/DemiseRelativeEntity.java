package it.ripapp.ripapp.entities;

import it.ripapp.ripapp.jooqgen.enums.Kinship;

import java.util.UUID;

public class DemiseRelativeEntity implements IEntity {

    private UUID demiseid;
    private UUID accountid;
    private Kinship kinship;
    private String phone;
    private String prefix;
    private String name;
    private String phonehash;


    public UUID getDemiseid() {
        return demiseid;
    }

    public DemiseRelativeEntity setDemiseid(UUID demiseid) {
        this.demiseid = demiseid;
        return this;
    }

    public UUID getAccountid() {
        return accountid;
    }

    public DemiseRelativeEntity setAccountid(UUID accountid) {
        this.accountid = accountid;
        return this;
    }

    public Kinship getKinship() {
        return kinship;
    }

    public DemiseRelativeEntity setKinship(Kinship kinship) {
        this.kinship = kinship;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public DemiseRelativeEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public DemiseRelativeEntity setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemiseRelativeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getPhonehash() {
        return phonehash;
    }

    public DemiseRelativeEntity setPhonehash(String phonehash) {
        this.phonehash = phonehash;
        return this;
    }
}
