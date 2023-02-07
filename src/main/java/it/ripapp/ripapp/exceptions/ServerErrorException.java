package it.ripapp.ripapp.exceptions;

import org.springframework.http.HttpStatus;

public class ServerErrorException extends ResponseException {

    public ServerErrorException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ServerErrorException() {
        super("server_error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
