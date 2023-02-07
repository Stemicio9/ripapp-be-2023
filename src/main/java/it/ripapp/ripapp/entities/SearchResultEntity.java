package it.ripapp.ripapp.entities;

import java.util.UUID;

public class SearchResultEntity implements IEntity {

    private String result;
    private UUID resultid;

    public String getResult() {
        return result;
    }

    public SearchResultEntity setResult(String result) {
        this.result = result;
        return this;
    }

    public UUID getResultid() {
        return resultid;
    }

    public SearchResultEntity setResultid(UUID resultid) {
        this.resultid = resultid;
        return this;
    }
}
