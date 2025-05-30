package me.h_yang.my_homepage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    Logger logger = LoggerFactory.getLogger(ImageController.class);


    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() {

        logger.info("GET: /open/images/logo - Request for logo image");

        return getImageResponse("blog_logo.png");
    }

    @GetMapping("/main-background")
    public ResponseEntity<byte[]> getMainBackground() {

        logger.info("GET: /open/images/main-background - Request for main background image");

        return getImageResponse("main_background.png");
    }

    @GetMapping("/welcome-image")
    public ResponseEntity<byte[]> getWelcomeBackground() {

        logger.info("GET: /open/images/welcome-image - Request for welcome image");

        return getImageResponse("welcome_image.png");
    }

    @GetMapping ("/white-logo")
    public ResponseEntity<byte[]> getWhiteLogo() {

        logger.info("GET: /open/images/white-logo - Request for white logo image");

        return getImageResponse("logo-white.png");
    }


    private ResponseEntity<byte[]> getImageResponse(String fileName) {

        try {

            Resource resource = new ClassPathResource("image/" + fileName);

            if (!resource.exists()) {

                logger.error("Response Status 404: {} not found", fileName);

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
            logger.error("Response Status 500: Server Error while processing file {}", fileName, e);
            return ResponseEntity.status(500).body(("Server Error Occurred").getBytes());
        }
    }
}
