package com.project.controller.management;

import com.project.dto.DocumentDTO;
import com.project.dto.common.MessageResponse;
import com.project.dto.common.ResponseDTO;
import com.project.enums.MessageCodeEnum;
import com.project.service.DocumentService;
import com.project.utils.ExceptionUtil;
import com.project.utils.ResponseUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/system")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/document/upload-file")
    public ResponseEntity<ResponseDTO> uploadFile(@RequestPart("file") MultipartFile file,
                                                  @RequestParam("type") String type,
                                                  @RequestParam(value = "userCode", required = false) String userCode,
                                                  @RequestParam(value = "classCode", required = false) String classCode) {
        return ResponseUtil.buildSuccess(documentService.uploadFile(file, type, userCode, classCode));
    }

    @PostMapping(value = "/document/upload-files",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> uploadMultipleFile(@RequestPart("files") MultipartFile[] files,
                                                  @RequestParam("type") String type,
                                                  @RequestParam(value = "userCode", required = false) String userCode,
                                                  @RequestParam(value = "classCode", required = false) String classCode) {
        return ResponseUtil.buildSuccess(documentService.uploadMultipleFile(files, type, userCode, classCode));
    }

    @DeleteMapping("/document/delete-file")
    public ResponseEntity<ResponseDTO> deleteFile(@RequestBody DocumentDTO dto) {
        if (ObjectUtils.isEmpty(dto.getPath())) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }
        documentService.updateDeleteFile(dto.getPath(), true);
        return ResponseUtil.buildSuccess(
                MessageResponse
                        .builder()
                        .status(HttpStatus.SC_OK)
                        .message("Delete File Successfully!")
                        .build());
    }

    @PutMapping("/document/recover-file")
    public ResponseEntity<ResponseDTO> recoverFile(@RequestBody DocumentDTO dto) {
        if (ObjectUtils.isEmpty(dto.getPath())) {
            ExceptionUtil.throwCustomException(MessageCodeEnum.VALIDATION_REQUEST_NULL);
        }
        documentService.updateDeleteFile(dto.getPath(), false);
        return ResponseUtil.buildSuccess(
                MessageResponse
                        .builder()
                        .status(HttpStatus.SC_OK)
                        .message("Delete File Successfully!")
                        .build());
    }
}
