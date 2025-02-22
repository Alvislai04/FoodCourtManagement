/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package system.customer;

import com.system.Login;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import system.admin.TransactionPanel;

/**
 *
 * @author nickkhawchunmin
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        notificationScrollPane.setViewportView(notificationListPanel);
        this.setLocationRelativeTo(null);//to center the gui form
        this.pack();//to make gui full screen

        this.setSize(1100, 650);// Set the size of the frame
        this.setResizable(false);// Prevent the frame from being resizable

        MenuTab.setBackground(Color.black);//set OrderTab to black and order Opened after login
        NotificationPanel.setVisible(false);
        TransactionPanel panel = new TransactionPanel();
        panel.loadUserTransaction(Login.getLoggedInUserId());
        populateOrderStatusTable();
        populateOrderHistoryTable();
        loadFoodIDs();
        loadFoodImages();
    }
    
    String vendorFoodFilePath = "vendorFood.txt";
    
    // Overloaded method for normal clicks
private void openOrderPanel(int index) {
    openOrderPanel(index, null, null, null);
}

// Updated method for reordering
private void openOrderPanel(Integer index, String reorderFoodName, String reorderPrice, String reorderQuantity) {
    JLabel[] descriptionFood = {descriptionFoodId, descriptionFoodName, descriptionFoodPrice};
    String selectedFoodID = null; // Store the selected Food ID

    if (index != null) { // Case 1: Opening from FoodPanel
        try (BufferedReader reader = new BufferedReader(new FileReader("vendorFood.txt"))) {
            String line;
            int currentIndex = 0;

            while ((line = reader.readLine()) != null) {
                if (currentIndex == index) {
                    String[] data = line.split(",");
                    selectedFoodID = data[0].trim(); // Store Food ID
                    descriptionFood[0].setText("ID: " + selectedFoodID);
                    descriptionFood[1].setText("Name: " + data[1]);
                    descriptionFood[2].setText("Price: " + data[2]);
                    System.out.println("Selected Food ID: " + selectedFoodID); // Debugging
                    break;
                }
                currentIndex++;
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error reading vendorFood.txt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    } else { // Case 2: Opening from Reorder
        descriptionFood[0].setText(""); // No need for ID
        descriptionFood[1].setText("Name: " + reorderFoodName);
        descriptionFood[2].setText("Price: " + reorderPrice);
        quantity.setText(reorderQuantity); // Pre-fill quantity
    }

    // If a Food ID was selected, load its reviews
    if (selectedFoodID != null) {
        System.out.println("Calling loadReviewsForFood with ID: " + selectedFoodID); // Debugging
        loadReviewsForFood(selectedFoodID);
    }

    // Show OrderPanel
    JOptionPane optionPane = new JOptionPane(
        OrderPanel, 
        JOptionPane.PLAIN_MESSAGE, 
        JOptionPane.DEFAULT_OPTION, 
        null, 
        new Object[]{}
    );

    JDialog dialog = optionPane.createDialog("Food Details");
    dialog.setVisible(true);
    dialog.pack();
}


    private void loadFoodImages () {

        JLabel[] foodIcon = {foodIcon1, foodIcon2, foodIcon3, foodIcon4, foodIcon5, foodIcon6};
        JLabel[] foodLabels = {foodLabel1, foodLabel2, foodLabel3, foodLabel4, foodLabel5, foodLabel6};
        
         for (int i = 0; i < foodIcon.length; i++) {
        foodIcon[i].setIcon(null); // Clear the icon
        foodLabels[i].setText(""); // Clear the text
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(vendorFoodFilePath))) {
            String line;
            int index = 0; // Track the current label index

            while ((line = reader.readLine()) != null && index < foodIcon.length) {
                String[] data = line.split(",");
                String foodId = data[0];
                String foodName = data[1];
                String price = data[2];
                String imagePath = data[3]; // Relative path to the image

                // Load the image
                File imageFile = new File(imagePath);
                if (imageFile.exists()) {
                    BufferedImage image = ImageIO.read(imageFile);


                    // Resize the image (optional)
                    Image scaledImage = image.getScaledInstance(
                        187, 179, Image.SCALE_SMOOTH
                    );
                    ImageIcon icon = new ImageIcon(scaledImage);

                    // Set the icon and text for the current label
                    foodIcon[index].setIcon(icon);
                    foodLabels[index].setText(foodName + " - " + price);
                } else {
                    System.out.println("Image not found: " + imagePath);
                }

                index++; // Move to the next label
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading vendorFood.txt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Force the GUI to refresh
        MenuPanel.revalidate();
        MenuPanel.repaint();
    }
    
   private void saveOrder(String orderId, String customerEmail, String foodName, int quantity, String price, String orderDate, String orderTime, String status) {
    String orderFilePath = "customerOrder.txt"; // Ensure the correct filename

    System.out.println("Inside saveOrder method...");

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFilePath, true))) {
        // New order format: Separate Date and Time
        String orderData = orderId + "," + customerEmail + "," + foodName + "," + quantity + "," 
                         + price + "," + orderDate + "," + orderTime + "," + status;
        
        System.out.println("Writing to file: " + orderData);
        writer.write(orderData);
        writer.newLine();
        writer.flush(); // Ensure data is written immediately

        System.out.println("Order saved successfully!");

    } catch (IOException ex) {
        System.out.println("Failed to write to file!");
        ex.printStackTrace();
        JOptionPane.showMessageDialog(OrderPanel, "Error saving order! Check file permissions.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


   
   private String generateOrderId() {
    String orderFilePath = "customerOrder.txt";
    int lastOrderNumber = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(orderFilePath))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith("ORD")) { // Check if line starts with 'ORD'
                String[] data = line.split(",");
                String lastOrderId = data[0]; // Example: "ORD003"

                // Extract numeric part (003)
                String numberPart = lastOrderId.substring(3); 
                int orderNumber = Integer.parseInt(numberPart);
                lastOrderNumber = Math.max(lastOrderNumber, orderNumber); // Keep track of max ID
            }
        }
    } catch (IOException | NumberFormatException ex) {
        ex.printStackTrace(); // Print error for debugging
    }

    // Increment and format new order ID
    int newOrderNumber = lastOrderNumber + 1;
    return String.format("ORD%03d", newOrderNumber); // ORD001, ORD002, etc.
}
   
   private void populateOrderStatusTable() {
    // Define column names (excluding customer email)
    String[] columnNames = {"Order ID", "Food Ordered", "Quantity", "Total Price", "Order Date", "Order Time", "Status"};

    // Use the correct DefaultTableModel
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    StatusjTable1.setModel(model); // Set model to the table
    StatusjTable1.setRowHeight(50);

    // **Clear table before loading new data**
    model.setRowCount(0);

    try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // Ensure the row has exactly 8 columns (due to new date/time format)
            if (data.length == 8) {
                String orderId = data[0];     // Order ID
                String foodOrdered = data[2]; // Food Name
                String quantity = data[3];    // Quantity
                String totalPrice = data[4];  // Total Price
                String orderDate = data[5];   // Order Date (New Format)
                String orderTime = data[6];   // Order Time (New Format)
                String status = data[7];      // Status

                // Add only necessary data to the table
                model.addRow(new Object[]{orderId, foodOrdered, quantity, totalPrice, orderDate, orderTime, status});
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error reading customerOrder.txt!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   
   private void populateOrderHistoryTable() {
    // Define column names (excluding customer email and status)
    String[] columnNames = {"Order ID", "Food Ordered", "Quantity", "Transaction", "Order Date", "Order Time"};

    // Use the correct DefaultTableModel
    DefaultTableModel model = new DefaultTableModel(columnNames, 0);
    HistoryjTable1.setModel(model); // Set model to the table
    HistoryjTable1.setRowHeight(50);

    // **Clear table before loading new data**
    model.setRowCount(0);

    try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // Ensure the row has exactly 8 columns (due to new date/time format)
            if (data.length == 8) {
                String orderId = data[0];     // Order ID
                String foodOrdered = data[2]; // Food Name
                String quantity = data[3];    // Quantity
                String transaction = data[4]; // Transaction (Total Price)
                String orderDate = data[5];   // Order Date
                String orderTime = data[6];   // Order Time
                String status = data[7].trim(); // Status (Remove any extra spaces)

                // **Only add orders where the status is "Completed"**
                if (status.equalsIgnoreCase("Completed")) {
                    model.addRow(new Object[]{orderId, foodOrdered, quantity, transaction, orderDate, orderTime});
                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error reading customerOrder.txt!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   
   private String generateNewComplaintID() {
    int lastID = 0;

    // Read the last complaint ID from complaint.txt
    try (BufferedReader reader = new BufferedReader(new FileReader("complaint.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length > 0 && data[0].startsWith("CMP")) {
                int currentID = Integer.parseInt(data[0].substring(3)); // Extract numeric part of ID
                if (currentID > lastID) {
                    lastID = currentID;
                }
            }
        }
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }

    // Generate new ID (e.g., CMP001, CMP002)
    return String.format("CMP%03d", lastID + 1);
}

   private void loadFoodIDs() {
    try (BufferedReader reader = new BufferedReader(new FileReader("vendorFood.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] foodDetails = line.split(",");
            if (foodDetails.length > 0) {
                String foodID = foodDetails[0]; // Extract FoodID (e.g., V001-F001)
                FoodIDComboBox.addItem(foodID); // Add FoodID to ComboBox
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading food IDs!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   private void openAddReviewPanel() {
    // Show AddReviewPanel inside a dialog
    JOptionPane optionPane = new JOptionPane(
        AddReviewPanel, 
        JOptionPane.PLAIN_MESSAGE, 
        JOptionPane.DEFAULT_OPTION, 
        null, 
        new Object[]{} // No buttons
    );

    JDialog dialog = optionPane.createDialog(this, "Add Food Review");
    dialog.pack(); // Adjust size
    dialog.setVisible(true);
}
   
   private String generateNewReviewID() {
    int lastID = 0;
    
    // Read last review ID from foodReview.txt
    try (BufferedReader reader = new BufferedReader(new FileReader("foodReview.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length > 0 && parts[0].startsWith("REV")) {
                int currentID = Integer.parseInt(parts[0].substring(3)); // Extract number from "REVxxx"
                if (currentID > lastID) {
                    lastID = currentID;
                }
            }
        }
    } catch (IOException e) {
        System.out.println("No previous reviews found.");
    }

    // Generate new Review ID
    return "REV" + String.format("%03d", lastID + 1);
}

   private void loadReviewsForFood(String foodID) {
    DefaultTableModel model = (DefaultTableModel) ReviewjTable.getModel();
    model.setRowCount(0); // Clear previous reviews

    System.out.println("Loading reviews for Food ID: " + foodID); // Debugging

    try (BufferedReader reader = new BufferedReader(new FileReader("foodReview.txt"))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",", 4); // Ensure it splits into 4 parts: ReviewID, FoodID, Rate, ReviewText
            System.out.println("Checking review line: " + line); // Debugging

            if (data.length == 4 && data[1].trim().equals(foodID)) {
                System.out.println("Matched Review: " + line); // Debugging
                // Add only Rating and Review to JTable
                model.addRow(new Object[]{data[2], data[3]});
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error loading reviews!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        OrderPanel = new javax.swing.JPanel();
        quantity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        OrderBtn = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        descriptionFoodName = new javax.swing.JLabel();
        descriptionFoodPrice = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        descriptionFoodId = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ReviewjTable = new javax.swing.JTable();
        AddReviewPanel = new javax.swing.JPanel();
        FoodIDComboBox = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        AddReviewTextArea = new javax.swing.JTextArea();
        RateComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        SubmitBtn = new javax.swing.JButton();
        SkipBtn = new javax.swing.JButton();
        title = new javax.swing.JPanel();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        menubar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        MenuTab = new javax.swing.JLabel();
        OrderStatusTab = new javax.swing.JLabel();
        OrderHistoryTab = new javax.swing.JLabel();
        ComplaintTab = new javax.swing.JLabel();
        NotificationTab = new javax.swing.JLabel();
        line = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        NotificationPanel = new javax.swing.JPanel();
        notificationScrollPane = new javax.swing.JScrollPane();
        notificationListPanel = new javax.swing.JPanel();
        MenuPanel = new javax.swing.JPanel();
        foodLabel3 = new javax.swing.JLabel();
        foodLabel4 = new javax.swing.JLabel();
        foodLabel5 = new javax.swing.JLabel();
        foodIcon3 = new javax.swing.JLabel();
        foodLabel6 = new javax.swing.JLabel();
        foodIcon2 = new javax.swing.JLabel();
        foodIcon5 = new javax.swing.JLabel();
        foodIcon6 = new javax.swing.JLabel();
        foodIcon4 = new javax.swing.JLabel();
        foodIcon1 = new javax.swing.JLabel();
        foodLabel1 = new javax.swing.JLabel();
        foodLabel2 = new javax.swing.JLabel();
        OrderStatusPanel = new javax.swing.JPanel();
        LeaveReviewBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        StatusjTable1 = new javax.swing.JTable();
        CancelOrder = new javax.swing.JLabel();
        FoodReview = new javax.swing.JLabel();
        OrderHistoryPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        HistoryjTable1 = new javax.swing.JTable();
        Reorder = new javax.swing.JLabel();
        Receipt = new javax.swing.JLabel();
        ComplaintPanel = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        ComplaintTextArea = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        Submit = new javax.swing.JLabel();
        ComplaintTypeComboBox = new javax.swing.JComboBox<>();

        quantity.setText("0");
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });

        jLabel2.setText("Quantity:");

        OrderBtn.setText("Order");
        OrderBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OrderBtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel3.setText("Order");

        descriptionFoodName.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodName.setText("foodName");

        descriptionFoodPrice.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodPrice.setText("foodPrice");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setText("Vendor - Food ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Food Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Food Price");

        descriptionFoodId.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodId.setText("foodID");

        ReviewjTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Rating", "Review"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(ReviewjTable);
        if (ReviewjTable.getColumnModel().getColumnCount() > 0) {
            ReviewjTable.getColumnModel().getColumn(0).setResizable(false);
        }

        javax.swing.GroupLayout OrderPanelLayout = new javax.swing.GroupLayout(OrderPanel);
        OrderPanel.setLayout(OrderPanelLayout);
        OrderPanelLayout.setHorizontalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionFoodPrice)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(descriptionFoodId)
                    .addComponent(descriptionFoodName)
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(OrderBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addContainerGap())
        );
        OrderPanelLayout.setVerticalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionFoodId)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionFoodName)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descriptionFoodPrice)
                        .addGap(18, 18, 18)
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(OrderBtn)
                        .addGap(0, 7, Short.MAX_VALUE))))
        );

        jLabel8.setText("Enter Your Review:");

        AddReviewTextArea.setColumns(20);
        AddReviewTextArea.setRows(5);
        jScrollPane5.setViewportView(AddReviewTextArea);

        RateComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "5", "4", "3", "2", "1" }));
        RateComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RateComboBoxActionPerformed(evt);
            }
        });

        jLabel9.setText("Select Food ID: ");

        jLabel10.setText("Rate:");

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel11.setText("ADD REVIEW");

        SubmitBtn.setText("Submit");
        SubmitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitBtnActionPerformed(evt);
            }
        });

        SkipBtn.setText("Skip");
        SkipBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SkipBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddReviewPanelLayout = new javax.swing.GroupLayout(AddReviewPanel);
        AddReviewPanel.setLayout(AddReviewPanelLayout);
        AddReviewPanelLayout.setHorizontalGroup(
            AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddReviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(AddReviewPanelLayout.createSequentialGroup()
                        .addComponent(SkipBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(SubmitBtn))
                    .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addGroup(AddReviewPanelLayout.createSequentialGroup()
                            .addGap(93, 93, 93)
                            .addComponent(FoodIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddReviewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(RateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(143, 143, 143))
        );
        AddReviewPanelLayout.setVerticalGroup(
            AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AddReviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(FoodIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(RateComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(AddReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SubmitBtn)
                    .addComponent(SkipBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        title.setBackground(new java.awt.Color(0, 0, 0));

        title_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        title_lbl1.setText("TAS");

        title_lbl2.setBackground(new java.awt.Color(0, 0, 0));
        title_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl2.setForeground(new java.awt.Color(153, 89, 16));
        title_lbl2.setText("TIES");

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titleLayout = new javax.swing.GroupLayout(title);
        title.setLayout(titleLayout);
        titleLayout.setHorizontalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addComponent(title_lbl1)
                .addGap(1, 1, 1)
                .addComponent(title_lbl2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout)
                .addContainerGap())
        );
        titleLayout.setVerticalGroup(
            titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titleLayout.createSequentialGroup()
                .addGroup(titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(title_lbl1)
                        .addComponent(title_lbl2))
                    .addGroup(titleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logout)))
                .addContainerGap())
        );

        menubar.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome");

        MenuTab.setBackground(new java.awt.Color(153, 89, 16));
        MenuTab.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        MenuTab.setForeground(new java.awt.Color(255, 255, 255));
        MenuTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuTab.setText("Menu");
        MenuTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        MenuTab.setOpaque(true);
        MenuTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                MenuTabMousePressed(evt);
            }
        });

        OrderStatusTab.setBackground(new java.awt.Color(153, 89, 16));
        OrderStatusTab.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        OrderStatusTab.setForeground(new java.awt.Color(255, 255, 255));
        OrderStatusTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OrderStatusTab.setText("Order Status");
        OrderStatusTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OrderStatusTab.setOpaque(true);
        OrderStatusTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderStatusTabMousePressed(evt);
            }
        });

        OrderHistoryTab.setBackground(new java.awt.Color(153, 89, 16));
        OrderHistoryTab.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        OrderHistoryTab.setForeground(new java.awt.Color(255, 255, 255));
        OrderHistoryTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        OrderHistoryTab.setText("Order History");
        OrderHistoryTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        OrderHistoryTab.setOpaque(true);
        OrderHistoryTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                OrderHistoryTabMousePressed(evt);
            }
        });

        ComplaintTab.setBackground(new java.awt.Color(153, 89, 16));
        ComplaintTab.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        ComplaintTab.setForeground(new java.awt.Color(255, 255, 255));
        ComplaintTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ComplaintTab.setText("Complaint");
        ComplaintTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ComplaintTab.setOpaque(true);
        ComplaintTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ComplaintTabMousePressed(evt);
            }
        });

        NotificationTab.setBackground(new java.awt.Color(153, 89, 16));
        NotificationTab.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        NotificationTab.setForeground(new java.awt.Color(255, 255, 255));
        NotificationTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NotificationTab.setText("Notification");
        NotificationTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NotificationTab.setOpaque(true);
        NotificationTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                NotificationTabMousePressed(evt);
            }
        });

        javax.swing.GroupLayout menubarLayout = new javax.swing.GroupLayout(menubar);
        menubar.setLayout(menubarLayout);
        menubarLayout.setHorizontalGroup(
            menubarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menubarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menubarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComplaintTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OrderHistoryTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(OrderStatusTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(MenuTab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addComponent(NotificationTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        menubarLayout.setVerticalGroup(
            menubarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menubarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MenuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OrderStatusTab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(OrderHistoryTab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ComplaintTab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(NotificationTab, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(156, Short.MAX_VALUE))
        );

        line.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout lineLayout = new javax.swing.GroupLayout(line);
        line.setLayout(lineLayout);
        lineLayout.setHorizontalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        lineLayout.setVerticalGroup(
            lineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new javax.swing.OverlayLayout(jPanel1));

        NotificationPanel.setBackground(new java.awt.Color(255, 255, 255));

        notificationListPanel.setLayout(new javax.swing.BoxLayout(notificationListPanel, javax.swing.BoxLayout.Y_AXIS));
        notificationScrollPane.setViewportView(notificationListPanel);

        javax.swing.GroupLayout NotificationPanelLayout = new javax.swing.GroupLayout(NotificationPanel);
        NotificationPanel.setLayout(NotificationPanelLayout);
        NotificationPanelLayout.setHorizontalGroup(
            NotificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NotificationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notificationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                .addContainerGap())
        );
        NotificationPanelLayout.setVerticalGroup(
            NotificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NotificationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notificationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 609, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(NotificationPanel);

        MenuPanel.setBackground(new java.awt.Color(255, 255, 255));

        foodLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel3.setText("Food Number 3");

        foodLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel4.setText("Food Number 4");

        foodLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel5.setText("Food Number 5");

        foodIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon3MouseClicked(evt);
            }
        });

        foodLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel6.setText("Food Number 6");

        foodIcon2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon2MouseClicked(evt);
            }
        });

        foodIcon5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon5MouseClicked(evt);
            }
        });

        foodIcon6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon6MouseClicked(evt);
            }
        });

        foodIcon4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon4MouseClicked(evt);
            }
        });

        foodIcon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon1MouseClicked(evt);
            }
        });

        foodLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel1.setText("Food Number 1");

        foodLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel2.setText("Food Number 2");

        javax.swing.GroupLayout MenuPanelLayout = new javax.swing.GroupLayout(MenuPanel);
        MenuPanel.setLayout(MenuPanelLayout);
        MenuPanelLayout.setHorizontalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 912, Short.MAX_VALUE)
            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MenuPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuPanelLayout.createSequentialGroup()
                            .addGap(4, 4, 4)
                            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(foodIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(foodIcon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(60, 60, 60)
                    .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(foodLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(foodLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(60, 60, 60)
                    .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(foodIcon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(foodIcon6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        MenuPanelLayout.setVerticalGroup(
            MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 621, Short.MAX_VALUE)
            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MenuPanelLayout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, MenuPanelLayout.createSequentialGroup()
                            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(foodIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(MenuPanelLayout.createSequentialGroup()
                                    .addGap(2, 2, 2)
                                    .addComponent(foodIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(MenuPanelLayout.createSequentialGroup()
                                    .addGap(6, 6, 6)
                                    .addComponent(foodLabel3))
                                .addGroup(MenuPanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(foodLabel1)))
                            .addGap(39, 39, 39)
                            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(foodIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(foodIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(MenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(foodLabel4)
                                .addComponent(foodLabel6)))
                        .addGroup(MenuPanelLayout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foodIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(foodLabel2)
                            .addGap(39, 39, 39)
                            .addComponent(foodIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(foodLabel5)))
                    .addContainerGap(141, Short.MAX_VALUE)))
        );

        jPanel1.add(MenuPanel);

        OrderStatusPanel.setBackground(new java.awt.Color(255, 255, 255));

        LeaveReviewBtn.setText("DELIVERY REVIEW");
        LeaveReviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveReviewBtnActionPerformed(evt);
            }
        });

        StatusjTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Food Ordered", "Quantity", "Total Price", "Order Date", "Order Time", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(StatusjTable1);
        if (StatusjTable1.getColumnModel().getColumnCount() > 0) {
            StatusjTable1.getColumnModel().getColumn(0).setResizable(false);
            StatusjTable1.getColumnModel().getColumn(1).setResizable(false);
            StatusjTable1.getColumnModel().getColumn(2).setResizable(false);
            StatusjTable1.getColumnModel().getColumn(3).setResizable(false);
            StatusjTable1.getColumnModel().getColumn(5).setResizable(false);
            StatusjTable1.getColumnModel().getColumn(6).setResizable(false);
        }

        CancelOrder.setBackground(new java.awt.Color(153, 89, 16));
        CancelOrder.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        CancelOrder.setForeground(new java.awt.Color(255, 255, 255));
        CancelOrder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CancelOrder.setText("Cancel Order");
        CancelOrder.setOpaque(true);
        CancelOrder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CancelOrderMousePressed(evt);
            }
        });

        FoodReview.setBackground(new java.awt.Color(153, 89, 16));
        FoodReview.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        FoodReview.setForeground(new java.awt.Color(255, 255, 255));
        FoodReview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FoodReview.setText("Food Review");
        FoodReview.setOpaque(true);
        FoodReview.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FoodReviewMousePressed(evt);
            }
        });

        javax.swing.GroupLayout OrderStatusPanelLayout = new javax.swing.GroupLayout(OrderStatusPanel);
        OrderStatusPanel.setLayout(OrderStatusPanelLayout);
        OrderStatusPanelLayout.setHorizontalGroup(
            OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderStatusPanelLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(OrderStatusPanelLayout.createSequentialGroup()
                        .addComponent(FoodReview, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(LeaveReviewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(CancelOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 746, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(151, Short.MAX_VALUE))
        );
        OrderStatusPanelLayout.setVerticalGroup(
            OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderStatusPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FoodReview, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LeaveReviewBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(81, Short.MAX_VALUE))
        );

        jPanel1.add(OrderStatusPanel);

        OrderHistoryPanel.setBackground(new java.awt.Color(255, 255, 255));

        HistoryjTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Food Ordered", "Quantity", "Transaction", "Order Date", "Order Time"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(HistoryjTable1);
        if (HistoryjTable1.getColumnModel().getColumnCount() > 0) {
            HistoryjTable1.getColumnModel().getColumn(0).setResizable(false);
            HistoryjTable1.getColumnModel().getColumn(1).setResizable(false);
            HistoryjTable1.getColumnModel().getColumn(2).setResizable(false);
            HistoryjTable1.getColumnModel().getColumn(3).setResizable(false);
            HistoryjTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        Reorder.setBackground(new java.awt.Color(153, 89, 16));
        Reorder.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Reorder.setForeground(new java.awt.Color(255, 255, 255));
        Reorder.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reorder.setText("Reorder");
        Reorder.setOpaque(true);
        Reorder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ReorderMousePressed(evt);
            }
        });

        Receipt.setBackground(new java.awt.Color(153, 89, 16));
        Receipt.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Receipt.setForeground(new java.awt.Color(255, 255, 255));
        Receipt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Receipt.setText("Receipt");
        Receipt.setOpaque(true);
        Receipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ReceiptMousePressed(evt);
            }
        });

        javax.swing.GroupLayout OrderHistoryPanelLayout = new javax.swing.GroupLayout(OrderHistoryPanel);
        OrderHistoryPanel.setLayout(OrderHistoryPanelLayout);
        OrderHistoryPanelLayout.setHorizontalGroup(
            OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderHistoryPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(OrderHistoryPanelLayout.createSequentialGroup()
                        .addComponent(Receipt, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 698, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        OrderHistoryPanelLayout.setVerticalGroup(
            OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderHistoryPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 483, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Receipt, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel1.add(OrderHistoryPanel);

        ComplaintPanel.setBackground(new java.awt.Color(255, 255, 255));

        ComplaintTextArea.setColumns(20);
        ComplaintTextArea.setRows(5);
        jScrollPane4.setViewportView(ComplaintTextArea);

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel7.setText("Describe your complaint below:Describe your complaint below:");

        Submit.setBackground(new java.awt.Color(153, 89, 16));
        Submit.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        Submit.setForeground(new java.awt.Color(255, 255, 255));
        Submit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Submit.setText("Submit");
        Submit.setOpaque(true);
        Submit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                SubmitMousePressed(evt);
            }
        });

        ComplaintTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "Food" }));

        javax.swing.GroupLayout ComplaintPanelLayout = new javax.swing.GroupLayout(ComplaintPanel);
        ComplaintPanel.setLayout(ComplaintPanelLayout);
        ComplaintPanelLayout.setHorizontalGroup(
            ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComplaintPanelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(ComplaintPanelLayout.createSequentialGroup()
                        .addComponent(ComplaintTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane4)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(358, Short.MAX_VALUE))
        );
        ComplaintPanelLayout.setVerticalGroup(
            ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ComplaintPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComplaintTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(211, Short.MAX_VALUE))
        );

        jPanel1.add(ComplaintPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menubar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(line, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menubar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(line, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 10, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        goToLogout();
    }//GEN-LAST:event_logoutActionPerformed

    private void MenuTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MenuTabMousePressed
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(true);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(false);
        MenuTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationTab.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_MenuTabMousePressed

    private void OrderStatusTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderStatusTabMousePressed
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(true);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(false);
        OrderStatusTab.setBackground(Color.black);
        MenuTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationTab.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_OrderStatusTabMousePressed

    private void OrderHistoryTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderHistoryTabMousePressed
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(true);
        ComplaintPanel.setVisible(false);
        OrderHistoryTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        MenuTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationTab.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_OrderHistoryTabMousePressed

    private void ComplaintTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComplaintTabMousePressed
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(true);
        ComplaintTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        MenuTab.setBackground(new Color(153, 89, 16));
        NotificationTab.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_ComplaintTabMousePressed

    private void NotificationTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NotificationTabMousePressed
        NotificationPanel.setVisible(true);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(false);
        NotificationTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        MenuTab.setBackground(new Color(153, 89, 16));

        String loggedInUser = Login.getLoggedInUserId(); // Example: "C01"

        if (loggedInUser != null) {
            updateNotificationPanel(loggedInUser);
        } else {
            JOptionPane.showMessageDialog(null, "User not logged in!");
        }
    }//GEN-LAST:event_NotificationTabMousePressed

    private void foodIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon3MouseClicked
        openOrderPanel(2);
    }//GEN-LAST:event_foodIcon3MouseClicked

    private void foodIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon2MouseClicked
        openOrderPanel(1);
    }//GEN-LAST:event_foodIcon2MouseClicked

    private void foodIcon5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon5MouseClicked
        openOrderPanel(4);
    }//GEN-LAST:event_foodIcon5MouseClicked

    private void foodIcon6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon6MouseClicked
        openOrderPanel(5);
    }//GEN-LAST:event_foodIcon6MouseClicked

    private void foodIcon4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon4MouseClicked
        openOrderPanel(3);
    }//GEN-LAST:event_foodIcon4MouseClicked

    private void foodIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon1MouseClicked
       openOrderPanel(0);
    }//GEN-LAST:event_foodIcon1MouseClicked

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void OrderBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OrderBtnActionPerformed
        String quantityText = quantity.getText();

    try {
        int quantityValue = Integer.parseInt(quantityText.trim()); // Ensure no spaces
        if (quantityValue <= 0) {
            JOptionPane.showMessageDialog(OrderPanel, "Please enter a valid quantity!", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Retrieve food details
        String foodId = descriptionFoodId.getText().replace("ID: ", "").trim();
        String foodName = descriptionFoodName.getText().replace("Name: ", "").trim();
        String priceText = descriptionFoodPrice.getText().replace("Price: ", "").trim();

        // Ensure price only contains numbers and a decimal point
        priceText = priceText.replaceAll("[^0-9.]", ""); 

        // Convert price to numeric value
        double basePrice = Double.parseDouble(priceText);
        double totalPrice = basePrice * quantityValue; // Update price based on quantity

        // Format price to 2 decimal places
        String finalPrice = String.format("%.2f", totalPrice);

        // Generate order ID
        String orderId = generateOrderId();
        String customerEmail = "customer@test"; // Replace with actual user data

        // Get current date & time (now separate)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        String orderDate = dateFormat.format(new Date()); // Date only
        String orderTime = timeFormat.format(new Date()); // Time only
        String status = "Pending";

        // Debugging output
        System.out.println("Attempting to save order: " + orderId + ", " + customerEmail + ", " + foodName + ", " 
            + quantityValue + ", " + finalPrice + ", " + orderDate + ", " + orderTime + ", " + status);

        // Save order with new format (separated date & time)
        saveOrder(orderId, customerEmail, foodName, quantityValue, finalPrice, orderDate, orderTime, status);

        // **Refresh both order status and order history tables**
        populateOrderStatusTable(); // 🔹 Updates Order Status Table
        populateOrderHistoryTable(); // 🔹 Updates Order History Table

        // Reset quantity field
        quantity.setText("0");

        // Show confirmation message
        JOptionPane.showMessageDialog(OrderPanel, "Order placed: " + quantityValue + " x " + foodName, "Order Confirmed", JOptionPane.INFORMATION_MESSAGE);

        // Close the Order Panel
        Window window = SwingUtilities.getWindowAncestor(OrderPanel);
        if (window != null) {
            window.dispose(); // Close the panel window
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(OrderPanel, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace(); // Print error in console for debugging
    }
    }//GEN-LAST:event_OrderBtnActionPerformed

    private void CancelOrderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CancelOrderMousePressed
        int selectedRow = StatusjTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an order to cancel!", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get order ID from the selected row
    String orderIdToCancel = StatusjTable1.getValueAt(selectedRow, 0).toString();

    File file = new File("customerOrder.txt");
    List<String> updatedOrders = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // Ensure valid row (8 columns instead of 7)
            if (data.length == 8) {
                String orderId = data[0]; // Order ID

                if (orderId.equals(orderIdToCancel)) {
                    data[7] = "Cancelled"; // Change status to "Cancelled" (8th column)
                }

                updatedOrders.add(String.join(",", data));
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading orders!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Write the updated list back to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
        for (String order : updatedOrders) {
            writer.write(order);
            writer.newLine();
        }
    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating orders!", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Refresh the table
    populateOrderStatusTable();
    JOptionPane.showMessageDialog(this, "Order has been cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_CancelOrderMousePressed

    private void ReorderMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReorderMousePressed
        int selectedRow = HistoryjTable1.getSelectedRow();
    
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an order to reorder!", "No Selection", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Extract data from the selected row
    String foodName = HistoryjTable1.getValueAt(selectedRow, 1).toString();
    String quantity = HistoryjTable1.getValueAt(selectedRow, 2).toString();
    String price = HistoryjTable1.getValueAt(selectedRow, 3).toString();

    // Open the OrderPanel with extracted data
    openOrderPanel(null, foodName, price, quantity); // Pass `null` for foodId
    }//GEN-LAST:event_ReorderMousePressed

    private void LeaveReviewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveReviewBtnActionPerformed
        String deliveryID = getLatestDeliveryID(); // Fetch delivery ID (you need to implement this method)

        if (deliveryID != null) {
            ReviewForm reviewForm = new ReviewForm(deliveryID); // Pass the correct deliveryID
            reviewForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Delivery ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LeaveReviewBtnActionPerformed

    private void SubmitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SubmitMousePressed
        String customerEmail = "customer@test"; // Replace with actual user data
    String complaintText = ComplaintTextArea.getText().trim();
    String complaintType = ComplaintTypeComboBox.getSelectedItem().toString();

    // Validate that complaint is not empty
    if (complaintText.isEmpty()) {
        JOptionPane.showMessageDialog(ComplaintPanel, "Please enter your complaint!", "Empty Complaint", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Generate a new Complaint ID
    String complaintID = generateNewComplaintID();

    // Get current date & time
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    String complaintDate = dateFormat.format(new Date());
    String complaintTime = timeFormat.format(new Date());

    String complaintEntry;

    if (complaintType.equals("General Complaint")) {
        // Save as General Complaint
        complaintEntry = complaintID + ",General," + customerEmail + "," + complaintText + "," + complaintDate + "," + complaintTime;
    } else {
        // Save as Food Complaint (user must enter Order ID manually in text area)
        complaintEntry = complaintID + ",Food," + customerEmail + "," + complaintText + "," + complaintDate + "," + complaintTime;
    }

    // Save complaint to complaint.txt
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("complaint.txt", true))) {
        writer.write(complaintEntry);
        writer.newLine();
        writer.flush();
        JOptionPane.showMessageDialog(ComplaintPanel, "Complaint submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Reset fields after submission
        ComplaintTextArea.setText("");
        ComplaintTypeComboBox.setSelectedIndex(0); // Reset combo box

    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(ComplaintPanel, "Error saving complaint!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_SubmitMousePressed

    private void FoodReviewMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodReviewMousePressed
        openAddReviewPanel(); // Opens the review panel
    }//GEN-LAST:event_FoodReviewMousePressed

    private void SubmitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitBtnActionPerformed
        String reviewText = AddReviewTextArea.getText().trim();
    String foodID = FoodIDComboBox.getSelectedItem().toString(); 
    String rate = RateComboBox.getSelectedItem().toString(); 

    // Check if review is empty
    if (reviewText.isEmpty()) {
        JOptionPane.showMessageDialog(AddReviewPanel, "Review cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Generate a new Review ID
    String reviewID = generateNewReviewID();

    // Prepare review entry
    String reviewEntry = reviewID + "," + foodID + "," + rate + "," + reviewText;

    // Save to foodReview.txt
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("foodReview.txt", true))) {
        writer.write(reviewEntry);
        writer.newLine();
        writer.flush();
        JOptionPane.showMessageDialog(AddReviewPanel, "Review submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Reset fields after submission
        AddReviewTextArea.setText("");
        FoodIDComboBox.setSelectedIndex(0);
        RateComboBox.setSelectedIndex(0);

        // Close the AddReviewPanel window
        Window window = SwingUtilities.getWindowAncestor(AddReviewPanel);
        if (window != null) {
            window.dispose();
        }

    } catch (IOException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(AddReviewPanel, "Error saving review!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_SubmitBtnActionPerformed

    private void SkipBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SkipBtnActionPerformed
        String selectedFoodID = FoodIDComboBox.getSelectedItem().toString();
    String selectedRating = RateComboBox.getSelectedItem().toString();
    
    // Generate a new Review ID
    String reviewID = generateNewReviewID();
    
    // Format: ReviewID, FoodID, Rating, (No Review)
    String reviewEntry = reviewID + "," + selectedFoodID + "," + selectedRating + ",-"; 

    // Save to foodReview.txt
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("foodReview.txt", true))) {
        writer.write(reviewEntry);
        writer.newLine();
        writer.flush();
        JOptionPane.showMessageDialog(AddReviewPanel, "Rating submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

        // Close AddReviewPanel
        Window window = SwingUtilities.getWindowAncestor(AddReviewPanel);
        if (window != null) {
            window.dispose();
        }

    } catch (IOException e) {
        JOptionPane.showMessageDialog(AddReviewPanel, "Error saving rating!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_SkipBtnActionPerformed

    private void RateComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RateComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_RateComboBoxActionPerformed

    private void ReceiptMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ReceiptMousePressed
        int selectedRow = HistoryjTable1.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select an order to view the receipt!", "No Order Selected", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // Get order details from the selected row
    String orderId = HistoryjTable1.getValueAt(selectedRow, 0).toString();
    String foodOrdered = HistoryjTable1.getValueAt(selectedRow, 1).toString();
    String quantity = HistoryjTable1.getValueAt(selectedRow, 2).toString();
    String transaction = HistoryjTable1.getValueAt(selectedRow, 3).toString();
    String orderDate = HistoryjTable1.getValueAt(selectedRow, 4).toString();
    String orderTime = HistoryjTable1.getValueAt(selectedRow, 5).toString();

    // Format the receipt
    String receipt = "===== Order Receipt =====\n"
                   + "Order ID: " + orderId + "\n"
                   + "Food Ordered: " + foodOrdered + "\n"
                   + "Quantity: " + quantity + "\n"
                   + "Transaction: " + transaction + "\n"
                   + "Order Date: " + orderDate + "\n"
                   + "Order Time: " + orderTime + "\n"
                   + "=========================";

    // Display receipt in a dialog
    JOptionPane.showMessageDialog(this, receipt, "Order Receipt", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_ReceiptMousePressed

        private void updateNotificationPanel(String userID) {
        System.out.println("Updating Notification Panel for: " + userID);

        notificationListPanel.removeAll(); // Clear previous notifications
        notificationListPanel.setLayout(new javax.swing.BoxLayout(notificationListPanel, javax.swing.BoxLayout.Y_AXIS));

        java.util.List<String> transactions = new java.util.ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("userTopup.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(";");

                if (details.length >= 4 && details[0].equals(userID)) {
                    transactions.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        if (transactions.isEmpty()) {
            notificationListPanel.add(new javax.swing.JLabel("No transactions found."));
        } else {
            java.util.Collections.reverse(transactions); // Show latest transaction at the top

            for (String transaction : transactions) {
                String[] details = transaction.split(";");
                String paymentMethod = details[1];
                double amount = Double.parseDouble(details[2]);
                double balance = Double.parseDouble(details[3]);

                javax.swing.JPanel notificationItem = new javax.swing.JPanel();
                notificationItem.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 5));
                notificationItem.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.BLACK, 1));

                // **Set notification width to 590px & height to 45px**
                notificationItem.setPreferredSize(new java.awt.Dimension(590, 60));
                notificationItem.setMaximumSize(new java.awt.Dimension(590, 60));
                notificationItem.setMinimumSize(new java.awt.Dimension(590, 60));

                // **Formatted Label**
                javax.swing.JLabel messageLabel = new javax.swing.JLabel(
                        "<html><b>Top up amount:</b> RM " + amount + "<br>"
                        + "<b>Payment Method:</b> " + paymentMethod + "<br>"
                        + "<b>Total balance:</b> RM " + balance + "</html>"
                );

                messageLabel.setFont(new java.awt.Font("Arial", java.awt.Font.PLAIN, 12));
                notificationItem.add(messageLabel);

                notificationListPanel.add(notificationItem);
            }
        }

        // **Ensure Scroll Works**
        notificationListPanel.setPreferredSize(new java.awt.Dimension(
                notificationListPanel.getWidth(),
                transactions.size() * 65
        ));

        SwingUtilities.invokeLater(() -> {
            notificationListPanel.revalidate();
            notificationListPanel.repaint();
        });
    }

        private String getUserName(String userID) {
    try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] details = line.split(";");
            
            if (details.length >= 2 && details[0].equals(userID)) {
                return details[1]; // Return the name in column [1]
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading users.txt: " + e.getMessage());
    }
    return null; // Return null if user not found
}

    private String getLatestDeliveryID() {
        String taskHistoryFilePath = "taskHistory.txt";
        String latestDeliveryID = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(taskHistoryFilePath))) {
            String line;

            // Read through the file line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim();  // Remove extra spaces
                if (line.startsWith("DR")) {  // Find the line containing the Delivery ID
                    latestDeliveryID = line.replace("Delivery ID:", "").trim(); // Extract only the ID
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading task history.");
        }

        return latestDeliveryID; // Return the last found delivery ID
    }

    private void goToLogout() {
        Login loginframe = new Login();
        loginframe.setVisible(true);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AddReviewPanel;
    private javax.swing.JTextArea AddReviewTextArea;
    private javax.swing.JLabel CancelOrder;
    private javax.swing.JPanel ComplaintPanel;
    private javax.swing.JLabel ComplaintTab;
    private javax.swing.JTextArea ComplaintTextArea;
    private javax.swing.JComboBox<String> ComplaintTypeComboBox;
    private javax.swing.JComboBox<String> FoodIDComboBox;
    private javax.swing.JLabel FoodReview;
    private javax.swing.JTable HistoryjTable1;
    private javax.swing.JButton LeaveReviewBtn;
    private javax.swing.JPanel MenuPanel;
    private javax.swing.JLabel MenuTab;
    private javax.swing.JPanel NotificationPanel;
    private javax.swing.JLabel NotificationTab;
    private javax.swing.JButton OrderBtn;
    private javax.swing.JPanel OrderHistoryPanel;
    private javax.swing.JLabel OrderHistoryTab;
    private javax.swing.JPanel OrderPanel;
    private javax.swing.JPanel OrderStatusPanel;
    private javax.swing.JLabel OrderStatusTab;
    private javax.swing.JComboBox<String> RateComboBox;
    private javax.swing.JLabel Receipt;
    private javax.swing.JLabel Reorder;
    private javax.swing.JTable ReviewjTable;
    private javax.swing.JButton SkipBtn;
    private javax.swing.JTable StatusjTable1;
    private javax.swing.JLabel Submit;
    private javax.swing.JButton SubmitBtn;
    private javax.swing.JLabel descriptionFoodId;
    private javax.swing.JLabel descriptionFoodName;
    private javax.swing.JLabel descriptionFoodPrice;
    private javax.swing.JLabel foodIcon1;
    private javax.swing.JLabel foodIcon2;
    private javax.swing.JLabel foodIcon3;
    private javax.swing.JLabel foodIcon4;
    private javax.swing.JLabel foodIcon5;
    private javax.swing.JLabel foodIcon6;
    private javax.swing.JLabel foodLabel1;
    private javax.swing.JLabel foodLabel2;
    private javax.swing.JLabel foodLabel3;
    private javax.swing.JLabel foodLabel4;
    private javax.swing.JLabel foodLabel5;
    private javax.swing.JLabel foodLabel6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JPanel line;
    private javax.swing.JButton logout;
    private javax.swing.JPanel menubar;
    private javax.swing.JPanel notificationListPanel;
    private javax.swing.JScrollPane notificationScrollPane;
    private javax.swing.JTextField quantity;
    private javax.swing.JPanel title;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    // End of variables declaration//GEN-END:variables
}
