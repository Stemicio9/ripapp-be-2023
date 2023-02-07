package it.ripapp.ripapp.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ResponseException {

    public BadRequestException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }

}
