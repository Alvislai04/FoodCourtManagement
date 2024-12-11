package com.login;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    private static String username;
    public Login() {
        initComponents();
        
        this.pack();
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
        title_label = new javax.swing.JLabel();
        username_lbl = new javax.swing.JLabel();
        password_lbl = new javax.swing.JLabel();
        passwordtxt = new javax.swing.JPasswordField();
        login_btn = new javax.swing.JButton();
        clear_btn = new javax.swing.JButton();
        noacc_lbl = new javax.swing.JLabel();
        usernametxt = new javax.swing.JTextField();
        showPw = new javax.swing.JCheckBox();

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

        title_label.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label.setText("Nasty Food Court");

        username_lbl.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        username_lbl.setText("Username:");

        password_lbl.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        password_lbl.setText("Password:");

        passwordtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordtxtActionPerformed(evt);
            }
        });

        login_btn.setText("Login");
        login_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_btnActionPerformed(evt);
            }
        });

        clear_btn.setText("Clear");
        clear_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear_btnActionPerformed(evt);
            }
        });

        noacc_lbl.setFont(new java.awt.Font("Segoe UI Symbol", 0, 12)); // NOI18N
        noacc_lbl.setText("No account? Kindly seek Administrator for help.");

        showPw.setText("Show Password");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(title_label))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(passwordtxt, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(password_lbl, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(username_lbl, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(usernametxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(noacc_lbl, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(showPw)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(clear_btn)
                                .addGap(18, 18, 18)
                                .addComponent(login_btn)))))
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(title_label)
                .addGap(19, 19, 19)
                .addComponent(username_lbl)
                .addGap(10, 10, 10)
                .addComponent(usernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(password_lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(showPw)
                    .addComponent(clear_btn)
                    .addComponent(login_btn))
                .addGap(18, 18, 18)
                .addComponent(noacc_lbl)
                .addContainerGap(92, Short.MAX_VALUE))
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
        try{
            String filename = "admin.txt";
            FileReader fr = new FileReader(filename);
            BufferedReader br = new BufferedReader(fr);
            String usernamegui = usernametxt.getText();
            String passwordgui = new String(passwordtxt.getPassword());
            String read;
            boolean found = false;
            //scanning the content of the file
            while((read=br.readLine()) !=null){
                String usernamefile = read.split(";")[0];
                String passwordfile = read.split(";")[1];
                //username & password match
                if(usernamegui.equals(usernamefile) && 
                        passwordgui.equals(passwordfile)){
                    found = true;
                    setUsername(usernametxt.getText());
                    break;
                }
            }//end of while loop
            if(found){
                JOptionPane.showMessageDialog(null,"Successfully login");
                this.dispose();//close the current form
                new AdDashboard().setVisible(true);//it will open the home form
            } else{
                JOptionPane.showMessageDialog(null,"Invalid login!");
            }
        }catch(IOException e){
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

    private void goToAdminDashboard(){
        AdDashboard adminFrame = new AdDashboard();
        adminFrame.setVisible(true);
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
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JButton login_btn;
    private javax.swing.JLabel noacc_lbl;
    private javax.swing.JLabel password_lbl;
    private javax.swing.JPasswordField passwordtxt;
    private javax.swing.JCheckBox showPw;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel username_lbl;
    private javax.swing.JTextField usernametxt;
    // End of variables declaration//GEN-END:variables
}
