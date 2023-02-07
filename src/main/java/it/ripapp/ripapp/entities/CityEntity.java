package it.ripapp.ripapp.entities;

import java.util.UUID;

public class CityEntity implements IEntity {

    private UUID cityid;
    private String name;

    public UUID getCityid() {
        return cityid;
    }

    public CityEntity setCityid(UUID cityid) {
        this.cityid = cityid;
        return this;
    }

    public String getName() {
        return name;
    }

    public CityEntity setName(String name) {
        this.name = name;
        return this;
    }
}
