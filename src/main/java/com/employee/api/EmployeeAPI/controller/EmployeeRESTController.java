package com.employee.api.EmployeeAPI.controller;

import com.employee.api.EmployeeAPI.error.CustomError;
import com.employee.api.EmployeeAPI.manager.EmployeeManager;
import com.employee.api.EmployeeAPI.model.dto.EmployeeDTO;
import com.employee.api.EmployeeAPI.projection.JobsSummary;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/employees")
public class EmployeeRESTController {

    @Autowired
    EmployeeManager employeeManager;

    // -------------- CREATE EMPLOYEES --------------

    @PostMapping
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employee) {

        boolean isCreated = employeeManager.addEmployee(employee);

        if(isCreated) {
            return new ResponseEntity("Employee created successfully. ", HttpStatus.CREATED);
        }

        return new ResponseEntity(new CustomError("Unable to create, employee with email " +
                employee.getEmail() + " already exist."), HttpStatus.CONFLICT);
    }

    // -------- UPDATE EMPLOYEES ----------------

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDTO employee) {

        boolean hasUpdated = employeeManager.updateEmployee(employee, id);

        if (hasUpdated) {
            return new ResponseEntity("Employee updated successfully. ", HttpStatus.OK);
        }

        return new ResponseEntity(new CustomError("Unable to update. Employee with id " + id + " not found."),
                HttpStatus.NOT_FOUND);
    }

    // ------------- DELETE EMPLOYEES ----------------

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@Valid @PathVariable Long id) {
        boolean hasDeleted = employeeManager.deleteEmployee(id);

        if(hasDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity(new CustomError("Unable to delete, employee with id " + id + " does not exist."), HttpStatus.NOT_FOUND);
    }

    // ---------- RETRIEVE EMPLOYEES ----------------

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = employeeManager.getEmployees();

        if(employees.isEmpty()) {
            return new ResponseEntity(new CustomError("No employees in database. "), HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/name/{firstname}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesFilteredByName(
            @PathVariable(value = "firstname") String firstName)
             {
        List<EmployeeDTO> employees = employeeManager.filterByFirstName(firstName);

        if(employees.isEmpty()) {
            return new ResponseEntity(new CustomError("No employees with given first name found. "), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/lastname/{lastname}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByLastName(
            @PathVariable(value = "lastname") String lastName) {
        List<EmployeeDTO> employees = employeeManager.filterByLastName(lastName);

        if(employees.isEmpty()) {
            return new ResponseEntity(new CustomError("No employees with given last name found. "), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{email}")
    public ResponseEntity<EmployeeDTO> getEmployeeByEmail(@PathVariable String email) {
        EmployeeDTO employee = employeeManager.searchByEmail(email);

        if(employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        }
        return new ResponseEntity(new CustomError("Employee with email " + email + " not found."),
                HttpStatus.NOT_FOUND);
    }

    // -------- RETRIEVE JOBS -------------

    @GetMapping("/jobs")
    public ResponseEntity<List<JobsSummary>> getJobs() {
        List<JobsSummary> jobs = employeeManager.getJobsWithEmployeesNo();

        if(jobs.isEmpty()) {
            return new ResponseEntity(new CustomError("No employees nor jobs in database. "),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
}
