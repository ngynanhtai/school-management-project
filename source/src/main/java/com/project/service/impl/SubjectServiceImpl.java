package com.project.service.impl;

import com.project.dto.SubjectDTO;
import com.project.model.mapstruct.SubjectMapstruct;
import com.project.repository.SubjectRepository;
import com.project.service.SubjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    @Transactional
    public SubjectDTO add(SubjectDTO dto) {
        return SubjectMapstruct.toDTO(subjectRepository.save(SubjectMapstruct.toEntity(dto)));
    }
}
