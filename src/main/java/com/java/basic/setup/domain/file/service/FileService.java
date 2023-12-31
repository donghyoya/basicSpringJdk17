package com.java.basic.setup.domain.file.service;

import com.java.basic.setup.domain.file.entity.FileMetadata;
import com.java.basic.setup.domain.file.repository.FileRepository;
import com.java.basic.setup.domain.file.repository.FileRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileRepositorySupport fileSupport;

    public FileMetadata storeFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        long fileSize = file.getSize();
        LocalDateTime uploadDate = LocalDateTime.now();

        // 파일 저장 로직 (예: 파일 시스템, 클라우드 스토리지 등)
        String downloadUrl = ""; // 저장된 파일의 URL

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileName(fileName);
        fileMetadata.setFileSize(fileSize);
        fileMetadata.setUploadDate(uploadDate);
        fileMetadata.setDownloadUrl(downloadUrl);

        return fileRepository.save(fileMetadata);
    }

    public FileMetadata downloadFileFromUrl(String urlString) throws IOException{
        URL url = new URL(urlString);
        Path tempDir = Files.createTempDirectory("downloaded_files");
        String fileName = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);
        Path filePath = tempDir.resolve(fileName);

        try (InputStream in = url.openStream()) {
            Files.copy(in, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        FileMetadata fileMetadata = new FileMetadata();
        fileMetadata.setFileName(fileName);
        fileMetadata.setFileSize(Files.size(filePath));
        fileMetadata.setUploadDate(LocalDateTime.now());
        fileMetadata.setDownloadUrl(urlString); // 원본 URL 사용

        return fileRepository.save(fileMetadata);
    }

    public List<FileMetadata> getFilesBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return fileSupport.findFilesBetweenDates(startDate, endDate);
    }

}
