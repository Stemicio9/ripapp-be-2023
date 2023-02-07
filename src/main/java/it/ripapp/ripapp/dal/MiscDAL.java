package it.ripapp.ripapp.dal;

import it.ripapp.ripapp.jooqgen.Tables;
import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import it.ripapp.ripapp.jooqgen.tables.records.AppversionsRecord;
import it.ripapp.ripapp.jooqgen.tables.records.ServerinfoRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MiscDAL extends AbstractDAL {

    public MiscDAL(DSLContext dsl) {
        super(dsl);
    }

    public Serverstatus getServerStatus() {
        return dsl.selectFrom(Tables.SERVERINFO).fetchOne(ServerinfoRecord::getStatus);
    }

    public Set<String> getSupportedAppVersions() {
        return dsl.selectFrom(Tables.APPVERSIONS).fetchSet(AppversionsRecord::getVersion);
    }

}
