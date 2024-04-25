package onlineshop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

/**
 * Manages the Shop
 */
@SpringBootApplication
public class Shop {
    private final static Logger log = LogManager.getLogger(Shop.class);

    public static void main(String[] args) {
        SpringApplication.run(Shop.class, args);
        log.info("http://localhost:8080");

    }
}
