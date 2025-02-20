/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.DeliveryRunner;

/**
 *
 * @author Jenna
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

//ENCAPSULATION

public class Chart extends JPanel {
    private JTable taskhistoryTable;
    private JPanel chartPanel;

    // Constructor
    public Chart(JTable taskhistoryTable, JPanel chartPanel) {
        this.taskhistoryTable = taskhistoryTable;
        this.chartPanel = chartPanel;
    }

    // Encapsulated Getters
    public JTable getTaskHistoryTable() {
        return taskhistoryTable;
    }

    public JPanel getChartPanel() {
        return chartPanel;
    }

    // Method to update chart from table
    public void updateChartFromTable(String filterOption) {
        DefaultTableModel model = (DefaultTableModel) taskhistoryTable.getModel();

        double[] revenueData = new double[3]; // For Day, Week, Month
        int[] ordersData = new int[3]; // For Day, Week, Month
        String[] labels = {"Today", "This Week", "This Month"};

        LocalDate today = LocalDate.now();

        for (int i = 0; i < model.getRowCount(); i++) {
            String dateString = model.getValueAt(i, 0).toString(); // Column 0 = Date
            double revenue = Double.parseDouble(model.getValueAt(i, 7).toString()); // Column 7 = total

            LocalDate orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if (orderDate.equals(today)) {
                revenueData[0] += revenue * 0.1;
                ordersData[0]++;
            }
            if (orderDate.isAfter(today.minusDays(7)) && orderDate.isBefore(today.plusDays(1))) {
                revenueData[1] += revenue * 0.1;
                ordersData[1]++;
            }
            if (orderDate.getMonth() == today.getMonth() && orderDate.getYear() == today.getYear()) {
                revenueData[2] += revenue * 0.1;
                ordersData[2]++;
            }
        }

        updateChart(revenueData, ordersData, labels);
    }

    // Method to update chart
    private void updateChart(double[] revenue, int[] orders, String[] labels) {
        CategoryDataset revenueDataset = createRevenueDataset(revenue, labels);
        CategoryDataset ordersDataset = createOrdersDataset(orders, labels);

        JFreeChart chart = ChartFactory.createBarChart(
                "Total Revenue & Orders Delivered",
                "Time Period",
                "Total Revenue (RM)",
                revenueDataset
        );

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setRangeAxis(1, new NumberAxis("Orders Delivered"));
        plot.setDataset(1, ordersDataset);
        plot.mapDatasetToRangeAxis(1, 1);

        BarRenderer barRenderer = new BarRenderer();
        plot.setRenderer(0, barRenderer);

        LineAndShapeRenderer lineRenderer = new LineAndShapeRenderer();
        plot.setRenderer(1, lineRenderer);

        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        ChartPanel chartPanelComponent = new ChartPanel(chart);
        chartPanelComponent.setPreferredSize(new Dimension(620, 220));
        chartPanel.removeAll();
        chartPanel.setLayout(new BorderLayout());
        chartPanel.add(chartPanelComponent, BorderLayout.CENTER);
        chartPanel.revalidate();
        chartPanel.repaint();
    }

    // Private dataset creation methods
    private CategoryDataset createRevenueDataset(double[] revenue, String[] labels) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < revenue.length; i++) {
            dataset.addValue(revenue[i], "Revenue", labels[i]);
        }
        return dataset;
    }

    private CategoryDataset createOrdersDataset(int[] orders, String[] labels) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < orders.length; i++) {
            dataset.addValue(orders[i], "Orders", labels[i]);
        }
        return dataset;
    }
    
}