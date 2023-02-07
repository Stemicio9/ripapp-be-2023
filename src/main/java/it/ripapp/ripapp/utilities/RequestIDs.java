package it.ripapp.ripapp.utilities;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;

@Component
public class RequestIDs {

    private HashMap<Long, UUID> thread_reqid;

    public RequestIDs() {
        this.thread_reqid = new HashMap<>();
    }


    public UUID addRequestID(Long threadID){
        //serve controllo che non ci sia già il thread id nella mappa?
        UUID reqid = UUID.randomUUID();
        thread_reqid.put(threadID, reqid);
        return reqid;
    }

    public UUID getReqID(Long threadID){
        return thread_reqid.getOrDefault(threadID, null);
    }

    public void removeReqID(Long threadID){
        thread_reqid.remove(threadID);
    }


}
