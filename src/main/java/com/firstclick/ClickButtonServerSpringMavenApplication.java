package com.firstclick;

import com.firstclick.config.SpringConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class ClickButtonServerSpringMavenApplication {

    static final Logger log = LoggerFactory.getLogger(ClickButtonServerSpringMavenApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ClickButtonServerSpringMavenApplication.class, args);
        log.info("App started");

    }

}
