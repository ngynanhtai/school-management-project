package com.project.service;

import com.project.dto.DocumentDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DocumentService {
    DocumentDTO uploadFile(MultipartFile file, String type, String userCode, String classCode);
    List<DocumentDTO> uploadMultipleFile(MultipartFile[] files, String type, String userCode, String classCode);
    void updateDeleteFile(String path, boolean isDelete);
    List<DocumentDTO> findByClassCode(String classCode);
    List<DocumentDTO> findByUserCode(String userCode);
}
