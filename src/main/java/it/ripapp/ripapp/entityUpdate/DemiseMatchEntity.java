package it.ripapp.ripapp.EntityUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.bll.Demisematchtype;
import it.ripapp.ripapp.bll.Kinship;
import it.ripapp.ripapp.bll.Lang;
import lombok.*;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
