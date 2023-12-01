package com.project.repository;

import com.project.model.entity.CourseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTimeRepository extends JpaRepository<CourseTime, Long> {
}
