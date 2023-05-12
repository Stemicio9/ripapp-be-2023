package it.ripapp.ripapp.utilities;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LogManager {

    private DSLContext dsl;

    @Autowired
    public LogManager(DSLContext dsl){
        this.dsl = dsl;
    }


    public void logEvent(LoggingEvent event){

        switch (event.getType()){
            case INSERT -> insertLoggingEvent(event);

            case UPDATE ->  updateLoggingEvent(event);

            case EXCEPTION ->  updateLoggingEventException(event);

        }
    }

    private void updateLoggingEventException(LoggingEvent event){

    }

    private void updateLoggingEvent(LoggingEvent event) {

    }

    private void insertLoggingEvent(LoggingEvent event){

    }

}
