package com.employee.api.EmployeeAPI.error;

import lombok.Data;

@Data
public class EmployeeFieldError {

    private String field;

    private String message;

    public EmployeeFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

}
