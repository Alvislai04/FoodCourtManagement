
package system.Vendor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class customerTotalOrders {
    public static int countTotalOrders() {
            int totalCustOrders = 0;

            try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
                // Read each line (order) in the file
                while (reader.readLine() != null) {
                    totalCustOrders++; // Increment the count for each order
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error reading customerOrder.txt: " + e.getMessage());
            }

            return totalCustOrders;
        }
}
