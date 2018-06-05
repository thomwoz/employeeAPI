package com.employee.api.EmployeeAPI.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomError {
    private String errorMessage;
}
