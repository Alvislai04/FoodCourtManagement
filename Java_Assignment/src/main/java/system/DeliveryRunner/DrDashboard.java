  // Delivery Runner Dashboard
package system.DeliveryRunner;

import com.system.Login;
import java.awt.Color;

public class DrDashboard extends javax.swing.JFrame {
    Color DefaultColor, ClickedColor;
    
    Login Login = new Login();
    

    public DrDashboard() {
        initComponents();
        usernametxt.setText(Login.getUsername());
        
        this.setSize(776, 496);
        this.setResizable(false);
        
        DefaultColor = new Color(153,89,16);
        ClickedColor = new Color(0,0,0);
        
        //set default color to pan on run time
        viewtasksTab.setBackground(DefaultColor);
        taskhistoryTab.setBackground(DefaultColor);
        notificationsTab.setBackground(DefaultColor);
        custreviewsTab.setBackground(DefaultColor);
        supportTab.setBackground(DefaultColor);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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
        welcometxt = new javax.swing.JLabel();
        usernametxt = new javax.swing.JLabel();
        notificationsPanel = new javax.swing.JPanel();
        custreviewsPanel = new javax.swing.JPanel();
        supportPanel = new javax.swing.JPanel();
        taskhistoryPanel = new javax.swing.JPanel();
        viewtasksPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(779, 535));

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
                .addContainerGap(675, Short.MAX_VALUE))
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

        welcomePanel.setBackground(new java.awt.Color(255, 255, 255));
        welcomePanel.setLayout(null);

        welcometxt.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        welcometxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcometxt.setText("WELCOME,");
        welcometxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        welcomePanel.add(welcometxt);
        welcometxt.setBounds(49, 0, 196, 460);

        usernametxt.setFont(new java.awt.Font("Segoe UI Black", 0, 36)); // NOI18N
        usernametxt.setText("username");
        usernametxt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        welcomePanel.add(usernametxt);
        usernametxt.setBounds(269, 0, 171, 460);

        notificationsPanel.setBackground(new java.awt.Color(102, 255, 102));

        javax.swing.GroupLayout notificationsPanelLayout = new javax.swing.GroupLayout(notificationsPanel);
        notificationsPanel.setLayout(notificationsPanelLayout);
        notificationsPanelLayout.setHorizontalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        notificationsPanelLayout.setVerticalGroup(
            notificationsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(notificationsPanel);
        notificationsPanel.setBounds(0, 0, 490, 460);

        custreviewsPanel.setBackground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout custreviewsPanelLayout = new javax.swing.GroupLayout(custreviewsPanel);
        custreviewsPanel.setLayout(custreviewsPanelLayout);
        custreviewsPanelLayout.setHorizontalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        custreviewsPanelLayout.setVerticalGroup(
            custreviewsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(custreviewsPanel);
        custreviewsPanel.setBounds(0, 0, 490, 460);

        supportPanel.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout supportPanelLayout = new javax.swing.GroupLayout(supportPanel);
        supportPanel.setLayout(supportPanelLayout);
        supportPanelLayout.setHorizontalGroup(
            supportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        supportPanelLayout.setVerticalGroup(
            supportPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(supportPanel);
        supportPanel.setBounds(0, 0, 490, 460);

        taskhistoryPanel.setBackground(new java.awt.Color(204, 102, 255));

        javax.swing.GroupLayout taskhistoryPanelLayout = new javax.swing.GroupLayout(taskhistoryPanel);
        taskhistoryPanel.setLayout(taskhistoryPanelLayout);
        taskhistoryPanelLayout.setHorizontalGroup(
            taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        taskhistoryPanelLayout.setVerticalGroup(
            taskhistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(taskhistoryPanel);
        taskhistoryPanel.setBounds(0, 0, 490, 460);

        viewtasksPanel.setBackground(new java.awt.Color(153, 153, 255));

        jTable1.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setGridColor(new java.awt.Color(0, 0, 0));
        jTable1.setPreferredSize(new java.awt.Dimension(490, 460));
        jTable1.setSelectionBackground(new java.awt.Color(153, 89, 16));
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout viewtasksPanelLayout = new javax.swing.GroupLayout(viewtasksPanel);
        viewtasksPanel.setLayout(viewtasksPanelLayout);
        viewtasksPanelLayout.setHorizontalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        viewtasksPanelLayout.setVerticalGroup(
            viewtasksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        welcomePanel.add(viewtasksPanel);
        viewtasksPanel.setBounds(0, 0, 490, 460);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tastiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(menubarPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    private javax.swing.JPanel custreviewsPanel;
    private javax.swing.JLabel custreviewsTab;
    private javax.swing.JLabel drName;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JPanel menubarPanel;
    private javax.swing.JPanel notificationsPanel;
    private javax.swing.JLabel notificationsTab;
    private javax.swing.JPanel supportPanel;
    private javax.swing.JLabel supportTab;
    private javax.swing.JPanel taskhistoryPanel;
    private javax.swing.JLabel taskhistoryTab;
    private javax.swing.JPanel tastiesPanel;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JLabel usernametxt;
    private javax.swing.JPanel viewtasksPanel;
    private javax.swing.JLabel viewtasksTab;
    private javax.swing.JPanel welcomePanel;
    private javax.swing.JLabel welcometxt;
    // End of variables declaration//GEN-END:variables
}
