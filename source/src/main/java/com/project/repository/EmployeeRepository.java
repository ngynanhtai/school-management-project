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

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE id = :id AND deleted = false")
    Optional<Employee> findOneById(@Param("id") Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE email = :email AND deleted = false")
    Optional<Employee> findOneByEmail(@Param("email") String email);

    @Query(nativeQuery = true, value = "SELECT * FROM employee ORDER BY created_date desc LIMIT :limit OFFSET :offset")
    Optional<List<Employee>> findEmployeePagination(@Param("limit") int limit,
                                                    @Param("offset") int offset);

    @Query(nativeQuery = true, value = "SELECT * FROM employee WHERE " +
            "full_name ILIKE %:query% OR " +
            "email ILIKE %:query% OR " +
            "code ILIKE %:query% " +
            "ORDER BY created_date desc LIMIT :limit OFFSET :offset")
    Optional<List<Employee>> findEmployeeQueryPagination(@Param("query") String query,
                                                    @Param("limit") int limit,
                                                    @Param("offset") int offset);
}