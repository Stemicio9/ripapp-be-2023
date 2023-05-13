package it.ripapp.ripapp.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
@Slf4j
public class AbstractService {

    public <T> T executeAction(Callable<T> action){
        try {
            T result = action.call();
            // TODO here used redundant variable if we want to add something before return
            return result;
        } catch (Exception e) {
            log.error("Error executing action", e);
            return null;
        }
    }

}
