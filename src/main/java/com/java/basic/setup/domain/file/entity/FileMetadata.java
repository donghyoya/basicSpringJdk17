package com.java.basic.setup.domain.file.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class FileMetadata {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private long fileSize;
    private LocalDateTime uploadDate;
    private String downloadUrl;

}
