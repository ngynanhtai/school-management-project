package com.project.service.impl;

import com.project.dto.DocumentTypeDTO;
import com.project.model.mapstruct.DocumentTypeMapstruct;
import com.project.repository.DocumentTypeRepository;
import com.project.service.DocumentTypeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DocumentTypeServiceImpl implements DocumentTypeService {

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Override
    @Transactional
    @CacheEvict(value = "documentTypes", allEntries = true)
    public DocumentTypeDTO createDocumentType(DocumentTypeDTO dto) {
        return DocumentTypeMapstruct.toDTO(documentTypeRepository.save(DocumentTypeMapstruct.toEntity(dto)));
    }

    @Override
    @Cacheable("documentTypes")
    public List<DocumentTypeDTO> findAll() {
        return documentTypeRepository.findAll().stream().map(DocumentTypeMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    @CacheEvict(value = "documentTypes", allEntries = true)
    public void deleteDocumentType(Long id) {
        documentTypeRepository.deleteById(id);
    }
}
