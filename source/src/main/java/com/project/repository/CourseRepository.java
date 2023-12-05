package com.project.repository;

import com.project.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM course WHERE id = :id AND deleted = false")
    Optional<Course> findOneById(@Param("id") Long id);
}
