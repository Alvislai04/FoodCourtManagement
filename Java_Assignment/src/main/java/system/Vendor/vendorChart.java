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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Bar Chart with Line Plot Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());
            
            // Create the chart panel and add it to the frame
            ChartPanel chartPanel = new ChartPanel(createChart());
            frame.add(chartPanel, BorderLayout.CENTER);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static JFreeChart createChart() {
        // Bar dataset (Order Amount)
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        barDataset.addValue(120, "Order Amount", "Day 1");
        barDataset.addValue(150, "Order Amount", "Day 2");
        barDataset.addValue(80, "Order Amount", "Day 3");
        barDataset.addValue(200, "Order Amount", "Day 4");

        // Line dataset (Total Revenue)
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
        lineDataset.addValue(300, "Total Revenue", "Day 1");
        lineDataset.addValue(400, "Total Revenue", "Day 2");
        lineDataset.addValue(250, "Total Revenue", "Day 3");
        lineDataset.addValue(500, "Total Revenue", "Day 4");

        // Create the bar chart
        JFreeChart chart = ChartFactory.createBarChart(
                "Order Amount and Revenue",
                "Day",
                "Amount",
                barDataset
        );

        // Customize the chart
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        
        // Bar Renderer
        BarRenderer barRenderer = new BarRenderer();
        plot.setRenderer(0, barRenderer);
        plot.setDataset(0, barDataset);

        // Line Renderer
        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setRenderer(1, lineRenderer);
        plot.setDataset(1, lineDataset);

        // Set axis for Line Renderer
        NumberAxis axis2 = new NumberAxis("Revenue");
        plot.setRangeAxis(1, axis2);
        plot.mapDatasetToRangeAxis(1, 1); // Map Line Dataset to secondary axis

        // Customize rendering order
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        return chart;
    }
}
