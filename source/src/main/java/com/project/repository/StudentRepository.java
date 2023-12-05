package com.project.repository;

import com.project.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE id in (:studentIds) AND deleted = false")
    Optional<List<Student>> findStudentsByIds(@Param("studentIds") List<Long> studentIds);

    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE id = :id AND deleted = false")
    Optional<Student> findOneById(@Param("id") Long id);
}
