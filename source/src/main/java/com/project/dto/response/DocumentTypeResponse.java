package com.project.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeResponse {
    private Long id;
    private String code;
    private String name;
    private String mediaType;
    private String fileExtension;
    private boolean isMultiple;
    private List<DocumentResponse> documents;
}
