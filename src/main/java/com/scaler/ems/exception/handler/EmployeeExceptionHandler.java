package com.scaler.ems.exception.handler;

import com.scaler.ems.exception.EmployeeNotFoundException;
import com.scaler.ems.exception.responsemodel.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleEmployeeNotFound(
            EmployeeNotFoundException exception, final HttpServletRequest request) {
        return ExceptionResponse.builder().errorCode("E-5454")
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI()).build();
    }
}
