package it.ripapp.ripapp.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends ResponseException {

    public ForbiddenException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }

}
