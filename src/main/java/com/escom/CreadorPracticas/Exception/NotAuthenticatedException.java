package com.escom.CreadorPracticas.Exception;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException() {
        super("User cannot be authenticated");
    }
}
