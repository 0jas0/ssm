package com.jas.web.exception;

public class UserException extends RuntimeException {

    public UserException() {
    }

    public UserException(String msg) {
        super(msg);
    }
}
