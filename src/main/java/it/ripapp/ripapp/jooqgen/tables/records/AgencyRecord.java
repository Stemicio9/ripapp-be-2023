/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables.records;


import it.ripapp.ripapp.jooqgen.tables.Agency;

import java.util.UUID;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AgencyRecord extends UpdatableRecordImpl<AgencyRecord> implements Record5<UUID, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.agency.agencyid</code>.
     */
    public void setAgencyid(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.agency.agencyid</code>.
     */
    public UUID getAgencyid() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>public.agency.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.agency.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.agency.address</code>.
     */
    public void setAddress(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.agency.address</code>.
     */
    public String getAddress() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.agency.email</code>.
     */
    public void setEmail(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.agency.email</code>.
     */
    public String getEmail() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.agency.agencylogo</code>.
     */
    public void setAgencylogo(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.agency.agencylogo</code>.
     */
    public String getAgencylogo() {
        return (String) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<UUID, String, String, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<UUID, String, String, String, String> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Agency.AGENCY.AGENCYID;
    }

    @Override
    public Field<String> field2() {
        return Agency.AGENCY.NAME;
    }

    @Override
    public Field<String> field3() {
        return Agency.AGENCY.ADDRESS;
    }

    @Override
    public Field<String> field4() {
        return Agency.AGENCY.EMAIL;
    }

    @Override
    public Field<String> field5() {
        return Agency.AGENCY.AGENCYLOGO;
    }

    @Override
    public UUID component1() {
        return getAgencyid();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getAddress();
    }

    @Override
    public String component4() {
        return getEmail();
    }

    @Override
    public String component5() {
        return getAgencylogo();
    }

    @Override
    public UUID value1() {
        return getAgencyid();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getAddress();
    }

    @Override
    public String value4() {
        return getEmail();
    }

    @Override
    public String value5() {
        return getAgencylogo();
    }

    @Override
    public AgencyRecord value1(UUID value) {
        setAgencyid(value);
        return this;
    }

    @Override
    public AgencyRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public AgencyRecord value3(String value) {
        setAddress(value);
        return this;
    }

    @Override
    public AgencyRecord value4(String value) {
        setEmail(value);
        return this;
    }

    @Override
    public AgencyRecord value5(String value) {
        setAgencylogo(value);
        return this;
    }

    @Override
    public AgencyRecord values(UUID value1, String value2, String value3, String value4, String value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AgencyRecord
     */
    public AgencyRecord() {
        super(Agency.AGENCY);
    }

    /**
     * Create a detached, initialised AgencyRecord
     */
    public AgencyRecord(UUID agencyid, String name, String address, String email, String agencylogo) {
        super(Agency.AGENCY);

        setAgencyid(agencyid);
        setName(name);
        setAddress(address);
        setEmail(email);
        setAgencylogo(agencylogo);
    }
}
