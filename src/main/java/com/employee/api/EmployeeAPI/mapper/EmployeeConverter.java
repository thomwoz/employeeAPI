package com.employee.api.EmployeeAPI.mapper;

import com.employee.api.EmployeeAPI.model.dto.EmployeeDTO;
import com.employee.api.EmployeeAPI.model.entity.EmployeeVO;

public abstract class EmployeeConverter {

    public static EmployeeDTO convertEmployeeVOtoDTO(EmployeeVO employeeVO) {

        EmployeeDTO employeeDTO = new EmployeeDTO();

        if(employeeVO != null) {
            employeeDTO.setId(employeeVO.getId());
            employeeDTO.setFirstName(employeeVO.getFirstName());
            employeeDTO.setLastName(employeeVO.getLastName());
            employeeDTO.setEmail(employeeVO.getEmail());
            employeeDTO.setJob(employeeVO.getJob());
        }
        return employeeDTO;
    }

    public static EmployeeVO convertEmployeeDTOtoVO(EmployeeDTO employeeDTO) {

        EmployeeVO employeeVO = new EmployeeVO();

        if(employeeDTO != null) {
            employeeVO.setFirstName(employeeDTO.getFirstName());
            employeeVO.setLastName(employeeDTO.getLastName());
            employeeVO.setEmail(employeeDTO.getEmail());
            employeeVO.setJob(employeeDTO.getJob());
        }

        return employeeVO;
    }
}
