/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.enums.Lang;
import it.ripapp.ripapp.jooqgen.tables.NotificationText;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class NotificationTextRecord extends UpdatableRecordImpl<NotificationTextRecord> implements Record2<Lang, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.notification_text.lang</code>.
     */
    public void setLang(Lang value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.notification_text.lang</code>.
     */
    public Lang getLang() {
        return (Lang) get(0);
    }

    /**
     * Setter for <code>public.notification_text.text</code>.
     */
    public void setText(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.notification_text.text</code>.
     */
    public String getText() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Lang> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Lang, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Lang, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Lang> field1() {
        return NotificationText.NOTIFICATION_TEXT.LANG;
    }

    @Override
    public Field<String> field2() {
        return NotificationText.NOTIFICATION_TEXT.TEXT;
    }

    @Override
    public Lang component1() {
        return getLang();
    }

    @Override
    public String component2() {
        return getText();
    }

    @Override
    public Lang value1() {
        return getLang();
    }

    @Override
    public String value2() {
        return getText();
    }

    @Override
    public NotificationTextRecord value1(Lang value) {
        setLang(value);
        return this;
    }

    @Override
    public NotificationTextRecord value2(String value) {
        setText(value);
        return this;
    }

    @Override
    public NotificationTextRecord values(Lang value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NotificationTextRecord
     */
    public NotificationTextRecord() {
        super(NotificationText.NOTIFICATION_TEXT);
    }

    /**
     * Create a detached, initialised NotificationTextRecord
     */
    public NotificationTextRecord(Lang lang, String text) {
        super(NotificationText.NOTIFICATION_TEXT);

        setLang(lang);
        setText(text);
    }
}
