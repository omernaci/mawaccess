package com.omernaci.achecker.exception;

import com.omernaci.achecker.dto.response.BaseApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RestTemplateException.class)
    ResponseEntity<BaseApiResponse> handleRestTemplateException(RestTemplateException exception) {
        return new ResponseEntity<>(new BaseApiResponse(false, exception.getMessage()), exception.getStatusCode());
    }

}

