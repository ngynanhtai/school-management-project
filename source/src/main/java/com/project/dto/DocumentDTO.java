package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentDTO {
    private Long id;
    private String path;
    private String type;
    private String userCode;
    private String classCode;
    private String mediaType;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp updatedDate;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp createdDate;
}
