package com.employee.api.EmployeeAPI.manager.impl;

import com.employee.api.EmployeeAPI.mapper.EmployeeConverter;
import com.employee.api.EmployeeAPI.model.dto.EmployeeDTO;
import com.employee.api.EmployeeAPI.model.entity.EmployeeVO;
import com.employee.api.EmployeeAPI.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeManagerImplTest {

    private static final String EMPLOYEE_EMAIL_EXISTING = "existing.employee@test.com";
    private static final String EMPLOYEE_EMAIL_NOT_EXISTING = "not.existing.employee@test.com";

    private static final String EMPLOYEE_NAME = "John";
    private static final String EMPLOYEE_LASTNAME = "Stravinsky";
    private static final String EMPLOYEE_JOB = "Secretary";

    private EmployeeDTO secretaryEmployeeExisting;
    private EmployeeDTO secretaryEmployeeNotExisting;

    private EmployeeVO secretaryEmployeeExistingVO;
    private EmployeeVO secretaryEmployeeNotExistingVO;

    @Spy
    @InjectMocks
    EmployeeManagerImpl employeeManager;

    @Mock
    EmployeeRepository employeeRepository;

    @Before
    public void prepareMocks() {
        secretaryEmployeeExisting = new EmployeeDTO();
        secretaryEmployeeExisting.setJob(EMPLOYEE_JOB);
        secretaryEmployeeExisting.setEmail(EMPLOYEE_EMAIL_EXISTING);
        secretaryEmployeeExisting.setFirstName(EMPLOYEE_NAME);
        secretaryEmployeeExisting.setLastName(EMPLOYEE_LASTNAME);

        secretaryEmployeeExistingVO = EmployeeConverter.convertEmployeeDTOtoVO(secretaryEmployeeExisting);

        secretaryEmployeeNotExisting = new EmployeeDTO();
        secretaryEmployeeNotExisting.setJob(EMPLOYEE_JOB);
        secretaryEmployeeNotExisting.setEmail(EMPLOYEE_EMAIL_NOT_EXISTING);
        secretaryEmployeeNotExisting.setFirstName(EMPLOYEE_NAME);
        secretaryEmployeeNotExisting.setLastName(EMPLOYEE_LASTNAME);

        secretaryEmployeeNotExistingVO = null;
    }


    @Test
    public void addEmployeeTestExistingEmployee() {
        Mockito.doReturn(secretaryEmployeeExistingVO).when(employeeRepository).findByEmail(EMPLOYEE_EMAIL_EXISTING);

        boolean result = employeeManager.addEmployee(secretaryEmployeeExisting);

        assertFalse(result);
    }

    @Test
    public void addEmployeeTestNotExistingEmployee() {
        Mockito.doReturn(secretaryEmployeeNotExistingVO).when(employeeRepository).findByEmail(EMPLOYEE_EMAIL_NOT_EXISTING);

        boolean result = employeeManager.addEmployee(secretaryEmployeeNotExisting);

        assertTrue(result);
    }

}
