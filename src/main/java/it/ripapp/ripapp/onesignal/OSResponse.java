package it.ripapp.ripapp.onesignal;
import java.util.UUID;

public class OSResponse {
    private UUID id;
    private long recipients;
    private String external_id;
    private OSResponseErrors errors;

    public UUID getId() {
        return id;
    }

    public OSResponse setId(UUID id) {
        this.id = id;
        return this;
    }

    public long getRecipients() {
        return recipients;
    }

    public OSResponse setRecipients(long recipients) {
        this.recipients = recipients;
        return this;
    }

    public String getExternal_id() {
        return external_id;
    }

    public OSResponse setExternal_id(String external_id) {
        this.external_id = external_id;
        return this;
    }

    public OSResponseErrors getErrors() {
        return errors;
    }

    public OSResponse setErrors(OSResponseErrors errors) {
        this.errors = errors;
        return this;
    }
}


