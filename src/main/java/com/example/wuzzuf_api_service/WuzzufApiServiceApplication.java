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

    // Requirement 1: Read and display data.
    @GetMapping("/First Method")
    public String display_data() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        return df.printTable();
    }

    // Requirement 2: Display structure and summary of the data.


    // Requirement 3: Clean the data (null, duplications).
    @GetMapping("/Third Method")
    public String display_cleaned_data() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        return " <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Cleaning Data</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Checking for Nulls</h1>\n" +
                df.checkNulls()+"\n\n"+
                "<h1>Data after eliminating duplicates</h1>\n" +
                "<p>Data length with duplicates = " + df.df.length() + "</p>\n"+
                "\n"+
                "<p>Data length without duplicates = " + df.checkDuplicity().length() + "</p>\n"+
                df.checkDuplicity()+"\n" +
                "\n" +
                "</body>\n" +
                "</html> ";
    }

}
