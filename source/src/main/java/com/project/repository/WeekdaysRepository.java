package com.project.repository;

import com.project.model.entity.Weekdays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekdaysRepository extends JpaRepository<Weekdays, Long> {
}
