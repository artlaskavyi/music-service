package com.arty.musicservice;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusicServiceApplication {

    public static void main(String[] args) {
        // Check if the active profile is 'prod'
        String activeProfile = System.getenv("SPRING_PROFILES_ACTIVE");

        if (activeProfile == null || !activeProfile.equals("prod")) {
            // In non-prod profiles, load .env file for local development
            Dotenv dotenv = Dotenv.configure().directory("/Users/user/IdeaProjects/MusicService/").load();
            System.setProperty("DB_URL", dotenv.get("DB_URL"));
            System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
            System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
            System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
            System.out.println("Loaded environment variables from .env file for local development.");
        }
        // In production, environment variables are expected to be set
        // do nothing

        SpringApplication.run(MusicServiceApplication.class, args);
    }
}