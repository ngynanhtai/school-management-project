package com.project.repository;

import com.project.model.entity.ClassroomCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomCourseRepository extends JpaRepository<ClassroomCourse, Long> {
}
