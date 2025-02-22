package system.Vendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;


public class vendorRevenue {
    public static double calculateRevenue() {
            LocalDate today = LocalDate.now(); // Get today's date
            double totalRevenue = 0.0;

            try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
                String line;

                // Read each order and accumulate data for today
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length < 8) continue; // Ensure there are 8 fields

                    String status = data[7].trim();
                    if ("Completed".equalsIgnoreCase(status)) {
                        String dateString = data[5].trim(); // Extract date (e.g., "21/02/2025")
                        LocalDate orderDate = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                        // Check if the order date is today
                        if (orderDate.equals(today)) {
                            double totalPrice = Double.parseDouble(data[4].trim());
                            totalRevenue += totalPrice;
                        }
                    }
                }
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                System.out.println(e);
            }

            return totalRevenue;
        }
}