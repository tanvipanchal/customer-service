package com.tanvipanchal.customerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Customer request is null or some fields are empty or null. All fields are mandatory.")
public class CustomerBadRequestException extends RuntimeException{
    public CustomerBadRequestException(Long id, String message) {
        super(message + " " + id);
    }
}
