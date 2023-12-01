package com.project.repository;

import com.project.model.entity.ClassCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassCourseRepository extends JpaRepository<ClassCourse, Long> {
}
