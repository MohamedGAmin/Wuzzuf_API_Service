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
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Wuzzuf Data</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Sample from Wuzzuf Data</h1>\n" +
                df.printTable() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 2: Display structure and summary of the data.


    // Requirement 3: Clean the data (null, duplications).
    @GetMapping("/Third Method")
    public String display_cleaned_data() throws IOException {
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
                "<h1>Checking for Nulls</h1>\n" +
                df.checkNulls()+"\n\n"+
                "<h1>Data after Eliminating Duplicates</h1>\n" +
                "<p>Data length with duplicates = " + df.df.length() + "</p>\n"+
                "\n"+
                "<p>Data length without duplicates = " + df.checkDuplicity().length() + "</p>\n"+
                df.checkDuplicity()+"\n" +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
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
                "  <title>Companies Demand</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job Demand for Each Company</h1>\n" +
                df.jobs_per_company() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 5: Show step 4 in a pie chart.
    @GetMapping("/Fifth Method")
    public String show_company_demand_pieChart() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        df.company_pieChart();
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Companies Demand</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job demand for Each Company</h1>\n" +
                df.jobs_per_company() +
                "\n" +
                "<h3> Company Demand Pie Chart </h3>"+
                "<img src=\"company_demand.jpg\">"+
                "\n"+
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Step 6: What are it the most popular job titles?
    @GetMapping("/Sixth Method")
    public String display_most_popular_jobs() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Job Titles</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job Titles Demand</h1>\n" +
                df.jobs_per_company() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 7: Show step 6 in a bar chart.
    @GetMapping("/Seventh Method")
    public String show_job_demand_barChart() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        df.job_demand_barChart();
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Job Titles</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Job Titles Demand</h1>\n" +
                df.job_demand() +
                "\n" +
                "<h3> Job Title Demand Bar Chart </h3>"+
                "<img src=\"job_demand.jpg\">"+
                "\n"+
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 8: Find out the most popular areas?
    @GetMapping("/Eighth Method")
    public String display_most_popular_areas() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Job Areas</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Popular Areas for Jobs</h1>\n" +
                df.popular_areas() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // Requirement 9: Find out the most popular areas?
    @GetMapping("/Ninth Method")
    public String show_popular_areas_barChart() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        df.area_barChart();
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Job Areas</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Popular Areas for Jobs</h1>\n" +
                df.popular_areas() +
                "\n" +
                "<h3> Popular Areas' Bar Chart </h3>"+
                "<img src=\"area_demand.jpg\">"+
                "\n"+
                "</body>\n" +
                "</html> ");
        return buf.toString();
    }

    // 10. Print skills one by one and how many each repeated and order
    // the output to find out the most important skills required?
    @GetMapping("/Tenth Method")
    public String show_most_demanded_skills() throws IOException {
        String path = "Wuzzuf_Jobs.csv";
        Joinery_DataFrame df = new Joinery_DataFrame(path);
        df.area_barChart();
        StringBuilder buf = new StringBuilder();
        buf.append(" <!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Job Skills</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Popular Skills for Jobs</h1>\n" +
                df.popular_skills() +
                "\n" +
                "</body>\n" +
                "</html> ");
        return df.popular_skills();
    }
}

