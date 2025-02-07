/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.admin;

import java.io.*;
import java.util.*;

/**
 *
 * @author alvis
 */
public class UserManager {
      private List<User> users;

    public UserManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    private void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String address = parts[2];
                String phonenumber = parts[3];
                String username = parts[4];
                String password = parts[5];
                String roles = parts[6];
                double balance = Double.parseDouble(parts[7]);
                users.add(new User(id, name, address, phonenumber, username, password, roles, balance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public void updateUserBalance(User user) {
        // Update the user's balance in the users.txt file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt"))) {
            for (User u : users) {
                String line = String.format("%s,%s,%s,%s,%s,%s,%s,%.2f%n",
                        u.getId(), u.getName(), u.getAddress(), u.getphoneNo(),
                        u.getUsername(), u.getPassword(), u.getRoles(), u.getBalance());
                writer.write(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
