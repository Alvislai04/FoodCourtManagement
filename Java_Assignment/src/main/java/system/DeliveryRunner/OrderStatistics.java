/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.DeliveryRunner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class OrderStatistics {
    protected JTable taskhistoryTable;

    public OrderStatistics(JTable taskhistoryTable) {
        this.taskhistoryTable = taskhistoryTable;
    }

    public abstract double calculate(String filterOption);

    protected boolean matchesFilter(String date, String filterOption) {
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
                return true; // No filter applied
        }
    }
}
