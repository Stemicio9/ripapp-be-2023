package it.ripapp.ripapp.entityUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agencyid;
    private String name;
    private String address;
    private String logo;
    private String telephoneNumber;
    private String idtoken; //uid di firebase
    private String photoUrl;

    @JsonIgnore
    private Double similarity;

    @Column(unique = true)
    private String email;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<DemiseEntity> demises;

    @ManyToMany
    @Cascade(CascadeType.ALL)
    private List<ProductEntity> products;


    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<AgencyEntity> agencies;



}
