package it.ripapp.ripapp.entityUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AgencyEntity {
    @Id
    private UUID agencyid;
    private String name;
    private String address;
    private String logo;

    @JsonIgnore
    private Double similarity;

    @JsonIgnore
    private String email;

    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<DemiseEntity> demises;


    @OneToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<AgencyEntity> agencies;


}
