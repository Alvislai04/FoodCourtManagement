  // Delivery Runner Dashboard
package system.DeliveryRunner;

import com.system.Login;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DrDashboard extends javax.swing.JFrame {
    Color DefaultColor, ClickedColor;
    
    Login Login = new Login();
    
    private OrdersDelivered ordersDelivered;
    
    private Chart chart;
    
    public DrDashboard() {
        initComponents();
        usernametxt.setText(Login.getUsername());
        
        this.setSize(1022,560); //(width,height)
        this.setResizable(false);
        
        DefaultColor = new Color(153,89,16);
        ClickedColor = new Color(0,0,0);
        
        //set default color to pan on run time
        viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        revenueDbTab.setBackground(DefaultColor);
        
        populateviewtasksTable();
        addTableRowClickListener();
        
        populateNotificationsTable();
        
        populatetaskhistoryTable();
        viewtasksTable.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
            updateButtonState();
            }
        });
        
        CollectedBtn.setEnabled(false);
        DeliveredBtn.setEnabled(false);
        
        notificationsTable.getSelectionModel().addListSelectionListener(event -> {
         if (!event.getValueIsAdjusting()) {
        updateNotificationButtonState();
            }
        });
        
        taskhistoryTable.getModel().addTableModelListener(e -> {
            filterComboBoxActionPerformed(null); // Refresh revenue calculation
        });
        
        
         SwingUtilities.invokeLater(() -> {
        ordersDelivered = new OrdersDelivered(taskhistoryTable);
        });
         
        chart = new Chart(taskhistoryTable, chartPanel);
        chart.updateChartFromTable("Today"); // Default chart filter
        
        loadCustomerReviews();
         
                        
        }
    
    private void populateNotificationsTable() {
    // Example of new tasks waiting for acceptance
    Object[][] notificationData = {
        {"DR003", "Chicken Rice, Iced Lemon Tea", "22 Jalan ABC", "90 Jalan XYZ", "018-2233445", "Unpaid", "18.00"},
        {"DR004", "Burger, Fries, Soda", "Mall Food Court", "55 Apartment JKL", "017-5566778", "Paid", "22.50"}
    };

    // Column headers
    String[] columnNames = {"Delivery ID", "Food Items", "Pick-up Address", "Delivery Address", "Phone Number", "Payment Status", "Total"};

    // Set the table model
    notificationsTable.setModel(new DefaultTableModel(notificationData, columnNames));
}
    
    private void updateNotificationButtonState() {
    int selectedRow = notificationsTable.getSelectedRow();
    
    if (selectedRow != -1) {
        AcceptBtn.setEnabled(true);
        DeclineBtn.setEnabled(true);
    } else {
        AcceptBtn.setEnabled(false);
        DeclineBtn.setEnabled(false);
    }
}
    
    
    
    private void updateButtonState() {
    int selectedRow = viewtasksTable.getSelectedRow();

    if (selectedRow != -1) {
        String status = viewtasksTable.getValueAt(selectedRow, 7).toString(); // Assuming status is in column 5

        if (status.equals("Pending")) {
            CollectedBtn.setEnabled(true);
            DeliveredBtn.setEnabled(false);
        } else if (status.equals("Collected")) {
            CollectedBtn.setEnabled(false);
            DeliveredBtn.setEnabled(true);
        }
    } else {
        // No row selected, disable both buttons
        CollectedBtn.setEnabled(false);
        DeliveredBtn.setEnabled(false);
    }
}
    private ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    // Add this method to set a custom renderer for the proof of delivery column
    private void setTableImageRenderer() {
        taskhistoryTable.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ImageIcon) {
                    JLabel label = new JLabel((ImageIcon) value);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setVerticalAlignment(JLabel.CENTER);
                    return label;
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        });
    }
    // Populate the tasks table with data (sample)
    private void populateviewtasksTable() {
        // Example data for the table
        Object[][] taskData = {
            {"DR001", """
                      Nasi Lemak
                      Nasi Goreng
                      Teh Ais""", "123 Street A", "456 Street B", "012-3456789", "Paid", "25.50", "Pending"},
            {"DR002", """
                      Nasi Kerabu
                      Mee Goreng
                      Kopi Ais""","789 Street C", "101 Street D", "011-9876543", "Unpaid", "15.00", "Pending"}
        };

        // Column headers
        String[] columnNames = {"Delivery ID", "Food Items","Pick-up Address", "Delivery Address", "Phone Number", "Payment Status", "Total", "Order Status"};

        // Set the table model with data and column names
        viewtasksTable.setModel(new javax.swing.table.DefaultTableModel(taskData, columnNames));
        viewtasksTable.setRowHeight(100); // Sets row height to 50 pixels

          
    }
    
    private void populatetaskhistoryTable() {
    // Column headers for the task history table
    String[] columnNames = {"Date", "Delivery ID", "Food Items", "Pick-up Address", "Delivery Address", "Phone Number", "Payment Status", "Total", "Proof of Delivery"};

    // Set the table model with just column names (no data initially)
    taskhistoryTable.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], columnNames));
    taskhistoryTable.setRowHeight(50);
    setTableImageRenderer();
    
    }
    

     // Add a listener for row clicks in the table
    private void addTableRowClickListener() {
        viewtasksTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int selectedRow = viewtasksTable.getSelectedRow();
                if (selectedRow != -1) {
                    //show button when row is selected
                    DeliveredBtn.setVisible(true);
                    
                    // Get the task details from the selected row
                    String deliveryID = viewtasksTable.getValueAt(selectedRow, 0).toString();
                    String foodItems = viewtasksTable.getValueAt (selectedRow, 1). toString();
                    String pickupAddress = viewtasksTable.getValueAt(selectedRow, 2).toString();
                    String deliveryAddress = viewtasksTable.getValueAt(selectedRow, 3).toString();
                    String phoneNumber = viewtasksTable.getValueAt(selectedRow, 4).toString();
                    String paymentStatus = viewtasksTable.getValueAt(selectedRow, 5).toString();
                    String total = viewtasksTable.getValueAt(selectedRow, 6).toString();

                    // Pass these details to the receipt generation method
                    displayReceipt(deliveryID, foodItems, pickupAddress, deliveryAddress, phoneNumber, paymentStatus, total);
                } else {
                    DeliveredBtn.setVisible(false);
                }
            }
        });
    }
    
    
    private void displayReceipt(String deliveryID, String foodItems, String pickupAddress, String deliveryAddress, 
                                   String phoneNumber, String paymentStatus, String total) {
    String receipt = "Delivery ID: " + deliveryID + "\n"
                   + "Food Items: " + foodItems + "\n"
                   + "Pick-up Address: " + pickupAddress + "\n"
                   + "Delivery Address: " + deliveryAddress + "\n"
                   + "Phone Number: " + phoneNumber + "\n"
                   + "Payment Status: " + paymentStatus + "\n"
                   + "Total: RM " + total;
    receiptArea.setText(receipt);
}
    
    private void loadCustomerReviews() {
        
    File file = new File("reviews.txt");

    if (!file.exists()) {
        CustReviewsDisplay.setText("No reviews available.");
        return;
    }

    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        StringBuilder reviewsText = new StringBuilder();
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",", -1); // Format: deliveryID;Rating;Comment
            
            if (data.length >= 3) {
                String deliveryID = data[0];   // ID of the delivery runner
                String rating = data[1]; // Star rating
                String comment = data[2]; // Review text
                
                // Check if the review belongs to the logged-in runner
               
                    reviewsText.append("Delivery ID: ").append(deliveryID).append("\n")
                               .append("⭐ Rating: ").append(rating).append("/5\n")
                               .append("📝 Comment: ").append(comment).append("\n\n");
                }
            }
        

        // Display the reviews or show a default message
        if (reviewsText.length() > 0) {
            CustReviewsDisplay.setText(reviewsText.toString());
        } else {
            CustReviewsDisplay.setText("No reviews available for you."); //not showing reviews
        }

    } catch (IOException e) {
        CustReviewsDisplay.setText("Error loading reviews.");
        JOptionPane.showMessageDialog(this, "Error loading reviews.", "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    
    

    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        list1 = new java.awt.List();
        tastiesPanel = new javax.swing.JPanel();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();
        menubarPanel = new javax.swing.JPanel();
        viewtasksTab = new javax.swing.JLabel();
        taskhistoryTab = new javax.swing.JLabel();
        notificationsTab = new javax.swing.JLabel();
        custreviewsTab = new javax.swing.JLabel();
        revenueDbTab = new javax.swing.JLabel();
        drName = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        welcomePanel = new javax.swing.JPanel();
        homePanel = new javax.swing.JPanel();
        usernametxt = new javax.swing.JLabel();
        welcometxt = new javax.swing.JLabel();
        notificationsPanel = new javax.swing.JPanel();
        taskhistoryLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        notificationsTable = new javax.swing.JTable();
        DeclineBtn = new javax.swing.JButton();
        AcceptBtn = new javax.swing.JButton();
        custreviewsPanel = new javax.swing.JPanel();
        CustReviewsLabel = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        CustReviewsDisplay = new javax.swing.JTextArea();
        taskhistoryPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskhistoryTable = new javax.swing.JTable();
        taskhistoryLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        SearchBtn = new javax.swing.JButton();
        revenueDbPanel = new javax.swing.JPanel();
        revenueDbLabel = new javax.swing.JLabel();
        totalRevenuePanel = new javax.swing.JPanel();
        revenueLabel = new javax.swing.JLabel();
        totalrevenue = new javax.swing.JLabel();
        ordersDeliveredPanel = new javax.swing.JPanel();
        ordersDeliveredLabel = new javax.swing.JLabel();
        totalorders = new javax.swing.JLabel();
        filterComboBox = new javax.swing.JComboBox<>();
        chartPanel = new javax.swing.JPanel();
        viewtasksPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewtasksTable = new javax.swing.JTable();
        receiptArea = new javax.swing.JTextArea();
        DeliveredBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        taskhistoryLabel1 = new javax.swing.JLabel();
        CollectedBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tastiesPanel.setBackground(new java.awt.Color(0, 0, 0));

        title_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        title_lbl1.setText("TAS");

        title_lbl2.setBackground(new java.awt.Color(0, 0, 0));
        title_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl2.setForeground(new java.awt.Color(153, 89, 16));
        title_lbl2.setText("TIES");

        javax.swing.GroupLayout tastiesPanelLayout = new javax.swing.GroupLayout(tastiesPanel);
        tastiesPanel.setLayout(tastiesPanelLayout);
        tastiesPanelLayout.setHorizontalGroup(
            tastiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tastiesPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(title_lbl1)
                .addGap(2, 2, 2)
                .addComponent(title_lbl2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        tastiesPanelLayout.setVerticalGroup(
            tastiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title_lbl1)
            .addComponent(title_lbl2)
        );

        menubarPanel.setBackground(new java.awt.Color(255, 255, 255));
        menubarPanel.setPreferredSize(new java.awt.Dimension(285, 460));

        viewtasksTab.setBackground(new java.awt.Color(153, 89, 16));
        viewtasksTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        viewtasksTab.setForeground(new java.awt.Color(255, 255, 255));
        viewtasksTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewtasksTab.setText("View Tasks");
        viewtasksTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewtasksTab.setOpaque(true);
        viewtasksTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewtasksTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewtasksTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewtasksTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                viewtasksTabMousePressed(evt);
            }
        });

        taskhistoryTab.setBackground(new java.awt.Color(153, 89, 16));
        taskhistoryTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        taskhistoryTab.setForeground(new java.awt.Color(255, 255, 255));
        taskhistoryTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskhistoryTab.setText("Task History");
        taskhistoryTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        taskhistoryTab.setOpaque(true);
        taskhistoryTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taskhistoryTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taskhistoryTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taskhistoryTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taskhistoryTabMousePressed(evt);
            }
        });

        notificationsTab.setBackground(new java.awt.Color(153, 89, 16));
        notificationsTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        notificationsTab.setForeground(new java.awt.Color(255, 255, 255));
        notificationsTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notificationsTab.setText("Notifications");
        notificationsTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        notificationsTab.setOpaque(true);
        notificationsTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notificationsTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                notificationsTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                notificationsTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                notificationsTabMousePressed(evt);
            }
        });

        custreviewsTab.setBackground(new java.awt.Color(153, 89, 16));
        custreviewsTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        custreviewsTab.setForeground(new java.awt.Color(255, 255, 255));
        custreviewsTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        custreviewsTab.setText("Customer Reviews");
        custreviewsTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        custreviewsTab.setOpaque(true);
        custreviewsTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custreviewsTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                custreviewsTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                custreviewsTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                custreviewsTabMousePressed(evt);
            }
        });

        revenueDbTab.setBackground(new java.awt.Color(153, 89, 16));
        revenueDbTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        revenueDbTab.setForeground(new java.awt.Color(255, 255, 255));
        revenueDbTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueDbTab.setText("Revenue Dashboard");
        revenueDbTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        revenueDbTab.setOpaque(true);
        revenueDbTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueDbTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueDbTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueDbTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenueDbTabMousePressed(evt);
            }
        });

        drName.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        drName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        drName.setText("Dashboard");

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menubarPanelLayout = new javax.swing.GroupLayout(menubarPanel);
        menubarPanel.setLayout(menubarPanelLayout);
        menubarPanelLayout.setHorizontalGroup(
            menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menubarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menubarPanelLayout.createSequentialGroup()
                        .addComponent(logoutBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menubarPanelLayout.createSequentialGroup()
                        .addGroup(menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(drName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(revenueDbTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custreviewsTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menubarPanelLayout.createSequentialGroup()
                                .addGroup(menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(taskhistoryTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(viewtasksTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(notificationsTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(9, 9, 9))))
        );
        menubarPanelLayout.setVerticalGroup(
            menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menubarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drName, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(notificationsTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewtasksTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(12, 12, 12)
                .addComponent(taskhistoryTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(custreviewsTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(revenueDbTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(logoutBtn)
                .addContainerGap())
        );

        welcomePanel.setBackground(new java.awt.Color(153, 89, 16));
        welcomePanel.setLayout(null);

        homePanel.setBackground(new java.awt.Color(153, 89, 16));
        homePanel.setLayout(null);

        usernametxt.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        usernametxt.setForeground(new java.awt.Color(255, 255, 255));
        usernametxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernametxt.setText("username");
        usernametxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homePanel.add(usernametxt);
        usernametxt.setBounds(380, 0, 190, 460);

        welcometxt.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        welcometxt.setForeground(new java.awt.Color(255, 255, 255));
        welcometxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcometxt.setText("WELCOME,");
        welcometxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homePanel.add(welcometxt);
        welcometxt.setBounds(120, 0, 230, 460);

        welcomePanel.add(homePanel);
        homePanel.setBounds(0, 0, 710, 460);

        notificationsPanel.setBackground(new java.awt.Color(153, 89, 16));
        notificationsPanel.setMaximumSize(new java.awt.Dimension(1022, 530));

        taskhistoryLabel2.setBackground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        taskhistoryLabel2.setForeground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskhistoryLabel2.setText("NOTIFICATIONS");

        notificationsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Delivery ID", "Food Items", "Pick-up Add.", "Delivery Add.", "Phone Number", "Payment Status", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(notificationsTable);

        DeclineBtn.setText("DECLINE");
        DeclineBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeclineBtnActionPerformed(evt);
            }
        });

        AcceptBtn.setText("ACCEPT");
        AcceptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcceptBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout notificationsPanelLayout = new javax.swing.GroupLayout(notificationsPanel);
        notificationsPanel.setLayout(notificationsPanelLayout);
        notificationsPanelLayout.setHorizontalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(taskhistoryLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
                    .addComponent(jScrollPane3))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, notificationsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(AcceptBtn)
                .addGap(41, 41, 41)
                .addComponent(DeclineBtn)
                .addGap(14, 14, 14))
        );
        notificationsPanelLayout.setVerticalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notificationsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taskhistoryLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeclineBtn)
                    .addComponent(AcceptBtn))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        welcomePanel.add(notificationsPanel);
        notificationsPanel.setBounds(0, 0, 710, 460);

        custreviewsPanel.setBackground(new java.awt.Color(153, 89, 16));

        CustReviewsLabel.setBackground(new java.awt.Color(255, 255, 255));
        CustReviewsLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        CustReviewsLabel.setForeground(new java.awt.Color(255, 255, 255));
        CustReviewsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        CustReviewsLabel.setText("CUSTOMER REVIEWS");

        CustReviewsDisplay.setColumns(20);
        CustReviewsDisplay.setRows(5);
        jScrollPane4.setViewportView(CustReviewsDisplay);

        javax.swing.GroupLayout custreviewsPanelLayout = new javax.swing.GroupLayout(custreviewsPanel);
        custreviewsPanel.setLayout(custreviewsPanelLayout);
        custreviewsPanelLayout.setHorizontalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custreviewsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CustReviewsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, custreviewsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        custreviewsPanelLayout.setVerticalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custreviewsPanelLayout.createSequentialGroup()
                .addComponent(CustReviewsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );

        welcomePanel.add(custreviewsPanel);
        custreviewsPanel.setBounds(0, 0, 710, 460);

        taskhistoryPanel.setBackground(new java.awt.Color(153, 89, 16));
        taskhistoryPanel.setMinimumSize(new java.awt.Dimension(700, 580));
        taskhistoryPanel.setPreferredSize(new java.awt.Dimension(980, 580));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(980, 580));

        taskhistoryTable.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        taskhistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Delivery No.", "Pick-up Add.", "Delivery Add.", "Cust. Phone", "Payment Status", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        taskhistoryTable.setGridColor(new java.awt.Color(0, 0, 0));
        taskhistoryTable.setPreferredSize(new java.awt.Dimension(980, 580));
        taskhistoryTable.setSelectionBackground(new java.awt.Color(153, 89, 16));
        taskhistoryTable.setShowVerticalLines(true);
        jScrollPane2.setViewportView(taskhistoryTable);

        taskhistoryLabel.setBackground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        taskhistoryLabel.setForeground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskhistoryLabel.setText("TASK HISTORY");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });

        searchLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        searchLabel.setForeground(new java.awt.Color(255, 255, 255));
        searchLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        searchLabel.setText("SEARCH DELIVERY HISTORY:");

        SearchBtn.setText("SEARCH");
        SearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout taskhistoryPanelLayout = new javax.swing.GroupLayout(taskhistoryPanel);
        taskhistoryPanel.setLayout(taskhistoryPanelLayout);
        taskhistoryPanelLayout.setHorizontalGroup(
            taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskhistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(taskhistoryPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SearchBtn))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(taskhistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 703, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 271, Short.MAX_VALUE))
        );
        taskhistoryPanelLayout.setVerticalGroup(
            taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskhistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taskhistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addGroup(taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBtn))
                .addContainerGap(180, Short.MAX_VALUE))
        );

        welcomePanel.add(taskhistoryPanel);
        taskhistoryPanel.setBounds(0, 0, 710, 460);

        revenueDbPanel.setBackground(new java.awt.Color(153, 89, 16));
        revenueDbPanel.setMinimumSize(new java.awt.Dimension(720, 480));
        revenueDbPanel.setPreferredSize(new java.awt.Dimension(720, 480));

        revenueDbLabel.setBackground(new java.awt.Color(255, 255, 255));
        revenueDbLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        revenueDbLabel.setForeground(new java.awt.Color(255, 255, 255));
        revenueDbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueDbLabel.setText("REVENUE DASHBOARD");

        totalRevenuePanel.setBackground(new java.awt.Color(255, 255, 255));

        revenueLabel.setBackground(new java.awt.Color(0, 0, 0));
        revenueLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        revenueLabel.setForeground(new java.awt.Color(255, 255, 255));
        revenueLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueLabel.setText("TOTAL REVENUE");
        revenueLabel.setOpaque(true);

        totalrevenue.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalrevenue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalrevenue.setText("total revenue");

        javax.swing.GroupLayout totalRevenuePanelLayout = new javax.swing.GroupLayout(totalRevenuePanel);
        totalRevenuePanel.setLayout(totalRevenuePanelLayout);
        totalRevenuePanelLayout.setHorizontalGroup(
            totalRevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(revenueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(totalRevenuePanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(totalrevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        totalRevenuePanelLayout.setVerticalGroup(
            totalRevenuePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalRevenuePanelLayout.createSequentialGroup()
                .addComponent(revenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(totalrevenue, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        ordersDeliveredPanel.setBackground(new java.awt.Color(255, 255, 255));

        ordersDeliveredLabel.setBackground(new java.awt.Color(0, 0, 0));
        ordersDeliveredLabel.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        ordersDeliveredLabel.setForeground(new java.awt.Color(255, 255, 255));
        ordersDeliveredLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersDeliveredLabel.setText("ORDERS DELIVERED ");
        ordersDeliveredLabel.setOpaque(true);

        totalorders.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        totalorders.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalorders.setText("no. of orders delivered");

        javax.swing.GroupLayout ordersDeliveredPanelLayout = new javax.swing.GroupLayout(ordersDeliveredPanel);
        ordersDeliveredPanel.setLayout(ordersDeliveredPanelLayout);
        ordersDeliveredPanelLayout.setHorizontalGroup(
            ordersDeliveredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ordersDeliveredLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
            .addGroup(ordersDeliveredPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(totalorders, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ordersDeliveredPanelLayout.setVerticalGroup(
            ordersDeliveredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ordersDeliveredPanelLayout.createSequentialGroup()
                .addComponent(ordersDeliveredLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(totalorders, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
        );

        filterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Day", "Week", "Month", " " }));
        filterComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboBoxActionPerformed(evt);
            }
        });

        chartPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 232, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout revenueDbPanelLayout = new javax.swing.GroupLayout(revenueDbPanel);
        revenueDbPanel.setLayout(revenueDbPanelLayout);
        revenueDbPanelLayout.setHorizontalGroup(
            revenueDbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(revenueDbLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, revenueDbPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(revenueDbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(revenueDbPanelLayout.createSequentialGroup()
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(ordersDeliveredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(totalRevenuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        revenueDbPanelLayout.setVerticalGroup(
            revenueDbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(revenueDbPanelLayout.createSequentialGroup()
                .addComponent(revenueDbLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(revenueDbPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalRevenuePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ordersDeliveredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        welcomePanel.add(revenueDbPanel);
        revenueDbPanel.setBounds(0, 0, 720, 460);

        viewtasksPanel.setBackground(new java.awt.Color(153, 89, 16));
        viewtasksPanel.setMinimumSize(new java.awt.Dimension(720, 480));
        viewtasksPanel.setName(""); // NOI18N
        viewtasksPanel.setPreferredSize(new java.awt.Dimension(720, 480));

        viewtasksTable.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        viewtasksTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Delivery No.", "Pick-up Add.", "Delivery Add.", "Cust. Phone", "Payment Status", "Total", "Order Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        viewtasksTable.setGridColor(new java.awt.Color(0, 0, 0));
        viewtasksTable.setPreferredSize(new java.awt.Dimension(490, 560));
        viewtasksTable.setSelectionBackground(new java.awt.Color(153, 89, 16));
        viewtasksTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(viewtasksTable);

        receiptArea.setColumns(20);
        receiptArea.setRows(5);

        DeliveredBtn.setText("DELIVERED");
        DeliveredBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                DeliveredBtnMousePressed(evt);
            }
        });
        DeliveredBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeliveredBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 8)); // NOI18N
        jLabel1.setText("*PLEASE INSERT IMAGE FOR PROOF OF DELIVERY");

        taskhistoryLabel1.setBackground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        taskhistoryLabel1.setForeground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskhistoryLabel1.setText("VIEW TASKS");

        CollectedBtn.setText("COLLECTED");
        CollectedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CollectedBtnActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 8)); // NOI18N
        jLabel2.setText("*PLEASE UPDATE WHEN ORDER HAS BEEN COLLECTED");

        javax.swing.GroupLayout viewtasksPanelLayout = new javax.swing.GroupLayout(viewtasksPanel);
        viewtasksPanel.setLayout(viewtasksPanelLayout);
        viewtasksPanelLayout.setHorizontalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                        .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(taskhistoryLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(viewtasksPanelLayout.createSequentialGroup()
                        .addComponent(receiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                                .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, viewtasksPanelLayout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel1)))
                                .addGap(56, 56, 56))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                                .addComponent(CollectedBtn)
                                .addGap(111, 111, 111))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                                .addComponent(DeliveredBtn)
                                .addGap(110, 110, 110))))))
        );
        viewtasksPanelLayout.setVerticalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                .addComponent(taskhistoryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewtasksPanelLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(CollectedBtn)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(DeliveredBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(receiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))))
        );

        welcomePanel.add(viewtasksPanel);
        viewtasksPanel.setBounds(0, 0, 720, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menubarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
            .addComponent(tastiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tastiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menubarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        goToLogout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void taskhistoryTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskhistoryTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_taskhistoryTabMouseEntered

    private void taskhistoryTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskhistoryTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_taskhistoryTabMouseExited

    private void notificationsTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_notificationsTabMouseEntered

    private void notificationsTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_notificationsTabMouseExited

    private void custreviewsTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custreviewsTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_custreviewsTabMouseEntered

    private void custreviewsTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custreviewsTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_custreviewsTabMouseExited

    private void viewtasksTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewtasksTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_viewtasksTabMouseEntered

    private void viewtasksTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewtasksTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_viewtasksTabMouseExited

    private void revenueDbTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueDbTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueDbTabMouseExited

    private void revenueDbTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueDbTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueDbTabMouseEntered

    private void viewtasksTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewtasksTabMousePressed
        // TODO add your handling code here:
        viewtasksTab.setBackground(ClickedColor);
         
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        revenueDbTab.setBackground(DefaultColor);
        
        viewtasksPanel.setVisible(true);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        revenueDbPanel.setVisible(false);
        homePanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
        
        
    }//GEN-LAST:event_viewtasksTabMousePressed

    private void taskhistoryTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskhistoryTabMousePressed
        // TODO add your handling code here:
        taskhistoryTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        revenueDbTab.setBackground(DefaultColor);
        
        homePanel.setVisible(false);
           viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(true);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        revenueDbPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_taskhistoryTabMousePressed

    private void notificationsTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTabMousePressed
        // TODO add your handling code here:
        notificationsTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        revenueDbTab.setBackground(DefaultColor);
        
        homePanel.setVisible(false);
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(true);
        custreviewsPanel.setVisible(false);
        revenueDbPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_notificationsTabMousePressed

    private void custreviewsTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custreviewsTabMousePressed
        // TODO add your handling code here:
        custreviewsTab.setBackground(ClickedColor);
        viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        revenueDbTab.setBackground(DefaultColor);
        
        homePanel.setVisible(false);
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(true);
        revenueDbPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_custreviewsTabMousePressed

    private void revenueDbTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueDbTabMousePressed
        // TODO add your handling code here:
        revenueDbTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        
        homePanel.setVisible(false);
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        revenueDbPanel.setVisible(true);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
        
    }//GEN-LAST:event_revenueDbTabMousePressed

    private void viewtasksTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewtasksTabMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_viewtasksTabMouseClicked

    private void taskhistoryTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskhistoryTabMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_taskhistoryTabMouseClicked

    private void notificationsTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTabMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_notificationsTabMouseClicked

    private void custreviewsTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custreviewsTabMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_custreviewsTabMouseClicked

    private void revenueDbTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueDbTabMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_revenueDbTabMouseClicked

    private void DeliveredBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeliveredBtnActionPerformed
        // TODO add your handling code here:
        
            int selectedRow = viewtasksTable.getSelectedRow();

    if (selectedRow != -1) {
        // Retrieve order details from the selected row
        String deliveryID = viewtasksTable.getValueAt(selectedRow, 0).toString();
        String foodItems = viewtasksTable.getValueAt(selectedRow, 1).toString();
        String pickupAddress = viewtasksTable.getValueAt(selectedRow, 2).toString();
        String deliveryAddress = viewtasksTable.getValueAt(selectedRow, 3).toString();
        String phoneNumber = viewtasksTable.getValueAt(selectedRow, 4).toString();
        String paymentStatus = viewtasksTable.getValueAt(selectedRow, 5).toString();
        String total = viewtasksTable.getValueAt(selectedRow, 6).toString();
        
        // Get today's date
        LocalDate today = LocalDate.now();
        String formattedDate = today.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));


        // Prompt the user to select proof of delivery image
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Image as Proof of Delivery");
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File imageFile = fileChooser.getSelectedFile();
            String proofOfDelivery = imageFile.getAbsolutePath(); // Path to the selected image file
            
              // Save task history to the text file
                saveToTaskHistoryFile(deliveryID, foodItems, pickupAddress, deliveryAddress, phoneNumber, 
                                      paymentStatus, total, proofOfDelivery);

            // Create a scaled ImageIcon from the file path
            ImageIcon proofOfDeliveryImage = scaleImageIcon(proofOfDelivery, 100, 100); // Scale the image to 100x100 pixels

            // Move the order to the task history table, adding the ImageIcon instead of the file path
            DefaultTableModel taskHistoryModel = (DefaultTableModel) taskhistoryTable.getModel();
            taskHistoryModel.addRow(new Object[]{
                    formattedDate, deliveryID, foodItems, pickupAddress, deliveryAddress, phoneNumber, paymentStatus, total, proofOfDeliveryImage
            });

            // Remove the order from the view tasks table
            DefaultTableModel viewTasksModel = (DefaultTableModel) viewtasksTable.getModel();
            viewTasksModel.removeRow(selectedRow);

            // Optionally, hide the Delivered button after action
            DeliveredBtn.setVisible(false);
        } else {
            System.out.println("No File Selected");
        }
    }
    
    }
    private void saveToTaskHistoryFile(String deliveryID, String foodItems, String pickupAddress, 
                                       String deliveryAddress, String phoneNumber, String paymentStatus, 
                                       String total, String proofOfDelivery) {
        // Define the file path where you want to save the task history
        String taskHistoryFilePath = "taskHistory.txt";  // Make sure this is a valid file path

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskHistoryFilePath, true))) {
            // Write the data into the file, each entry is written in a new line
            writer.write(deliveryID + "\n" +  // Delivery ID on its own line
             "Food Items: " + foodItems + "\n" +  // Food items on a new line
             pickupAddress + ";" + deliveryAddress + ";" +  
             phoneNumber + ";" + paymentStatus + ";" + total + ";" + proofOfDelivery + "\n");
        
            writer.write("------------------------------------------------------\n"); // Add a separator for better readability
            
            writer.newLine(); 
            
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error saving task history.");
        }


    }//GEN-LAST:event_DeliveredBtnActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void SearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBtnActionPerformed
        // TODO add your handling code here:
             String query = searchField.getText().trim();
        
        if (!query.isEmpty()) {
            boolean found = searchInTable(query);
            
            if (!found) {
                searchLabel.setText("No matching order found.");
            }
        } else {
            searchLabel.setText("Please enter search criteria.");
        }
    }


// Search logic
        private boolean searchInTable(String query) {
            DefaultTableModel model = (DefaultTableModel) taskhistoryTable.getModel();
            boolean found = false;

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    String cellValue = model.getValueAt(row, col).toString();
                    if (cellValue.toLowerCase().contains(query.toLowerCase())) {
                        // Highlight the found row
                        taskhistoryTable.setRowSelectionInterval(row, row);
                        taskhistoryTable.scrollRectToVisible(taskhistoryTable.getCellRect(row, 0, true));
                        searchLabel.setText("Delivery found: Row " + (row + 1));
                        found = true;
                        break;
                    }
                }
                if (found) break; // Stop searching once a match is found
            }
            if (!found) {
                taskhistoryTable.clearSelection(); // Clear selection if no match is found
            }
            return found;
        
    }//GEN-LAST:event_SearchBtnActionPerformed

    private void CollectedBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CollectedBtnActionPerformed
        // TODO add your handling code here:
         int selectedRow = viewtasksTable.getSelectedRow();

    if (selectedRow != -1) {
        viewtasksTable.setValueAt("Collected", selectedRow, 7); // Update status to "Collected"
        updateButtonState(); // Refresh button states
    }
  

    }//GEN-LAST:event_CollectedBtnActionPerformed

    private void DeliveredBtnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DeliveredBtnMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_DeliveredBtnMousePressed

    private void AcceptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcceptBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = notificationsTable.getSelectedRow();

    if (selectedRow != -1) {
        DefaultTableModel notificationsModel = (DefaultTableModel) notificationsTable.getModel();
        DefaultTableModel viewTasksModel = (DefaultTableModel) viewtasksTable.getModel();

        // Get task details
        String deliveryID = notificationsTable.getValueAt(selectedRow, 0).toString();
        String foodItems = notificationsTable.getValueAt(selectedRow, 1).toString();
        String pickupAddress = notificationsTable.getValueAt(selectedRow, 2).toString();
        String deliveryAddress = notificationsTable.getValueAt(selectedRow, 3).toString();
        String phoneNumber = notificationsTable.getValueAt(selectedRow, 4).toString();
        String paymentStatus = notificationsTable.getValueAt(selectedRow, 5).toString();
        String total = notificationsTable.getValueAt(selectedRow, 6).toString();

        // Move to view tasks
        viewTasksModel.addRow(new Object[]{deliveryID, foodItems, pickupAddress, deliveryAddress, phoneNumber, paymentStatus, total, "Pending"});

        // Remove from notifications
        notificationsModel.removeRow(selectedRow);
      // Show pop-up message
        JOptionPane.showMessageDialog(this, 
            "Task accepted! You can view your accepted tasks in the 'View Tasks' tab.", 
            "Task Accepted", 
            JOptionPane.INFORMATION_MESSAGE);
    } else {
        JOptionPane.showMessageDialog(null, "Please select a task to accept.", "No Task Selected", JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_AcceptBtnActionPerformed

    private void DeclineBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeclineBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = notificationsTable.getSelectedRow();

    if (selectedRow != -1) {
        DefaultTableModel notificationsModel = (DefaultTableModel) notificationsTable.getModel();

        // Remove the declined task
        notificationsModel.removeRow(selectedRow);
    } else {
        JOptionPane.showMessageDialog(null, "Please select a task to decline.");
    }

    }//GEN-LAST:event_DeclineBtnActionPerformed

    private void filterComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboBoxActionPerformed
        // TODO add your handling code here:
         String selectedOption = filterComboBox.getSelectedItem().toString(); 
        TotalRevenue revenueCalculator = new TotalRevenue(taskhistoryTable);
        double totalRevenue = revenueCalculator.calculateRevenue(selectedOption);

        // Display the total revenue (assuming you have a JLabel named revenueLabel)
        totalrevenue.setText("Total Revenue: RM " + String.format("%.2f", totalRevenue));
        
         //  Get total orders delivered using OrdersDelivered class
        int totalOrders = ordersDelivered.countOrders(selectedOption);
        totalorders.setText("Total Orders: " + totalOrders);
        
        
        chart.updateChartFromTable(selectedOption);
        
        
    }//GEN-LAST:event_filterComboBoxActionPerformed

    private void goToLogout(){
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
            java.util.logging.Logger.getLogger(DrDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex){
            java.util.logging.Logger.getLogger(DrDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DrDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DrDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AcceptBtn;
    private javax.swing.JButton CollectedBtn;
    private javax.swing.JTextArea CustReviewsDisplay;
    private javax.swing.JLabel CustReviewsLabel;
    private javax.swing.JButton DeclineBtn;
    private javax.swing.JButton DeliveredBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JPanel custreviewsPanel;
    private javax.swing.JLabel custreviewsTab;
    private javax.swing.JLabel drName;
    private javax.swing.JComboBox<String> filterComboBox;
    private javax.swing.JPanel homePanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private java.awt.List list1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menubarPanel;
    private javax.swing.JPanel notificationsPanel;
    private javax.swing.JLabel notificationsTab;
    private javax.swing.JTable notificationsTable;
    private javax.swing.JLabel ordersDeliveredLabel;
    private javax.swing.JPanel ordersDeliveredPanel;
    private javax.swing.JTextArea receiptArea;
    private javax.swing.JLabel revenueDbLabel;
    private javax.swing.JPanel revenueDbPanel;
    private javax.swing.JLabel revenueDbTab;
    private javax.swing.JLabel revenueLabel;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JLabel taskhistoryLabel;
    private javax.swing.JLabel taskhistoryLabel1;
    private javax.swing.JLabel taskhistoryLabel2;
    private javax.swing.JPanel taskhistoryPanel;
    private javax.swing.JLabel taskhistoryTab;
    private javax.swing.JTable taskhistoryTable;
    private javax.swing.JPanel tastiesPanel;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JPanel totalRevenuePanel;
    private javax.swing.JLabel totalorders;
    private javax.swing.JLabel totalrevenue;
    private javax.swing.JLabel usernametxt;
    private javax.swing.JPanel viewtasksPanel;
    private javax.swing.JLabel viewtasksTab;
    private javax.swing.JTable viewtasksTable;
    private javax.swing.JPanel welcomePanel;
    private javax.swing.JLabel welcometxt;
    // End of variables declaration//GEN-END:variables
}
