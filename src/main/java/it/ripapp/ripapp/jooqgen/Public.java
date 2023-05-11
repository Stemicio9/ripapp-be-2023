/*
 * This file is generated by jOOQ.
 */
package it.ripapp.ripapp.jooqgen;


import it.ripapp.ripapp.jooqgen.tables.Account;
import it.ripapp.ripapp.jooqgen.tables.AccountCity;
import it.ripapp.ripapp.jooqgen.tables.AccountInstanceid;
import it.ripapp.ripapp.jooqgen.tables.Admin;
import it.ripapp.ripapp.jooqgen.tables.Agency;
import it.ripapp.ripapp.jooqgen.tables.AgencyDemise;
import it.ripapp.ripapp.jooqgen.tables.AgencyOperator;
import it.ripapp.ripapp.jooqgen.tables.Appversions;
import it.ripapp.ripapp.jooqgen.tables.City;
import it.ripapp.ripapp.jooqgen.tables.Country;
import it.ripapp.ripapp.jooqgen.tables.Demise;
import it.ripapp.ripapp.jooqgen.tables.DemiseCity;
import it.ripapp.ripapp.jooqgen.tables.DemiseMatch;
import it.ripapp.ripapp.jooqgen.tables.DemiseRelative;
import it.ripapp.ripapp.jooqgen.tables.EndpointLogging;
import it.ripapp.ripapp.jooqgen.tables.KinshipText;
import it.ripapp.ripapp.jooqgen.tables.NotificationKinshipText;
import it.ripapp.ripapp.jooqgen.tables.NotificationText;
import it.ripapp.ripapp.jooqgen.tables.Phonebook;
import it.ripapp.ripapp.jooqgen.tables.Product;
import it.ripapp.ripapp.jooqgen.tables.Serverinfo;
import it.ripapp.ripapp.jooqgen.tables.Telegram;

import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.account</code>.
     */
    public final Account ACCOUNT = Account.ACCOUNT;

    /**
     * The table <code>public.account_city</code>.
     */
    public final AccountCity ACCOUNT_CITY = AccountCity.ACCOUNT_CITY;

    /**
     * The table <code>public.account_instanceid</code>.
     */
    public final AccountInstanceid ACCOUNT_INSTANCEID = AccountInstanceid.ACCOUNT_INSTANCEID;

    /**
     * The table <code>public.admin</code>.
     */
    public final Admin ADMIN = Admin.ADMIN;

    /**
     * The table <code>public.agency</code>.
     */
    public final Agency AGENCY = Agency.AGENCY;

    /**
     * The table <code>public.agency_demise</code>.
     */
    public final AgencyDemise AGENCY_DEMISE = AgencyDemise.AGENCY_DEMISE;

    /**
     * The table <code>public.agency_operator</code>.
     */
    public final AgencyOperator AGENCY_OPERATOR = AgencyOperator.AGENCY_OPERATOR;

    /**
     * The table <code>public.agency_product</code>.
     */
    public final AgencyProduct AGENCY_PRODUCT = AgencyProduct.AGENCY_PRODUCT;

    /**
     * The table <code>public.appversions</code>.
     */
    public final Appversions APPVERSIONS = Appversions.APPVERSIONS;

    /**
     * The table <code>public.city</code>.
     */
    public final City CITY = City.CITY;

    /**
     * The table <code>public.counter_set</code>.
     */
    public final CounterSet COUNTER_SET = CounterSet.COUNTER_SET;

    /**
     * The table <code>public.country</code>.
     */
    public final Country COUNTRY = Country.COUNTRY;

    /**
     * The table <code>public.demise</code>.
     */
    public final Demise DEMISE = Demise.DEMISE;

    /**
     * The table <code>public.demise_city</code>.
     */
    public final DemiseCity DEMISE_CITY = DemiseCity.DEMISE_CITY;

    /**
     * The table <code>public.demise_match</code>.
     */
    public final DemiseMatch DEMISE_MATCH = DemiseMatch.DEMISE_MATCH;

    /**
     * The table <code>public.demise_read</code>.
     */
    public final DemiseRead DEMISE_READ = DemiseRead.DEMISE_READ;

    /**
     * The table <code>public.demise_relative</code>.
     */
    public final DemiseRelative DEMISE_RELATIVE = DemiseRelative.DEMISE_RELATIVE;

    /**
     * The table <code>public.endpoint_logging</code>.
     */
    public final EndpointLogging ENDPOINT_LOGGING = EndpointLogging.ENDPOINT_LOGGING;

    /**
     * The table <code>public.kinship_text</code>.
     */
    public final KinshipText KINSHIP_TEXT = KinshipText.KINSHIP_TEXT;

    /**
     * The table <code>public.notification_kinship_text</code>.
     */
    public final NotificationKinshipText NOTIFICATION_KINSHIP_TEXT = NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT;

    /**
     * The table <code>public.notification_text</code>.
     */
    public final NotificationText NOTIFICATION_TEXT = NotificationText.NOTIFICATION_TEXT;

    /**
     * The table <code>public.phonebook</code>.
     */
    public final Phonebook PHONEBOOK = Phonebook.PHONEBOOK;

    /**
     * The table <code>public.product</code>.
     */
    public final Product PRODUCT = Product.PRODUCT;

    /**
     * The table <code>public.serverinfo</code>.
     */
    public final Serverinfo SERVERINFO = Serverinfo.SERVERINFO;

    /**
     * The table <code>public.telegram</code>.
     */
    public final Telegram TELEGRAM = Telegram.TELEGRAM;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            Account.ACCOUNT,
            AccountCity.ACCOUNT_CITY,
            AccountInstanceid.ACCOUNT_INSTANCEID,
            Admin.ADMIN,
            Agency.AGENCY,
            AgencyDemise.AGENCY_DEMISE,
            AgencyOperator.AGENCY_OPERATOR,
            AgencyProduct.AGENCY_PRODUCT,
            Appversions.APPVERSIONS,
            City.CITY,
            CounterSet.COUNTER_SET,
            Country.COUNTRY,
            Demise.DEMISE,
            DemiseCity.DEMISE_CITY,
            DemiseMatch.DEMISE_MATCH,
            DemiseRead.DEMISE_READ,
            DemiseRelative.DEMISE_RELATIVE,
            EndpointLogging.ENDPOINT_LOGGING,
            KinshipText.KINSHIP_TEXT,
            NotificationKinshipText.NOTIFICATION_KINSHIP_TEXT,
            NotificationText.NOTIFICATION_TEXT,
            Phonebook.PHONEBOOK,
            Product.PRODUCT,
            Serverinfo.SERVERINFO,
            Telegram.TELEGRAM);
    }
}