package com.employee.api.EmployeeAPI.manager;

import com.employee.api.EmployeeAPI.model.dto.EmployeeDTO;
import com.employee.api.EmployeeAPI.projection.JobsSummary;

import java.util.List;

public interface EmployeeManager {

    boolean addEmployee(EmployeeDTO employee);

    boolean deleteEmployee(Long id);

    boolean updateEmployee(EmployeeDTO employee, Long id);

    List<EmployeeDTO> getEmployees();

    List<EmployeeDTO> filterByFirstName(String name);

    List<EmployeeDTO> filterByLastName(String lastName);

    EmployeeDTO searchByEmail(String email);

    List<JobsSummary> getJobsWithEmployeesNo();
}
