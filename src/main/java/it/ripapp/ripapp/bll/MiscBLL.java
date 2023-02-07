package it.ripapp.ripapp.bll;

import it.ripapp.ripapp.dal.MiscDAL;
import it.ripapp.ripapp.jooqgen.enums.Serverstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MiscBLL {

    private final MiscDAL miscDAL;

    @Autowired
    public MiscBLL(MiscDAL miscDAL) {
        this.miscDAL = miscDAL;
    }


    public Serverstatus getServerStatus() {
        return miscDAL.getServerStatus();
    }

    public Set<String> getSupportedAppVersions() {
        return miscDAL.getSupportedAppVersions();
    }
}
