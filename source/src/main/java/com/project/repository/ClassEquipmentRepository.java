package com.project.repository;

import com.project.model.entity.ClassEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassEquipmentRepository extends JpaRepository<ClassEquipment, Long> {
}
