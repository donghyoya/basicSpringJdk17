package com.java.basic.setup.domain.file.repository;

import com.java.basic.setup.domain.file.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FileRepository extends JpaRepository<FileMetadata, Long> {


}
