package com.project.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentTypeDTO {
    private Long id;
    private String code;
    private String name;
    private String basicName;
    private String mediaType;
    private String fileExtension;
    private boolean isMultiple;
}
