package it.ripapp.ripapp.entities;

import it.ripapp.ripapp.bll.TextBLL;
import it.ripapp.ripapp.jooqgen.enums.Lang;

public abstract class LocalizedEntity implements IEntity {
    public abstract void resolveTexts(TextBLL textBLL, Lang lang);

}
