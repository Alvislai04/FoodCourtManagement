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
public ChartPanel createChartPanel(DefaultCategoryDataset barDataset, DefaultCategoryDataset lineDataset) {
        // Create the chart with the bar dataset
        JFreeChart chart = ChartFactory.createBarChart(
            "Completed Orders and Revenue",  // Chart title
            "Date",                          // X-Axis label
            "Order Count",                   // Y-Axis label for bars
            barDataset                       // Bar dataset
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        // Configure bar renderer
        BarRenderer barRenderer = new BarRenderer();
        plot.setDataset(0, barDataset);
        plot.setRenderer(0, barRenderer);

        // Add line dataset for revenue
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setDataset(1, lineDataset);
        plot.setRenderer(1, lineRenderer);

        // Add secondary axis for revenue
        NumberAxis revenueAxis = new NumberAxis("Revenue (RM)");
        plot.setRangeAxis(1, revenueAxis);
        plot.mapDatasetToRangeAxis(1, 1); // Map line dataset to secondary axis

        // Ensure lines render in front of bars
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        // Create and return the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(710, 250));
        return chartPanel;
    }
}
