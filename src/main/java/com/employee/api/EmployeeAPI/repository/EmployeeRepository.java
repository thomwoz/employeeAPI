package com.employee.api.EmployeeAPI.repository;


import com.employee.api.EmployeeAPI.model.entity.EmployeeVO;
import com.employee.api.EmployeeAPI.projection.JobsSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeVO, Long> {

    EmployeeVO save(EmployeeVO employee);

    void deleteById(Long id);

    Optional<EmployeeVO> findById(Long id);

    List<EmployeeVO> findAll();

    List<EmployeeVO> findByFirstName(String firstName);

    List<EmployeeVO> findByLastName(String lastName);

    EmployeeVO findByEmail(String email);

    @Query(value = "SELECT job, COUNT(id) as count FROM employees GROUP BY job", nativeQuery = true)
    List<JobsSummary> getJobsWithEmployeeNo();
}
