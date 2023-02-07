/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.enums.Kinship;
import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.tables.NotificationKinshipText;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NotificationKinshipTextRecord extends UpdatableRecordImpl<NotificationKinshipTextRecord> implements Record3<Kinship, Lang, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.notification_kinship_text.kinship</code>.
     */
    public void setKinship(Kinship value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.notification_kinship_text.kinship</code>.
     */
    public Kinship getKinship() {
        return (Kinship) get(0);
    }

    /**
     * Setter for <code>public.notification_kinship_text.lang</code>.
     */
    public void setLang(Lang value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.notification_kinship_text.lang</code>.
     */
    public Lang getLang() {
        return (Lang) get(1);
    }

    /**
     * Setter for <code>public.notification_kinship_text.text</code>.
     */
    public void setText(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.notification_kinship_text.text</code>.
     */
    public String getText() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Kinship, Lang> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Kinship, Lang, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Kinship, Lang, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Kinship> field1() {
        return NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT.KINSHIP;
    }

    @Override
    public Field<Lang> field2() {
        return NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT.LANG;
    }

    @Override
    public Field<String> field3() {
        return NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT.TEXT;
    }

    @Override
    public Kinship component1() {
        return getKinship();
    }

    @Override
    public Lang component2() {
        return getLang();
    }

    @Override
    public String component3() {
        return getText();
    }

    @Override
    public Kinship value1() {
        return getKinship();
    }

    @Override
    public Lang value2() {
        return getLang();
    }

    @Override
    public String value3() {
        return getText();
    }

    @Override
    public NotificationKinshipTextRecord value1(Kinship value) {
        setKinship(value);
        return this;
    }

    @Override
    public NotificationKinshipTextRecord value2(Lang value) {
        setLang(value);
        return this;
    }

    @Override
    public NotificationKinshipTextRecord value3(String value) {
        setText(value);
        return this;
    }

    @Override
    public NotificationKinshipTextRecord values(Kinship value1, Lang value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NotificationKinshipTextRecord
     */
    public NotificationKinshipTextRecord() {
        super(NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT);
    }

    /**
     * Create a detached, initialised NotificationKinshipTextRecord
     */
    public NotificationKinshipTextRecord(Kinship kinship, Lang lang, String text) {
        super(NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT);

        setKinship(kinship);
        setLang(lang);
        setText(text);
    }
}
