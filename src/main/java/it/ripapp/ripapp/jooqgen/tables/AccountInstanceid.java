/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.tables.records.AccountInstanceidRecord;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row2;
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
public class AccountInstanceid extends TableImpl<AccountInstanceidRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.account_instanceid</code>
     */
    public static final AccountInstanceid ACCOUNT_INSTANCEID = new AccountInstanceid();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AccountInstanceidRecord> getRecordType() {
        return AccountInstanceidRecord.class;
    }

    /**
     * The column <code>public.account_instanceid.accountid</code>.
     */
    public final TableField<AccountInstanceidRecord, UUID> ACCOUNTID = createField(DSL.name("accountid"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.account_instanceid.instanceid</code>.
     */
    public final TableField<AccountInstanceidRecord, String> INSTANCEID = createField(DSL.name("instanceid"), SQLDataType.VARCHAR.nullable(false), this, "");

    private AccountInstanceid(Name alias, Table<AccountInstanceidRecord> aliased) {
        this(alias, aliased, null);
    }

    private AccountInstanceid(Name alias, Table<AccountInstanceidRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.account_instanceid</code> table reference
     */
    public AccountInstanceid(String alias) {
        this(DSL.name(alias), ACCOUNT_INSTANCEID);
    }

    /**
     * Create an aliased <code>public.account_instanceid</code> table reference
     */
    public AccountInstanceid(Name alias) {
        this(alias, ACCOUNT_INSTANCEID);
    }

    /**
     * Create a <code>public.account_instanceid</code> table reference
     */
    public AccountInstanceid() {
        this(DSL.name("account_instanceid"), null);
    }

    public <O extends Record> AccountInstanceid(Table<O> child, ForeignKey<O, AccountInstanceidRecord> key) {
        super(child, key, ACCOUNT_INSTANCEID);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<AccountInstanceidRecord> getPrimaryKey() {
        return Keys.ACCOUNT_INSTANCEID_PKEY;
    }

    @Override
    public List<UniqueKey<AccountInstanceidRecord>> getKeys() {
        return Arrays.<UniqueKey<AccountInstanceidRecord>>asList(Keys.ACCOUNT_INSTANCEID_PKEY);
    }

    @Override
    public List<ForeignKey<AccountInstanceidRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AccountInstanceidRecord, ?>>asList(Keys.ACCOUNT_INSTANCEID__ACCOUNT_INSTANCEID_ACCOUNTID_FKEY);
    }

    private transient Account _account;

    public Account account() {
        if (_account == null)
            _account = new Account(this, Keys.ACCOUNT_INSTANCEID__ACCOUNT_INSTANCEID_ACCOUNTID_FKEY);

        return _account;
    }

    @Override
    public AccountInstanceid as(String alias) {
        return new AccountInstanceid(DSL.name(alias), this);
    }

    @Override
    public AccountInstanceid as(Name alias) {
        return new AccountInstanceid(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountInstanceid rename(String name) {
        return new AccountInstanceid(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public AccountInstanceid rename(Name name) {
        return new AccountInstanceid(name, null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }
}
