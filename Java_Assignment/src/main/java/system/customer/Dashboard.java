// User's Dashboard
package system.customer;

import com.system.Login;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Dashboard extends javax.swing.JFrame {

    public Dashboard() {
        initComponents();
        ImageIcon img = new ImageIcon(getClass().getResource("/logo.jpg"));
        Image scaledImage = img.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        logo.setText("");//remove the text
        logo.setIcon(scaledIcon);//get the image
        
        ImageIcon img1 = new ImageIcon(getClass().getResource("/malay.jpg"));
        Image scaledImage1 = img1.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        logo1.setText("");//remove the text
        logo1.setIcon(scaledIcon1);//get the image
        
        ImageIcon img2 = new ImageIcon(getClass().getResource("/chinese.jpg"));
        Image scaledImage2 = img2.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon2 = new ImageIcon(scaledImage2);
        logo2.setText("");//remove the text
        logo2.setIcon(scaledIcon2);//get the image
        
        ImageIcon img3 = new ImageIcon(getClass().getResource("/indian.jpg"));
        Image scaledImage3 = img3.getImage().getScaledInstance(230, 230, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon3 = new ImageIcon(scaledImage3);
        logo3.setText("");//remove the text
        logo3.setIcon(scaledIcon3);//get the image
        
        ImageIcon img4 = new ImageIcon(getClass().getResource("/western.jpg"));
        Image scaledImage4 = img4.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon4 = new ImageIcon(scaledImage4);
        logo4.setText("");//remove the text
        logo4.setIcon(scaledIcon4);//get the image
        
        ImageIcon img5 = new ImageIcon(getClass().getResource("/japanese.jpg"));
        Image scaledImage5 = img5.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon5 = new ImageIcon(scaledImage5);
        logo5.setText("");//remove the text
        logo5.setIcon(scaledIcon5);//get the image
        
        ImageIcon img6 = new ImageIcon(getClass().getResource("/drink.jpg"));
        Image scaledImage6 = img6.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);//Width: 200px, Height: 200px
        ImageIcon scaledIcon6 = new ImageIcon(scaledImage6);
        logo6.setText("");//remove the text
        logo6.setIcon(scaledIcon6);//get the image
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        logoutBtn = new javax.swing.JButton();
        logo4 = new javax.swing.JLabel();
        western = new javax.swing.JButton();
        logo = new javax.swing.JLabel();
        logo2 = new javax.swing.JLabel();
        chinese = new javax.swing.JButton();
        logo1 = new javax.swing.JLabel();
        malay = new javax.swing.JButton();
        logo3 = new javax.swing.JLabel();
        logo5 = new javax.swing.JLabel();
        logo6 = new javax.swing.JLabel();
        indian = new javax.swing.JButton();
        japanese = new javax.swing.JButton();
        drink = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Welcome to Nasty Food Court");

        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        logo4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo4.setText("Western");

        western.setText("Western");
        western.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                westernActionPerformed(evt);
            }
        });

        logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo.setText("Logo");
        logo.setToolTipText("");

        logo2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo2.setText("Chinese");

        chinese.setText("Chinese");
        chinese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chineseActionPerformed(evt);
            }
        });

        logo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo1.setText("Malay");

        malay.setText("Malay");
        malay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                malayActionPerformed(evt);
            }
        });

        logo3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo3.setText("Indian");

        logo5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo5.setText("Japanese");

        logo6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logo6.setText("Drink");

        indian.setText("Indian");
        indian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                indianActionPerformed(evt);
            }
        });

        japanese.setText("Japanese");
        japanese.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                japaneseActionPerformed(evt);
            }
        });

        drink.setText("Drink");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(western, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(logo4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(malay, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logo1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logo5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logo2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chinese, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(japanese, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(indian, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(logo6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(drink, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(logo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addComponent(logoutBtn)
                .addGap(51, 51, 51))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(logoutBtn)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(logo3, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(malay)
                    .addComponent(chinese)
                    .addComponent(indian))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logo4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo6, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(western)
                    .addComponent(japanese)
                    .addComponent(drink))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        goToLogout();
    }//GEN-LAST:event_logoutBtnActionPerformed

    private void westernActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_westernActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_westernActionPerformed

    private void chineseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chineseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chineseActionPerformed

    private void malayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_malayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_malayActionPerformed

    private void indianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_indianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_indianActionPerformed

    private void japaneseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_japaneseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_japaneseActionPerformed

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
    private javax.swing.JButton chinese;
    private javax.swing.JButton drink;
    private javax.swing.JButton indian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton japanese;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel logo1;
    private javax.swing.JLabel logo2;
    private javax.swing.JLabel logo3;
    private javax.swing.JLabel logo4;
    private javax.swing.JLabel logo5;
    private javax.swing.JLabel logo6;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JButton malay;
    private javax.swing.JButton western;
    // End of variables declaration//GEN-END:variables
}
