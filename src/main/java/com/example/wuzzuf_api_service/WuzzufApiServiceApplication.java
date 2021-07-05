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
                "<h1>Checking for nulls</h1>\n" +
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

    // Requirement 4: Count the jobs for each company and display that in order.
    @GetMapping("/Fourth Method")
    public String display_most_demanding_companies() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Cleaning Data</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job demand for each company</h1>\n" +
                df.job_demand() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 5: Show step 4 in a pie chart.
    @GetMapping("/Fifth Method")
    public String show_job_demand_barChart() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        df.job_demand_barChart();
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Cleaning Data</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job demand for each company</h1>\n" +
                df.job_demand() +
                "\n" +
                "<h3> Job Demand Bar Chart </h3>"+
                "<img src=\"job_demand.jpg\">"+
                "\n"+
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

}
