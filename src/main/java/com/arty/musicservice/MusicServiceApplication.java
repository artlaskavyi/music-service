package com.arty.musicservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicServiceApplication {

    public static void main(String[] args) {
        // No need to load .env, rely solely on environment variables
        SpringApplication.run(MusicServiceApplication.class, args);
    }
}