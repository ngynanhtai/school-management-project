package com.project.dto.response;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentResponse {
    private Long id;
    private String path;
    private String typeCode;
    private String userCode;
    private String classCode;
    private boolean isPublic;
    private Timestamp createdDate;
    private DocumentTypeResponse documentType;
}
