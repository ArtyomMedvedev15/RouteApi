package com.route.api.controller.advice;

import com.route.api.dto.ErrorMessageResponse;
import com.route.api.exception.InvalidParameterException;
import com.route.api.exception.LimitRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class DistanceControllerAdvice {
    @ExceptionHandler(value = LimitRequestException.class)
    @ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
    public ErrorMessageResponse handleLimitRequestException(LimitRequestException ex, WebRequest request) {
        return ErrorMessageResponse.builder()
                .error_statusCode(HttpStatus.TOO_MANY_REQUESTS.value())
                .timestamp(new Date())
                .error_message(ex.getMessage())
                .error_description(request.getDescription(false)).build();
    }

    @ExceptionHandler(value = InvalidParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessageResponse handleInvalidParameterRequestException(InvalidParameterException ex, WebRequest request) {
        return ErrorMessageResponse.builder()
                .error_statusCode(HttpStatus.BAD_REQUEST.value())
                .timestamp(new Date())
                .error_message(ex.getMessage())
                .error_description(request.getDescription(false)).build();
    }
}
