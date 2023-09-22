package com.escom.Creadordecasos.Exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super("The user has not found");
    }
}
