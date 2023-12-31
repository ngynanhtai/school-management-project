package com.project.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDTO {
    private Long id;
    private String code;
    private String name;
    private String type;
    private long totalOpen;
}
