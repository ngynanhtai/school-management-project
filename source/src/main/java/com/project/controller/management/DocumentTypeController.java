package com.project.controller.management;

import com.project.dto.DocumentTypeDTO;
import com.project.dto.common.MessageResponse;
import com.project.dto.common.ResponseDTO;
import com.project.service.DocumentTypeService;
import com.project.utils.ResponseUtil;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system")
public class DocumentTypeController {
    @Autowired
    private DocumentTypeService documentTypeService;

    @PostMapping("/document-type")
    public ResponseEntity<ResponseDTO> createDocumentType(@RequestBody DocumentTypeDTO dto) {
        return ResponseUtil.buildSuccess(documentTypeService.createDocumentType(dto));
    }

    @GetMapping("/document-type")
    public ResponseEntity<ResponseDTO> findAllDocumentTypes() {
        return ResponseUtil.buildSuccess(documentTypeService.findAll());
    }

    @DeleteMapping("/document-type/{id}")
    public ResponseEntity<ResponseDTO> deleteDocumentType(@PathVariable("id") Long id) {
        documentTypeService.deleteDocumentType(id);
        return ResponseUtil.buildSuccess(
                MessageResponse
                        .builder()
                        .status(HttpStatus.SC_OK)
                        .message("Delete Document Type Successfully!")
                        .build());
    }
}
