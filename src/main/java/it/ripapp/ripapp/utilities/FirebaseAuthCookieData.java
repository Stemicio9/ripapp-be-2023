package it.ripapp.ripapp.utilities;

import java.util.UUID;

public class FirebaseAuthCookieData {
    private String cookie;
    private String accountid;

    public FirebaseAuthCookieData() {}

    public FirebaseAuthCookieData(String accountid, String cookie) {
        this.cookie = cookie;
        this.accountid = accountid;
    }

    public String getCookie() {
        return cookie;
    }

    public String getAccountid() {
        return accountid;
    }
}
