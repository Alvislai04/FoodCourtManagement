package com.system;

// LOGIN PAGE

import system.Manager.MDashboard;
import system.Vendor.VDashboard;
import system.DeliveryRunner.DrDashboard;
import system.customer.Dashboard;
import system.admin.AdDashboard;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private static String username;
    public Login() {
        initComponents();
        
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void setUsername(String username){
        Login.username = username;
    }
    
    public String getUsername(){
        return Login.username;
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        username_lbl = new javax.swing.JLabel();
        password_lbl = new javax.swing.JLabel();
        passwordtxt = new javax.swing.JPasswordField();
        login_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        noacc_lbl = new javax.swing.JLabel();
        usernametxt = new javax.swing.JTextField();
        showPw = new javax.swing.JCheckBox();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();
        title_lbl3 = new javax.swing.JLabel();
        loginCover = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(720, 520));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setLayout(null);

        username_lbl.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        username_lbl.setText("Username");
        jPanel1.add(username_lbl);
        username_lbl.setBounds(50, 190, 80, 20);

        password_lbl.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 18)); // NOI18N
        password_lbl.setText("Password");
        jPanel1.add(password_lbl);
        password_lbl.setBounds(50, 270, 71, 20);

        passwordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtxtActionPerformed(evt);
            }
        });
        passwordtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordtxtKeyPressed(evt);
            }
        });
        jPanel1.add(passwordtxt);
        passwordtxt.setBounds(50, 292, 270, 30);

        login_btn.setText("Login");
        login_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });
        login_btn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                login_btnKeyPressed(evt);
            }
        });
        jPanel1.add(login_btn);
        login_btn.setBounds(250, 370, 76, 27);

        clear_btn.setText("Clear");
        clear_btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });
        jPanel1.add(clear_btn);
        clear_btn.setBounds(50, 370, 76, 27);

        noacc_lbl.setFont(new java.awt.Font("Tw Cen MT", 1, 12)); // NOI18N
        noacc_lbl.setText("No account? Kindly seek Administrator for help.");
        jPanel1.add(noacc_lbl);
        noacc_lbl.setBounds(50, 420, 252, 14);
        jPanel1.add(usernametxt);
        usernametxt.setBounds(50, 220, 270, 30);

        showPw.setFont(new java.awt.Font("Tw Cen MT Condensed", 1, 14)); // NOI18N
        showPw.setText("Show Password");
        showPw.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        showPw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showPwMouseClicked(evt);
            }
        });
        showPw.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showPwActionPerformed(evt);
            }
        });
        jPanel1.add(showPw);
        showPw.setBounds(50, 330, 120, 20);

        title_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 48)); // NOI18N
        title_lbl1.setText("TAS");
        jPanel1.add(title_lbl1);
        title_lbl1.setBounds(82, 60, 94, 66);

        title_lbl2.setBackground(new java.awt.Color(0, 0, 0));
        title_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 48)); // NOI18N
        title_lbl2.setForeground(new java.awt.Color(153, 89, 16));
        title_lbl2.setText("TIES");
        jPanel1.add(title_lbl2);
        title_lbl2.setBounds(178, 60, 110, 66);

        title_lbl3.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        title_lbl3.setText("Food Court");
        jPanel1.add(title_lbl3);
        title_lbl3.setBounds(140, 100, 130, 60);

        loginCover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/loginCover.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(loginCover)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginCover))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passwordtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordtxtActionPerformed

    }//GEN-LAST:event_passwordtxtActionPerformed

    private void clear_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear_btnActionPerformed
        usernametxt.setText("");
        passwordtxt.setText("");
    }//GEN-LAST:event_clear_btnActionPerformed

    private void login_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_btnActionPerformed
        try {
            String[] filenames = {"admin.txt", "users.txt"};
            boolean found = false;
            boolean isAdmin = false;
            boolean isManager = false;
            boolean isCustomer = false;
            boolean isVendor = false;
            boolean isDeliveryRunner = false;

            String usernameGui = usernametxt.getText();
            String passwordGui = new String(passwordtxt.getPassword());
            
            String vendorId = "";
            String vendorName = "";
            double vendorBalance = 0.0;

            for (String filename : filenames) {
                try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                    String read;

                    while ((read = br.readLine()) != null) {
                        String[] credentials = read.split(";");

                        if (filename.equals("admin.txt")) {

                            if (credentials.length >= 2) {
                                String usernameFile = credentials[0];
                                String passwordFile = credentials[1];
                                String roles = credentials[2];

                                if (usernameGui.equals(usernameFile) && passwordGui.equals(passwordFile) && roles.equals("Admin")) {
                                    found = true;
                                    isAdmin = true;
                                    setUsername(usernameGui);
                                    break;
                                } else if (usernameGui.equals(usernameFile) && passwordGui.equals(passwordFile) && roles.equals("Manager")){
                                    found = true;
                                    isManager = true;
                                    setUsername(usernameGui);
                                    break;
                                }
                            }
                        } else if (filename.equals("users.txt")) {

                            if (credentials.length >= 7) {
                                String usernameFile = credentials[4];
                                String passwordFile = credentials[5];
                                String userType = credentials[6];

                                if (usernameGui.equals(usernameFile) && passwordGui.equals(passwordFile)) {
                                    found = true;
                                    setUsername(usernameGui);

                                    if ("Customer".equalsIgnoreCase(userType)) {
                                        isCustomer = true;
                                    } else if ("Vendor".equalsIgnoreCase(userType)) {
                                        isVendor = true;
                                        
                                        vendorId = credentials[0]; // ID is at index 0
                                        vendorName = credentials[1]; // Name is at index 1
                                        vendorBalance = Double.parseDouble(credentials[7]);
                                        
                                    } else if ("Delivery Runner".equalsIgnoreCase(userType)) {
                                        isDeliveryRunner = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }

                    if (found) {
                        break;
                    }
                }
            }

            if (found) {
                if (isAdmin) { // Admin
                    JOptionPane.showMessageDialog(null, "Successfully logged in as Admin");
                    this.dispose();
                    new AdDashboard().setVisible(true);
                } else if (isCustomer) { // Customer
                    JOptionPane.showMessageDialog(null, "Successfully logged in");
                    this.dispose();
                    goToDashboard();
                } else if (isVendor) { // Vendor
                    JOptionPane.showMessageDialog(null, "Successfully logged in as Vendor");
                    this.dispose();
                    goToVDashboard(vendorId, vendorName, vendorBalance);
                } else if (isDeliveryRunner) { // Delivery Runner
                    JOptionPane.showMessageDialog(null, "Successfully logged in as Delivery Runner");
                    this.dispose();
                    goToDrDashboard();
                } else if (isManager){ // Manager
                    JOptionPane.showMessageDialog(null, "Sucessfully log in as Manager");
                    this.dispose();
                    goToMDashboard();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid login!");
                passwordtxt.setText("");
            }

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_login_btnActionPerformed

    private void showPwActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showPwActionPerformed
        if (showPw.isSelected()) {
            passwordtxt.setEchoChar((char)0);
        } else {
            passwordtxt.setEchoChar('*');
        }
    }//GEN-LAST:event_showPwActionPerformed

    private void showPwMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showPwMouseClicked

    }//GEN-LAST:event_showPwMouseClicked

    private void login_btnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_login_btnKeyPressed
        
    }//GEN-LAST:event_login_btnKeyPressed

    private void passwordtxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordtxtKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            login_btn.doClick();
        }
    }//GEN-LAST:event_passwordtxtKeyPressed

    private void goToDashboard(){ //Customer Dashboard
        Dashboard usersframe = new Dashboard();
        usersframe.setVisible(true);
        dispose();
    }
    
    private void goToVDashboard(String vendorId, String vendorName, double vendorBalance){ // Vendor Dashboard
        VDashboard vendorframe = new VDashboard(vendorId, vendorName, vendorBalance);
        vendorframe.setVisible(true);
        dispose();
    }
    
    private void goToDrDashboard(){ // Delivery Runner Dashboard
        DrDashboard drframe = new DrDashboard();
        drframe.setVisible(true);
        dispose();
    }
        
    private void goToMDashboard(){
        MDashboard managerFrame = new MDashboard();
        managerFrame.setVisible(true);
        dispose();
    }
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clear_btn;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JLabel loginCover;
    private javax.swing.JButton login_btn;
    private javax.swing.JLabel noacc_lbl;
    private javax.swing.JLabel password_lbl;
    private javax.swing.JPasswordField passwordtxt;
    private javax.swing.JCheckBox showPw;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    private javax.swing.JLabel title_lbl3;
    private javax.swing.JLabel username_lbl;
    private javax.swing.JTextField usernametxt;
    // End of variables declaration//GEN-END:variables

    private void login_btnActionPerformed() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
