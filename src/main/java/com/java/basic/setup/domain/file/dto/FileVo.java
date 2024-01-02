package com.java.basic.setup.domain.file.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(title = "파일 Vo")
public class FileVo {
    private String flId;
    private String seq;
    private String upPath;
    @Schema(title = "멀티파트 File Arrya", description = "멀티 파트 File Array")
    private MultipartFile files;
    private String flNm;
    private String flSize;
}
