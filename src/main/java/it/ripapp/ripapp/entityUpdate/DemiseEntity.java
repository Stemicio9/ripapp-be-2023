package it.ripapp.ripapp.EntityUpdate;




import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DemiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demiseid;
    private String name;
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


    // Do not call this column read, because it is a reserved keyword in SQL
    private boolean isread;

    private String kinship;
    private UUID accountid;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private AccountEntity relative;
    private String relativename;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<DemiseRelative> relatives;

   // @JsonIgnore
    //private Demisematchtype demisematchtype;



    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<CityEntity> cities;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private CityEntity city;


}





