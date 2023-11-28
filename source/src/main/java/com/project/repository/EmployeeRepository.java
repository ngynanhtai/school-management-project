package com.project.repository;

import com.project.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE role_id = :roleId")
    Optional<List<Employee>> findEmployeesByRoleId(@Param("roleId") Long roleId);
}
