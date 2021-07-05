package com.example.wuzzuf_api_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@SpringBootApplication
public class WuzzufApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WuzzufApiServiceApplication.class, args);
    }

    @GetMapping("/First Method")
    public String welcome() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        return df.printTable();
    }
}
