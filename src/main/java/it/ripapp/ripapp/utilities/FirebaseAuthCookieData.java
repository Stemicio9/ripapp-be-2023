package it.ripapp.ripapp.utilities;

import java.util.UUID;

public class FirebaseAuthCookieData {
    private String cookie;
    private UUID accountid;

    public FirebaseAuthCookieData() {}

    public FirebaseAuthCookieData(UUID accountid, String cookie) {
        this.cookie = cookie;
        this.accountid = accountid;
    }

    public String getCookie() {
        return cookie;
    }

    public UUID getAccountid() {
        return accountid;
    }
}
