package it.ripapp.ripapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.utilities.UserStatus;

import java.util.List;
import java.util.UUID;

public class AccountEntity implements IEntity {

    private UUID accountid;
    private String name;
    private String surname;
    private String email;
    private String prefix;
    private String phone;
    private String phonehashed;
    private Boolean notif;
    private List<CityEntity> cities;
    private Boolean enabled;
    private String photourl;
    private Lang lang;

    private String idtoken;
    private Kinship kinship;
    private String phonebookName;

    private UserStatus status;

    @JsonIgnore
    private Double similarity;

    public String getIdtoken() {
        return idtoken;
    }

    public AccountEntity setIdtoken(String idtoken) {
        this.idtoken = idtoken;
        return this;
    }

    public UUID getAccountid() {
        return accountid;
    }

    public AccountEntity setAccountid(UUID accountid) {
        this.accountid = accountid;
        return this;
    }

    public String getName() {
        return name;
    }

    public AccountEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public AccountEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AccountEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public AccountEntity setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public AccountEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Boolean getNotif() {
        return notif;
    }

    public AccountEntity setNotif(Boolean notif) {
        this.notif = notif;
        return this;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public AccountEntity setCities(List<CityEntity> cities) {
        this.cities = cities;
        return this;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public AccountEntity setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getPhotourl() {
        return photourl;
    }

    public AccountEntity setPhotourl(String photourl) {
        this.photourl = photourl;
        return this;
    }

    public Kinship getKinship() {
        return kinship;
    }

    public AccountEntity setKinship(Kinship kinship) {
        this.kinship = kinship;
        return this;
    }

    public Lang getLang() {
        return lang;
    }

    public AccountEntity setLang(Lang lang) {
        this.lang = lang;
        return this;
    }

    public String getPhonebookName() {
        return phonebookName;
    }

    public AccountEntity setPhonebookName(String phonebookName) {
        this.phonebookName = phonebookName;
        return this;
    }

    public String getPhonehashed() {
        return phonehashed;
    }

    public AccountEntity setPhonehashed(String phonehashed) {
        this.phonehashed = phonehashed;
        return this;
    }

    public Double getSimilarity() {
        return similarity;
    }

    public AccountEntity setSimilarity(Double similarity) {
        this.similarity = similarity;
        return this;
    }

    public UserStatus getStatus() {
        return status;
    }

    public AccountEntity setStatus(UserStatus status) {
        this.status = status;
        return this;
    }


}

