/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen.tables;


import it.ripapp.ripapp.jooqgen.Keys;
import it.ripapp.ripapp.jooqgen.Public;
import it.ripapp.ripapp.jooqgen.tables.records.EndpointLoggingRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row13;
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
public class EndpointLogging extends TableImpl<EndpointLoggingRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.endpoint_logging</code>
     */
    public static final EndpointLogging ENDPOINT_LOGGING = new EndpointLogging();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<EndpointLoggingRecord> getRecordType() {
        return EndpointLoggingRecord.class;
    }

    /**
     * The column <code>public.endpoint_logging.id</code>.
     */
    public final TableField<EndpointLoggingRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.endpoint_logging.reqid</code>.
     */
    public final TableField<EndpointLoggingRecord, String> REQID = createField(DSL.name("reqid"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.timestamp</code>.
     */
    public final TableField<EndpointLoggingRecord, LocalDateTime> TIMESTAMP = createField(DSL.name("timestamp"), SQLDataType.LOCALDATETIME(6).defaultValue(DSL.field("now()", SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>public.endpoint_logging.method</code>.
     */
    public final TableField<EndpointLoggingRecord, String> METHOD = createField(DSL.name("method"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.url</code>.
     */
    public final TableField<EndpointLoggingRecord, String> URL = createField(DSL.name("url"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.querystring</code>.
     */
    public final TableField<EndpointLoggingRecord, String> QUERYSTRING = createField(DSL.name("querystring"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.userid</code>.
     */
    public final TableField<EndpointLoggingRecord, UUID> USERID = createField(DSL.name("userid"), SQLDataType.UUID, this, "");

    /**
     * The column <code>public.endpoint_logging.body</code>.
     */
    public final TableField<EndpointLoggingRecord, String> BODY = createField(DSL.name("body"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.status</code>.
     */
    public final TableField<EndpointLoggingRecord, Integer> STATUS = createField(DSL.name("status"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.endpoint_logging.executiontime</code>.
     */
    public final TableField<EndpointLoggingRecord, Integer> EXECUTIONTIME = createField(DSL.name("executiontime"), SQLDataType.INTEGER, this, "");

    /**
     * The column <code>public.endpoint_logging.exceptionstacktrace</code>.
     */
    public final TableField<EndpointLoggingRecord, String> EXCEPTIONSTACKTRACE = createField(DSL.name("exceptionstacktrace"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.message</code>.
     */
    public final TableField<EndpointLoggingRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.VARCHAR, this, "");

    /**
     * The column <code>public.endpoint_logging.response</code>.
     */
    public final TableField<EndpointLoggingRecord, String> RESPONSE = createField(DSL.name("response"), SQLDataType.VARCHAR, this, "");

    private EndpointLogging(Name alias, Table<EndpointLoggingRecord> aliased) {
        this(alias, aliased, null);
    }

    private EndpointLogging(Name alias, Table<EndpointLoggingRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.endpoint_logging</code> table reference
     */
    public EndpointLogging(String alias) {
        this(DSL.name(alias), ENDPOINT_LOGGING);
    }

    /**
     * Create an aliased <code>public.endpoint_logging</code> table reference
     */
    public EndpointLogging(Name alias) {
        this(alias, ENDPOINT_LOGGING);
    }

    /**
     * Create a <code>public.endpoint_logging</code> table reference
     */
    public EndpointLogging() {
        this(DSL.name("endpoint_logging"), null);
    }

    public <O extends Record> EndpointLogging(Table<O> child, ForeignKey<O, EndpointLoggingRecord> key) {
        super(child, key, ENDPOINT_LOGGING);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<EndpointLoggingRecord> getPrimaryKey() {
        return Keys.ENDPOINT_LOGGING_PKEY;
    }

    @Override
    public List<UniqueKey<EndpointLoggingRecord>> getKeys() {
        return Arrays.<UniqueKey<EndpointLoggingRecord>>asList(Keys.ENDPOINT_LOGGING_PKEY);
    }

    @Override
    public EndpointLogging as(String alias) {
        return new EndpointLogging(DSL.name(alias), this);
    }

    @Override
    public EndpointLogging as(Name alias) {
        return new EndpointLogging(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public EndpointLogging rename(String name) {
        return new EndpointLogging(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public EndpointLogging rename(Name name) {
        return new EndpointLogging(name, null);
    }

    // -------------------------------------------------------------------------
    // Row13 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row13<UUID, String, LocalDateTime, String, String, String, UUID, String, Integer, Integer, String, String, String> fieldsRow() {
        return (Row13) super.fieldsRow();
    }
}