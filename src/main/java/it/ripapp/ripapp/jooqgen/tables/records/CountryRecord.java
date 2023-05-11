/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.tables.Country;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CountryRecord extends UpdatableRecordImpl<CountryRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.country.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.country.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.country.phonecode</code>.
     */
    public void setPhonecode(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.country.phonecode</code>.
     */
    public Integer getPhonecode() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Country.COUNTRY.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Country.COUNTRY.PHONECODE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getPhonecode();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getPhonecode();
    }

    @Override
    public CountryRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CountryRecord value2(Integer value) {
        setPhonecode(value);
        return this;
    }

    @Override
    public CountryRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CountryRecord
     */
    public CountryRecord() {
        super(Country.COUNTRY);
    }

    /**
     * Create a detached, initialised CountryRecord
     */
    public CountryRecord(Integer id, Integer phonecode) {
        super(Country.COUNTRY);

        setId(id);
        setPhonecode(phonecode);
    }
}