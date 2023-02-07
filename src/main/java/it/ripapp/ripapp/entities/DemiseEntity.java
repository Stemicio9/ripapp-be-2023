package it.ripapp.ripapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class DemiseEntity implements IEntity {

    private UUID demiseid;
    private String name;
    private String surname;
    private String photourl;
    private LocalDate date;
    private String obituary;
    private LocalDateTime wakets;
    private String wakeaddress;
    private LocalDateTime funeralts;
    private String funeraladdress;
    private String cityname;
    private String phonenumber;
    private String phoneprefix;
    private Integer age;
    private String agencylogo;
    private String title;
    private String phonehash;
    private String wakenotes;
    private String funeralnotes;

    private String kinshipdesc;

    private LocalDateTime ts;

    private Boolean read;

    private Kinship kinship;
    private UUID accountid;
    private AccountEntity relative;
    private String relativename;


    private List<DemiseRelativeEntity> relatives;

    @JsonIgnore
    private Demisematchtype demisematchtype;

    @JsonIgnore
    private DemiseMatchEntity match;


    private List<CityEntity> cities;
    private CityEntity city;


    public List<DemiseRelativeEntity> getRelatives() {
        return relatives;
    }

    public DemiseEntity setRelatives(List<DemiseRelativeEntity> relatives) {
        this.relatives = relatives;
        return this;
    }

    public UUID getDemiseid() {
        return demiseid;
    }

    public DemiseEntity setDemiseid(UUID demiseid) {
        this.demiseid = demiseid;
        return this;
    }

    public String getName() {
        return name;
    }

    public DemiseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public DemiseEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getPhotourl() {
        return photourl;
    }

    public DemiseEntity setPhotourl(String photourl) {
        this.photourl = photourl;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public DemiseEntity setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public String getObituary() {
        return obituary;
    }

    public DemiseEntity setObituary(String obituary) {
        this.obituary = obituary;
        return this;
    }

    public LocalDateTime getWakets() {
        return wakets;
    }

    public DemiseEntity setWakets(LocalDateTime wakets) {
        this.wakets = wakets;
        return this;
    }

    public String getWakeaddress() {
        return wakeaddress;
    }

    public DemiseEntity setWakeaddress(String wakeaddress) {
        this.wakeaddress = wakeaddress;
        return this;
    }

    public Kinship getKinship() {
        return kinship;
    }

    public DemiseEntity setKinship(Kinship kinship) {
        this.kinship = kinship;
        return this;
    }

    public UUID getAccountid() {
        return accountid;
    }

    public DemiseEntity setAccountid(UUID accountid) {
        this.accountid = accountid;
        return this;
    }

    public AccountEntity getRelative() {
        return relative;
    }

    public DemiseEntity setRelative(AccountEntity relative) {
        this.relative = relative;
        return this;
    }

    public String getCityname() {
        return cityname;
    }

    public DemiseEntity setCityname(String cityname) {
        this.cityname = cityname;
        return this;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public DemiseEntity setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public String getPhoneprefix() {
        return phoneprefix;
    }

    public DemiseEntity setPhoneprefix(String phoneprefix) {
        this.phoneprefix = phoneprefix;
        return this;
    }
    public LocalDateTime getTs() {
        return ts;
    }

    public DemiseEntity setTs(LocalDateTime ts) {
        this.ts = ts;
        return this;
    }

    public String getRelativename() {
        return relativename;
    }

    public DemiseEntity setRelativename(String relativename) {
        this.relativename = relativename;
        return this;
    }

    public Boolean getRead() {
        return read;
    }

    public DemiseEntity setRead(Boolean read) {
        this.read = read;
        return this;
    }

    public LocalDateTime getFuneralts() {
        return funeralts;
    }

    public DemiseEntity setFuneralts(LocalDateTime funeralts) {
        this.funeralts = funeralts;
        return this;
    }

    public String getFuneraladdress() {
        return funeraladdress;
    }

    public DemiseEntity setFuneraladdress(String funeraladdress) {
        this.funeraladdress = funeraladdress;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public DemiseEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    public String getAgencylogo() {
        return agencylogo;
    }

    public DemiseEntity setAgencylogo(String agencylogo) {
        this.agencylogo = agencylogo;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public DemiseEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getPhonehash() {
        return phonehash;
    }

    public DemiseEntity setPhonehash(String phonehash) {
        this.phonehash = phonehash;
        return this;
    }

    public String getKinshipdesc() {
        return kinshipdesc;
    }

    public DemiseEntity setKinshipdesc(String kinshipdesc) {
        this.kinshipdesc = kinshipdesc;
        return this;
    }

    public Demisematchtype getDemisematchtype() {
        return demisematchtype;
    }

    public DemiseEntity setDemisematchtype(Demisematchtype demisematchtype) {
        this.demisematchtype = demisematchtype;
        return this;
    }

    public String getWakenotes() {
        return wakenotes;
    }

    public DemiseEntity setWakenotes(String wakenotes) {
        this.wakenotes = wakenotes;
        return this;
    }

    public String getFuneralnotes() {
        return funeralnotes;
    }

    public DemiseEntity setFuneralnotes(String funeralnotes) {
        this.funeralnotes = funeralnotes;
        return this;
    }

    public DemiseMatchEntity getMatch() {
        return match;
    }

    public DemiseEntity setMatch(DemiseMatchEntity match) {
        this.match = match;
        return this;
    }

    public List<CityEntity> getCities() {
        return cities;
    }

    public DemiseEntity setCities(List<CityEntity> cities) {
        this.cities = cities;
        return this;
    }

    public CityEntity getCity() {
        return city;
    }

    public DemiseEntity setCity(CityEntity city) {
        this.city = city;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DemiseEntity that = (DemiseEntity) o;
        return Objects.equals(demiseid, that.demiseid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(demiseid);
    }

}
