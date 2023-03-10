/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.tables.records.DemiseRecord;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row20;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Demise extends TableImpl<DemiseRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.demise</code>
     */
    public static final Demise DEMISE = new Demise();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<DemiseRecord> getRecordType() {
        return DemiseRecord.class;
    }

    /**
     * The column <code>public.demise.demiseid</code>.
     */
    public final TableField<DemiseRecord, UUID> DEMISEID = createField(DSL.name("demiseid"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.demise.name</code>.
     */
    public final TableField<DemiseRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.demise.surname</code>.
     */
    public final TableField<DemiseRecord, String> SURNAME = createField(DSL.name("surname"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.demise.photourl</code>.
     */
    public final TableField<DemiseRecord, String> PHOTOURL = createField(DSL.name("photourl"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.date</code>.
     */
    public final TableField<DemiseRecord, LocalDate> DATE = createField(DSL.name("date"), SQLDataType.LOCALDATE, this, "");

    /**
     * The column <code>public.demise.obituary</code>.
     */
    public final TableField<DemiseRecord, String> OBITUARY = createField(DSL.name("obituary"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.wakets</code>.
     */
    public final TableField<DemiseRecord, LocalDateTime> WAKETS = createField(DSL.name("wakets"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.demise.wakeaddress</code>.
     */
    public final TableField<DemiseRecord, String> WAKEADDRESS = createField(DSL.name("wakeaddress"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.phonenumber</code>.
     */
    public final TableField<DemiseRecord, String> PHONENUMBER = createField(DSL.name("phonenumber"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.phoneprefix</code>.
     */
    public final TableField<DemiseRecord, String> PHONEPREFIX = createField(DSL.name("phoneprefix"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.phonehash</code>.
     */
    public final TableField<DemiseRecord, String> PHONEHASH = createField(DSL.name("phonehash"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.phoneprefixhash</code>.
     */
    public final TableField<DemiseRecord, String> PHONEPREFIXHASH = createField(DSL.name("phoneprefixhash"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.ts</code>.
     */
    public final TableField<DemiseRecord, LocalDateTime> TS = createField(DSL.name("ts"), SQLDataType.LOCALDATETIME(6).nullable(false).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.demise.funeralts</code>.
     */
    public final TableField<DemiseRecord, LocalDateTime> FUNERALTS = createField(DSL.name("funeralts"), SQLDataType.LOCALDATETIME(6), this, "");

    /**
     * The column <code>public.demise.funeraladdress</code>.
     */
    public final TableField<DemiseRecord, String> FUNERALADDRESS = createField(DSL.name("funeraladdress"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.age</code>.
     */
    public final TableField<DemiseRecord, Integer> AGE = createField(DSL.name("age"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.demise.cityname</code>.
     */
    public final TableField<DemiseRecord, String> CITYNAME = createField(DSL.name("cityname"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.wakenotes</code>.
     */
    public final TableField<DemiseRecord, String> WAKENOTES = createField(DSL.name("wakenotes"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.funeralnotes</code>.
     */
    public final TableField<DemiseRecord, String> FUNERALNOTES = createField(DSL.name("funeralnotes"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.demise.agencylogo</code>.
     */
    public final TableField<DemiseRecord, String> AGENCYLOGO = createField(DSL.name("agencylogo"), SQLDataType.VARCHAR, this, "");

    private Demise(Name alias, Table<DemiseRecord> aliased) {
        this(alias, aliased, null);
    }

    private Demise(Name alias, Table<DemiseRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.demise</code> table reference
     */
    public Demise(String alias) {
        this(DSL.name(alias), DEMISE);
    }

    /**
     * Create an aliased <code>public.demise</code> table reference
     */
    public Demise(Name alias) {
        this(alias, DEMISE);
    }

    /**
     * Create a <code>public.demise</code> table reference
     */
    public Demise() {
        this(DSL.name("demise"), null);
    }

    public <O extends Record> Demise(Table<O> child, ForeignKey<O, DemiseRecord> key) {
        super(child, key, DEMISE);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<DemiseRecord> getPrimaryKey() {
        return Keys.DEMISE_PKEY;
    }

    @Override
    public List<UniqueKey<DemiseRecord>> getKeys() {
        return Arrays.<UniqueKey<DemiseRecord>>asList(Keys.DEMISE_PKEY);
    }

    @Override
    public Demise as(String alias) {
        return new Demise(DSL.name(alias), this);
    }

    @Override
    public Demise as(Name alias) {
        return new Demise(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Demise rename(String name) {
        return new Demise(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Demise rename(Name name) {
        return new Demise(name, null);
    }

    // -------------------------------------------------------------------------
    // Row20 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row20<UUID, String, String, String, LocalDate, String, LocalDateTime, String, String, String, String, String, LocalDateTime, LocalDateTime, String, Integer, String, String, String, String> fieldsRow() {
        return (Row20) super.fieldsRow();
    }
}
