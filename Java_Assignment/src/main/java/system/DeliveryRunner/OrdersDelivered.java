/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.DeliveryRunner;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class OrdersDelivered extends OrderStatistics {
    public OrdersDelivered(JTable taskhistoryTable) {
        super(taskhistoryTable);
    }

    @Override
    public double calculate(String filterOption) {
        int count = 0;
        DefaultTableModel model = (DefaultTableModel) taskhistoryTable.getModel();

        for (int i = 0; i < model.getRowCount(); i++) {
            String date = model.getValueAt(i, 0).toString(); // Column 0 stores the date
            if (matchesFilter(date, filterOption)) {
                count++;
            }
        }

        return count; // Returns double to match parent method
    }
}

