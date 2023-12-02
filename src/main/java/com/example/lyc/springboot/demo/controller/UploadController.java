package com.example.lyc.springboot.demo.controller;

import com.example.lyc.springboot.demo.commons.api.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件上传
 * 限制上传大小、文件格式、存放位置、重命名上传文件
 * */
@Slf4j
@RestController
@RequestMapping("/v1/fileUpload")
@Schema(name="文件上传", description="文件上传")
@Tag(name = "文件上传")
public class UploadController {
    private static final String UPLOAD_DIR = "./upload";

    @PostMapping("/upload")
    public BaseResponse<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        // 限制上传文件大小
        if (file.getSize() > 1024 * 1024 * 5) {
            return new BaseResponse<>(1, "File size too large", null);
        }
        // 限制上传文件格式
        if (!"jpg".equals(fileExtension) && !"png".equals(fileExtension)) {
            return new BaseResponse<>(1, "Invalid file format", null);
        }

        // Rename the file
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String newFilename = userId + "_" + timestamp + "_" + originalFilename;

        // Save the file to the server
        Path filePath = Paths.get(UPLOAD_DIR, newFilename);
        try {
            Files.write(filePath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return new BaseResponse<>(1, "Failed to save file", null);
        }

        return new BaseResponse<>(0, "File uploaded successfully", newFilename);
    }
}
