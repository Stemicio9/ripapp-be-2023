package it.ripapp.ripapp.jooqgen.tables.compositeKeys;

import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;

import java.io.Serializable;

public class NotificationKinshipTextKey implements Serializable {
    private Kinship KINSHIP;
    private Lang LANG;
}