// Admin Dashboard
package system.admin;

import com.system.Login;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class AdDashboard extends javax.swing.JFrame {

    private boolean isPanelVisible = false;
    Login login = new Login();
    
    public JPanel getJp1(){
        jp2.setVisible(false);
        jp3.setVisible(false);
        return jp1;
    }
    
    public JPanel getJp2(){
        jp1.setVisible(false);
        jp3.setVisible(false);
        return jp2;
    }
    
    public JPanel getJp3(){
        jp1.setVisible(false);
        jp2.setVisible(false);
        return jp3;
    }

    public AdDashboard() {
        initComponents();
        TableActionEvent event = new TableActionEvent(){
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row: " + row);
            }

            @Override
            public void onDelete(int row) {
                if(topupTable.isEditing()){
                    topupTable.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) topupTable.getModel();
                model.removeRow(row);
            }

            @Override
            public void onView(int row) {
                System.out.println("View row: " + row);
            }
            
        };
        employeeTable.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        employeeTable.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        topupTable.getColumnModel().getColumn(6).setCellRenderer(new TableActionCellRender());
        topupTable.getColumnModel().getColumn(6).setCellEditor(new TableActionCellEditor(event));
        Navigation.setVisible(false);
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false);

        /*Just Added*/
        employeeTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) { // Ensure the event only fires once when the selection is final
                    populateFormFromTableSelection();
                }
            }
        });
        this.refreshData();
    }

    private void populateFormFromTableSelection() {
        int selectedRow = employeeTable.getSelectedRow();
        if (selectedRow != -1) { // Ensure a row is selected
            String id = (String) employeeTable.getValueAt(selectedRow, 0);
            String name = (String) employeeTable.getValueAt(selectedRow, 1);
            String address = (String) employeeTable.getValueAt(selectedRow, 2);
            int phoneno = (int) employeeTable.getValueAt(selectedRow, 3);
            String username = (String) employeeTable.getValueAt(selectedRow, 4);
            String password = (String) employeeTable.getValueAt(selectedRow, 5);
            String role = (String) employeeTable.getValueAt(selectedRow, 6);
            //get id from file
            User user = new User(id, name, address, phoneno, username, password, role);

            idtxt.setText(String.valueOf(user.getId()));
            //idtxt.setEnabled(false);
            nametxt.setText(user.getName());
            addresstxt.setText(user.getAddress());
            phonenotxt.setText(String.valueOf(user.getphoneNo()));
            usernametxt.setText(user.getUsername());
            passwordtxt.setText(user.getPassword());
            rolecbx.setSelectedItem(user.getRoles());

        }
    }
    
    public void refreshData() {
        try {
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            DefaultTableModel model1 = (DefaultTableModel) employeeTable1.getModel();
            model.setRowCount(0);//reset table
            model1.setRowCount(0);
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            while ((read = br.readLine()) != null) {
                if (read.split(";")[7].equals(login.getUsername())) {
                    String id = read.split(";")[0];
                    String name = read.split(";")[1];
                    String address = read.split(";")[2];
                    String phoneno = read.split(";")[3];
                    String username = read.split(";")[4];
                    String password = read.split(";")[5];
                    String role = read.split(";")[6];
                    model.addRow(
                            new Object[]{id, name, address, phoneno, username, password,
                                role});
                    model1.addRow(
                            new Object[]{id, name, address, phoneno, username, password,
                                role});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
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

        Navigation = new javax.swing.JPanel();
        title_label = new javax.swing.JLabel();
        home = new javax.swing.JPanel();
        tab1 = new javax.swing.JLabel();
        editAccount = new javax.swing.JPanel();
        tab2 = new javax.swing.JLabel();
        topUp = new javax.swing.JPanel();
        tab3 = new javax.swing.JLabel();
        logOut = new javax.swing.JPanel();
        tab4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        main = new javax.swing.JPanel();
        jp1 = new javax.swing.JPanel();
        title_label2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        employeeTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jp2 = new javax.swing.JPanel();
        title_label3 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        updatebtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        deletebtn = new javax.swing.JButton();
        addresstxt = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeeTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        phonenotxt = new javax.swing.JTextField();
        searchtxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        usernametxt = new javax.swing.JTextField();
        rolecbx = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        passwordtxt = new javax.swing.JPasswordField();
        clearbtn = new javax.swing.JButton();
        idtxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        addbtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jp3 = new javax.swing.JPanel();
        title_label4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        topupTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.gray);
        setMinimumSize(new java.awt.Dimension(1300, 586));
        setPreferredSize(new java.awt.Dimension(1300, 587));
        setSize(new java.awt.Dimension(1300, 586));

        Navigation.setBackground(java.awt.Color.gray);

        title_label.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Nasty food court");

        home.setBackground(java.awt.Color.gray);

        tab1.setBackground(java.awt.Color.gray);
        tab1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab1.setText("Home");
        tab1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                tab1MouseMoved(evt);
            }
        });
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab1MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        editAccount.setBackground(java.awt.Color.gray);

        tab2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tab2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab2.setText("Create / Edit Account");
        tab2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab2MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab2MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout editAccountLayout = new javax.swing.GroupLayout(editAccount);
        editAccount.setLayout(editAccountLayout);
        editAccountLayout.setHorizontalGroup(
            editAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        editAccountLayout.setVerticalGroup(
            editAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        topUp.setBackground(java.awt.Color.gray);

        tab3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tab3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab3.setText("Top-Up");
        tab3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab3MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab3MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout topUpLayout = new javax.swing.GroupLayout(topUp);
        topUp.setLayout(topUpLayout);
        topUpLayout.setHorizontalGroup(
            topUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        topUpLayout.setVerticalGroup(
            topUpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        logOut.setBackground(java.awt.Color.gray);

        tab4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tab4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tab4.setText("Logout");
        tab4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tab4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab4MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tab4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tab4MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout logOutLayout = new javax.swing.GroupLayout(logOut);
        logOut.setLayout(logOutLayout);
        logOutLayout.setHorizontalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        logOutLayout.setVerticalGroup(
            logOutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab4, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout NavigationLayout = new javax.swing.GroupLayout(Navigation);
        Navigation.setLayout(NavigationLayout);
        NavigationLayout.setHorizontalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topUp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editAccount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(home, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NavigationLayout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(title_label)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        NavigationLayout.setVerticalGroup(
            NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NavigationLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_label)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(283, Short.MAX_VALUE))
        );

        title_label2.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label2.setText("Admin dashboard");

        employeeTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "address", "phone no", "username", "role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(employeeTable1);
        if (employeeTable1.getColumnModel().getColumnCount() > 0) {
            employeeTable1.getColumnModel().getColumn(0).setResizable(false);
            employeeTable1.getColumnModel().getColumn(1).setResizable(false);
            employeeTable1.getColumnModel().getColumn(2).setResizable(false);
            employeeTable1.getColumnModel().getColumn(3).setResizable(false);
            employeeTable1.getColumnModel().getColumn(4).setResizable(false);
            employeeTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(title_label2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_label2)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jp2.setPreferredSize(new java.awt.Dimension(1300, 563));

        title_label3.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label3.setText("Edit account details");

        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        jLabel4.setText("address:");

        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        employeeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "name", "address", "phone no", "username", "password", "role", "Actions"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        employeeTable.setRowHeight(40);
        jScrollPane1.setViewportView(employeeTable);
        if (employeeTable.getColumnModel().getColumnCount() > 0) {
            employeeTable.getColumnModel().getColumn(0).setResizable(false);
            employeeTable.getColumnModel().getColumn(1).setResizable(false);
            employeeTable.getColumnModel().getColumn(2).setResizable(false);
            employeeTable.getColumnModel().getColumn(3).setResizable(false);
            employeeTable.getColumnModel().getColumn(4).setResizable(false);
            employeeTable.getColumnModel().getColumn(5).setResizable(false);
            employeeTable.getColumnModel().getColumn(6).setResizable(false);
            employeeTable.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel5.setText("Phone No.:");

        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
        });

        jLabel6.setText("Username:");

        jLabel9.setText("Search:");

        rolecbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Vendor", "Delivery Runner" }));

        jLabel7.setText("Password:");

        jLabel2.setText("id:");

        clearbtn.setText("Clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        jLabel8.setText("Role:");

        jLabel3.setText("name:");

        addbtn.setText("Register");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp2Layout = new javax.swing.GroupLayout(jp2);
        jp2.setLayout(jp2Layout);
        jp2Layout.setHorizontalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp2Layout.createSequentialGroup()
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)
                                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(22, 22, 22)
                                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(addresstxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nametxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idtxt, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(phonenotxt, javax.swing.GroupLayout.Alignment.LEADING)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp2Layout.createSequentialGroup()
                                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(deletebtn)
                                            .addComponent(addbtn, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(34, 34, 34))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp2Layout.createSequentialGroup()
                                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7))
                                            .addComponent(jLabel6))
                                        .addGap(25, 25, 25)
                                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jp2Layout.createSequentialGroup()
                                                .addComponent(rolecbx, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 431, Short.MAX_VALUE))
                                            .addComponent(passwordtxt)
                                            .addComponent(usernametxt)))))
                            .addGroup(jp2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel4))
                                    .addComponent(updatebtn)
                                    .addGroup(jp2Layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addGap(147, 147, 147)
                                        .addComponent(clearbtn)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jp2Layout.createSequentialGroup()
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title_label3)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jp2Layout.setVerticalGroup(
            jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_label3)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp2Layout.createSequentialGroup()
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(idtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(nametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(addresstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(phonenotxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(usernametxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(passwordtxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rolecbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updatebtn)
                            .addComponent(addbtn))
                        .addGap(18, 18, 18)
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clearbtn)
                            .addComponent(deletebtn)
                            .addComponent(jButton1))
                        .addGap(0, 101, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        title_label4.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label4.setText("Top-up Approval");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        topupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Phone Number", "Payment Method", "Payment Time", "Amount", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        topupTable.setRowHeight(40);
        topupTable.setSelectionBackground(new java.awt.Color(135, 98, 89));
        jScrollPane3.setViewportView(topupTable);
        if (topupTable.getColumnModel().getColumnCount() > 0) {
            topupTable.getColumnModel().getColumn(0).setPreferredWidth(1);
            topupTable.getColumnModel().getColumn(1).setPreferredWidth(1);
            topupTable.getColumnModel().getColumn(2).setPreferredWidth(2);
            topupTable.getColumnModel().getColumn(3).setPreferredWidth(1);
            topupTable.getColumnModel().getColumn(4).setPreferredWidth(1);
            topupTable.getColumnModel().getColumn(5).setPreferredWidth(1);
        }

        javax.swing.GroupLayout jp3Layout = new javax.swing.GroupLayout(jp3);
        jp3.setLayout(jp3Layout);
        jp3Layout.setHorizontalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1266, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(title_label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jp3Layout.setVerticalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(title_label4))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(jp1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addGap(3, 3, 3)
                    .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, 1303, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jp3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        refreshData();
        Navigation.setVisible(false);
        isPanelVisible = false;
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jButton4.setVisible(true);
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        Navigation.setVisible(false);
        isPanelVisible = false;
        jp1.setVisible(false);
        jp2.setVisible(true);
        jp3.setVisible(false);
        jButton3.setVisible(true);
    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        Navigation.setVisible(false);
        isPanelVisible = false;
        jp1.setVisible(false);
        jp2.setVisible(false);
        jp3.setVisible(true);
        jButton2.setVisible(true);
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        JOptionPane.showMessageDialog(this, "Logout successfully!");
        goToLogout();
    }//GEN-LAST:event_tab4MouseClicked

    private void tab1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseMoved

    }//GEN-LAST:event_tab1MouseMoved

    private void tab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseEntered
        home.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_tab1MouseEntered

    private void tab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseExited
        home.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab1MouseExited

    private void tab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseEntered
        editAccount.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_tab2MouseEntered

    private void tab2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseExited
        editAccount.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab2MouseExited

    private void tab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseEntered
        topUp.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_tab3MouseEntered

    private void tab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseExited
        topUp.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab3MouseExited

    private void tab4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseEntered
        logOut.setBackground(Color.LIGHT_GRAY);
    }//GEN-LAST:event_tab4MouseEntered

    private void tab4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseExited
        logOut.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab4MouseExited

    private void tab1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MousePressed
        home.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_tab1MousePressed

    private void tab1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseReleased
        home.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab1MouseReleased

    private void tab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MousePressed
        editAccount.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_tab2MousePressed

    private void tab2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseReleased
        editAccount.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab2MouseReleased

    private void tab3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MousePressed
        topUp.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_tab3MousePressed

    private void tab3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseReleased
        topUp.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab3MouseReleased

    private void tab4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MousePressed
        logOut.setBackground(Color.DARK_GRAY);
    }//GEN-LAST:event_tab4MousePressed

    private void tab4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseReleased
        logOut.setBackground(Color.GRAY);
    }//GEN-LAST:event_tab4MouseReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        isPanelVisible = !isPanelVisible;
        Navigation.setVisible(isPanelVisible);
        jButton5.setVisible(true);
        jButton2.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        isPanelVisible = !isPanelVisible;
        Navigation.setVisible(isPanelVisible);
        jButton5.setVisible(true);
        jButton4.setVisible(false);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        isPanelVisible = !isPanelVisible;
        Navigation.setVisible(isPanelVisible);
        jButton5.setVisible(false);
        jButton2.setVisible(true);
        jButton3.setVisible(true);
        jButton4.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        isPanelVisible = !isPanelVisible;
        Navigation.setVisible(isPanelVisible);
        jButton5.setVisible(true);
        jButton3.setVisible(false);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false);
        jButton4.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        try {
            // Writing data into the file
            String filename = "users.txt";
            FileWriter fw = new FileWriter(filename, true);

            // Check if any required field is empty
            if ((idtxt == null || idtxt.getText().trim().isEmpty()) ||
                (nametxt == null || nametxt.getText().trim().isEmpty()) ||
                (addresstxt == null || addresstxt.getText().trim().isEmpty()) ||
                (phonenotxt == null || phonenotxt.getText().trim().isEmpty()) ||
                (usernametxt == null || usernametxt.getText().trim().isEmpty()) ||
                (passwordtxt == null || new String(passwordtxt.getPassword()).trim().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
            } else {
                // Write data to the file
                fw.write(
                    idtxt.getText().trim() + ";"
                    + nametxt.getText().trim() + ";"
                    + addresstxt.getText().trim() + ";"
                    + phonenotxt.getText().trim() + ";"
                    + usernametxt.getText().trim() + ";"
                    + new String(passwordtxt.getPassword()).trim() + ";"
                    + rolecbx.getSelectedItem().toString() + ";"
                    + login.getUsername() + "\n"
                );
                fw.close();

                JOptionPane.showMessageDialog(null, "Successfully added the data!");
                refreshData();

                // Reset all fields
                idtxt.setEnabled(true);
                idtxt.setText("");
                nametxt.setText("");
                addresstxt.setText("");
                phonenotxt.setText("");
                usernametxt.setText("");
                passwordtxt.setText("");
                rolecbx.setSelectedIndex(0);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_addbtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
        // TODO add your handling code here:
        idtxt.setEnabled(true);
        idtxt.setText("");
        nametxt.setText("");
        addresstxt.setText("");
        phonenotxt.setText("");
        usernametxt.setText("");
        passwordtxt.setText("");
        rolecbx.setSelectedIndex(0);
    }//GEN-LAST:event_clearbtnActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        try {
            String searchText = searchtxt.getText().trim(); // Get search text
            DefaultTableModel model = (DefaultTableModel) employeeTable.getModel();
            model.setRowCount(0); // Clear the table before adding data

            if (searchText.isEmpty()) {
                // If search text is empty, reset the table to show all data
                refreshData();
            }

            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            // Read through the file line by line
            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String id = data[0].trim();
                String name = data[1].trim();
                String address = data[2].trim();
                String phoneno = data[3].trim();
                String username = data[4].trim();
                String password = data[5].trim();
                String role = data[6].trim();

                // Check if any field matches the search text
                if (id.toLowerCase().contains(searchText.toLowerCase())
                    || name.toLowerCase().contains(searchText.toLowerCase())
                    || address.toLowerCase().contains(searchText.toLowerCase())
                    || phoneno.toLowerCase().contains(searchText.toLowerCase())
                    || username.toLowerCase().contains(searchText.toLowerCase())
                    || password.toLowerCase().contains(searchText.toLowerCase())
                    || role.toLowerCase().contains(searchText.toLowerCase())) {
                    // Add the matching record to the table
                    model.addRow(new Object[]{id, name, address, phoneno, username, password, role});
                }
            }
            br.close(); // Close the file reader
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }//GEN-LAST:event_searchtxtKeyReleased

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        // TODO add your handling code here:
        try {
            //get selected row of data
            int selectedRow = employeeTable.getSelectedRow();
            String employeeId = (String) employeeTable.getValueAt(selectedRow, 0);
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;

            ArrayList<ArrayList<String>> employeeList = new ArrayList<>();
            while ((read = br.readLine()) != null) {
                ArrayList<String> record = new ArrayList<>();
                record.add(read.split(";")[0]);
                record.add(read.split(";")[1]);
                record.add(read.split(";")[2]);
                record.add(read.split(";")[3]);
                record.add(read.split(";")[4]);
                record.add(read.split(";")[5]);
                record.add(read.split(";")[6]);
                record.add(read.split(";")[7]);
                employeeList.add(record);
            }
            for (int row = 0; row < employeeList.size(); row++) {
                if (employeeList.get(row).get(0).equals(employeeId)) {
                    employeeList.remove(row);
                    break;
                }
            }

            // Writing the updated TODO records back to the file
            FileWriter fw = new FileWriter("users.txt");
            for (int i = 0; i < employeeList.size(); i++) {
                fw.write(employeeList.get(i).get(0) + ";");
                fw.write(employeeList.get(i).get(1) + ";");
                fw.write(employeeList.get(i).get(2) + ";");
                fw.write(employeeList.get(i).get(3) + ";");
                fw.write(employeeList.get(i).get(4) + ";");
                fw.write(employeeList.get(i).get(5) + ";");
                fw.write(employeeList.get(i).get(6) + ";");
                fw.write(employeeList.get(i).get(7) + ";\n");
            }

            fw.close();
            JOptionPane.showMessageDialog(null, "successfully deleted a record");
            this.refreshData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Successfully deleted employee");
        }
    }//GEN-LAST:event_deletebtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        try {
            String newId = idtxt.getText();
            String name = nametxt.getText();
            String address = addresstxt.getText();
            String phoneno = phonenotxt.getText();
            String username = usernametxt.getText();
            String password = new String(passwordtxt.getPassword());
            String role = rolecbx.getSelectedItem().toString();

            int selectedRow = employeeTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "No record selected for editing!");
                return;
            }

            if (newId.trim().isEmpty() ||
                name.trim().isEmpty() ||
                address.trim().isEmpty() ||
                phoneno.trim().isEmpty() ||
                username.trim().isEmpty() ||
                password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                return;
            }

            try {
                Integer.parseInt(phoneno);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Phone number must be numeric.");
                return;
            }

            String originalId = (String) employeeTable.getValueAt(selectedRow, 0); // get id from selected row

            BufferedReader br = new BufferedReader(new FileReader("users.txt"));
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            br.close();

            // Validate that the new ID does not already exist (except for the original ID)
            for (String existingLine : lines) {
                String[] data = existingLine.split(";");
                if (data[0].equals(newId) && !data[0].equals(originalId)) {
                    JOptionPane.showMessageDialog(null, "The new ID already exists. Please choose another.");
                    return;
                }
            }

            // Rewrite file with updated record
            try (FileWriter fw = new FileWriter("users.txt")) {
                for (String existingLine : lines) {
                    String[] data = existingLine.split(";");
                    if (data[0].equals(originalId)) { // Match using the original ID
                        // Write the updated record
                        fw.write(
                            newId + ";"
                            + name + ";"
                            + address + ";"
                            + phoneno + ";"
                            + username + ";"
                            + password + ";"
                            + role + ";"
                            + login.getUsername() + "\n"
                        );
                    } else {
                        // Write the existing record as-is
                        fw.write(existingLine + "\n");
                    }
                }
            }

            JOptionPane.showMessageDialog(null, "Record updated successfully!");
            this.refreshData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error updating record: " + e.getMessage());
        }
    }//GEN-LAST:event_updatebtnActionPerformed
    
    public void goToLogout(){
        Login loginframe = new Login();
        loginframe.setVisible(true);
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
            java.util.logging.Logger.getLogger(AdDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Navigation;
    private javax.swing.JButton addbtn;
    private javax.swing.JTextField addresstxt;
    private javax.swing.JButton clearbtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JPanel editAccount;
    private javax.swing.JTable employeeTable;
    private javax.swing.JTable employeeTable1;
    private javax.swing.JPanel home;
    private javax.swing.JTextField idtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JPanel jp1;
    private javax.swing.JPanel jp2;
    private javax.swing.JPanel jp3;
    private javax.swing.JPanel logOut;
    private javax.swing.JPanel main;
    private javax.swing.JTextField nametxt;
    private javax.swing.JPasswordField passwordtxt;
    private javax.swing.JTextField phonenotxt;
    private javax.swing.JComboBox<String> rolecbx;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JLabel tab1;
    private javax.swing.JLabel tab2;
    private javax.swing.JLabel tab3;
    private javax.swing.JLabel tab4;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel title_label2;
    private javax.swing.JLabel title_label3;
    private javax.swing.JLabel title_label4;
    private javax.swing.JPanel topUp;
    private javax.swing.JTable topupTable;
    private javax.swing.JButton updatebtn;
    private javax.swing.JTextField usernametxt;
    // End of variables declaration//GEN-END:variables
}
