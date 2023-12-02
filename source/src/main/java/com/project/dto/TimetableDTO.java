package com.project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.utils.DateUtil;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableDTO {
    private Long id;
    private Long teacherId;
    private String teacherName;
    private String classroomName;
    private String shift;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Date implementDate;
    @JsonFormat(pattern = DateUtil.DATE_HYPHEN)
    private Timestamp createdDate;
}
