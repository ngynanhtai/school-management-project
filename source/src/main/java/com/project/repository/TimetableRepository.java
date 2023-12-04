package com.project.repository;

import com.project.model.entity.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Optional<List<Timetable>> findByTeacherId(Long teacherId);

    @Query(nativeQuery = true, value = "DELETE timetable WHERE teacher_id = :teacherId AND shift = :shift AND week_day = :weekDay")
    long deleteTimetable(@Param("teacherId") Long teacherId,
                         @Param("shift") String shift,
                         @Param("weekDay") String weekDay);
}
