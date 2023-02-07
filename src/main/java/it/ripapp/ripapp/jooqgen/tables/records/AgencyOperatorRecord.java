/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.tables.AgencyOperator;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AgencyOperatorRecord extends UpdatableRecordImpl<AgencyOperatorRecord> implements Record2<UUID, UUID> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.agency_operator.agencyid</code>.
     */
    public void setAgencyid(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.agency_operator.agencyid</code>.
     */
    public UUID getAgencyid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.agency_operator.accountid</code>.
     */
    public void setAccountid(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.agency_operator.accountid</code>.
     */
    public UUID getAccountid() {
        return (UUID) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<UUID, UUID> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<UUID, UUID> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<UUID, UUID> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return AgencyOperator.AGENCY_OPERATOR.AGENCYID;
    }

    @Override
    public Field<UUID> field2() {
        return AgencyOperator.AGENCY_OPERATOR.ACCOUNTID;
    }

    @Override
    public UUID component1() {
        return getAgencyid();
    }

    @Override
    public UUID component2() {
        return getAccountid();
    }

    @Override
    public UUID value1() {
        return getAgencyid();
    }

    @Override
    public UUID value2() {
        return getAccountid();
    }

    @Override
    public AgencyOperatorRecord value1(UUID value) {
        setAgencyid(value);
        return this;
    }

    @Override
    public AgencyOperatorRecord value2(UUID value) {
        setAccountid(value);
        return this;
    }

    @Override
    public AgencyOperatorRecord values(UUID value1, UUID value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AgencyOperatorRecord
     */
    public AgencyOperatorRecord() {
        super(AgencyOperator.AGENCY_OPERATOR);
    }

    /**
     * Create a detached, initialised AgencyOperatorRecord
     */
    public AgencyOperatorRecord(UUID agencyid, UUID accountid) {
        super(AgencyOperator.AGENCY_OPERATOR);

        setAgencyid(agencyid);
        setAccountid(accountid);
    }
}
