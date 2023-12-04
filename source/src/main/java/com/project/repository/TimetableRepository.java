package com.project.repository;

import com.project.model.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Optional<List<Timetable>> findByTeacherId(Long teacherId);

    @Query(nativeQuery = true, value = "DELETE FROM timetable WHERE course_time_id = :courseTimeId")
    @Modifying
    int deleteTimetableByCourseTimeId(@Param("courseTimeId") Long courseTimeId);
}
