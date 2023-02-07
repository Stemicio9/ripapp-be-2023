package it.ripapp.ripapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class AgencyEntity implements IEntity {

    private UUID agencyid;
    private String name;
    private String address;
    private String logo;

    @JsonIgnore
    private Double similarity;

    @JsonIgnore
    private String email;

    public UUID getAgencyid() {
        return agencyid;
    }

    public AgencyEntity setAgencyid(UUID agencyid) {
        this.agencyid = agencyid;
        return this;
    }

    public String getName() {
        return name;
    }

    public AgencyEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public AgencyEntity setAddress(String address) {
        this.address = address;
        return this;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public AgencyEntity setSimilarity(Double similarity) {
        this.similarity = similarity;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AgencyEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public AgencyEntity setLogo(String logo) {
        this.logo = logo;
        return this;
    }
}
