/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.tables.records.TelegramRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row11;
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
public class Telegram extends TableImpl<TelegramRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.telegram</code>
     */
    public static final Telegram TELEGRAM = new Telegram();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<TelegramRecord> getRecordType() {
        return TelegramRecord.class;
    }

    /**
     * The column <code>public.telegram.telegramid</code>.
     */
    public final TableField<TelegramRecord, UUID> TELEGRAMID = createField(DSL.name("telegramid"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.telegram.name</code>.
     */
    public final TableField<TelegramRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.surname</code>.
     */
    public final TableField<TelegramRecord, String> SURNAME = createField(DSL.name("surname"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.email</code>.
     */
    public final TableField<TelegramRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.receiver</code>.
     */
    public final TableField<TelegramRecord, String> RECEIVER = createField(DSL.name("receiver"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.defunct</code>.
     */
    public final TableField<TelegramRecord, String> DEFUNCT = createField(DSL.name("defunct"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.message</code>.
     */
    public final TableField<TelegramRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.telegram.userid</code>.
     */
    public final TableField<TelegramRecord, UUID> USERID = createField(DSL.name("userid"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.telegram.demiseid</code>.
     */
    public final TableField<TelegramRecord, UUID> DEMISEID = createField(DSL.name("demiseid"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.telegram.approvedpay</code>.
     */
    public final TableField<TelegramRecord, Boolean> APPROVEDPAY = createField(DSL.name("approvedpay"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.telegram.paymentid</code>.
     */
    public final TableField<TelegramRecord, String> PAYMENTID = createField(DSL.name("paymentid"), SQLDataType.VARCHAR, this, "");

    private Telegram(Name alias, Table<TelegramRecord> aliased) {
        this(alias, aliased, null);
    }

    private Telegram(Name alias, Table<TelegramRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.telegram</code> table reference
     */
    public Telegram(String alias) {
        this(DSL.name(alias), TELEGRAM);
    }

    /**
     * Create an aliased <code>public.telegram</code> table reference
     */
    public Telegram(Name alias) {
        this(alias, TELEGRAM);
    }

    /**
     * Create a <code>public.telegram</code> table reference
     */
    public Telegram() {
        this(DSL.name("telegram"), null);
    }

    public <O extends Record> Telegram(Table<O> child, ForeignKey<O, TelegramRecord> key) {
        super(child, key, TELEGRAM);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<TelegramRecord> getPrimaryKey() {
        return Keys.TABLE_NAME_PK;
    }

    @Override
    public List<UniqueKey<TelegramRecord>> getKeys() {
        return Arrays.<UniqueKey<TelegramRecord>>asList(Keys.TABLE_NAME_PK);
    }

    @Override
    public List<ForeignKey<TelegramRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<TelegramRecord, ?>>asList(Keys.TELEGRAM__TABLE_NAME_ACCOUNT_ACCOUNTID_FK, Keys.TELEGRAM__TABLE_NAME_DEMISE_DEMISEID_FK);
    }

    private transient Account _account;
    private transient Demise _demise;

    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.TELEGRAM__TABLE_NAME_ACCOUNT_ACCOUNTID_FK);

        return _account;
    }

    public Demise demise() {
        if (_demise == null)
            _demise = new Demise(this, Keys.TELEGRAM__TABLE_NAME_DEMISE_DEMISEID_FK);

        return _demise;
    }

    @Override
    public Telegram as(String alias) {
        return new Telegram(DSL.name(alias), this);
    }

    @Override
    public Telegram as(Name alias) {
        return new Telegram(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Telegram rename(String name) {
        return new Telegram(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Telegram rename(Name name) {
        return new Telegram(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<UUID, String, String, String, String, String, String, UUID, UUID, Boolean, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}
