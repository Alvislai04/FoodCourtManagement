  // Delivery Runner Dashboard
package system.DeliveryRunner;

import com.system.Login;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class DrDashboard extends javax.swing.JFrame {
    Color DefaultColor, ClickedColor;
    
    Login Login = new Login();
    

    public DrDashboard() {
        initComponents();
        usernametxt.setText(Login.getUsername());
        
        this.setSize(980,580);
        this.setResizable(false);
        
        DefaultColor = new Color(153,89,16);
        ClickedColor = new Color(0,0,0);
        
        //set default color to pan on run time
        viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
        
        populateviewtasksTable();
        addTableRowClickListener();
        populatetaskhistoryTable();
        
        
                        
        }
    private ImageIcon scaleImageIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    // Add this method to set a custom renderer for the proof of delivery column
    private void setTableImageRenderer() {
        taskhistoryTable.getColumnModel().getColumn(7).setCellRenderer(new DefaultTableCellRenderer() {
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
                      Teh Ais""", "123 Street A", "456 Street B", "012-3456789", "Paid", "25.50"},
            {"DR002", """
                      Nasi Kerabu
                      Mee Goreng
                      Kopi Ais""","789 Street C", "101 Street D", "011-9876543", "Unpaid", "15.00"}
        };

        // Column headers
        String[] columnNames = {"Delivery ID", "Food Items","Pick-up Address", "Delivery Address", "Phone Number", "Payment Status", "Total"};

        // Set the table model with data and column names
        viewtasksTable.setModel(new javax.swing.table.DefaultTableModel(taskData, columnNames));
        viewtasksTable.setRowHeight(100); // Sets row height to 50 pixels

          
    }
    
    private void populatetaskhistoryTable() {
    // Column headers for the task history table
    String[] columnNames = {"Delivery ID", "Food Items", "Pick-up Address", "Delivery Address", "Phone Number", "Payment Status", "Total", "Proof of Delivery"};

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
        supportTab = new javax.swing.JLabel();
        drName = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        welcomePanel = new javax.swing.JPanel();
        usernametxt = new javax.swing.JLabel();
        welcometxt = new javax.swing.JLabel();
        notificationsPanel = new javax.swing.JPanel();
        custreviewsPanel = new javax.swing.JPanel();
        supportPanel = new javax.swing.JPanel();
        taskhistoryPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        taskhistoryTable = new javax.swing.JTable();
        taskhistoryLabel = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        searchLabel = new javax.swing.JLabel();
        SearchBtn = new javax.swing.JButton();
        viewtasksPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewtasksTable = new javax.swing.JTable();
        receiptArea = new javax.swing.JTextArea();
        DeliveredBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        taskhistoryLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(980, 580));

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

        supportTab.setBackground(new java.awt.Color(153, 89, 16));
        supportTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        supportTab.setForeground(new java.awt.Color(255, 255, 255));
        supportTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        supportTab.setText("Support");
        supportTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        supportTab.setOpaque(true);
        supportTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                supportTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                supportTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                supportTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                supportTabMousePressed(evt);
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menubarPanelLayout.createSequentialGroup()
                        .addGroup(menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(drName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewtasksTab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(supportTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(custreviewsTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(taskhistoryTab, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                            .addComponent(notificationsTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9))
                    .addGroup(menubarPanelLayout.createSequentialGroup()
                        .addComponent(logoutBtn)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        menubarPanelLayout.setVerticalGroup(
            menubarPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menubarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(drName, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(viewtasksTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(taskhistoryTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(notificationsTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(custreviewsTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(supportTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(9, 9, 9)
                .addComponent(logoutBtn)
                .addContainerGap())
        );

        welcomePanel.setBackground(new java.awt.Color(153, 89, 16));
        welcomePanel.setLayout(null);

        usernametxt.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        usernametxt.setForeground(new java.awt.Color(255, 255, 255));
        usernametxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernametxt.setText("username");
        usernametxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        welcomePanel.add(usernametxt);
        usernametxt.setBounds(269, 0, 190, 460);

        welcometxt.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        welcometxt.setForeground(new java.awt.Color(255, 255, 255));
        welcometxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcometxt.setText("WELCOME,");
        welcometxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        welcomePanel.add(welcometxt);
        welcometxt.setBounds(29, 0, 230, 460);

        notificationsPanel.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout notificationsPanelLayout = new javax.swing.GroupLayout(notificationsPanel);
        notificationsPanel.setLayout(notificationsPanelLayout);
        notificationsPanelLayout.setHorizontalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );
        notificationsPanelLayout.setVerticalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(notificationsPanel);
        notificationsPanel.setBounds(0, 0, 690, 460);

        custreviewsPanel.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout custreviewsPanelLayout = new javax.swing.GroupLayout(custreviewsPanel);
        custreviewsPanel.setLayout(custreviewsPanelLayout);
        custreviewsPanelLayout.setHorizontalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        custreviewsPanelLayout.setVerticalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(custreviewsPanel);
        custreviewsPanel.setBounds(0, 0, 610, 460);

        supportPanel.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout supportPanelLayout = new javax.swing.GroupLayout(supportPanel);
        supportPanel.setLayout(supportPanelLayout);
        supportPanelLayout.setHorizontalGroup(
            supportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 610, Short.MAX_VALUE)
        );
        supportPanelLayout.setVerticalGroup(
            supportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(supportPanel);
        supportPanel.setBounds(0, 0, 610, 460);

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
                    .addComponent(taskhistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(taskhistoryPanelLayout.createSequentialGroup()
                        .addComponent(searchLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(SearchBtn)))
                .addGap(0, 85, Short.MAX_VALUE))
        );
        taskhistoryPanelLayout.setVerticalGroup(
            taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(taskhistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(taskhistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchBtn))
                .addContainerGap(250, Short.MAX_VALUE))
        );

        welcomePanel.add(taskhistoryPanel);
        taskhistoryPanel.setBounds(0, 0, 610, 460);

        viewtasksPanel.setBackground(new java.awt.Color(153, 89, 16));
        viewtasksPanel.setMinimumSize(new java.awt.Dimension(700, 580));
        viewtasksPanel.setName(""); // NOI18N
        viewtasksPanel.setPreferredSize(new java.awt.Dimension(980, 580));

        viewtasksTable.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        viewtasksTable.setModel(new javax.swing.table.DefaultTableModel(
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
        viewtasksTable.setGridColor(new java.awt.Color(0, 0, 0));
        viewtasksTable.setPreferredSize(new java.awt.Dimension(490, 460));
        viewtasksTable.setSelectionBackground(new java.awt.Color(153, 89, 16));
        viewtasksTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(viewtasksTable);

        receiptArea.setColumns(20);
        receiptArea.setRows(5);

        DeliveredBtn.setText("DELIVERED");
        DeliveredBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeliveredBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 8)); // NOI18N
        jLabel1.setText("*ONLY CLICK WHEN ORDER HAS BEEN DELIVERED");

        taskhistoryLabel1.setBackground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        taskhistoryLabel1.setForeground(new java.awt.Color(255, 255, 255));
        taskhistoryLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        taskhistoryLabel1.setText("VIEW TASKS");

        javax.swing.GroupLayout viewtasksPanelLayout = new javax.swing.GroupLayout(viewtasksPanel);
        viewtasksPanel.setLayout(viewtasksPanelLayout);
        viewtasksPanelLayout.setHorizontalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewtasksPanelLayout.createSequentialGroup()
                        .addComponent(receiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1))
                            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(DeliveredBtn))))
                    .addComponent(taskhistoryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 87, Short.MAX_VALUE))
        );
        viewtasksPanelLayout.setVerticalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewtasksPanelLayout.createSequentialGroup()
                .addComponent(taskhistoryLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(DeliveredBtn)
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewtasksPanelLayout.createSequentialGroup()
                        .addComponent(receiptArea, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        welcomePanel.add(viewtasksPanel);
        viewtasksPanel.setBounds(0, 0, 610, 460);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menubarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 689, Short.MAX_VALUE))
            .addComponent(tastiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tastiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(menubarPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    private void supportTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supportTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_supportTabMouseExited

    private void supportTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supportTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_supportTabMouseEntered

    private void viewtasksTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewtasksTabMousePressed
        // TODO add your handling code here:
        viewtasksTab.setBackground(ClickedColor);
         
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
        
        viewtasksPanel.setVisible(true);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        supportPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
        
        
    }//GEN-LAST:event_viewtasksTabMousePressed

    private void taskhistoryTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taskhistoryTabMousePressed
        // TODO add your handling code here:
        taskhistoryTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
        
           viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(true);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        supportPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_taskhistoryTabMousePressed

    private void notificationsTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notificationsTabMousePressed
        // TODO add your handling code here:
        notificationsTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
        
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(true);
        custreviewsPanel.setVisible(false);
        supportPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_notificationsTabMousePressed

    private void custreviewsTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custreviewsTabMousePressed
        // TODO add your handling code here:
        custreviewsTab.setBackground(ClickedColor);
        viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
        
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(true);
        supportPanel.setVisible(false);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
    }//GEN-LAST:event_custreviewsTabMousePressed

    private void supportTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supportTabMousePressed
        // TODO add your handling code here:
        supportTab.setBackground(ClickedColor);
         viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        
        viewtasksPanel.setVisible(false);
        taskhistoryPanel.setVisible(false);
        notificationsPanel.setVisible(false);
        custreviewsPanel.setVisible(false);
        supportPanel.setVisible(true);
        
        welcometxt.setVisible(false);
        usernametxt.setVisible(false);
        
    }//GEN-LAST:event_supportTabMousePressed

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

    private void supportTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_supportTabMouseClicked
        // TODO add your handling code here:
         
    }//GEN-LAST:event_supportTabMouseClicked

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
                    deliveryID, foodItems, pickupAddress, deliveryAddress, phoneNumber, paymentStatus, total, proofOfDeliveryImage
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
            writer.write("Delivery ID: " + deliveryID + "\n");
            writer.write("Food Items: " + foodItems + "\n");
            writer.write("Pick-up Address: " + pickupAddress + "\n");
            writer.write("Delivery Address: " + deliveryAddress + "\n");
            writer.write("Phone Number: " + phoneNumber + "\n");
            writer.write("Payment Status: " + paymentStatus + "\n");
            writer.write("Total: " + total + "\n");
            writer.write("Proof of Delivery: " + proofOfDelivery + "\n");
            writer.write("------------------------------------------------------\n"); // Add a separator for better readability
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
            DefaultTableModel model = (DefaultTableModel) viewtasksTable.getModel();
            boolean found = false;

            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    String cellValue = model.getValueAt(row, col).toString();
                    if (cellValue.toLowerCase().contains(query.toLowerCase())) {
                        // Highlight the found row
                        viewtasksTable.setRowSelectionInterval(row, row);
                        viewtasksTable.scrollRectToVisible(viewtasksTable.getCellRect(row, 0, true));
                        searchLabel.setText("Delivery found: Row " + (row + 1));
                        found = true;
                        break;
                    }
                }
                if (found) break; // Stop searching once a match is found
            }
            if (!found) {
                viewtasksTable.clearSelection(); // Clear selection if no match is found
            }
            return found;
        
    }//GEN-LAST:event_SearchBtnActionPerformed

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
    private javax.swing.JButton DeliveredBtn;
    private javax.swing.JButton SearchBtn;
    private javax.swing.JPanel custreviewsPanel;
    private javax.swing.JLabel custreviewsTab;
    private javax.swing.JLabel drName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private java.awt.List list1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menubarPanel;
    private javax.swing.JPanel notificationsPanel;
    private javax.swing.JLabel notificationsTab;
    private javax.swing.JTextArea receiptArea;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchLabel;
    private javax.swing.JPanel supportPanel;
    private javax.swing.JLabel supportTab;
    private javax.swing.JLabel taskhistoryLabel;
    private javax.swing.JLabel taskhistoryLabel1;
    private javax.swing.JPanel taskhistoryPanel;
    private javax.swing.JLabel taskhistoryTab;
    private javax.swing.JTable taskhistoryTable;
    private javax.swing.JPanel tastiesPanel;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JLabel usernametxt;
    private javax.swing.JPanel viewtasksPanel;
    private javax.swing.JLabel viewtasksTab;
    private javax.swing.JTable viewtasksTable;
    private javax.swing.JPanel welcomePanel;
    private javax.swing.JLabel welcometxt;
    // End of variables declaration//GEN-END:variables
}
