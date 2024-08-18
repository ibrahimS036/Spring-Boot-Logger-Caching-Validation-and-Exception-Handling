package com.security.exceptionhandler;

import org.springframework.http.HttpStatus;

public class ExceptionPayload {

   private String message;
   private Throwable cause;
   private HttpStatus status;

    public ExceptionPayload(String message, Throwable cause, HttpStatus status) {
        super();
        this.message = message;
        this.cause = cause;
        this.status = status;
    }

    public ExceptionPayload() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExceptionPayload{" +
                "message='" + message + '\'' +
                ", cause=" + cause +
                ", status=" + status +
                '}';
    }
}
