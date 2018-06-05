package com.employee.api.EmployeeAPI.exception.handler;

import com.employee.api.EmployeeAPI.error.EmployeeValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public EmployeeValidationError processValidationError(MethodArgumentNotValidException ex) {

        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return prepareEmployeeValidationError(fieldErrors);

    }

    private EmployeeValidationError prepareEmployeeValidationError(List<FieldError> fieldErrors) {
        EmployeeValidationError employeeValidationError = new EmployeeValidationError();

        for (FieldError fieldError: fieldErrors) {
            String errorMessage;

            if(fieldError.getField().equals("email")) {
                errorMessage = "Incorrect email.";
            }
            else {
                errorMessage = "Wrong characters or empty.";
            }

            employeeValidationError.addFieldError(fieldError.getField(), errorMessage);
        }

        return employeeValidationError;
    }
}
