package it.ripapp.ripapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.ripapp.ripapp.jooqgen.enums.Demisematchtype;
import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DemiseMatchEntity {
    private UUID accountid;
    private UUID demiseid;
    private UUID cityid;
    private String name;
    private Kinship kinship;
    private Demisematchtype type;
    private Boolean notify;
    private LocalDateTime ts;

    @JsonIgnore
    private Lang matchLang;

}
