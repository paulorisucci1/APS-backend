package com.notflix.demo.exceptions;

public class EntityAlreadyExistException extends RuntimeException {

    public EntityAlreadyExistException() {
        super();
    }

    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
