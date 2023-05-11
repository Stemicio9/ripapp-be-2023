package it.ripapp.ripapp.entityUpdate.compositeKeys;

import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;

import java.io.Serializable;

public class KinshipTextKey implements Serializable {
    private Kinship KINSHIP;
    private Lang LANG;
}