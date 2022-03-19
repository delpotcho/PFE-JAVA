package org.sqli.authentification.exception;

public class ResponseMessage extends RuntimeException {

    public ResponseMessage(String message) {
        super(message);
    }

    public ResponseMessage(String message, Throwable cause) {
        super(message, cause);
    }
}
