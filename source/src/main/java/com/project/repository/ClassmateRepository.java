package com.project.repository;

import com.project.model.entity.Classmate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassmateRepository extends JpaRepository<Classmate, Long> {
}
