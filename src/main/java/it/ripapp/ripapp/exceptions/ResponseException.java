package it.ripapp.ripapp.exceptions;

import org.springframework.http.HttpStatus;

public class ResponseException extends Exception {

    private HttpStatus status;

    protected ResponseException(String message, HttpStatus status) {
        super(message, null, false, false);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
