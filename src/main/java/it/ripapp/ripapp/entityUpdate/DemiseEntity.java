package it.ripapp.ripapp.entityUpdate;




import lombok.*;

import javax.persistence.*;


import java.util.Date;
import java.util.UUID;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemiseEntity {

    @Id
    private UUID demiseid;
 /*   private String name;
    private String surname;
    private String photourl;
    private Date fdate;
    private String obituary;
    private Date wakets;
    private String wakeaddress;
    private Date funeralts;
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

    private Date ts;

    private boolean read;

    private String kinship;
    private UUID accountid; */

 /*   @OneToOne
    @Cascade(CascadeType.ALL)
    private AccountEntity relative;
    private String relativename;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<DemiseRelative> relatives; */

   // @JsonIgnore
    //private Demisematchtype demisematchtype;



  /*  @OneToMany
    @Cascade(CascadeType.ALL)
    private List<it.ripapp.ripapp.entityUpdate.CityEntity> cities;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private it.ripapp.ripapp.entityUpdate.CityEntity city; */


}





