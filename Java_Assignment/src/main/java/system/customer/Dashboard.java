/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package system.customer;

import com.system.Login;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
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

        this.setSize(1000, 561);// Set the size of the frame
        this.setResizable(false);// Prevent the frame from being resizable

        MenuTab.setBackground(Color.black);//set OrderTab to black and order Opened after login
        NotificationPanel.setVisible(false);
        TransactionPanel panel = new TransactionPanel();
        panel.loadUserTransaction(Login.getLoggedInUserId());
    }
    
    private void openOrderPanel (int index) {
    String vendorFoodFilePath = "vendorFood.txt";
    JLabel[] descriptionFood = {descriptionFoodId, descriptionFoodName, descriptionFoodPrice};

    try (BufferedReader reader = new BufferedReader(new FileReader(vendorFoodFilePath))) {
        String line;
        int currentIndex = 0;
        while ((line = reader.readLine()) != null) {
            // Only process the line that matches the clicked icon's index
            if (currentIndex == index) {
                String[] data = line.split(",");
                String foodId = data[0];
                String foodName = data[1];
                String price = data[2];
                String imagePath = data[3];

                // Update the description panel with the food details
                descriptionFood[0].setText("ID: " + foodId);
                descriptionFood[1].setText("Name: " + foodName);
                descriptionFood[2].setText("Price: " + price);

                // Create and show the dialog
                JOptionPane optionPane = new JOptionPane(
                    OrderPanel, 
                    JOptionPane.PLAIN_MESSAGE, 
                    JOptionPane.DEFAULT_OPTION, 
                    null, 
                    new Object[]{}
                );

                JDialog dialog = optionPane.createDialog("Food Details");
                optionPane.setBorder(null);
                dialog.setVisible(true);
                dialog.pack();

                break; // Exit the loop after finding the matching item
            }
            currentIndex++;
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error reading vendorFood.txt!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    }
    
    private void loadFoodImages () {
        String vendorFoodFilePath = "vendorFood.txt";
        JLabel[] foodIcon = {foodIcon1, foodIcon2, foodIcon3, foodIcon4, foodIcon5, foodIcon6};
        JLabel[] foodLabels = {foodLabel1, foodLabel2, foodLabel3, foodLabel4, foodLabel5, foodLabel6};

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
                    ImageIcon icon = new ImageIcon(image);

                    // Resize the image (optional)
                    Image scaledImage = image.getScaledInstance(
                        187, 179, Image.SCALE_SMOOTH
                    );
                    icon = new ImageIcon(scaledImage);

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
        OrderHistoryPanel = new javax.swing.JPanel();
        ComplaintPanel = new javax.swing.JPanel();

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
        jLabel4.setText("Food ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setText("Food Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setText("Food Price");

        descriptionFoodId.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodId.setText("foodID");

        javax.swing.GroupLayout OrderPanelLayout = new javax.swing.GroupLayout(OrderPanel);
        OrderPanel.setLayout(OrderPanelLayout);
        OrderPanelLayout.setHorizontalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(OrderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(OrderBtn))
                    .addGroup(OrderPanelLayout.createSequentialGroup()
                        .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionFoodPrice)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(descriptionFoodId)
                            .addComponent(descriptionFoodName))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        OrderPanelLayout.setVerticalGroup(
            OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderPanelLayout.createSequentialGroup()
                .addContainerGap()
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
                .addGroup(OrderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OrderBtn))
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
        jLabel1.setText("Dashboard");

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
        ComplaintTab.setText("Comment");
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(notificationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
                .addContainerGap())
        );
        NotificationPanelLayout.setVerticalGroup(
            NotificationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NotificationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notificationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
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
            .addGap(0, 697, Short.MAX_VALUE)
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
            .addGap(0, 552, Short.MAX_VALUE)
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
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        jPanel1.add(MenuPanel);

        OrderStatusPanel.setBackground(new java.awt.Color(0, 51, 255));

        LeaveReviewBtn.setText("LEAVE REVIEW");
        LeaveReviewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LeaveReviewBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout OrderStatusPanelLayout = new javax.swing.GroupLayout(OrderStatusPanel);
        OrderStatusPanel.setLayout(OrderStatusPanelLayout);
        OrderStatusPanelLayout.setHorizontalGroup(
            OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderStatusPanelLayout.createSequentialGroup()
                .addContainerGap(466, Short.MAX_VALUE)
                .addComponent(LeaveReviewBtn)
                .addGap(110, 110, 110))
        );
        OrderStatusPanelLayout.setVerticalGroup(
            OrderStatusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, OrderStatusPanelLayout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addComponent(LeaveReviewBtn)
                .addGap(257, 257, 257))
        );

        jPanel1.add(OrderStatusPanel);

        OrderHistoryPanel.setBackground(new java.awt.Color(51, 255, 51));

        javax.swing.GroupLayout OrderHistoryPanelLayout = new javax.swing.GroupLayout(OrderHistoryPanel);
        OrderHistoryPanel.setLayout(OrderHistoryPanelLayout);
        OrderHistoryPanelLayout.setHorizontalGroup(
            OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        OrderHistoryPanelLayout.setVerticalGroup(
            OrderHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jPanel1.add(OrderHistoryPanel);

        ComplaintPanel.setBackground(new java.awt.Color(255, 0, 255));

        javax.swing.GroupLayout ComplaintPanelLayout = new javax.swing.GroupLayout(ComplaintPanel);
        ComplaintPanel.setLayout(ComplaintPanelLayout);
        ComplaintPanelLayout.setHorizontalGroup(
            ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 697, Short.MAX_VALUE)
        );
        ComplaintPanelLayout.setVerticalGroup(
            ComplaintPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
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
        // TODO add your handling code here:
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(true);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(false);
        MenuTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationPanel.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_MenuTabMousePressed

    private void OrderStatusTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderStatusTabMousePressed
        // TODO add your handling code here:
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(true);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(false);
        OrderStatusTab.setBackground(Color.black);
        MenuTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationPanel.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_OrderStatusTabMousePressed

    private void OrderHistoryTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OrderHistoryTabMousePressed
        // TODO add your handling code here:
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(true);
        ComplaintPanel.setVisible(false);
        OrderHistoryTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        MenuTab.setBackground(new Color(153, 89, 16));
        ComplaintTab.setBackground(new Color(153, 89, 16));
        NotificationPanel.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_OrderHistoryTabMousePressed

    private void ComplaintTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ComplaintTabMousePressed
        // TODO add your handling code here:
        NotificationPanel.setVisible(false);
        MenuPanel.setVisible(false);
        OrderStatusPanel.setVisible(false);
        OrderHistoryPanel.setVisible(false);
        ComplaintPanel.setVisible(true);
        ComplaintTab.setBackground(Color.black);
        OrderStatusTab.setBackground(new Color(153, 89, 16));
        OrderHistoryTab.setBackground(new Color(153, 89, 16));
        MenuTab.setBackground(new Color(153, 89, 16));
        NotificationPanel.setBackground(new Color(153, 89, 16));
    }//GEN-LAST:event_ComplaintTabMousePressed

    private void LeaveReviewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LeaveReviewBtnActionPerformed
        // TODO add your handling code here:
        String deliveryID = getLatestDeliveryID(); // Fetch delivery ID (you need to implement this method)

        if (deliveryID != null) {
            ReviewForm reviewForm = new ReviewForm(deliveryID); // Pass the correct deliveryID
            reviewForm.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Delivery ID not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_LeaveReviewBtnActionPerformed

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
        // TODO add your handling code here:
    }//GEN-LAST:event_OrderBtnActionPerformed

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
    private javax.swing.JPanel ComplaintPanel;
    private javax.swing.JLabel ComplaintTab;
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
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
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
