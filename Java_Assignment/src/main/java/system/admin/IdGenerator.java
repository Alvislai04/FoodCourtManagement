package system.admin;

import java.io.*;

public class IdGenerator {

    public static String getNextRoleID(String prefix, String roleFilePath) {
        File file = new File(roleFilePath);

        int maxID = 0; // Store highest ID for the given prefix

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";"); // Split by semicolon
                if (tokens.length >= 1) {
                    String idStr = tokens[0].trim(); // First column is the ID

                    // Check if the ID starts with the desired prefix
                    if (idStr.startsWith(prefix)) {
                        // Extract numeric part (everything after prefix)
                        String numericPart = idStr.replaceFirst(prefix, ""); // Remove prefix
                        try {
                            int numericID = Integer.parseInt(numericPart);
                            if (numericID > maxID) {
                                maxID = numericID; // Update max ID
                            }
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid ID format: " + idStr);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Increment maxID to get the next available ID
        int nextID = maxID + 1;

        // Format the numeric part to always have 2 digits (e.g., "01", "02", ...)
        String formattedID = String.format("%02d", nextID);

        // Return the next ID with the prefix
        return prefix + formattedID;
    }
}