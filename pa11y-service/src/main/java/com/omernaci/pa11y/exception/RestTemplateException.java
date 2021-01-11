package com.omernaci.pa11y.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestTemplateException extends RuntimeException {

    private HttpStatus statusCode;

    private String error;

    public RestTemplateException(HttpStatus statusCode, String error) {
        this.statusCode = statusCode;
        this.error = error;
    }

}
