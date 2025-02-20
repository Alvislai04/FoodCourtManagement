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

public class vendorChart {
 public JPanel createChartPanel() {
        // Create datasets
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

        // Sample data (Replace with your own data source)
        barDataset.addValue(10, "Order Amount", "Day 1");
        barDataset.addValue(15, "Order Amount", "Day 2");
        barDataset.addValue(20, "Order Amount", "Day 3");
        barDataset.addValue(25, "Order Amount", "Day 4");

        lineDataset.addValue(100, "Revenue", "Day 1");
        lineDataset.addValue(150, "Revenue", "Day 2");
        lineDataset.addValue(200, "Revenue", "Day 3");
        lineDataset.addValue(300, "Revenue", "Day 4");

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
            "Order Amount and Revenue", // Chart Title
            "Days",                     // X-Axis Label
            "Amount",                   // Y-Axis Label
            barDataset                  // Dataset
        );

        // Customize the plot
        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Add bar renderer
        BarRenderer barRenderer = new BarRenderer();
        plot.setDataset(0, barDataset);
        plot.setRenderer(0, barRenderer);

        // Add line renderer
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setDataset(1, lineDataset);
        plot.setRenderer(1, lineRenderer);

        // Use secondary axis for the line dataset
        NumberAxis lineAxis = new NumberAxis("Revenue");
        plot.setRangeAxis(1, lineAxis);
        plot.mapDatasetToRangeAxis(1, 1);
        
        // Set lines in front of bars
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(710, 250));
        return chartPanel;
    }
}
