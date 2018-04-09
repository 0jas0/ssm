package com.jas.web.exception;

public class RecordNotExistException extends UserException {

    public RecordNotExistException() {
    }

    public RecordNotExistException(String msg) {
        super(msg);
    }
}
