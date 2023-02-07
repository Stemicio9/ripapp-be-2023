/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.tables.records.AccountRecord;

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
public class Account extends TableImpl<AccountRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.account</code>
     */
    public static final Account ACCOUNT = new Account();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountRecord> getRecordType() {
        return AccountRecord.class;
    }

    /**
     * The column <code>public.account.accountid</code>.
     */
    public final TableField<AccountRecord, UUID> ACCOUNTID = createField(DSL.name("accountid"), SQLDataType.UUID.nullable(false).defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.account.name</code>.
     */
    public final TableField<AccountRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.surname</code>.
     */
    public final TableField<AccountRecord, String> SURNAME = createField(DSL.name("surname"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.email</code>.
     */
    public final TableField<AccountRecord, String> EMAIL = createField(DSL.name("email"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.phone</code>.
     */
    public final TableField<AccountRecord, String> PHONE = createField(DSL.name("phone"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.phonehashed</code>.
     */
    public final TableField<AccountRecord, String> PHONEHASHED = createField(DSL.name("phonehashed"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.prefix</code>.
     */
    public final TableField<AccountRecord, String> PREFIX = createField(DSL.name("prefix"), SQLDataType.VARCHAR.nullable(false), this, "");

    /**
     * The column <code>public.account.notif</code>.
     */
    public final TableField<AccountRecord, Boolean> NOTIF = createField(DSL.name("notif"), SQLDataType.BOOLEAN.nullable(false).defaultValue(DSL.field("false", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.account.enabled</code>.
     */
    public final TableField<AccountRecord, Boolean> ENABLED = createField(DSL.name("enabled"), SQLDataType.BOOLEAN.defaultValue(DSL.field("true", SQLDataType.BOOLEAN)), this, "");

    /**
     * The column <code>public.account.lang</code>.
     */
    public final TableField<AccountRecord, Lang> LANG = createField(DSL.name("lang"), SQLDataType.VARCHAR.defaultValue(DSL.field("'it'::lang", SQLDataType.VARCHAR)).asEnumDataType(Lang.class), this, "");

    /**
     * The column <code>public.account.photourl</code>.
     */
    public final TableField<AccountRecord, String> PHOTOURL = createField(DSL.name("photourl"), SQLDataType.VARCHAR, this, "");

    private Account(Name alias, Table<AccountRecord> aliased) {
        this(alias, aliased, null);
    }

    private Account(Name alias, Table<AccountRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(String alias) {
        this(DSL.name(alias), ACCOUNT);
    }

    /**
     * Create an aliased <code>public.account</code> table reference
     */
    public Account(Name alias) {
        this(alias, ACCOUNT);
    }

    /**
     * Create a <code>public.account</code> table reference
     */
    public Account() {
        this(DSL.name("account"), null);
    }

    public <O extends Record> Account(Table<O> child, ForeignKey<O, AccountRecord> key) {
        super(child, key, ACCOUNT);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<AccountRecord> getPrimaryKey() {
        return Keys.ACCOUNT_PKEY;
    }

    @Override
    public List<UniqueKey<AccountRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountRecord>>asList(Keys.ACCOUNT_PKEY, Keys.ACCOUNT_EMAIL_KEY);
    }

    @Override
    public Account as(String alias) {
        return new Account(DSL.name(alias), this);
    }

    @Override
    public Account as(Name alias) {
        return new Account(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(String name) {
        return new Account(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Account rename(Name name) {
        return new Account(name, null);
    }

    // -------------------------------------------------------------------------
    // Row11 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row11<UUID, String, String, String, String, String, String, Boolean, Boolean, Lang, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }
}
