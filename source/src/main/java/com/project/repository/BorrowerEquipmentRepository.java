package com.project.repository;

import com.project.model.entity.BorrowEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowerEquipmentRepository extends JpaRepository<BorrowEquipment, Long> {
}
