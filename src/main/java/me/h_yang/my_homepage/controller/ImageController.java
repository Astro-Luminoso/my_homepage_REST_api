package me.h_yang.my_homepage.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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


    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() {


        return getImageResponse("blog_logo.png");
    }

    @GetMapping("/main-background")
    public ResponseEntity<byte[]> getMainBackground() {

        return getImageResponse("main_background.png");
    }

    @GetMapping("/welcome-image")
    public ResponseEntity<byte[]> getWelcomeBackground() {

        return getImageResponse("welcome_image.png");
    }

    @GetMapping ("/white-logo")
    public ResponseEntity<byte[]> getWhiteLogo() {

        return getImageResponse("logo-white.png");
    }


    private ResponseEntity<byte[]> getImageResponse(String fileName) {

        try {

            Resource resource = new ClassPathResource("image/" + fileName);

            if (!resource.exists()) {

                return ResponseEntity.status(404).body(("File not found: " + fileName).getBytes());
            }
            byte[] image = Files.readAllBytes(resource.getFile().toPath());

            String contentType = Files.probeContentType(resource.getFile().toPath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", contentType);

            return new ResponseEntity<>(image, headers, 200);

        } catch (IOException e) {
            return ResponseEntity.status(500).body(("File not found: " + fileName).getBytes());
        }
    }
}
