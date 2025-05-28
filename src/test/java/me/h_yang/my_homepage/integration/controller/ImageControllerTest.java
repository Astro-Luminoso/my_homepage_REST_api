package me.h_yang.my_homepage.integration.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImageControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    @Test
    void getBackgroundImage() {

        ResponseEntity<byte[]> response = testRestTemplate.exchange(
                "/open/images/main-background",
                HttpMethod.GET,
                null, byte[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }

    @Test
    void getLogoImage() {

        ResponseEntity<byte[]> response = testRestTemplate.exchange(
                "/open/images/logo",
                HttpMethod.GET,
                null, byte[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getWelcomeImage() {

        ResponseEntity<byte[]> response = testRestTemplate.exchange(
                "/open/images/welcome-image",
                HttpMethod.GET,
                null, byte[].class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
