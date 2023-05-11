package it.ripapp.ripapp.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
