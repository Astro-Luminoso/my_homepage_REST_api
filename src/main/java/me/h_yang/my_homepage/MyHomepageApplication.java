package me.h_yang.my_homepage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class MyHomepageApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHomepageApplication.class, args);
	}

}
