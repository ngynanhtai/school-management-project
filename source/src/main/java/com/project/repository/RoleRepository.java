package com.project.repository;

import com.project.model.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Cacheable
    @Query(nativeQuery = true, value = "SELECT * FROM role WHERE type = 'STUDENT'")
    Role findStudentType();
}
