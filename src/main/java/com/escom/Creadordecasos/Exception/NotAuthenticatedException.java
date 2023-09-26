package com.escom.Creadordecasos.Exception;

public class NotAuthenticatedException extends RuntimeException{
    public NotAuthenticatedException() {
        super("User cannot be authenticated");
    }
}
