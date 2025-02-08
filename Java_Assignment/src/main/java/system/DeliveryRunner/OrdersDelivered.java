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
public class OrdersDelivered extends DrDashboard{
    private JTable taskhistoryTable;

    public OrdersDelivered(JTable taskhistoryTable) {
        this.taskhistoryTable = taskhistoryTable;
    
    }
    
    public int countOrders(String filterOption) {
        int count = 0;
        DefaultTableModel model = (DefaultTableModel) taskhistoryTable.getModel();
        
        for (int i = 0; i < model.getRowCount(); i++) {
            String date = model.getValueAt(i, 0).toString(); // Column 0 stores the date
            if (matchesFilter(date, filterOption)) {
                count++;
            }
        }
        
        return count;
    }

    private boolean matchesFilter(String date, String filterOption) {
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate today = LocalDate.now();

        switch (filterOption.toLowerCase()) {
            case "day":
                return orderDate.equals(today);
            case "week":
                return orderDate.isAfter(today.minusDays(7)) && orderDate.isBefore(today.plusDays(1));
            case "month":
                return orderDate.getMonth() == today.getMonth() && orderDate.getYear() == today.getYear();
            default:
                return true; // Show all if no filter
        }
    }

}
