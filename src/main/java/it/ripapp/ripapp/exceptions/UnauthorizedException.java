package it.ripapp.ripapp.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends ResponseException{

    public UnauthorizedException(String message){
        super(message, HttpStatus.BAD_REQUEST);
    }

}
