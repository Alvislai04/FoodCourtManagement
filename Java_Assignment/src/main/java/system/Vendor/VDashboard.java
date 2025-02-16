// Vendor's Dashboard
package system.Vendor;

import com.system.Login;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


public class VDashboard extends javax.swing.JFrame {
    
    Color DefaultColor, ClickedColor;

//    String id;
//    String name;
//    String address;
//    String phoneno;
//    String username;
//    String password;
//    String role;
//    double balance;
            
    Login LogIn = new Login();

    private BufferedImage tempImage;
    private String tempImageFileName;
    private JDialog dialog;
    // filepath for storing image temporarily
    
    
    public VDashboard() {
        initComponents();
        populateOrderTable();
        setResizable(false); // Disable resizing window
        setLocationRelativeTo(null); // Set window to center
        jScrollMenu.setVisible(true);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
        DefaultColor = new Color(153,89,16);
        ClickedColor = new Color(0,0,0);
        
        // Set labels to username, getName doesn't work yet.
        nameLabel1.setText(LogIn.getUsername());
    }
    
    private void populateOrderTable() {
        // Define column names
        String[] columnNames = {"Order ID", "Cust. Name", "Food Ordered", "Quantity", "Total Price", "Order Date/Time", "Order Status"};

        // Use the correct DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
        orderTable.setModel(model); // Set model to the table
        orderTable.setRowHeight(50);

        try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) { 
                    model.addRow(data); // Add row to the correct model
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    private void openFoodDescription (int index) {
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
                    descriptionPanel, 
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addPanel = new javax.swing.JPanel();
        addBtn = new javax.swing.JLabel();
        clearBtn = new javax.swing.JLabel();
        imageLabel = new javax.swing.JLabel();
        foodIDLabel = new javax.swing.JLabel();
        foodNameLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        foodIdField = new javax.swing.JTextField();
        foodNameField = new javax.swing.JTextField();
        priceField = new javax.swing.JTextField();
        uploadBtn = new javax.swing.JButton();
        descriptionPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        descriptionFoodId = new javax.swing.JLabel();
        descriptionFoodName = new javax.swing.JLabel();
        descriptionFoodPrice = new javax.swing.JLabel();
        removeBtn = new javax.swing.JButton();
        jpOrders = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        orderTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jpReviews = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jpRevenue = new javax.swing.JPanel();
        jScrollMenu = new javax.swing.JScrollPane();
        jScrollMenu.getVerticalScrollBar().setUnitIncrement(16);
        menuPanel = new javax.swing.JPanel();
        menuTitle = new javax.swing.JLabel();
        menuAdd = new javax.swing.JLabel();
        foodIcon3 = new javax.swing.JLabel();
        foodIcon2 = new javax.swing.JLabel();
        foodIcon5 = new javax.swing.JLabel();
        foodIcon6 = new javax.swing.JLabel();
        foodIcon4 = new javax.swing.JLabel();
        foodIcon1 = new javax.swing.JLabel();
        foodLabel1 = new javax.swing.JLabel();
        foodLabel2 = new javax.swing.JLabel();
        foodLabel3 = new javax.swing.JLabel();
        foodLabel4 = new javax.swing.JLabel();
        foodLabel5 = new javax.swing.JLabel();
        foodLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jpDivider = new javax.swing.JPanel();
        jpTab = new javax.swing.JPanel();
        nameLabel1 = new javax.swing.JLabel();
        menuTab = new javax.swing.JLabel();
        ordersTab = new javax.swing.JLabel();
        reviewTab = new javax.swing.JLabel();
        revenueTab = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jpTitle = new javax.swing.JPanel();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();

        addPanel.setBackground(new java.awt.Color(255, 255, 255));
        addPanel.setPreferredSize(new java.awt.Dimension(421, 230));

        addBtn.setBackground(new java.awt.Color(153, 89, 16));
        addBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        addBtn.setForeground(new java.awt.Color(255, 255, 255));
        addBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addBtn.setText("Add");
        addBtn.setOpaque(true);
        addBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addBtnMouseClicked(evt);
            }
        });

        clearBtn.setBackground(new java.awt.Color(153, 89, 16));
        clearBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        clearBtn.setForeground(new java.awt.Color(255, 255, 255));
        clearBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clearBtn.setText("Clear");
        clearBtn.setOpaque(true);
        clearBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearBtnMouseClicked(evt);
            }
        });

        imageLabel.setForeground(new java.awt.Color(0, 0, 0));
        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        foodIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        foodIDLabel.setText("Food ID:");

        foodNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        foodNameLabel.setText("Food Name:");

        priceLabel.setForeground(new java.awt.Color(0, 0, 0));
        priceLabel.setText("Price (RM):");

        foodIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodIdFieldActionPerformed(evt);
            }
        });

        foodNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                foodNameFieldActionPerformed(evt);
            }
        });

        uploadBtn.setText("Upload");
        uploadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(foodIDLabel)
                            .addComponent(foodNameLabel)
                            .addComponent(priceLabel))
                        .addGap(18, 18, 18)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceField)
                            .addComponent(foodNameField)
                            .addComponent(foodIdField)))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(48, 48, 48))
            .addGroup(addPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(uploadBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodIDLabel)
                            .addComponent(foodIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(foodNameLabel)
                            .addComponent(foodNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceLabel)
                            .addComponent(priceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(uploadBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        descriptionPanel.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Food ID");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Food Name");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Food Price");

        descriptionFoodId.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodId.setForeground(new java.awt.Color(0, 0, 0));
        descriptionFoodId.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodId.setText("foodID");

        descriptionFoodName.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodName.setForeground(new java.awt.Color(0, 0, 0));
        descriptionFoodName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodName.setText("foodName");

        descriptionFoodPrice.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        descriptionFoodPrice.setForeground(new java.awt.Color(0, 0, 0));
        descriptionFoodPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descriptionFoodPrice.setText("foodPrice");

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout descriptionPanelLayout = new javax.swing.GroupLayout(descriptionPanel);
        descriptionPanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(descriptionFoodId)
                    .addComponent(descriptionFoodName)
                    .addComponent(descriptionFoodPrice))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, descriptionPanelLayout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addComponent(removeBtn)
                .addGap(19, 19, 19))
        );
        descriptionPanelLayout.setVerticalGroup(
            descriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(descriptionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionFoodId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionFoodName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionFoodPrice)
                .addGap(18, 18, 18)
                .addComponent(removeBtn)
                .addGap(11, 11, 11))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));

        jpOrders.setBackground(new java.awt.Color(255, 255, 255));

        orderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Cust. Name", "Food Ordered", "Quantity", "Total Price", "Order Time", "Order Status"
            }
        ));
        jScrollPane1.setViewportView(orderTable);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList1);

        jLabel1.setBackground(new java.awt.Color(153, 89, 16));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Accept");
        jLabel1.setOpaque(true);

        jLabel2.setBackground(new java.awt.Color(153, 89, 16));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Cancel");
        jLabel2.setOpaque(true);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Daily", "Monthly", "Quaterly" }));

        jLabel8.setBackground(new java.awt.Color(153, 89, 16));
        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Complete");
        jLabel8.setOpaque(true);

        javax.swing.GroupLayout jpOrdersLayout = new javax.swing.GroupLayout(jpOrders);
        jpOrders.setLayout(jpOrdersLayout);
        jpOrdersLayout.setHorizontalGroup(
            jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104))))
        );
        jpOrdersLayout.setVerticalGroup(
            jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(40, Short.MAX_VALUE))))
        );

        jpReviews.setBackground(new java.awt.Color(255, 153, 51));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 807, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 575, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout jpReviewsLayout = new javax.swing.GroupLayout(jpReviews);
        jpReviews.setLayout(jpReviewsLayout);
        jpReviewsLayout.setHorizontalGroup(
            jpReviewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jpReviewsLayout.setVerticalGroup(
            jpReviewsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        jpRevenue.setBackground(new java.awt.Color(102, 255, 153));

        javax.swing.GroupLayout jpRevenueLayout = new javax.swing.GroupLayout(jpRevenue);
        jpRevenue.setLayout(jpRevenueLayout);
        jpRevenueLayout.setHorizontalGroup(
            jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 814, Short.MAX_VALUE)
        );
        jpRevenueLayout.setVerticalGroup(
            jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 581, Short.MAX_VALUE)
        );

        jScrollMenu.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuPanel.setBackground(new java.awt.Color(254, 254, 254));

        menuTitle.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        menuTitle.setForeground(new java.awt.Color(0, 0, 0));
        menuTitle.setText("Menu");

        menuAdd.setBackground(new java.awt.Color(153, 89, 16));
        menuAdd.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        menuAdd.setForeground(new java.awt.Color(255, 255, 255));
        menuAdd.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuAdd.setText("Add");
        menuAdd.setOpaque(true);
        menuAdd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuAddMouseClicked(evt);
            }
        });

        foodIcon3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodIcon3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        foodIcon3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                foodIcon3MouseClicked(evt);
            }
        });

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

        foodLabel1.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel1.setText("Food Number 1");

        foodLabel2.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel2.setText("Food Number 2");

        foodLabel3.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel3.setText("Food Number 3");

        foodLabel4.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel4.setText("Food Number 4");

        foodLabel5.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel5.setText("Food Number 5");

        foodLabel6.setForeground(new java.awt.Color(0, 0, 0));
        foodLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodLabel6.setText("Food Number 6");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("*Add food items. Up to 6 only.");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(foodIcon1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(foodLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(foodIcon4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(foodLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(foodLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(foodIcon3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(foodIcon6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(foodLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(menuTitle)
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addComponent(menuAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(97, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(menuTitle)
                .addGap(6, 6, 6)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(42, 42, 42)
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuPanelLayout.createSequentialGroup()
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(foodIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(foodLabel3))
                            .addGroup(menuPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(foodLabel1)))
                        .addGap(39, 39, 39)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(foodIcon6, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(foodLabel4)
                            .addComponent(foodLabel6)))
                    .addGroup(menuPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(foodIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(foodLabel2)
                        .addGap(39, 39, 39)
                        .addComponent(foodIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(foodLabel5)))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jScrollMenu.setViewportView(menuPanel);
        loadFoodImages();

        jpDivider.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jpDividerLayout = new javax.swing.GroupLayout(jpDivider);
        jpDivider.setLayout(jpDividerLayout);
        jpDividerLayout.setHorizontalGroup(
            jpDividerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );
        jpDividerLayout.setVerticalGroup(
            jpDividerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 614, Short.MAX_VALUE)
        );

        jpTab.setBackground(new java.awt.Color(255, 255, 255));
        jpTab.setToolTipText("");

        nameLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        nameLabel1.setText("Vendorname");

        menuTab.setBackground(new java.awt.Color(153, 89, 16));
        menuTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        menuTab.setForeground(new java.awt.Color(255, 255, 255));
        menuTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuTab.setText("Menu");
        menuTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuTab.setOpaque(true);
        menuTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuTabMousePressed(evt);
            }
        });

        ordersTab.setBackground(new java.awt.Color(153, 89, 16));
        ordersTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        ordersTab.setForeground(new java.awt.Color(255, 255, 255));
        ordersTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersTab.setText("Orders");
        ordersTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersTab.setOpaque(true);
        ordersTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                ordersTabMousePressed(evt);
            }
        });

        reviewTab.setBackground(new java.awt.Color(153, 89, 16));
        reviewTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        reviewTab.setForeground(new java.awt.Color(255, 255, 255));
        reviewTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reviewTab.setText("Customer Reviews");
        reviewTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reviewTab.setOpaque(true);
        reviewTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reviewTabMousePressed(evt);
            }
        });

        revenueTab.setBackground(new java.awt.Color(153, 89, 16));
        revenueTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        revenueTab.setForeground(new java.awt.Color(255, 255, 255));
        revenueTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueTab.setText("Revenue Dashboard");
        revenueTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        revenueTab.setMaximumSize(new java.awt.Dimension(163, 26));
        revenueTab.setMinimumSize(new java.awt.Dimension(163, 26));
        revenueTab.setOpaque(true);
        revenueTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenueTabMousePressed(evt);
            }
        });

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jpTabLayout = new javax.swing.GroupLayout(jpTab);
        jpTab.setLayout(jpTabLayout);
        jpTabLayout.setHorizontalGroup(
            jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTabLayout.createSequentialGroup()
                .addGroup(jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(reviewTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ordersTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(menuTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(revenueTab, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                    .addGroup(jpTabLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(nameLabel1))
                    .addGroup(jpTabLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoutBtn)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jpTabLayout.setVerticalGroup(
            jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(nameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ordersTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reviewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenueTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 231, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addContainerGap())
        );

        jpTitle.setBackground(new java.awt.Color(0, 0, 0));

        title_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        title_lbl1.setText("TAS");

        title_lbl2.setBackground(new java.awt.Color(0, 0, 0));
        title_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl2.setForeground(new java.awt.Color(153, 89, 16));
        title_lbl2.setText("TIES");

        javax.swing.GroupLayout jpTitleLayout = new javax.swing.GroupLayout(jpTitle);
        jpTitle.setLayout(jpTitleLayout);
        jpTitleLayout.setHorizontalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_lbl1)
                .addGap(2, 2, 2)
                .addComponent(title_lbl2)
                .addGap(0, 1022, Short.MAX_VALUE))
        );
        jpTitleLayout.setVerticalGroup(
            jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTitleLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jpTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_lbl2)
                    .addComponent(title_lbl1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 314, Short.MAX_VALUE)
                    .addComponent(jpReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 313, Short.MAX_VALUE)
                    .addComponent(jpRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 313, Short.MAX_VALUE)
                    .addComponent(jpOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 313, Short.MAX_VALUE)
                    .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(287, 287, 287)
                    .addComponent(jpDivider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(815, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 33, Short.MAX_VALUE)
                    .addComponent(jpReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 33, Short.MAX_VALUE)
                    .addComponent(jpRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 32, Short.MAX_VALUE)
                    .addComponent(jpOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 31, Short.MAX_VALUE)
                    .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jpDivider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        goToLogout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void reviewTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMousePressed
        menuTab.setBackground(DefaultColor);
        ordersTab.setBackground(DefaultColor);
        reviewTab.setBackground(ClickedColor);
        revenueTab.setBackground(DefaultColor);
        
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(false);
        jpReviews.setVisible(true);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_reviewTabMousePressed

    private void revenueTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMousePressed
        menuTab.setBackground(DefaultColor);
        ordersTab.setBackground(DefaultColor);
        reviewTab.setBackground(DefaultColor);
        revenueTab.setBackground(ClickedColor);
        
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(true);
    }//GEN-LAST:event_revenueTabMousePressed

    private void menuTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuTabMousePressed
        menuTab.setBackground(ClickedColor);
        ordersTab.setBackground(DefaultColor);
        reviewTab.setBackground(DefaultColor);
        revenueTab.setBackground(DefaultColor);
        
        
        jScrollMenu.setVisible(true);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_menuTabMousePressed

    private void ordersTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersTabMousePressed
        menuTab.setBackground(DefaultColor);
        ordersTab.setBackground(ClickedColor);
        reviewTab.setBackground(DefaultColor);
        revenueTab.setBackground(DefaultColor);
        
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(true);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_ordersTabMousePressed

    private void menuAddMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_menuAddMouseClicked
        foodIdField.setText("");
        foodNameField.setText("");
        priceField.setText("");
        imageLabel.setIcon(null);
        
    JOptionPane optionPane = new JOptionPane(
        addPanel, 
        JOptionPane.PLAIN_MESSAGE, 
        JOptionPane.DEFAULT_OPTION, 
        null, 
        new Object[]{} // Empty array removes all default buttons
    );

    dialog = optionPane.createDialog("Add New Food Item");
    optionPane.setBorder(null);
    
    dialog.setVisible(true);
    dialog.pack();

    }//GEN-LAST:event_menuAddMouseClicked

    private void foodIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodIdFieldActionPerformed
        
        
        
        
    }//GEN-LAST:event_foodIdFieldActionPerformed

    private void clearBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearBtnMouseClicked
        foodIdField.setText("");
        foodNameField.setText("");
        priceField.setText("");
    }//GEN-LAST:event_clearBtnMouseClicked

    private void addBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addBtnMouseClicked
        String foodId = foodIdField.getText();
        String foodName = foodNameField.getText();
        String price = priceField.getText();
        
        if (foodId.isEmpty() || foodName.isEmpty() || price.isEmpty() || tempImage == null) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields and upload an image!");
            return;
        } else {
                
            String foodPicturesFolderPath = "foodPictures";
            File foodPicturesFolder = new File(foodPicturesFolderPath);
            if (!foodPicturesFolder.exists()) {
                JOptionPane.showMessageDialog(this, "foodPictures folder not found.");
            }

            String fileName = tempImageFileName;
            File outputFile = new File(foodPicturesFolder, fileName);

            try {
            ImageIO.write(tempImage, "png", outputFile); // Save as PNG (or use "jpg" for JPEG)
            // Store the relative path (e.g., "foodPictures/image.png")
            String relativeImagePath = foodPicturesFolderPath + File.separator + fileName;

            // Process the data (e.g., save to a file or database)
            String data = foodId + "," + foodName + "," + "RM" + price + "," + relativeImagePath + "\n";
            try (java.io.FileWriter fw = new java.io.FileWriter("vendorFood.txt", true)) {
                fw.write(data);
                JOptionPane.showMessageDialog(this, "Item added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            dialog.dispose();
        }

        loadFoodImages();
    }//GEN-LAST:event_addBtnMouseClicked

    private void uploadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadBtnActionPerformed
        JFileChooser chooser = new JFileChooser();
        
        // Create a file filter for image files
    javax.swing.filechooser.FileNameExtensionFilter filter = new javax.swing.filechooser.FileNameExtensionFilter(
        "Png Files", "png" // Allowed file extensions
    );
    chooser.setFileFilter(filter); // Set the filter to the file chooser

    // Show the file chooser dialog
    int returnValue = chooser.showOpenDialog(this);

    // Check if a file was selected
    if (returnValue == JFileChooser.APPROVE_OPTION) {
        // Get the selected file
        File selectedFile = chooser.getSelectedFile();
        tempImageFileName = selectedFile.getName();

        // Load the image and set it as the icon for imageLabel
        try {
            
            tempImage = ImageIO.read(selectedFile); // Store the image in memory

            // Display the image in imageLabel
            ImageIcon imageIcon = new ImageIcon(tempImage);

            // Resize the image to fit the label
            Image image = imageIcon.getImage();
            Image scaledImage = image.getScaledInstance(
                imageLabel.getWidth(), 
                imageLabel.getHeight(), 
                Image.SCALE_SMOOTH
            );
            imageIcon = new ImageIcon(scaledImage);

            // Set the image as the icon for imageLabel
            imageLabel.setIcon(imageIcon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading image: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
         }
        }
    }//GEN-LAST:event_uploadBtnActionPerformed

    private void foodNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_foodNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_foodNameFieldActionPerformed

    private void foodIcon1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon1MouseClicked
        openFoodDescription(0);
    }//GEN-LAST:event_foodIcon1MouseClicked

    private void foodIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon2MouseClicked
        openFoodDescription(1);
    }//GEN-LAST:event_foodIcon2MouseClicked

    private void foodIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon3MouseClicked
        openFoodDescription(2);
    }//GEN-LAST:event_foodIcon3MouseClicked

    private void foodIcon4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon4MouseClicked
        openFoodDescription(3);
    }//GEN-LAST:event_foodIcon4MouseClicked

    private void foodIcon5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon5MouseClicked
        openFoodDescription(4);
    }//GEN-LAST:event_foodIcon5MouseClicked

    private void foodIcon6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon6MouseClicked
        openFoodDescription(5);
    }//GEN-LAST:event_foodIcon6MouseClicked

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed
        // TODO add your handling code here:
        JOptionPane.showConfirmDialog(null, 
                "Do you want to remove this item?", "Remove food...",JOptionPane.YES_NO_CANCEL_OPTION);
    }//GEN-LAST:event_removeBtnActionPerformed

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
            java.util.logging.Logger.getLogger(VDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBtn;
    private javax.swing.JPanel addPanel;
    private javax.swing.JLabel clearBtn;
    private javax.swing.JLabel descriptionFoodId;
    private javax.swing.JLabel descriptionFoodName;
    private javax.swing.JLabel descriptionFoodPrice;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JLabel foodIDLabel;
    private javax.swing.JLabel foodIcon1;
    private javax.swing.JLabel foodIcon2;
    private javax.swing.JLabel foodIcon3;
    private javax.swing.JLabel foodIcon4;
    private javax.swing.JLabel foodIcon5;
    private javax.swing.JLabel foodIcon6;
    private javax.swing.JTextField foodIdField;
    private javax.swing.JLabel foodLabel1;
    private javax.swing.JLabel foodLabel2;
    private javax.swing.JLabel foodLabel3;
    private javax.swing.JLabel foodLabel4;
    private javax.swing.JLabel foodLabel5;
    private javax.swing.JLabel foodLabel6;
    private javax.swing.JTextField foodNameField;
    private javax.swing.JLabel foodNameLabel;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jpDivider;
    private javax.swing.JPanel jpOrders;
    private javax.swing.JPanel jpRevenue;
    private javax.swing.JPanel jpReviews;
    private javax.swing.JPanel jpTab;
    private javax.swing.JPanel jpTitle;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel menuAdd;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JLabel menuTab;
    private javax.swing.JLabel menuTitle;
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel ordersTab;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel revenueTab;
    private javax.swing.JLabel reviewTab;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
