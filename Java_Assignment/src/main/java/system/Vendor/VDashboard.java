// Vendor's Dashboard
package system.Vendor;

import com.system.Login;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.table.DefaultTableModel;


public class VDashboard extends javax.swing.JFrame {

//        // TODO:
//    public Home() {
//        initComponents();
//         // Putting the class into login variable. So can use across different classes
//         // Changes usernameLabel (jlabel2) into username typed in Login.
//        usernameLabel.setText(login.getUsername() + ".");
//        this.refreshData();
//    }
    Login LogIn = new Login();
    
    public VDashboard() {
        initComponents();
        populateOrderTable();
        setResizable(false); // Disable resizing window
        setLocationRelativeTo(null); // Set window to center
        jScrollMenu.setVisible(true);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
        
        // Set labels to username, getName doesn't work yet.
        nameLabel1.setText(LogIn.getUsername());
        nameLabel2.setText(LogIn.getUsername());
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        jPanel3 = new javax.swing.JPanel();
        nameLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jpDivider = new javax.swing.JPanel();
        jpTab = new javax.swing.JPanel();
        nameLabel1 = new javax.swing.JLabel();
        foodbeverageTab = new javax.swing.JLabel();
        ordersTab = new javax.swing.JLabel();
        reviewTab = new javax.swing.JLabel();
        revenueTab = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jpTitle = new javax.swing.JPanel();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();

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

        jPanel3.setBackground(new java.awt.Color(254, 254, 254));

        nameLabel2.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 36)); // NOI18N
        nameLabel2.setText("Vendorname");

        jButton1.setBackground(new java.awt.Color(153, 89, 16));
        jButton1.setText("Edit Profile");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("____________________________________________________");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 153, 153));
        jLabel4.setText("Pasta");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel5.setText("Pasta/Main");

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel6.setText("Dessert");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 0, 18)); // NOI18N
        jLabel7.setText("Beverages");

        jPanel4.setBackground(new java.awt.Color(153, 89, 16));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(nameLabel2)
                        .addGap(42, 42, 42)
                        .addComponent(jButton1))
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addGap(0, 789, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel2)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(46, 46, 46)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(128, 128, 128)
                .addComponent(jLabel6)
                .addGap(213, 213, 213)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollMenu.setViewportView(jPanel3);

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

        foodbeverageTab.setBackground(new java.awt.Color(153, 89, 16));
        foodbeverageTab.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        foodbeverageTab.setForeground(new java.awt.Color(255, 255, 255));
        foodbeverageTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        foodbeverageTab.setText("Menu");
        foodbeverageTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        foodbeverageTab.setOpaque(true);
        foodbeverageTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                foodbeverageTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                foodbeverageTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                foodbeverageTabMousePressed(evt);
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
                            .addComponent(foodbeverageTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(foodbeverageTab, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ordersTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reviewTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(revenueTab, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 227, Short.MAX_VALUE)
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
        // TODO add your handling code here:
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(false);
        jpReviews.setVisible(true);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_reviewTabMousePressed

    private void revenueTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMousePressed
        // TODO add your handling code here:
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(true);
    }//GEN-LAST:event_revenueTabMousePressed

    private void foodbeverageTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodbeverageTabMouseEntered
        // TODO add your handling code here:
        
        
        
        
        
    }//GEN-LAST:event_foodbeverageTabMouseEntered

    private void foodbeverageTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodbeverageTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbeverageTabMouseExited

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void foodbeverageTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodbeverageTabMousePressed
        // TODO add your handling code here:
        jScrollMenu.setVisible(true);
        jpOrders.setVisible(false);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_foodbeverageTabMousePressed

    private void ordersTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordersTabMousePressed
        // TODO add your handling code here:
        jScrollMenu.setVisible(false);
        jpOrders.setVisible(true);
        jpReviews.setVisible(false);
        jpRevenue.setVisible(false);
    }//GEN-LAST:event_ordersTabMousePressed

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
    private javax.swing.JLabel foodbeverageTab;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
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
    private javax.swing.JLabel nameLabel1;
    private javax.swing.JLabel nameLabel2;
    private javax.swing.JTable orderTable;
    private javax.swing.JLabel ordersTab;
    private javax.swing.JLabel revenueTab;
    private javax.swing.JLabel reviewTab;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    // End of variables declaration//GEN-END:variables
}
