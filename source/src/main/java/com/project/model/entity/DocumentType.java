package com.project.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "document_type")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentType {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "basic_name")
    private String basicName;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "file_extension")
    private String fileExtension;

    @Column(name = "is_multiple")
    private boolean isMultiple;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "documentType")
    private Set<Document> documents;
}
