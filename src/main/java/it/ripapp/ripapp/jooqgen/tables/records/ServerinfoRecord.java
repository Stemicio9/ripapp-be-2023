/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import it.ripapp.ripapp.jooqgen.tables.Serverinfo;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Row1;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class ServerinfoRecord extends UpdatableRecordImpl<ServerinfoRecord> implements Record1<Serverstatus> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.serverinfo.status</code>.
     */
    public void setStatus(Serverstatus value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.serverinfo.status</code>.
     */
    public Serverstatus getStatus() {
        return (Serverstatus) get(0);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Serverstatus> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record1 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row1<Serverstatus> fieldsRow() {
        return (Row1) super.fieldsRow();
    }

    @Override
    public Row1<Serverstatus> valuesRow() {
        return (Row1) super.valuesRow();
    }

    @Override
    public Field<Serverstatus> field1() {
        return Serverinfo.SERVERINFO.STATUS;
    }

    @Override
    public Serverstatus component1() {
        return getStatus();
    }

    @Override
    public Serverstatus value1() {
        return getStatus();
    }

    @Override
    public ServerinfoRecord value1(Serverstatus value) {
        setStatus(value);
        return this;
    }

    @Override
    public ServerinfoRecord values(Serverstatus value1) {
        value1(value1);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ServerinfoRecord
     */
    public ServerinfoRecord() {
        super(Serverinfo.SERVERINFO);
    }

    /**
     * Create a detached, initialised ServerinfoRecord
     */
    public ServerinfoRecord(Serverstatus status) {
        super(Serverinfo.SERVERINFO);

        setStatus(status);
    }
}
