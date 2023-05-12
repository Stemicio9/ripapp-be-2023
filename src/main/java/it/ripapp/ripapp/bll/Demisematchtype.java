package it.ripapp.ripapp.bll;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;

public enum Demisematchtype implements EnumType {

    contact("contact"),

    relative("relative"),

    city("city");

    private final String literal;

    private Demisematchtype(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }



    @Override
    public String getName() {
        return "demisematchtype";
    }

    @Override
    public String getLiteral() {
        return literal;
    }

    public int getType() {
        return ordinal();
    }
}


