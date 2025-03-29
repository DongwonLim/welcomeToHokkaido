package com.example.welcomeToHokkaido.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor
@Controller
public class ImageController {

//    private final String uploadDir = "C:/uploads/";
//
//    @GetMapping("/uploads/{fileName:.+}")
//    @ResponseBody
//    public ResponseEntity<Resource> getImage(@PathVariable String fileName) {
//        Path filePath = Paths.get(uploadDir + fileName);
//        Resource resource = new FileSystemResource(filePath);
//        if (!resource.exists()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG)
//                .body(resource);
//    }
}
