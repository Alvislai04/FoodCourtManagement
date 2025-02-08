/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.DeliveryRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jenna
 */
public class TotalRevenue extends DrDashboard{
    private JTable taskhistoryTable;

    public TotalRevenue(JTable taskhistoryTable) {
        this.taskhistoryTable = taskhistoryTable;
    }

    public double calculateRevenue(String filterOption) {
        DefaultTableModel model = (DefaultTableModel) taskhistoryTable.getModel();
        double totalRevenue = 0.0;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < model.getRowCount(); i++) {
            // Read the date from the table
            String dateStr = model.getValueAt(i, 0).toString();
            LocalDate orderDate = LocalDate.parse(dateStr, formatter);

            // Read the total column 
            double orderTotal = Double.parseDouble(model.getValueAt(i, 7).toString());

            // Apply filter
            if (filterOption.equals("Day") && orderDate.isEqual(today)) {
                totalRevenue += orderTotal * 0.1;
            } else if (filterOption.equals("Week") && orderDate.isAfter(today.minusDays(7))) {
                totalRevenue += orderTotal * 0.1;
            } else if (filterOption.equals("Month") && orderDate.isAfter(today.minusDays(30))) {
                totalRevenue += orderTotal * 0.1;
            }
        }

        return totalRevenue;
    }
}
