package com.example.demo.controller.advice;

import com.example.demo.domain.dto.exception.ApiErrorException;
import com.example.demo.domain.dto.response.ApiError;
import com.example.demo.domain.dto.response.ApiErrorResponse;
import javax.persistence.EntityNotFoundException;
import javax.persistence.RollbackException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerErrorHandlerAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(ControllerErrorHandlerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse onContraintViolationException(ConstraintViolationException e) {
        LOG.warn("", e);
        ApiErrorResponse response = new ApiErrorResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            response.getErrors().add(
                    new ApiError(violation.getPropertyPath().toString() + ":" + violation.getInvalidValue(), violation.getMessage()));
        }
        return response;
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse onTransactionSystemException(TransactionSystemException e) {
        if(e.getCause() instanceof RollbackException && e.getCause().getCause() instanceof ConstraintViolationException) {
            return onContraintViolationException((ConstraintViolationException)e.getCause().getCause());
        } else {
            return onRuntimeException(e);
        }
    }


    @ExceptionHandler(ApiErrorException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiErrorResponse onApiErrorException(ApiErrorException e) {
        LOG.warn("", e);
        ApiErrorResponse response = new ApiErrorResponse();
        response.getErrors().add(new ApiError("", e.getMessage()));
        return response;
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiErrorResponse onEntityNotFoundException(EntityNotFoundException e) {
        LOG.error("", e);
        ApiErrorResponse response = new ApiErrorResponse();
        response.getErrors().add(new ApiError("NOT_FOUND", e.getMessage()));
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiErrorResponse onRuntimeException(RuntimeException e) {
        LOG.error("", e);
        ApiErrorResponse response = new ApiErrorResponse();
        response.getErrors().add(new ApiError("INTERNAL_ERROR", e.getMessage()));
        return response;
    }

}

