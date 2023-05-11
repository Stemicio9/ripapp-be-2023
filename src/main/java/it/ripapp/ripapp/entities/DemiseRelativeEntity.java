package it.ripapp.ripapp.entities;

import it.ripapp.ripapp.jooqgen.enums.Kinship;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemiseRelativeEntity implements IEntity {
    private UUID demiseid;
    private UUID accountid;
    private Kinship kinship;
    private String phone;
    private String prefix;
    private String name;
    private String phonehash;

}
