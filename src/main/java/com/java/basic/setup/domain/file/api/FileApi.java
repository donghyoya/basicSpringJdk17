package com.java.basic.setup.domain.file.api;

import com.java.basic.setup.domain.file.dto.FileVo;
import com.java.basic.setup.domain.file.entity.FileMetadata;
import com.java.basic.setup.domain.file.service.FileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/files")
public class FileApi {

    @Autowired
    private FileService fileService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileMetadata> uploadFile(@ModelAttribute("file") @Valid FileVo file) throws IOException {
        FileMetadata fileMetadata = fileService.storeFile(file);
        return ResponseEntity.ok(fileMetadata);
    }

    // FileController 클래스 내에 추가 메서드
    @PostMapping("/download")
    public ResponseEntity<FileMetadata> downloadFile(@RequestParam("url") String url) throws IOException {
        FileMetadata fileMetadata = fileService.downloadFileFromUrl(url);
        return ResponseEntity.ok(fileMetadata);
    }

    @GetMapping("/between")
    public ResponseEntity<List<FileMetadata>> getFilesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<FileMetadata> files = fileService.getFilesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(files);
    }

}
