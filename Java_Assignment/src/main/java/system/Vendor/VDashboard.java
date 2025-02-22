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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import system.Vendor.vendorChart;
import system.Vendor.customListRender.MultilineListCellRenderer;


public class VDashboard extends javax.swing.JFrame {
    
    String vendorId;
    String vendorName;
    double vendorBalance;
    String selectedFoodId;
    
    String vendorFoodFilePath = "vendorFood.txt";

    
    Color DefaultColor, ClickedColor;

    private BufferedImage tempImage;
    private String tempImageFileName;
    private JDialog dialog;
    // filepath for storing image temporarily
    

    
    
    public VDashboard(String vendorId, String vendorName, double vendorBalance) {
        initComponents();
        
        this.vendorId = vendorId;
        this.vendorName = vendorName;
        this.vendorBalance = vendorBalance;
        
        // Set label to vendor's name
        nameLabel1.setText(vendorName);
        
        populateOrderTable();
        populateReviewTable();
        
        initializeRevenueChart();
        
        setResizable(false); // Disable resizing window
        setLocationRelativeTo(null); // Set window to center
        jScrollMenu.setVisible(true);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
        DefaultColor = new Color(153,89,16);
        ClickedColor = new Color(0,0,0);

        
        
        
        
        
        
        
        customerReviewList.setCellRenderer(new MultilineListCellRenderer());
        
    }
    
    private void initializeRevenueChart() { 
        
    vendorChart chart = new vendorChart(); // Instantiate the vendorChart class
        chartPanel.removeAll(); // Clear any previous content
        chartPanel.setLayout(new BorderLayout()); // Ensure it has a layout
        chartPanel.add(chart.createChart(), BorderLayout.CENTER); // Add the chart
        chartPanel.revalidate(); // Refresh the panel
        chartPanel.repaint(); // Redraw the panel

        
        
        
        double totalRevenue = vendorRevenue.calculateRevenue();
        revenueNumbers.setText(String.format("RM %.2f", totalRevenue));
        
        // Calculate total orders and update the orderNumbers label
        int totalOrders = customerTotalOrders.countTotalOrders();
        orderNumbers.setText(String.valueOf(totalOrders));
}

    
    
    private void populateOrderTable() {
        // Define column names
        String[] columnNames = {"Order ID", "Cust. Name", "Food Ordered","Quantity", "Total Price", "Order Date", "Order Time", "Order Status"};

        // Use the correct DefaultTableModel
        DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
        orderTable.setModel(model); // Set model to the table
        orderTable.setRowHeight(50);

        try (BufferedReader reader = new BufferedReader(new FileReader("customerOrder.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) { 
                    model.addRow(data); // Add row to the correct model
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void populateReviewTable() {

    String[] columnNames = {"ID", "Cust. Name", "Date"};
    
    DefaultTableModel model = new DefaultTableModel(columnNames, 0); 
    reviewTable.setModel(model); // Set model to the table
    reviewTable.setRowHeight(50);
    
    try (BufferedReader reader = new BufferedReader(new FileReader("vendorReview.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 5) { 
                model.addRow(data); // Add row to the correct model
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    
    reviewTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            int selectedRow = reviewTable.getSelectedRow();
            if (selectedRow != -1) {
                // Get the ID from the selected row
                String reviewId = reviewTable.getValueAt(selectedRow, 0).toString();
                
                // Now find the full row data by reading the file again or from the data you've already read
                try (BufferedReader reader = new BufferedReader(new FileReader("vendorReview.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(",");
                        if (data.length == 5 && data[0].equals(reviewId)) {
                            // Found the row with matching ID, populate the list
                            // Assuming 'list' is the List you want to populate
                            customerReviewList.setListData(new String[]{
                                "ID:\n" + data[0],
                                "Customer Name:\n" + data[1],
                                "Date:\n" + data[2],
                                "Rating:\n" + data[3],
                                "Review:\n" + data[4]
                            });
                            break; // Exit the loop once the data is found
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}


    
    
    private void storeFoodInfo (int index) {
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
                
                selectedFoodId = foodId;

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

                dialog = optionPane.createDialog("Food Details");
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
    
    
    private void removeFoodItem() {
        File file = new File(vendorFoodFilePath);
        
        try {
            Path path = file.toPath();
            List<String> lines = Files.readAllLines(path);
            
            List<String> updatedLines = new ArrayList<>();
            for (String line : lines) {
                String[] data = line.split(",");
                if (!data[0].equals(selectedFoodId)) {
                    updatedLines.add(line);
                }
            }
            
            Files.write(path, updatedLines);
        
            

        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating vendorFood.txt!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
        menuPanel.revalidate();
        menuPanel.repaint();
    }
    
    private void updateOrderStatusInFile(String orderId, String newStatus) {
    String inputFile = "customerOrder.txt";
    String tempFile = "customerOrder_temp.txt";

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
         BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(","); // Assuming the file is CSV
            if (data.length == 7 && data[0].equals(orderId)) {
                // Update the "Order Status" field (index 6)
                data[6] = newStatus;
                line = String.join(",", data);
            }
            writer.write(line);
            writer.newLine();
        }
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    // Replace the old file with the updated file
    File oldFile = new File(inputFile);
    File newFile = new File(tempFile);

    if (oldFile.delete()) {
        newFile.renameTo(oldFile);
    }
}
    
    private void filterOrderTable(String statusFilter) {
        DefaultTableModel model = (DefaultTableModel) orderTable.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        orderTable.setRowSorter(sorter);

        // Apply the filter
        if (statusFilter.equals("All")) {
            sorter.setRowFilter(null); // Show all rows
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("^" + statusFilter + "$", 6)); // Filter by "Order Status" column (index 6)
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
        orderAcceptBtn = new javax.swing.JLabel();
        orderCancelBtn = new javax.swing.JLabel();
        filterCombobox = new javax.swing.JComboBox<>();
        orderCompleteBtn = new javax.swing.JLabel();
        jpReviews = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        customerReviewList = new javax.swing.JList<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        reviewTable = new javax.swing.JTable();
        jpRevenue = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        revenueNumbers = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        orderNumbers = new javax.swing.JLabel();
        chartPanel = new javax.swing.JPanel();
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
        jLabel9 = new javax.swing.JLabel();
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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        orderTable.getTableHeader().setReorderingAllowed(false);
        orderTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(orderTable);
        if (orderTable.getColumnModel().getColumnCount() > 0) {
            orderTable.getColumnModel().getColumn(0).setResizable(false);
            orderTable.getColumnModel().getColumn(1).setResizable(false);
            orderTable.getColumnModel().getColumn(2).setResizable(false);
            orderTable.getColumnModel().getColumn(3).setResizable(false);
            orderTable.getColumnModel().getColumn(4).setResizable(false);
            orderTable.getColumnModel().getColumn(5).setResizable(false);
            orderTable.getColumnModel().getColumn(6).setResizable(false);
        }

        orderAcceptBtn.setBackground(new java.awt.Color(153, 89, 16));
        orderAcceptBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        orderAcceptBtn.setForeground(new java.awt.Color(255, 255, 255));
        orderAcceptBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderAcceptBtn.setText("Accept");
        orderAcceptBtn.setOpaque(true);
        orderAcceptBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderAcceptBtnMouseClicked(evt);
            }
        });

        orderCancelBtn.setBackground(new java.awt.Color(153, 89, 16));
        orderCancelBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        orderCancelBtn.setForeground(new java.awt.Color(255, 255, 255));
        orderCancelBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderCancelBtn.setText("Cancel");
        orderCancelBtn.setOpaque(true);
        orderCancelBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderCancelBtnMouseClicked(evt);
            }
        });

        filterCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Accepted", "Completed", "Cancelled", "All" }));
        filterCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterComboboxActionPerformed(evt);
            }
        });

        orderCompleteBtn.setBackground(new java.awt.Color(153, 89, 16));
        orderCompleteBtn.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        orderCompleteBtn.setForeground(new java.awt.Color(255, 255, 255));
        orderCompleteBtn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderCompleteBtn.setText("Complete");
        orderCompleteBtn.setOpaque(true);
        orderCompleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderCompleteBtnMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpOrdersLayout = new javax.swing.GroupLayout(jpOrders);
        jpOrders.setLayout(jpOrdersLayout);
        jpOrdersLayout.setHorizontalGroup(
            jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpOrdersLayout.createSequentialGroup()
                                .addComponent(orderAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(orderCompleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                                .addComponent(orderCancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jpOrdersLayout.createSequentialGroup()
                        .addComponent(filterCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jpOrdersLayout.setVerticalGroup(
            jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpOrdersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(filterCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jpOrdersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(orderAcceptBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orderCompleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jpReviews.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Customer Reviews");

        customerReviewList.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jScrollPane5.setViewportView(customerReviewList);

        jScrollPane6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        reviewTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane6.setViewportView(reviewTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 456, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                .addContainerGap(69, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpReviewsLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jpRevenue.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Revenue Dashboard");

        jPanel2.setBackground(new java.awt.Color(153, 89, 16));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(260, 200));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));

        jLabel12.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("TOTAL REVENUE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        revenueNumbers.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        revenueNumbers.setForeground(new java.awt.Color(255, 255, 255));
        revenueNumbers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueNumbers.setText("Total revenue");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(66, Short.MAX_VALUE)
                .addComponent(revenueNumbers)
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(revenueNumbers)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(153, 89, 16));
        jPanel3.setPreferredSize(new java.awt.Dimension(260, 200));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        jLabel11.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("ORDERS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        orderNumbers.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        orderNumbers.setForeground(new java.awt.Color(255, 255, 255));
        orderNumbers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orderNumbers.setText("No. of orders");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(orderNumbers)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(orderNumbers)
                .addGap(0, 59, Short.MAX_VALUE))
        );

        chartPanel.setBackground(new java.awt.Color(221, 221, 221));
        chartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        chartPanel.setPreferredSize(new java.awt.Dimension(715, 252));

        javax.swing.GroupLayout chartPanelLayout = new javax.swing.GroupLayout(chartPanel);
        chartPanel.setLayout(chartPanelLayout);
        chartPanelLayout.setHorizontalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartPanelLayout.setVerticalGroup(
            chartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jpRevenueLayout = new javax.swing.GroupLayout(jpRevenue);
        jpRevenue.setLayout(jpRevenueLayout);
        jpRevenueLayout.setHorizontalGroup(
            jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRevenueLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpRevenueLayout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                    .addGroup(jpRevenueLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 166, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jpRevenueLayout.setVerticalGroup(
            jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpRevenueLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 34, Short.MAX_VALUE)
                .addGroup(jpRevenueLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
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
                .addContainerGap(123, Short.MAX_VALUE))
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
            .addGap(0, 615, Short.MAX_VALUE)
        );

        jpTab.setBackground(new java.awt.Color(255, 255, 255));
        jpTab.setToolTipText("");

        nameLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        nameLabel1.setText("Vendorname");

        menuTab.setBackground(new java.awt.Color(153, 89, 16));
        menuTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        menuTab.setForeground(new java.awt.Color(255, 255, 255));
        menuTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        menuTab.setText("Restaurant Menu");
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
        ordersTab.setText("Customer Orders");
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

        jLabel9.setFont(new java.awt.Font("Segoe UI Black", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Welcome,");

        javax.swing.GroupLayout jpTabLayout = new javax.swing.GroupLayout(jpTab);
        jpTab.setLayout(jpTabLayout);
        jpTabLayout.setHorizontalGroup(
            jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(reviewTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ordersTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(menuTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(revenueTab, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                    .addComponent(logoutBtn)
                    .addComponent(jLabel9)
                    .addGroup(jpTabLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(nameLabel1)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jpTabLayout.setVerticalGroup(
            jpTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpTabLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameLabel1)
                .addGap(12, 12, 12)
                .addComponent(menuTab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ordersTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reviewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenueTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
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
                .addGap(0, 1040, Short.MAX_VALUE))
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
                .addGap(0, 857, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 305, Short.MAX_VALUE)
                    .addComponent(jpReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 307, Short.MAX_VALUE)
                    .addComponent(jpRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 305, Short.MAX_VALUE)
                    .addComponent(jpOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 305, Short.MAX_VALUE)
                    .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(287, 287, 287)
                    .addComponent(jpDivider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(833, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(jpReviews, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 25, Short.MAX_VALUE)
                    .addComponent(jpRevenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 26, Short.MAX_VALUE)
                    .addComponent(jpOrders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 30, Short.MAX_VALUE)
                    .addComponent(jScrollMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 593, javax.swing.GroupLayout.PREFERRED_SIZE)))
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

        initializeRevenueChart();
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
        storeFoodInfo(0);
    }//GEN-LAST:event_foodIcon1MouseClicked

    private void foodIcon2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon2MouseClicked
        storeFoodInfo(1);
    }//GEN-LAST:event_foodIcon2MouseClicked

    private void foodIcon3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon3MouseClicked
        storeFoodInfo(2);
    }//GEN-LAST:event_foodIcon3MouseClicked

    private void foodIcon4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon4MouseClicked
        storeFoodInfo(3);
    }//GEN-LAST:event_foodIcon4MouseClicked

    private void foodIcon5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon5MouseClicked
        storeFoodInfo(4);
    }//GEN-LAST:event_foodIcon5MouseClicked

    private void foodIcon6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodIcon6MouseClicked
        storeFoodInfo(5);
    }//GEN-LAST:event_foodIcon6MouseClicked

    private void removeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeBtnActionPerformed

        int result = JOptionPane.showConfirmDialog(null, 
                "Do you want to remove this item?", "Remove food...",JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            System.out.println(selectedFoodId);
            removeFoodItem();
            loadFoodImages();
            JOptionPane.showMessageDialog(this, "Food Item Removed!");
            
            if (dialog != null) {
            dialog.dispose();
            }
        }
    }//GEN-LAST:event_removeBtnActionPerformed

    private void filterComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterComboboxActionPerformed
        // TODO add your handling code here:
        String selectedFilter = filterCombobox.getSelectedItem().toString();
        filterOrderTable(selectedFilter);
        
        
        
    }//GEN-LAST:event_filterComboboxActionPerformed

    private void orderAcceptBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderAcceptBtnMouseClicked
            // TODO add your handling code here:
        int selectedRow = orderTable.getSelectedRow(); // Get the selected row
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select an order to accept!");
            return;
        }

        // Get Order ID and current Order Status
        String orderId = orderTable.getValueAt(selectedRow, 0).toString();
        String orderStatus = orderTable.getValueAt(selectedRow, 7).toString();

        // Check if the order is already accepted or completed
        if (orderStatus.equals("Accepted") || orderStatus.equals("Completed")) {
            JOptionPane.showMessageDialog(null, "Order is already accepted or completed!");
            return;
        } else if (orderStatus.equals("Cancelled")) {
            JOptionPane.showMessageDialog(null, "Order is already cancelled!");
            return;
        }

        int result = JOptionPane.showConfirmDialog(null, "Accept this order?", "Accepting order...", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // Update the order status in the table
            orderTable.setValueAt("Accepted", selectedRow, 7); // Update the "Order Status" column

            // Update the order status in the text file
            updateOrderStatusInFile(orderId, "Accepted");

            JOptionPane.showMessageDialog(null, "Order " + orderId + " has been accepted!");
        }
    }//GEN-LAST:event_orderAcceptBtnMouseClicked

    private void orderCompleteBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderCompleteBtnMouseClicked
        int selectedRow = orderTable.getSelectedRow(); // Get the selected row
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select an order to complete!");
            return;
        }

        // Get Order ID and current Order Status
        String orderId = orderTable.getValueAt(selectedRow, 0).toString();
        String orderStatus = orderTable.getValueAt(selectedRow, 7).toString();

        // Check if the order is "Accepted"
        if (!orderStatus.equals("Accepted")) {
            JOptionPane.showMessageDialog(null, "Only orders with 'Accepted' status can be completed!");
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(null, "Complete this order?", "Completing order...", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // Update the order status in the table
            orderTable.setValueAt("Completed", selectedRow, 7); // Update the "Order Status" column

            // Update the order status in the text file
            updateOrderStatusInFile(orderId, "Completed");

            JOptionPane.showMessageDialog(null, "Order " + orderId + " has been completed!");
        }
    }//GEN-LAST:event_orderCompleteBtnMouseClicked

    private void orderCancelBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderCancelBtnMouseClicked
        // TODO add your handling code here:
        int selectedRow = orderTable.getSelectedRow(); // Get the selected row
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Please select an order to complete!");
            return;
        }

        // Get Order ID and current Order Status
        String orderId = orderTable.getValueAt(selectedRow, 0).toString();
        String orderStatus = orderTable.getValueAt(selectedRow, 7).toString();

        if (orderStatus.equals("Accepted") || orderStatus.equals("Completed")) {
            JOptionPane.showMessageDialog(null, "Completed or Accepted orders can't be cancelled!");
            return;
        } else if (orderStatus.equals("Cancelled")) {
            JOptionPane.showMessageDialog(null, "Order is already cancelled!");
            return;
        }
        
        int result = JOptionPane.showConfirmDialog(null, "Cancel this order?", "Cancelling order...", JOptionPane.YES_NO_OPTION);
        
        if (result == JOptionPane.YES_OPTION) {
            // Update the order status in the table
            orderTable.setValueAt("Cancelled", selectedRow, 7); // Update the "Order Status" column

            // Update the order status in the text file
            updateOrderStatusInFile(orderId, "Cancelled");

            JOptionPane.showMessageDialog(null, "Order " + orderId + " has been cancelled!");
        }


    }//GEN-LAST:event_orderCancelBtnMouseClicked

    private void orderTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_orderTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = orderTable.getSelectedRow();
        if (selectedRow != -1) {
            String orderId = orderTable.getValueAt(selectedRow, 0).toString();
            System.out.println("Selected Order ID:" + orderId);
        }
    }//GEN-LAST:event_orderTableMouseClicked

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
                new VDashboard("", "", 0.0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addBtn;
    private javax.swing.JPanel addPanel;
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel clearBtn;
    private javax.swing.JList<String> customerReviewList;
    private javax.swing.JLabel descriptionFoodId;
    private javax.swing.JLabel descriptionFoodName;
    private javax.swing.JLabel descriptionFoodPrice;
    private javax.swing.JPanel descriptionPanel;
    private javax.swing.JComboBox<String> filterCombobox;
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
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollMenu;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
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
    private javax.swing.JLabel orderAcceptBtn;
    private javax.swing.JLabel orderCancelBtn;
    private javax.swing.JLabel orderCompleteBtn;
    private javax.swing.JLabel orderNumbers;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel ordersTab;
    private javax.swing.JTextField priceField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JButton removeBtn;
    private javax.swing.JLabel revenueNumbers;
    private javax.swing.JLabel revenueTab;
    private javax.swing.JLabel reviewTab;
    private javax.swing.JTable reviewTable;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JButton uploadBtn;
    // End of variables declaration//GEN-END:variables
}
