package com.project.model.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "document")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "path")
    private String path;

    @Column(name = "type_code")
    private String typeCode;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "class_code")
    private String classCode;

    @Column(name = "is_public")
    private boolean isPublic;

    @Column(name = "created_date", updatable = false)
    @CreationTimestamp
    private Timestamp createdDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private DocumentType documentType;
}
