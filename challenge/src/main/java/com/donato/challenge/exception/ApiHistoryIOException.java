package com.donato.challenge.exception;

import java.lang.reflect.InvocationTargetException;

public class ApiHistoryIOException extends InvocationTargetException {

    public ApiHistoryIOException(Throwable target, String s) {
        super(target, s);
    }
}
