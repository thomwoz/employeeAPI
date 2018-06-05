package com.employee.api.EmployeeAPI.manager.impl;

import com.employee.api.EmployeeAPI.manager.EmployeeManager;
import com.employee.api.EmployeeAPI.mapper.EmployeeConverter;
import com.employee.api.EmployeeAPI.model.dto.EmployeeDTO;
import com.employee.api.EmployeeAPI.model.entity.EmployeeVO;
import com.employee.api.EmployeeAPI.projection.JobsSummary;
import com.employee.api.EmployeeAPI.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class EmployeeManagerImpl implements EmployeeManager {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean addEmployee(EmployeeDTO employeeDTO) {
        EmployeeVO employee = EmployeeConverter.convertEmployeeDTOtoVO(employeeDTO);
        if(employeeRepository.findByEmail(employee.getEmail()) != null) {
            log.info("Employee with given email: {} already exists", employee.getEmail());
            return false;
        }

        log.info("Employee added to the database successfully.");
        employeeRepository.save(employee);
        return true;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        if(employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            log.info("Employee with id {} deleted successfully.", id);
            return true;
        }

        log.info("Employee with id {} not found.", id);
        return false;
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        List<EmployeeVO> employeeVOS = employeeRepository.findAll();

        if(employeeVOS.isEmpty()) {
            log.info("No employees in database.");
        }

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(EmployeeVO employee: employeeVOS) {
            employeeDTOS.add(EmployeeConverter.convertEmployeeVOtoDTO(employee));
        }

        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> filterByFirstName(String name) {
        List<EmployeeVO> employeeVOS = employeeRepository.findByFirstName(name);

        if(employeeVOS.isEmpty()) {
            log.info("No employees with first name {} found", name);
        }

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(EmployeeVO employee: employeeVOS) {
            employeeDTOS.add(EmployeeConverter.convertEmployeeVOtoDTO(employee));
        }

        return employeeDTOS;
    }

    @Override
    public List<EmployeeDTO> filterByLastName(String lastName) {
        List<EmployeeVO> employeeVOS = employeeRepository.findByLastName(lastName);

        if(employeeVOS.isEmpty()) {
            log.info("No employees with last name {} found", lastName);
        }

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for(EmployeeVO employee: employeeVOS) {
            employeeDTOS.add(EmployeeConverter.convertEmployeeVOtoDTO(employee));
        }

        return employeeDTOS;
    }

    @Override
    public EmployeeDTO searchByEmail(String email) {
        EmployeeVO employeeVO = employeeRepository.findByEmail(email);
        if(employeeVO == null) {
            log.info("Employee with email {} not found", email);
            return null;
        }
        return EmployeeConverter.convertEmployeeVOtoDTO(employeeVO);
    }

    @Override
    public List<JobsSummary> getJobsWithEmployeesNo() {
        List<JobsSummary> jobs = employeeRepository.getJobsWithEmployeeNo();

        if(jobs.isEmpty()) {
            log.info("No employees/jobs in database.");
        }

        return jobs;
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employee, Long id) {

        Optional<EmployeeVO> employeeVO = employeeRepository.findById(id);
        if(employeeVO.isPresent()) {
            employeeVO.get().setEmail(employee.getEmail());
            employeeVO.get().setFirstName(employee.getFirstName());
            employeeVO.get().setLastName(employee.getLastName());
            employeeVO.get().setJob(employee.getJob());

            employeeRepository.save(employeeVO.get());

            log.info("Employee with id {} updated successfully.", id);
            return true;
        }

        log.info("Employee with id {} not found.", id);
        return false;
    }


}
