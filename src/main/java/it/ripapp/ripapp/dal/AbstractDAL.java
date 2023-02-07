package it.ripapp.ripapp.dal;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class AbstractDAL {

    protected final DSLContext dsl;

    @Autowired
    public AbstractDAL(DSLContext dsl){
        this.dsl = dsl;
    }

}
