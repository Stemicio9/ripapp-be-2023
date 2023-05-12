package it.ripapp.ripapp.entityUpdate;


import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.bll.Kinship;

import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DemiseEntity {

    @Id
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

    @OneToOne
    @Cascade(CascadeType.ALL)
    private it.ripapp.ripapp.entityUpdate.AccountEntity relative;
    private String relativename;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<it.ripapp.ripapp.entityUpdate.DemiseRelative> relatives;

   // @JsonIgnore
    //private Demisematchtype demisematchtype;

    @ManyToOne
    @JsonIgnore
    private DemiseMatchEntity match;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<it.ripapp.ripapp.entityUpdate.CityEntity> cities;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private it.ripapp.ripapp.entityUpdate.CityEntity city;


}





