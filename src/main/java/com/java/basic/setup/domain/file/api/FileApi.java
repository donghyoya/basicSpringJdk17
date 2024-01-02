package com.java.basic.setup.domain.file.api;

import com.java.basic.setup.domain.file.dto.FileVo;
import com.java.basic.setup.domain.file.entity.FileMetadata;
import com.java.basic.setup.domain.file.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
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
    @PostMapping("/download/url")
    public ResponseEntity<FileMetadata> downloadFilebyUrl(@RequestParam("url") String url)
            throws IOException {
        FileMetadata fileMetadata = fileService.downloadFileFromUrl(url);
        return ResponseEntity.ok(fileMetadata);
    }

    @GetMapping("/download/path")
    public ResponseEntity<Resource> downloadFileByPath(@RequestParam("path") String path,
                                                       HttpServletRequest request,
                                                       HttpHeaders httpHeaders)
            throws IOException {

        Resource resource = fileService.downloadFilePath(path);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            // logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    @GetMapping("/between")
    public ResponseEntity<List<FileMetadata>> getFilesBetweenDates(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<FileMetadata> files = fileService.getFilesBetweenDates(startDate, endDate);
        return ResponseEntity.ok(files);
    }

}
