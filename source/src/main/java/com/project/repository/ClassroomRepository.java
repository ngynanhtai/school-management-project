package com.project.repository;

import com.project.model.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM classroom WHERE id = :id AND deleted = false")
    Optional<Classroom> findOneById(@Param("id") Long id);
}
