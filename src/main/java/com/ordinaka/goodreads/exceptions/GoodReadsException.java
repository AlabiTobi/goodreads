package com.ordinaka.goodreads.exceptions;

public class GoodReadsException extends Exception{
    private int statusCode;
    public GoodReadsException(String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public int getStatusCode(){
        return statusCode;
    }

}
