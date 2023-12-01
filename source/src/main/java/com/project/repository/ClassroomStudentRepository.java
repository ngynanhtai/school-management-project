package com.project.repository;

import com.project.model.entity.ClassroomStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomStudentRepository extends JpaRepository<ClassroomStudent, Long> {
}
