package com.project.repository;

import com.project.model.entity.ClassAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassAllocationRepository extends JpaRepository<ClassAllocation, Long> {
}
