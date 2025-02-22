package system.Vendor;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class vendorChart {

public JPanel createChart() {
        // Create datasets
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

        // Get today's date and past 7 days
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Maps to store daily order count and revenue
        Map<String, Integer> dailyOrders = new HashMap<>();
        Map<String, Double> dailyRevenue = new HashMap<>();

        // Initialize past 7 days in the map with default 0 values
        for (int i = 6; i >= 0; i--) {
            LocalDate pastDate = today.minusDays(i);
            String dateStr = pastDate.format(formatter);
            dailyOrders.put(dateStr, 0);
            dailyRevenue.put(dateStr, 0.0);
        }

        // Read order data from customerOrder.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 8) continue; // Ensure correct format

                String status = data[7].trim(); // Order status
                if ("Completed".equalsIgnoreCase(status)) {
                    String orderDateStr = data[5].trim(); // Extract date
                    LocalDate orderDate = LocalDate.parse(orderDateStr, formatter);

                    // Check if the order is within the last 7 days
                    if (dailyOrders.containsKey(orderDateStr)) {
                        int quantity = Integer.parseInt(data[3].trim());
                        double revenue = Double.parseDouble(data[4].trim());

                        // Update order count and revenue for that day
                        dailyOrders.put(orderDateStr, dailyOrders.get(orderDateStr) + quantity);
                        dailyRevenue.put(orderDateStr, dailyRevenue.get(orderDateStr) + revenue);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        // Add collected data to the chart datasets
        for (String date : dailyOrders.keySet()) {
            barDataset.addValue(dailyOrders.get(date), "Order Count", date);
            lineDataset.addValue(dailyRevenue.get(date), "Revenue (RM)", date);
        }

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Weekly Orders and Revenue", // Chart Title
            "Date",                        // X-Axis Label
            "Order Count",                 // Y-Axis Label for bars
            barDataset                      // Bar dataset
        );

        // Customize the plot
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Bar Renderer
        BarRenderer barRenderer = new BarRenderer();
        plot.setDataset(0, barDataset);
        plot.setRenderer(0, barRenderer);

        // Line Renderer
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setDataset(1, lineDataset);
        plot.setRenderer(1, lineRenderer);

        // Secondary axis for revenue
        NumberAxis revenueAxis = new NumberAxis("Revenue (RM)");
        plot.setRangeAxis(1, revenueAxis);
        plot.mapDatasetToRangeAxis(1, 1);

        // Render lines in front of bars
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // Create a ChartPanel and return it
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(710, 250));
        return chartPanel;
    }
        
//        
//        // Create datasets
//        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
//        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
//
//        // Sample data (Replace with your own data source)
//        barDataset.addValue(10, "Order Amount", "Day 1");
//        barDataset.addValue(15, "Order Amount", "Day 2");
//        barDataset.addValue(20, "Order Amount", "Day 3");
//        barDataset.addValue(25, "Order Amount", "Day 4");
//
//        lineDataset.addValue(100, "Revenue", "Day 1");
//        lineDataset.addValue(150, "Revenue", "Day 2");
//        lineDataset.addValue(200, "Revenue", "Day 3");
//        lineDataset.addValue(300, "Revenue", "Day 4");
//
//        // Create the bar chart
//        JFreeChart chart = ChartFactory.createBarChart(
//            "Order Amount and Revenue", // Chart Title
//            "Days",                     // X-Axis Label
//            "Amount",                   // Y-Axis Label
//            barDataset                  // Dataset
//        );
//
//        // Customize the plot
//        CategoryPlot plot = (CategoryPlot) chart.getPlot();
//
//        // Add bar renderer
//        BarRenderer barRenderer = new BarRenderer();
//        plot.setDataset(0, barDataset);
//        plot.setRenderer(0, barRenderer);
//
//        // Add line renderer
//        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
//        plot.setDataset(1, lineDataset);
//        plot.setRenderer(1, lineRenderer);
//
//        // Use secondary axis for the line dataset
//        NumberAxis lineAxis = new NumberAxis("Revenue");
//        plot.setRangeAxis(1, lineAxis);
//        plot.mapDatasetToRangeAxis(1, 1);
//
//        // Add the chart to a panel
//        ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(710, 250));
//        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
//        return chartPanel;
//    }
    
}
