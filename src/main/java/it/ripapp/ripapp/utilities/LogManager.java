package it.ripapp.ripapp.utilities;

import it.ripapp.ripapp.jooqgen.tables.EndpointLogging;
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
        dsl.update(EndpointLogging.ENDPOINT_LOGGING)
                .set(EndpointLogging.ENDPOINT_LOGGING.EXCEPTIONSTACKTRACE, event.getStacktrace())
                .set(EndpointLogging.ENDPOINT_LOGGING.MESSAGE, event.getExMessage())
                .where(EndpointLogging.ENDPOINT_LOGGING.REQID.eq(event.getReqid()))
                .execute();
    }

    private void updateLoggingEvent(LoggingEvent event) {
        dsl.update(EndpointLogging.ENDPOINT_LOGGING)
                .set(EndpointLogging.ENDPOINT_LOGGING.STATUS, event.getStatus())
                .set(EndpointLogging.ENDPOINT_LOGGING.EXECUTIONTIME, event.getExecutiontime().intValue())
                .where(EndpointLogging.ENDPOINT_LOGGING.REQID.eq(event.getReqid()))
                .execute();
    }

    private void insertLoggingEvent(LoggingEvent event){
        dsl.insertInto(EndpointLogging.ENDPOINT_LOGGING)
                .columns(
                        EndpointLogging.ENDPOINT_LOGGING.ID,
                        EndpointLogging.ENDPOINT_LOGGING.REQID,
                        EndpointLogging.ENDPOINT_LOGGING.TIMESTAMP,
                        EndpointLogging.ENDPOINT_LOGGING.METHOD,
                        EndpointLogging.ENDPOINT_LOGGING.URL,
                        EndpointLogging.ENDPOINT_LOGGING.QUERYSTRING,
                        EndpointLogging.ENDPOINT_LOGGING.USERID
                )
                .values(
                        UUID.randomUUID(),
                        event.getReqid(),
                        event.getTimestamp(),
                        event.getMethod(),
                        event.getUrl(),
                        event.getQuerystring(),
                        event.getUserid()
                )
                .execute();
    }

}
