/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.tables.Account;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record11;
import org.jooq.Row11;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record11<UUID, String, String, String, String, String, String, Boolean, Boolean, Lang, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.account.accountid</code>.
     */
    public void setAccountid(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.account.accountid</code>.
     */
    public UUID getAccountid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.account.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.account.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.account.surname</code>.
     */
    public void setSurname(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.account.surname</code>.
     */
    public String getSurname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.account.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.account.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.account.phone</code>.
     */
    public void setPhone(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.account.phone</code>.
     */
    public String getPhone() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.account.phonehashed</code>.
     */
    public void setPhonehashed(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.account.phonehashed</code>.
     */
    public String getPhonehashed() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.account.prefix</code>.
     */
    public void setPrefix(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.account.prefix</code>.
     */
    public String getPrefix() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.account.notif</code>.
     */
    public void setNotif(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.account.notif</code>.
     */
    public Boolean getNotif() {
        return (Boolean) get(7);
    }

    /**
     * Setter for <code>public.account.enabled</code>.
     */
    public void setEnabled(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.account.enabled</code>.
     */
    public Boolean getEnabled() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>public.account.lang</code>.
     */
    public void setLang(Lang value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.account.lang</code>.
     */
    public Lang getLang() {
        return (Lang) get(9);
    }

    /**
     * Setter for <code>public.account.photourl</code>.
     */
    public void setPhotourl(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>public.account.photourl</code>.
     */
    public String getPhotourl() {
        return (String) get(10);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record11 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row11<UUID, String, String, String, String, String, String, Boolean, Boolean, Lang, String> fieldsRow() {
        return (Row11) super.fieldsRow();
    }

    @Override
    public Row11<UUID, String, String, String, String, String, String, Boolean, Boolean, Lang, String> valuesRow() {
        return (Row11) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Account.ACCOUNT.ACCOUNTID;
    }

    @Override
    public Field<String> field2() {
        return Account.ACCOUNT.NAME;
    }

    @Override
    public Field<String> field3() {
        return Account.ACCOUNT.SURNAME;
    }

    @Override
    public Field<String> field4() {
        return Account.ACCOUNT.EMAIL;
    }

    @Override
    public Field<String> field5() {
        return Account.ACCOUNT.PHONE;
    }

    @Override
    public Field<String> field6() {
        return Account.ACCOUNT.PHONEHASHED;
    }

    @Override
    public Field<String> field7() {
        return Account.ACCOUNT.PREFIX;
    }

    @Override
    public Field<Boolean> field8() {
        return Account.ACCOUNT.NOTIF;
    }

    @Override
    public Field<Boolean> field9() {
        return Account.ACCOUNT.ENABLED;
    }

    @Override
    public Field<Lang> field10() {
        return Account.ACCOUNT.LANG;
    }

    @Override
    public Field<String> field11() {
        return Account.ACCOUNT.PHOTOURL;
    }

    @Override
    public UUID component1() {
        return getAccountid();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getSurname();
    }

    @Override
    public String component4() {
        return getEmail();
    }

    @Override
    public String component5() {
        return getPhone();
    }

    @Override
    public String component6() {
        return getPhonehashed();
    }

    @Override
    public String component7() {
        return getPrefix();
    }

    @Override
    public Boolean component8() {
        return getNotif();
    }

    @Override
    public Boolean component9() {
        return getEnabled();
    }

    @Override
    public Lang component10() {
        return getLang();
    }

    @Override
    public String component11() {
        return getPhotourl();
    }

    @Override
    public UUID value1() {
        return getAccountid();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getSurname();
    }

    @Override
    public String value4() {
        return getEmail();
    }

    @Override
    public String value5() {
        return getPhone();
    }

    @Override
    public String value6() {
        return getPhonehashed();
    }

    @Override
    public String value7() {
        return getPrefix();
    }

    @Override
    public Boolean value8() {
        return getNotif();
    }

    @Override
    public Boolean value9() {
        return getEnabled();
    }

    @Override
    public Lang value10() {
        return getLang();
    }

    @Override
    public String value11() {
        return getPhotourl();
    }

    @Override
    public AccountRecord value1(UUID value) {
        setAccountid(value);
        return this;
    }

    @Override
    public AccountRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AccountRecord value3(String value) {
        setSurname(value);
        return this;
    }

    @Override
    public AccountRecord value4(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public AccountRecord value5(String value) {
        setPhone(value);
        return this;
    }

    @Override
    public AccountRecord value6(String value) {
        setPhonehashed(value);
        return this;
    }

    @Override
    public AccountRecord value7(String value) {
        setPrefix(value);
        return this;
    }

    @Override
    public AccountRecord value8(Boolean value) {
        setNotif(value);
        return this;
    }

    @Override
    public AccountRecord value9(Boolean value) {
        setEnabled(value);
        return this;
    }

    @Override
    public AccountRecord value10(Lang value) {
        setLang(value);
        return this;
    }

    @Override
    public AccountRecord value11(String value) {
        setPhotourl(value);
        return this;
    }

    @Override
    public AccountRecord values(UUID value1, String value2, String value3, String value4, String value5, String value6, String value7, Boolean value8, Boolean value9, Lang value10, String value11) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(UUID accountid, String name, String surname, String email, String phone, String phonehashed, String prefix, Boolean notif, Boolean enabled, Lang lang, String photourl) {
        super(Account.ACCOUNT);

        setAccountid(accountid);
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPhone(phone);
        setPhonehashed(phonehashed);
        setPrefix(prefix);
        setNotif(notif);
        setEnabled(enabled);
        setLang(lang);
        setPhotourl(photourl);
    }
}
