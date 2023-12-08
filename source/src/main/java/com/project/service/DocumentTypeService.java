package com.project.service;

import com.project.dto.DocumentTypeDTO;

import java.util.List;

public interface DocumentTypeService {
    DocumentTypeDTO createDocumentType(DocumentTypeDTO dto);
    List<DocumentTypeDTO> findAll();
    void deleteDocumentType(Long id);
}
