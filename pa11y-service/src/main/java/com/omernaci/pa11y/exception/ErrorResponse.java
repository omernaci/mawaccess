package com.omernaci.pa11y.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private String timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

    public ErrorResponse(String message) {
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.message = message;
    }

    public ErrorResponse(RestTemplateException exception, String path) {
        this.timestamp = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(LocalDateTime.now());
        this.status = exception.getStatusCode().value();
        this.error = exception.getStatusCode().getReasonPhrase();
        this.message = exception.getError();
        this.path = path;
    }

}