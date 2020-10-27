package com.mengyu;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.InvalidParameterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mengyu.controller.EventController;

@SpringBootApplication
public class ApplicationRunner implements CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);

    public static void main(String[] args) {
        SpringApplication.run(ApplicationRunner.class, args);
    }

    // entry point
    @Override
    public void run(String... args) throws IOException {
        if (args.length != 1 || args[0].isEmpty()) {
            throw new InvalidParameterException("Please provide a valid file path of log file");
        }
        String filePath = args[0];
        logger.info("file path " + filePath);
        EventController.parseLogFile(filePath);
    }
}
