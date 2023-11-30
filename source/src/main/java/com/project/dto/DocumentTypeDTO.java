package com.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeDTO {
    private Long id;
    private String code;
    private String name;
    private String mediaType;
    private String fileExtension;
    private boolean isMultiple;
    private List<DocumentDTO> documents;
}
