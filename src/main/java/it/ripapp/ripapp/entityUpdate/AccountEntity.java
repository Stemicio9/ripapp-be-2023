package it.ripapp.ripapp.entityUpdate;




import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.utilities.UserStatus;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

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
public class AccountEntity {

    //cappire se fare DTO o registrazione a db
    @Id
    private UUID accountid;
    private String name;
    private String surname;
    private String email;
    private String prefix;
    private String phone;
    private String phonehashed;
    private Boolean notif;
    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<it.ripapp.ripapp.entityUpdate.CityEntity> city;
    private Boolean enabled;
    private String photourl;
    private Lang lang;
    private String idtoken;
    private Kinship kinship;
    private String phonebookName;
    private UserStatus status;
    @JsonIgnore
    private Double similarity;



}
