package com.demo.miniapp.presentation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.Serializable;

@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ApiResponse<ErrorResponse> onError(RuntimeException ex) {
        return new ApiResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, new ErrorResponse(ex.getMessage()));
    }

    class ErrorResponse implements Serializable {

        private static final long serialVersionUID = 8306284665471000547L;

        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }
}
