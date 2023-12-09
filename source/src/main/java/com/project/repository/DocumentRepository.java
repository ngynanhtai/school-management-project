package com.project.repository;

import com.project.model.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    Optional<Document> findByPath(String path);

    @Query(nativeQuery = true, value = "SELECT * FROM document WHERE class_code = :classCode AND deleted = false")
    Optional<List<Document>> findByClassCode(@Param("classCode") String classCode);

    @Query(nativeQuery = true, value = "SELECT * FROM document WHERE user_code = :userCode AND deleted = false")
    Optional<List<Document>> findByUserCode(@Param("userCode") String userCode);

    @Query(nativeQuery = true, value = "SELECT * FROM document d INNER JOIN document_type dt ON d.type_id = dt.id " +
            "WHERE d.deleted = true AND dt.is_multiple = true AND d.updated_date IS NOT NULL " +
            "AND d.updated_date < CURRENT_DATE - :overdueDays")
    Optional<List<Document>> findDocumentOverdue(@Param("overdueDays") int overdueDays);
}
