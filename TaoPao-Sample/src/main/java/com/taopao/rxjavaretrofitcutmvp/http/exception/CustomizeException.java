package com.taopao.rxjavaretrofitcutmvp.http.exception;


public class CustomizeException extends RuntimeException {

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
