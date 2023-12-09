package com.project.service.impl;

import com.project.constant.Constant;
import com.project.dto.DocumentDTO;
import com.project.dto.DocumentTypeDTO;
import com.project.enums.MessageCodeEnum;
import com.project.model.entity.Document;
import com.project.model.entity.DocumentType;
import com.project.model.mapstruct.DocumentMapstruct;
import com.project.model.mapstruct.DocumentTypeMapstruct;
import com.project.repository.DocumentRepository;
import com.project.service.DocumentService;
import com.project.service.DocumentTypeService;
import com.project.utils.ExceptionUtil;
import com.project.utils.FileUtil;
import com.project.utils.ListUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class DocumentServiceImpl implements DocumentService {

    @Value("${storage.upload-folder}")
    private String rootUploadFolder;

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    @Lazy
    private DocumentTypeService documentTypeService;

    @Override
    public DocumentDTO uploadFile(MultipartFile file, String type, String userCode, String classCode) {
        List<DocumentTypeDTO> documentTypeDTOs = documentTypeService.findAll();
        DocumentTypeDTO documentTypeDTO = documentTypeDTOs.stream().filter(item -> item.getCode().equalsIgnoreCase(type)).findFirst().orElse(null);
        if (documentTypeDTO == null) {
            log.error("DocumentType not found with Type: ".concat(type));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "DocumentType not found with Type: ".concat(type));
        }
        Path generatePath = FileUtil.generatePath(type, userCode, classCode);
        String fileName;
        if (documentTypeDTO.isMultiple()) {
            fileName = documentTypeDTO.getBasicName()
                    .concat(Constant.UNDERSCORE)
                    .concat(String.valueOf(new Date().getTime() / 1000000))
                    .concat(documentTypeDTO.getFileExtension());
        } else {
            fileName = documentTypeDTO.getBasicName()
                    .concat(documentTypeDTO.getFileExtension());
        }
        try {
            Path folder = Files.createDirectories(generatePath);
            Files.copy(file.getInputStream(), folder.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            log.error("Upload File Error for {}, Error: {} ", !StringUtils.isEmpty(userCode) ? userCode : classCode, e.getMessage());
            ExceptionUtil.throwCustomException(MessageCodeEnum.UPLOAD_FILE_ERROR.getCode(), "Upload File Error: ".concat(e.getMessage()));
        }
        return createDocument(DocumentTypeMapstruct.toEntity(documentTypeDTO), Paths.get(generatePath.toString(), fileName).toString(), userCode, classCode);
    }

    @Override
    public List<DocumentDTO> uploadMultipleFile(MultipartFile[] files, String type, String userCode, String classCode) {
        List<DocumentTypeDTO> documentTypeDTOs = documentTypeService.findAll();
        DocumentTypeDTO documentTypeDTO = documentTypeDTOs.stream().filter(item -> item.getCode().equalsIgnoreCase(type)).findFirst().orElse(null);
        if (documentTypeDTO == null) {
            log.error("DocumentType not found with Type: ".concat(type));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "DocumentType not found with Type: ".concat(type));
        }

        if (!documentTypeDTO.isMultiple()) {
            log.error(String.format("Type %s is not Multiple DocumentType", type));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DOC_TYPE_NOT_MULTIPLE.getCode(), String.format("Type %s is not Multiple DocumentType", type));
        }

        Path generatePath = FileUtil.generatePath(type, userCode, classCode);
        String fileName;
        List<DocumentDTO> result = new ArrayList<>();

        for (MultipartFile file : files) {
            fileName = documentTypeDTO.getBasicName()
                    .concat(Constant.UNDERSCORE)
                    .concat(RandomStringUtils.random(4, true, true).toLowerCase())
                    .concat(documentTypeDTO.getFileExtension());
            try {
                Path folder = Files.createDirectories(generatePath);
                Files.copy(file.getInputStream(), folder.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                log.error("Upload File Error for {}, Error: {} ", !StringUtils.isEmpty(userCode) ? userCode : classCode, e.getMessage());
                ExceptionUtil.throwCustomException(MessageCodeEnum.UPLOAD_FILE_ERROR.getCode(), "Upload File Error: ".concat(e.getMessage()));
            }
            result.add(createDocument(DocumentTypeMapstruct.toEntity(documentTypeDTO), Paths.get(generatePath.toString(), fileName).toString(), userCode, classCode));
        }
        return result;
    }

    @Transactional
    private DocumentDTO createDocument(DocumentType documentType, String finalPath, String userCode, String classCode) {
        Document existDocument = null;
        Document document;
        if (!documentType.isMultiple()) {
            existDocument = documentRepository.findByPath(finalPath).orElse(null);
        }
        if (existDocument != null) {
            document = existDocument;
            document.setUpdatedDate(new Timestamp(new Date().getTime()));

        } else {
            document = new Document();
            document.setDocumentType(documentType);
            document.setPath(finalPath);
            document.setUserCode(userCode);
            document.setClassCode(classCode);
            document.setType(documentType.getCode());
        }
        return DocumentMapstruct.toDTO(documentRepository.save(document));
    }

    @Override
    @Transactional
    public void updateDeleteFile(String path, boolean isDelete) {
        Document document = documentRepository.findByPath(path).orElse(null);
        if (document == null) {
            log.error("Document not found with Path: ".concat(path));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Document not found with Path: ".concat(path));
        }

        if (!document.getDocumentType().isMultiple()) {
            log.error(String.format("Cannot delete. Only Delete Multiple DocumentType, Type %s is not Multiple", document.getType()));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DOC_TYPE_NOT_MULTIPLE.getCode(), String.format("Cannot delete. Only Delete Multiple DocumentType, Type %s is not Multiple", document.getType()));
        }
        document.setDeleted(isDelete);
        document.setUpdatedDate(new Timestamp(new Date().getTime()));
    }

    @Override
    public List<DocumentDTO> findByClassCode(String classCode) {
        List<Document> documents = documentRepository.findByClassCode(classCode).orElse(ListUtil.emptyList());
        if (ObjectUtils.isEmpty(documents)) {
            log.error("Document not found with ClassCode: ".concat(classCode));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Document not found with ClassCode: ".concat(classCode));
        }
        return documents.stream().map(DocumentMapstruct::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<DocumentDTO> findByUserCode(String userCode) {
        List<Document> documents = documentRepository.findByUserCode(userCode).orElse(ListUtil.emptyList());
        if (ObjectUtils.isEmpty(documents)) {
            log.error("Document not found with UserCode: ".concat(userCode));
            ExceptionUtil.throwCustomException(MessageCodeEnum.DATA_NOT_FOUND.getCode(), "Document not found with UserCode: ".concat(userCode));
        }
        return documents.stream().map(DocumentMapstruct::toDTO).collect(Collectors.toList());
    }
}
