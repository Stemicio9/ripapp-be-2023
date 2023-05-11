/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import it.ripapp.ripapp.jooqgen.tables.records.ServerinfoRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row1;
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
public class Serverinfo extends TableImpl<ServerinfoRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.serverinfo</code>
     */
    public static final Serverinfo SERVERINFO = new Serverinfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ServerinfoRecord> getRecordType() {
        return ServerinfoRecord.class;
    }

    /**
     * The column <code>public.serverinfo.status</code>.
     */
    public final TableField<ServerinfoRecord, Serverstatus> STATUS = createField(DSL.name("status"), SQLDataType.VARCHAR.nullable(false).asEnumDataType(it.ripapp.ripapp.jooqgen.enums.Serverstatus.class), this, "");

    private Serverinfo(Name alias, Table<ServerinfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Serverinfo(Name alias, Table<ServerinfoRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.serverinfo</code> table reference
     */
    public Serverinfo(String alias) {
        this(DSL.name(alias), SERVERINFO);
    }

    /**
     * Create an aliased <code>public.serverinfo</code> table reference
     */
    public Serverinfo(Name alias) {
        this(alias, SERVERINFO);
    }

    /**
     * Create a <code>public.serverinfo</code> table reference
     */
    public Serverinfo() {
        this(DSL.name("serverinfo"), null);
    }

    public <O extends Record> Serverinfo(Table<O> child, ForeignKey<O, ServerinfoRecord> key) {
        super(child, key, SERVERINFO);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<ServerinfoRecord> getPrimaryKey() {
        return Keys.SERVERINFO_PKEY;
    }

    @Override
    public List<UniqueKey<ServerinfoRecord>> getKeys() {
        return Arrays.<UniqueKey<ServerinfoRecord>>asList(Keys.SERVERINFO_PKEY);
    }

    @Override
    public Serverinfo as(String alias) {
        return new Serverinfo(DSL.name(alias), this);
    }

    @Override
    public Serverinfo as(Name alias) {
        return new Serverinfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Serverinfo rename(String name) {
        return new Serverinfo(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Serverinfo rename(Name name) {
        return new Serverinfo(name, null);
    }

    // -------------------------------------------------------------------------
    // Row1 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row1<Serverstatus> fieldsRow() {
        return (Row1) super.fieldsRow();
    }
}