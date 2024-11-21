package com.example.pethotel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${spring.file.upload-dir}")
    private String uploadDir;

    // 파일 저장 로직
    public List<String> saveFiles(String uploadUrl, MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();

        File dir = new File(uploadUrl);
        if (!dir.exists()) {
            dir.mkdirs();  // 디렉터리 생성
        }

        // 파일 처리
        if (files != null) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    String fileName = file.getOriginalFilename();
                    String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();

                    // 확장자 검사
                    if (!Arrays.asList("jpg", "jpeg", "png", "gif").contains(fileExtension)) {
                        throw new IllegalArgumentException("허용되지 않은 파일 형식입니다.");
                    }

                    // 파일 크기 제한 (예: 5MB)
                    if (file.getSize() > 5 * 1024 * 1024) {
                        throw new IllegalArgumentException("파일 크기는 5MB를 초과할 수 없습니다.");
                    }

                    // 파일 이름 중복 처리
                    String uniqueFileName = System.currentTimeMillis() + "_" + fileName;
                    Path path = Paths.get(uploadUrl, uniqueFileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    fileNames.add(uniqueFileName);
                }
            }
        }
        return fileNames;
    }
}
