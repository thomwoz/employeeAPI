package com.employee.api.EmployeeAPI.error;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EmployeeValidationError {

    private List<EmployeeFieldError> fieldErrors = new ArrayList<>();

    public void addFieldError(String field, String message) {
        EmployeeFieldError error = new EmployeeFieldError(field, message);
        fieldErrors.add(error);
    }

}
