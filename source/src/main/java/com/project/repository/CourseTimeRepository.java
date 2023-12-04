package com.project.repository;

import com.project.model.entity.CourseTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTimeRepository extends JpaRepository<CourseTime, Long> {
    @Query(nativeQuery = true, value = "DELETE FROM course_time WHERE id = :id")
    @Modifying
    int removeById(@Param("id") long id);
}
