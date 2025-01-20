// Vendor's Dashboard
package system.Vendor;

import com.system.Login;

public class VDashboard extends javax.swing.JFrame {

//        // TODO:
//    public Home() {
//        initComponents();
//         // Putting the class into login variable. So can use across different classes
//         // Changes usernameLabel (jlabel2) into username typed in Login.
//        usernameLabel.setText(login.getUsername() + ".");
//        this.refreshData();
//    }
    public VDashboard() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        foodbeverageTab = new javax.swing.JLabel();
        ordersTab = new javax.swing.JLabel();
        reviewTab = new javax.swing.JLabel();
        revenueTab = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        title_lbl1 = new javax.swing.JLabel();
        title_lbl2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Vendorname");

        foodbeverageTab.setBackground(new java.awt.Color(153, 89, 16));
        foodbeverageTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
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
        });

        ordersTab.setBackground(new java.awt.Color(153, 89, 16));
        ordersTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        ordersTab.setForeground(new java.awt.Color(255, 255, 255));
        ordersTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ordersTab.setText("Orders");
        ordersTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        ordersTab.setOpaque(true);

        reviewTab.setBackground(new java.awt.Color(153, 89, 16));
        reviewTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        reviewTab.setForeground(new java.awt.Color(255, 255, 255));
        reviewTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reviewTab.setText("Customer Reviews");
        reviewTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        reviewTab.setOpaque(true);
        reviewTab.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                reviewTabMouseMoved(evt);
            }
        });
        reviewTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reviewTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reviewTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reviewTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                reviewTabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                reviewTabMouseReleased(evt);
            }
        });

        revenueTab.setBackground(new java.awt.Color(153, 89, 16));
        revenueTab.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        revenueTab.setForeground(new java.awt.Color(255, 255, 255));
        revenueTab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        revenueTab.setText("Revenue Dashboard");
        revenueTab.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        revenueTab.setOpaque(true);
        revenueTab.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                revenueTabMouseMoved(evt);
            }
        });
        revenueTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                revenueTabMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                revenueTabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                revenueTabMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                revenueTabMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                revenueTabMouseReleased(evt);
            }
        });

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(reviewTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ordersTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(foodbeverageTab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(revenueTab, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(logoutBtn)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(foodbeverageTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ordersTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(reviewTab, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(revenueTab, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addGap(66, 66, 66)
                .addComponent(logoutBtn)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        title_lbl1.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl1.setForeground(new java.awt.Color(255, 255, 255));
        title_lbl1.setText("TAS");

        title_lbl2.setBackground(new java.awt.Color(0, 0, 0));
        title_lbl2.setFont(new java.awt.Font("Segoe UI Black", 0, 24)); // NOI18N
        title_lbl2.setForeground(new java.awt.Color(153, 89, 16));
        title_lbl2.setText("TIES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title_lbl1)
                .addGap(2, 2, 2)
                .addComponent(title_lbl2)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(title_lbl2)
                    .addComponent(title_lbl1))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 494, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        goToLogout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void reviewTabMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMouseMoved

    private void reviewTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMouseClicked

    private void reviewTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMouseEntered

    private void reviewTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMouseExited

    private void reviewTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMousePressed

    private void reviewTabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reviewTabMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_reviewTabMouseReleased

    private void revenueTabMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMouseMoved
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMouseMoved

    private void revenueTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMouseClicked

    private void revenueTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMouseEntered

    private void revenueTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMouseExited

    private void revenueTabMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMousePressed

    private void revenueTabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_revenueTabMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_revenueTabMouseReleased

    private void foodbeverageTabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodbeverageTabMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbeverageTabMouseEntered

    private void foodbeverageTabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_foodbeverageTabMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_foodbeverageTabMouseExited

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel ordersTab;
    private javax.swing.JLabel revenueTab;
    private javax.swing.JLabel reviewTab;
    private javax.swing.JLabel title_lbl1;
    private javax.swing.JLabel title_lbl2;
    // End of variables declaration//GEN-END:variables
}
