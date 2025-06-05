package me.h_yang.my_homepage.controller;

import me.h_yang.my_homepage.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/open/images")
public class ImageController {


    Logger logger = LoggerFactory.getLogger(ImageController.class);
    ImageService imageService;

    /**
     * Default constructor for the ImageController class.
     */
    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    /**
     * Get the logo image.
     *
     * @return ResponseEntity with the logo image.
     */
    @GetMapping("/logo")
    public ResponseEntity<byte[]> getLogo() {

        logger.info("GET: /open/images/logo - Request for logo image");

        return imageService.getImageResponse("blog_logo.png");
    }

    /**
     * Get the main background image.
     *
     * @return ResponseEntity with the main background image.
     */
    @GetMapping("/main-background")
    public ResponseEntity<byte[]> getMainBackground() {

        logger.info("GET: /open/images/main-background - Request for main background image");

        return imageService.getImageResponse("main_background.png");
    }

    /**
     * Get the welcome image.
     *
     * @return ResponseEntity with the welcome background image.
     */
    @GetMapping("/welcome-image")
    public ResponseEntity<byte[]> getWelcomeBackground() {

        logger.info("GET: /open/images/welcome-image - Request for welcome image");

        return imageService.getImageResponse("welcome_image.png");
    }

    /**
     * Get the white logo image.
     *
     * @return ResponseEntity with the white logo image.
     */
    @GetMapping ("/white-logo")
    public ResponseEntity<byte[]> getWhiteLogo() {

        logger.info("GET: /open/images/white-logo - Request for white logo image");

        return imageService.getImageResponse("logo-white.png");
    }



}
