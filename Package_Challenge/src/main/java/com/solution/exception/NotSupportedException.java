package com.solution.exception;
/**
 * Excetion class is used when input file type is not supported by reader/service.
 * @author Sandeep Agrawal
 */
public class NotSupportedException extends Exception {

    public NotSupportedException(String msg) {
        super(String.format("Requested file type not supported - '%s'  ", msg));
    }
}
