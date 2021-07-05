package com.example.wuzzuf_api_service;

import joinery.DataFrame;
import org.apache.commons.csv.CSVFormat;
import org.knowm.xchart.*;
import smile.io.Read;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Joinery_DataFrame {
    String path;
    DataFrame df;

    public Joinery_DataFrame(String path) throws IOException {
        this.path = path;
        this.df = DataFrame.readCsv(this.path);
        List<Integer> count = this.df.col(0);
        this.df.add("Count", count);
    }

    public String printTable(){
        DataFrame newdf = this.df.head(5);
        return this.dataFrame_toString(newdf);
    }

    public void displayStructure(){
        CSVFormat format = CSVFormat.DEFAULT.withFirstRecordAsHeader();
        smile.data.DataFrame df = null;
        try {
            df = Read.csv(this.path, format);
            System.out.println(df.structure());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void displaySummary(){
        System.out.println(this.df.groupBy(2).count().sortBy("-Title").retain("Location", "Count").head(5));
        System.out.println(this.df.groupBy(3).count().sortBy("-Title").retain("Type", "Count").head(5));
        System.out.println(this.df.groupBy(0).count().sortBy("-Type").retain("Title", "Count").head(5));
        System.out.println(this.df.groupBy(4).count().sortBy("-Title").retain("Level", "Count").head(5));
    }

    public String checkNulls(){
        DataFrame newdf = this.df.isnull().groupBy(0).count();
        return dataFrame_toString(newdf);
    }

    public String checkDuplicity(){
        DataFrame newdf = this.df.unique(0, 1, 2, 3, 4, 5, 6, 7, 8);
        return dataFrame_toString(newdf.head(10));
    }

    public String jobs_per_company(){
        DataFrame newdf = this.df.groupBy(1).count().sortBy("-Title").retain("Company", "Count").head(10);
        return dataFrame_toString(newdf);
    }

    public void company_pieChart() throws IOException {
        DataFrame newdf = this.df.groupBy(1).count().sortBy("-Type").retain("Company", "Count").head(20);
        List<String> company = newdf.col(0);
        List<Integer> count = newdf.col(1);

        PieChart chart = new PieChartBuilder()
                .width(1024)
                .title("Most Demanding Companies for Jobs")
                .build();
        for(int i = 0; i < newdf.length(); i++){
            chart.addSeries(company.get(i), count.get(i));
        }

        chart.getStyler().setHasAnnotations(true);
        //new SwingWrapper(chart).displayChart();
        BitmapEncoder.saveJPGWithQuality(chart, "./src/main/resources/static/company_demand.jpg", 0.95f);
    }

    public String job_demand(){
        DataFrame newdf = this.df.groupBy(0).count().sortBy("-Type").retain("Title", "Count").head(5);
        return dataFrame_toString(newdf);
    }

    public void job_demand_barChart() throws IOException {
        DataFrame newdf = this.df.groupBy(0).count().sortBy("-Type").retain("Title", "Count").head(5);
        List<String> title = newdf.col(0);
        List<Integer> count = newdf.col(1);
        CategoryChart chart = new CategoryChartBuilder()
                .width(1024)
                .title("Job Demand")
                .xAxisTitle("Job")
                .yAxisTitle("Count of Jobs")
                .build();
        chart.addSeries(" ", title, count);
        chart.getStyler().setHasAnnotations(true);
        //new SwingWrapper(chart).displayChart();
        //BitmapEncoder.saveBitmapWithDPI(chart, "./job_demand", BitmapEncoder.BitmapFormat.PNG, 300);
        BitmapEncoder.saveJPGWithQuality(chart, "./src/main/resources/static/job_demand.jpg", 0.95f);
    }

    public String popular_areas(){
        DataFrame newdf = this.df.groupBy(2).count().sortBy("-Title").retain("Location", "Count").head(5);
        return dataFrame_toString(newdf);
    }

    public void area_barChart() throws IOException {
        DataFrame newdf = this.df.groupBy(2).count().sortBy("-Type").retain("Location", "Count").head(5);
        List<String> area = newdf.col(0);
        List<Integer> count = newdf.col(1);
        CategoryChart chart = new CategoryChartBuilder()
                .width(1024)
                .title("Most Popular Areas")
                .xAxisTitle("Location")
                .yAxisTitle("Count of Jobs")
                .build();
        chart.addSeries(" ", area, count);
        chart.getStyler().setHasAnnotations(true);
        //new SwingWrapper(chart).displayChart();
        BitmapEncoder.saveJPGWithQuality(chart, "./src/main/resources/static/area_demand.jpg", 0.95f);
    }

    public String popular_skills(){
        List<String> skills = ((List<String> )this.df.col(7).stream().flatMap(a-> Arrays.stream(a.toString().split(","))).collect(Collectors.toList()));
        Map<String, Integer> map_1 = new HashMap<>();
        skills.stream().forEach(a->map_1.put(a, (map_1.get(a)==null)? 1: map_1.get(a)+1));
        List<Map.Entry> skillCount = map_1.entrySet().stream().sorted((a, b)->b.getValue()-a.getValue()).collect(Collectors.toList());

        StringBuilder buf = new StringBuilder();
        buf.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>");
        buf.append("<style>\n" +
                "#customers {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #04AA6D;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>");
        buf.append("</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>");
        buf.append("<th>");
        buf.append("Skills");
        buf.append("</th>");
        buf.append("<th>");
        buf.append("Count");
        buf.append("</th>");
        buf.append("</tr>");
        for(int i = 0; i < 15; i++) {
            buf.append("<tr>");
            buf.append("<td>");
            buf.append(skillCount.get(i).getKey().toString());
            buf.append("</td>");
            buf.append("<td>");
            buf.append(skillCount.get(i).getValue().toString());
            buf.append("</td>");
            buf.append("</tr>");
        }
        buf.append("</table>" +
                "</body>" +
                "</html>");
        return buf.toString();
    }

    private String dataFrame_toString(DataFrame df){
        StringBuilder buf = new StringBuilder();
        buf.append("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>");
        buf.append("<style>\n" +
                "#customers {\n" +
                "  font-family: Arial, Helvetica, sans-serif;\n" +
                "  border-collapse: collapse;\n" +
                "  width: 100%;\n" +
                "}\n" +
                "\n" +
                "#customers td, #customers th {\n" +
                "  border: 1px solid #ddd;\n" +
                "  padding: 8px;\n" +
                "}\n" +
                "\n" +
                "#customers tr:nth-child(even){background-color: #f2f2f2;}\n" +
                "\n" +
                "#customers tr:hover {background-color: #ddd;}\n" +
                "\n" +
                "#customers th {\n" +
                "  padding-top: 12px;\n" +
                "  padding-bottom: 12px;\n" +
                "  text-align: left;\n" +
                "  background-color: #04AA6D;\n" +
                "  color: white;\n" +
                "}\n" +
                "</style>");
        buf.append("</head>\n" +
                "<body>\n" +
                "\n" +
                "<table id=\"customers\">\n" +
                "  <tr>");
        for(Object header: df.columns()){
            buf.append("<th>");
            buf.append(header.toString());
            buf.append("</th>");
        }
        buf.append("</tr>");
        for(int i = 0; i < df.length(); i++) {
            buf.append("<tr>");
            for(int j = 0; j < df.size(); j++){
                buf.append("<td>")
                        .append(df.get(i, j))
                        .append("</td>");
            }
            buf.append("</tr>");
        }
        buf.append("</table>" +
                "</body>" +
                "</html>");
        return buf.toString();
    }
}

