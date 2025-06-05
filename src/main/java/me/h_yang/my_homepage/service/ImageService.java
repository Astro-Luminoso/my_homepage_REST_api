package me.h_yang.my_homepage.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;

@Service
public class ImageService {

    Logger logger = LoggerFactory.getLogger(ImageService.class);

    /**
     * Default constructor for the ImageService class.
     */
    public ImageService() {
        // Default constructor
    }

    public ResponseEntity<byte[]> getImageResponse(String fileName) {

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
