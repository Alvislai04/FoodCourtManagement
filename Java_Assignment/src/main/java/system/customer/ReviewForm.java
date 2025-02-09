/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package system.customer;

/**
 *
 * @author Jenna
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ReviewForm extends JFrame {
    private JComboBox<Integer> starRating;
    private JTextArea reviewText;
    private JButton submitButton;
    private JButton skipButton;
    public String runnerId; //runner id

    public ReviewForm(String runnerId) {
        this.runnerId = runnerId;
        setTitle("Order Delivered! Leave a Review");
        setSize(400, 300);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(153, 89, 16));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(153, 89, 16));
        JLabel label = new JLabel("Rate your delivery experience:");
        label.setForeground(Color.WHITE);
        topPanel.add(label);
        
        starRating = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5});
        topPanel.add(starRating);
        
        add(topPanel, BorderLayout.NORTH);
        
        reviewText = new JTextArea("Write your review here...");
        add(new JScrollPane(reviewText), BorderLayout.CENTER);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(153, 89, 16));
        submitButton = new JButton("Submit");
        submitButton.setEnabled(false);
        skipButton = new JButton("Skip");
        
        buttonPanel.add(submitButton);
        buttonPanel.add(skipButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        starRating.addActionListener(e -> submitButton.setEnabled(true));
        
        submitButton.addActionListener(e -> saveReview());
        skipButton.addActionListener(e -> dispose());
    }

    private void saveReview() {
        String rating = starRating.getSelectedItem().toString();
        String review = reviewText.getText().trim();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("reviews.txt", true))) {
            writer.write(runnerId + "," + rating + "," + review);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        dispose();
    }
    
}
