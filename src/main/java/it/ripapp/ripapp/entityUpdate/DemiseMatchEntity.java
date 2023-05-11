package it.ripapp.ripapp.entityUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DemiseMatchEntity {
    @Id
    private UUID id;

    @OneToOne
    private AccountEntity accountid;
    @OneToOne
    private DemiseEntity demiseid;
    @OneToOne
    private CityEntity cityid;
    private String name;
    private Kinship kinship;
    private Demisematchtype type;
    private Boolean notify;
    private LocalDateTime ts;

    @JsonIgnore
    private Lang matchLang;
}
