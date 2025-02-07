/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.admin;

import java.io.*;
/**
 *
 * @author alvis
 */
public class TopupSystem {
    private UserManager userManager;

    public TopupSystem(UserManager userManager) {
        this.userManager = userManager;
    }

    public void topup(String userId, String paymentMethod, double amount) {
        User user = userManager.getUserById(userId);
        if (user != null) {
            double newBalance = user.getBalance() + amount;
            user.setBalance(newBalance);

            // Save top-up details to userTopup.txt
            saveTopupDetails(userId, paymentMethod, amount, newBalance);

            // Update the user's balance in users.txt
            userManager.updateUserBalance(user);

            System.out.println("Top-up successful. New balance: " + newBalance);
        } else {
            System.out.println("User not found.");
        }
    }

    private void saveTopupDetails(String userId, String paymentMethod, double amount, double newBalance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userTopup.txt", true))) {
            String line = String.format("%s,%s,%.2f,%.2f%n", userId, paymentMethod, amount, newBalance);
            writer.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
