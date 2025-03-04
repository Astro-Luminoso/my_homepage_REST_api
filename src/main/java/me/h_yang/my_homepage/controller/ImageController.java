package me.h_yang.my_homepage.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequestMapping("/open/images")
public class ImageController {

    private final String IMAGE_PATH = "src/main/resources/image/";


    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() {

        Path imagePath = Path.of(IMAGE_PATH + "blog_logo.png");

        try {
            byte[] image = Files.readAllBytes(imagePath);

            String contentType = Files.probeContentType(imagePath);
            if (contentType == null) {
                contentType = "application/octet-stream";  // MIME 타입이 추정되지 않으면 기본값 설정
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", contentType);

            return new ResponseEntity<>(image, headers, 200);

        } catch (IOException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
