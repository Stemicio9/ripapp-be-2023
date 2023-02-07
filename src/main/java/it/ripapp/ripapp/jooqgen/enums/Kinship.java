/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.enums;


import it.ripapp.ripapp.jooqgen.Public;

import org.jooq.Catalog;
import org.jooq.EnumType;
import org.jooq.Schema;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public enum Kinship implements EnumType {

    son("son"),

    daughter("daughter"),

    nephew("nephew"),

    uncle("uncle"),

    aunt("aunt"),

    father("father"),

    mother("mother"),

    wife("wife"),

    brother("brother"),

    sister("sister"),

    grandmother("grandmother"),

    grandfather("grandfather"),

    husband("husband"),

    mother_in_law("mother_in_law"),

    father_in_law("father_in_law"),

    son_in_law("son_in_law"),

    daughter_in_law("daughter_in_law"),

    brother_in_law("brother_in_law"),

    sister_in_law("sister_in_law"),

    cousin_m("cousin_m"),

    cousin_f("cousin_f"),

    grandniece_m("grandniece_m"),

    grandniece_f("grandniece_f"),

    nephew_f("nephew_f");

    private final String literal;

    private Kinship(String literal) {
        this.literal = literal;
    }

    @Override
    public Catalog getCatalog() {
        return getSchema().getCatalog();
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public String getName() {
        return "kinship";
    }

    @Override
    public String getLiteral() {
        return literal;
    }
}
