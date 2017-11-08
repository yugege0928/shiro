package com.lanou.exception;

/**
 * Created by yugege on 17/11/7.
 */
public class CustomExcetion extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomExcetion(String message) {

        this.message = message;
    }
}
