// Admin Dashboard
package system.admin;

import com.system.Login;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class AdDashboard extends javax.swing.JFrame {

    String roleFilePath = "users.txt";

    // Prefix for different roles
    String customerPrefix = "C";
    String vendorPrefix = "V";
    String deliveryRunnerPrefix = "D";
    String nextCustomerID = IdGenerator.getNextRoleID(customerPrefix, roleFilePath);

    private boolean isPanelVisible = false;
    private boolean isLoaded = false;
    Login login = new Login();

    public JPanel getJp1() {
        jp2.setVisible(false);
        jp3.setVisible(false);
        return jp1;
    }

    public JPanel getJp2() {
        jp1.setVisible(false);
        jp3.setVisible(false);
        return jp2;
    }

    public JPanel getJp3() {
        jp1.setVisible(false);
        jp2.setVisible(false);
        return jp3;
    }

    public AdDashboard() {
        initComponents();

        showIdInCbx(null);
        jp2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!userDetailTable.getBounds().contains(e.getPoint())) {
                    userDetailTable.clearSelection(); // Deselect row
                    for (ActionListener al : rolecbx.getActionListeners()) {
                        rolecbx.addActionListener(al);
                    }
                    rolecbx.setEnabled(true); // Disable after removing listeners
                }
            }
        });

        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onDelete(java.awt.event.ActionEvent evt) {
                int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this row?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    if (userDetailTable.isEditing()) {
                        userDetailTable.getCellEditor().stopCellEditing();
                    }
                    DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
                    try {
                        //get selected row of data
                        int selectedRow = userDetailTable.getSelectedRow();
                        String employeeId = (String) userDetailTable.getValueAt(selectedRow, 0);
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
                            fw.write(employeeList.get(i).get(6) + ";\n");
                        }

                        fw.close();

                        idtxt.setText("");
                        nametxt.setText("");
                        addresstxt.setText("");
                        phonenotxt.setText("");
                        usernametxt.setText("");
                        passwordtxt.setText("");
                        rolecbx.setSelectedIndex(0);

                        JOptionPane.showMessageDialog(null, "successfully deleted a record");
                        this.refreshData();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Successfully deleted employee");
                    }
                }
            }

            @Override
            public void onView(int row) {
                System.out.println("View row: " + row);
            }

            private void refreshData() {
                try {
                    DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
                    DefaultTableModel model1 = (DefaultTableModel) userTable.getModel();
                    model.setRowCount(0);//reset table
                    model1.setRowCount(0);
                    FileReader fr = new FileReader("users.txt");
                    BufferedReader br = new BufferedReader(fr);
                    String read;
                    while ((read = br.readLine()) != null) {
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
                                new Object[]{id, name, address, phoneno, username,
                                    role});
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        };

        userDetailTable.getColumnModel().getColumn(7).setCellRenderer(new TableActionCellRender());
        userDetailTable.getColumnModel().getColumn(7).setCellEditor(new TableActionCellEditor(event));
        Navigation.setVisible(false);
        jp1.setVisible(true);
        jp2.setVisible(false);
        jp3.setVisible(false);

        /*Just Added*/
        userDetailTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            @Override
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) { // Ensure the event only fires once when the selection is final
                    populateFormFromTableSelection();
                }
            }
        });
        this.refreshData();
        this.refreshTopupData();
    }

    private void populateFormFromTableSelection() {
        int selectedRow = userDetailTable.getSelectedRow();
        if (selectedRow != -1) { // Ensure a row is selected
            String id = (String) userDetailTable.getValueAt(selectedRow, 0);
            String name = (String) userDetailTable.getValueAt(selectedRow, 1);
            String address = (String) userDetailTable.getValueAt(selectedRow, 2);
            String phoneno = (String) userDetailTable.getValueAt(selectedRow, 3);
            String username = (String) userDetailTable.getValueAt(selectedRow, 4);
            String password = (String) userDetailTable.getValueAt(selectedRow, 5);
            String role = (String) userDetailTable.getValueAt(selectedRow, 6);

            double balance = getBalanceFromFile(id);
            //get id from file
            User user = new User(id, name, address, phoneno, username, password, role, balance);

            idtxt.setText(user.getId());
            nametxt.setText(user.getName());
            addresstxt.setText(user.getAddress());
            phonenotxt.setText(user.getphoneNo());
            usernametxt.setText(user.getUsername());
            passwordtxt.setText(user.getPassword());
            rolecbx.setSelectedItem(user.getRoles());

            rolecbx.setEnabled(false);
        } else {
            rolecbx.addActionListener(e -> updateIDBasedOnRole());
            nametxt.setText("");
            addresstxt.setText("");
            phonenotxt.setText("");
            usernametxt.setText("");
            passwordtxt.setText("");
            rolecbx.setSelectedIndex(0);
        }
    }

    private void refreshTopupData() {
        try {
            DefaultTableModel model = (DefaultTableModel) topupTable.getModel();
            model.setRowCount(0); // Reset table

            // Read users.txt into a map (id -> name)
            Map<String, String> userMap = new HashMap<>();
            try (BufferedReader userReader = new BufferedReader(new FileReader("users.txt"))) {
                String userLine;
                while ((userLine = userReader.readLine()) != null) {
                    String[] userDetails = userLine.split(";");
                    if (userDetails.length > 1) {
                        userMap.put(userDetails[0], userDetails[1]); // ID -> Name
                    }
                }
            }

            // Read userTopup.txt and store rows in a list
            List<Object[]> topupData = new ArrayList<>();
            try (BufferedReader topupReader = new BufferedReader(new FileReader("userTopup.txt"))) {
                String read;
                while ((read = topupReader.readLine()) != null) {
                    String[] topupDetails = read.split(";");
                    if (topupDetails.length == 4) {
                        String id = topupDetails[0];
                        String paymentMethod = topupDetails[1];
                        String amount = topupDetails[2];
                        String totalBalance = topupDetails[3];

                        // Retrieve name from users.txt based on id
                        String name = userMap.getOrDefault(id, "Unknown");

                        // Store the row data
                        topupData.add(new Object[]{id, name, paymentMethod, amount, totalBalance});
                    }
                }
            }

            // Add rows to the table in reverse order
            for (int i = topupData.size() - 1; i >= 0; i--) {
                model.addRow(topupData.get(i));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private double getBalanceFromFile(String userId) {
        try (BufferedReader br = new BufferedReader(new FileReader("userTopup.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(";");
                if (userDetails.length >= 2 && userDetails[0].equals(userId)) {
                    return Double.parseDouble(userDetails[2]); // Assuming balance is in index 2
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error fetching balance: " + e.getMessage());
        }
        return 0.0; // Default balance if not found
    }

    public void refreshData() {
        try {
            DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
            DefaultTableModel userModel = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);//reset table
            userModel.setRowCount(0);
            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String read;
            while ((read = br.readLine()) != null) {
                String id = read.split(";")[0];
                String name = read.split(";")[1];
                String address = read.split(";")[2];
                String phoneno = read.split(";")[3];
                String username = read.split(";")[4];
                String password = read.split(";")[5];
                String role = read.split(";")[6];
                String balance = read.split(";")[7];
                
                if (!role.equalsIgnoreCase("Customer")) {
                    balance = "-";
                }
                
                model.addRow(
                        new Object[]{id, name, address, phoneno, username, password,
                            role});
                userModel.addRow(
                        new Object[]{id, name, address, phoneno, username,
                            role, balance});
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void updateIDBasedOnRole() {
        String roleFilePath = "users.txt";
        String selectedRole = rolecbx.getSelectedItem().toString();

        // Determine the correct prefix
        String prefix = "";
        if (selectedRole.equalsIgnoreCase("Vendor")) {
            prefix = "V";
        } else if (selectedRole.equalsIgnoreCase("Delivery Runner")) {
            prefix = "D";
        } else if (selectedRole.equalsIgnoreCase("Customer")) {
            prefix = "C";
        }

        String newId = IdGenerator.getNextRoleID(prefix, roleFilePath);
        idtxt.setText(newId);
    }

    private void showIdInCbx(java.awt.event.ItemEvent evt) {
        if (isLoaded) {
            return;
        }
        cbxSelectId.removeAll();

        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(";");
                if (userDetails.length > 0) {
                    String userType = userDetails[6];
                    if ("Customer".equals(userType)) {
                        cbxSelectId.addItem(userDetails[0]);
                    }
                }
            }
            isLoaded = true;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error loading user IDs: " + e.getMessage());
        }
    }

    private void filterTableByRole(String role) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0); // Clear the table before adding filtered data

        if ("Please select a role.".equals(role)) {
            // If "Please select a role." is selected, reset the table to show all data
            refreshData();
            return;
        }

        try {
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
                String userRole = data[6].trim();
                String balance = data[7].trim();

                if (!role.equalsIgnoreCase("Customer")) {
                    balance = "-";
                }

                // Check if the role matches the selected role
                if (userRole.equalsIgnoreCase(role)) {
                    // Add the matching record to the table
                    model.addRow(new Object[]{id, name, address, phoneno, username, userRole, balance});
                }
            }
            br.close(); // Close the file reader
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

    public void goToLogout() {
        Login loginframe = new Login();
        loginframe.setVisible(true);
        dispose();
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
        userTable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        searchField = new javax.swing.JTextField();
        sortRoleCbx = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        clearSearchBtn = new javax.swing.JButton();
        jp2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userDetailTable = new javax.swing.JTable();
        title_label3 = new javax.swing.JLabel();
        nametxt = new javax.swing.JTextField();
        updatebtn = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        addresstxt = new javax.swing.JTextField();
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
        jButton2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        topupTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbxPaymentMethod = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        topUpTxt = new javax.swing.JTextField();
        topupBtn = new javax.swing.JButton();
        GenerateReceiptBtn = new javax.swing.JButton();
        title_label5 = new javax.swing.JLabel();
        cbxSelectId = new java.awt.Choice();
        clearTableBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.gray);
        setMaximumSize(new java.awt.Dimension(1600, 600));
        setMinimumSize(new java.awt.Dimension(1300, 586));
        setPreferredSize(new java.awt.Dimension(1300, 586));
        setSize(new java.awt.Dimension(1300, 586));

        Navigation.setBackground(java.awt.Color.gray);

        title_label.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title_label.setText("Navigation");

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
                .addContainerGap()
                .addGroup(NavigationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_label)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(home, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editAccount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(topUp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        title_label2.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label2.setText("Admin dashboard");

        userTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Address", "Phone No.", "Username", "Role", "Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userTable.setRowHeight(30);
        userTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(userTable);
        if (userTable.getColumnModel().getColumnCount() > 0) {
            userTable.getColumnModel().getColumn(0).setResizable(false);
            userTable.getColumnModel().getColumn(1).setResizable(false);
            userTable.getColumnModel().getColumn(3).setResizable(false);
            userTable.getColumnModel().getColumn(4).setResizable(false);
            userTable.getColumnModel().getColumn(5).setResizable(false);
            userTable.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel12.setText("Search:");

        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        sortRoleCbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Please select a role.", "Customer", "Vendor", "Delivery Runner" }));
        sortRoleCbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortRoleCbxActionPerformed(evt);
            }
        });

        jLabel13.setText("Role:");

        clearSearchBtn.setText("Clear search");
        clearSearchBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clearSearchBtnMouseReleased(evt);
            }
        });
        clearSearchBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                clearSearchBtnKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jp1Layout = new javax.swing.GroupLayout(jp1);
        jp1.setLayout(jp1Layout);
        jp1Layout.setHorizontalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sortRoleCbx, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(clearSearchBtn))
                    .addGroup(jp1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(title_label2)))
                .addContainerGap())
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1288, Short.MAX_VALUE)
                .addContainerGap())
        );
        jp1Layout.setVerticalGroup(
            jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(title_label2)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jp1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortRoleCbx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(clearSearchBtn))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jp2.setPreferredSize(new java.awt.Dimension(1300, 563));

        userDetailTable.setModel(new javax.swing.table.DefaultTableModel(
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
        userDetailTable.setMinimumSize(new java.awt.Dimension(120, 700));
        userDetailTable.setRowHeight(40);
        jScrollPane1.setViewportView(userDetailTable);
        if (userDetailTable.getColumnModel().getColumnCount() > 0) {
            userDetailTable.getColumnModel().getColumn(0).setResizable(false);
            userDetailTable.getColumnModel().getColumn(1).setResizable(false);
            userDetailTable.getColumnModel().getColumn(2).setResizable(false);
            userDetailTable.getColumnModel().getColumn(3).setResizable(false);
            userDetailTable.getColumnModel().getColumn(4).setResizable(false);
            userDetailTable.getColumnModel().getColumn(5).setResizable(false);
            userDetailTable.getColumnModel().getColumn(6).setResizable(false);
            userDetailTable.getColumnModel().getColumn(7).setResizable(false);
        }

        title_label3.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label3.setText("Manage account details");

        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        jLabel4.setText("address:");

        jLabel5.setText("Phone No.:");

        phonenotxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonenotxtActionPerformed(evt);
            }
        });

        searchtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxtActionPerformed(evt);
            }
        });
        searchtxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchtxtKeyTyped(evt);
            }
        });

        jLabel6.setText("Username:");

        jLabel9.setText("Search:");

        rolecbx.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Customer", "Vendor", "Delivery Runner" }));
        rolecbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rolecbxActionPerformed(evt);
            }
        });

        jLabel7.setText("Password:");

        jLabel2.setText("id:");

        clearbtn.setText("Clear");
        clearbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearbtnActionPerformed(evt);
            }
        });

        idtxt.setEditable(false);
        idtxt.setEnabled(false);
        idtxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtxtActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 666, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp2Layout.createSequentialGroup()
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jp2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(27, 27, 27)
                                .addComponent(searchtxt, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jp2Layout.createSequentialGroup()
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(title_label3)))
                        .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jp2Layout.createSequentialGroup()
                                    .addGap(118, 118, 118)
                                    .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(usernametxt, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                        .addComponent(rolecbx, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passwordtxt)))
                                .addGroup(jp2Layout.createSequentialGroup()
                                    .addGap(115, 115, 115)
                                    .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2))
                                    .addGap(18, 18, 18)
                                    .addGroup(jp2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(addresstxt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                                        .addComponent(nametxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(idtxt, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(phonenotxt))))
                            .addGroup(jp2Layout.createSequentialGroup()
                                .addGap(159, 159, 159)
                                .addComponent(jButton1)
                                .addGap(65, 65, 65)
                                .addComponent(clearbtn)
                                .addGap(65, 65, 65)
                                .addComponent(updatebtn)
                                .addGap(65, 65, 65)
                                .addComponent(addbtn)))))
                .addContainerGap(86, Short.MAX_VALUE))
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
                            .addComponent(addbtn)
                            .addComponent(jButton1)
                            .addComponent(clearbtn)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image-40x35.jpg"))); // NOI18N
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        topupTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Name", "Payment Method", "Amount", "Total Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
            topupTable.getColumnModel().getColumn(2).setPreferredWidth(1);
            topupTable.getColumnModel().getColumn(3).setPreferredWidth(1);
        }

        jLabel1.setText("Select a customer id: ");

        cbxPaymentMethod.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "TnGo E-Wallet", "Credit/Debit Card", "Bank Transfer" }));

        jLabel10.setText("Payment Method: ");

        jLabel11.setText("Top-Up Amount (RM) : ");

        topUpTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topUpTxtActionPerformed(evt);
            }
        });

        topupBtn.setText("Top-Up");
        topupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                topupBtnActionPerformed(evt);
            }
        });

        GenerateReceiptBtn.setText("Generate Transaction Receipt");
        GenerateReceiptBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateReceiptBtnActionPerformed(evt);
            }
        });

        title_label5.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        title_label5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        title_label5.setText("Top-up System");

        clearTableBtn.setText("Clear History");
        clearTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearTableBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp3Layout = new javax.swing.GroupLayout(jp3);
        jp3.setLayout(jp3Layout);
        jp3Layout.setHorizontalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(title_label5))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbxPaymentMethod, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(topUpTxt)
                            .addComponent(topupBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxSelectId, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(clearTableBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GenerateReceiptBtn)))
                .addGap(60, 60, 60))
        );
        jp3Layout.setVerticalGroup(
            jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGap(226, 226, 226)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(cbxSelectId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxPaymentMethod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(topUpTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(54, 54, 54)
                        .addComponent(topupBtn)
                        .addGap(37, 37, 37)
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(GenerateReceiptBtn)
                            .addComponent(clearTableBtn))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jp3Layout.createSequentialGroup()
                        .addGroup(jp3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(title_label5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)))
                .addContainerGap())
        );

        cbxSelectId.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                showIdInCbx(evt);
            }
        });

        javax.swing.GroupLayout mainLayout = new javax.swing.GroupLayout(main);
        main.setLayout(mainLayout);
        mainLayout.setHorizontalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainLayout.createSequentialGroup()
                .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp2, javax.swing.GroupLayout.DEFAULT_SIZE, 1306, Short.MAX_VALUE))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        mainLayout.setVerticalGroup(
            mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jp1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
            .addGroup(mainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jp3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Navigation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Navigation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        idtxt.setText(nextCustomerID);
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
            if ((idtxt == null || idtxt.getText().trim().isEmpty())
                    || (nametxt == null || nametxt.getText().trim().isEmpty())
                    || (addresstxt == null || addresstxt.getText().trim().isEmpty())
                    || (phonenotxt == null || phonenotxt.getText().trim().isEmpty())
                    || (usernametxt == null || usernametxt.getText().trim().isEmpty())
                    || (passwordtxt == null || new String(passwordtxt.getPassword()).trim().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields!");
                return;
            }

            // Validate that the phone number is numeric
            String phoneNumber = phonenotxt.getText().trim();
            try {
                Integer.parseInt(phoneNumber);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Phone number must be numeric!");
                return;
            }

            // Write data to the file
            fw.write(
                    idtxt.getText().trim() + ";"
                    + nametxt.getText().trim() + ";"
                    + addresstxt.getText().trim() + ";"
                    + phoneNumber + ";"
                    + usernametxt.getText().trim() + ";"
                    + new String(passwordtxt.getPassword()).trim() + ";"
                    + rolecbx.getSelectedItem().toString() + ";"
                    + "0" + ";" //Balance
                    + "\n"
            );
            fw.close();

            JOptionPane.showMessageDialog(null, "Successfully added the data!");
            refreshData();
            for (ActionListener al : rolecbx.getActionListeners()) {
                rolecbx.removeActionListener(al);
            }

            // Reset all fields
            nametxt.setText("");
            addresstxt.setText("");
            phonenotxt.setText("");
            usernametxt.setText("");
            passwordtxt.setText("");
            rolecbx.setSelectedIndex(0);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_addbtnActionPerformed

    private void clearbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearbtnActionPerformed
        // TODO add your handling code here:
        rolecbx.addActionListener(e -> updateIDBasedOnRole());
        nametxt.setText("");
        addresstxt.setText("");
        phonenotxt.setText("");
        usernametxt.setText("");
        passwordtxt.setText("");
        rolecbx.setSelectedIndex(0);
    }//GEN-LAST:event_clearbtnActionPerformed

    private void searchtxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyReleased
        try {
            String searchText = searchtxt.getText().trim();
            DefaultTableModel model = (DefaultTableModel) userDetailTable.getModel();
            model.setRowCount(0);
            if (searchText.isEmpty()) {
                refreshData();
                return;
            }

            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String id = data[0].trim();
                String name = data[1].trim();
                String address = data[2].trim();
                String phoneno = data[3].trim();
                String username = data[4].trim();
                String password = data[5].trim();
                String role = data[6].trim();

                String searchableText = id + " " + name + " " + address + " " + phoneno + " " + username + " " + role;

                // Check if the search text matches the order in the searchable text
                if (isOrderedMatch(searchText.toLowerCase(), searchableText.toLowerCase())) {
                    // Add the matching record to the table
                    model.addRow(new Object[]{id, name, address, phoneno, username, password, role});
                }
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }

// Helper method to check if the search text follows the word order in the searchable text
    private boolean isOrderedMatch(String searchText, String searchableText) {
        String[] searchWords = searchText.split("\\s+");
        String[] searchableWords = searchableText.split("\\s+");

        int searchIndex = 0;
        for (String searchableWord : searchableWords) {
            if (searchableWord.equals(searchWords[searchIndex])) {
                searchIndex++;
                if (searchIndex == searchWords.length) {
                    return true;
                }
            }
        }
        return false;
    }//GEN-LAST:event_searchtxtKeyReleased

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        try {
            String newId = idtxt.getText();
            String name = nametxt.getText();
            String address = addresstxt.getText();
            String phoneno = phonenotxt.getText();
            String username = usernametxt.getText();
            String password = new String(passwordtxt.getPassword());
            String role = rolecbx.getSelectedItem().toString();

            int selectedRow = userDetailTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "No record selected for editing!");
                rolecbx.addActionListener(e -> updateIDBasedOnRole());
                return;
            }

            if (newId.trim().isEmpty()
                    || name.trim().isEmpty()
                    || address.trim().isEmpty()
                    || phoneno.trim().isEmpty()
                    || username.trim().isEmpty()
                    || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the fields.");
                return;
            }

            try {
                Integer.parseInt(phoneno);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Phone number must be numeric.");
                return;
            }

            String originalId = (String) userDetailTable.getValueAt(selectedRow, 0); // get id from selected row

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
                                + role + "\n"
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

    private void phonenotxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonenotxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phonenotxtActionPerformed

    private void rolecbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rolecbxActionPerformed

    }//GEN-LAST:event_rolecbxActionPerformed

    private void idtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtxtActionPerformed

    }//GEN-LAST:event_idtxtActionPerformed

    private void topupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topupBtnActionPerformed
        try {
            // Get input values
            String id = cbxSelectId.getSelectedItem().toString();
            String paymentMethod = cbxPaymentMethod.getSelectedItem().toString();
            String amountStr = topUpTxt.getText().trim();

            // Validate inputs
            if (id.isEmpty() || paymentMethod.isEmpty() || amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter the top up amount!");
                return;
            }

            // Validate amount
            double topupAmount;
            try {
                topupAmount = Double.parseDouble(amountStr);
                if (topupAmount <= 0) {
                    JOptionPane.showMessageDialog(null, "Amount must be greater than zero!");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid amount format!");
                return;
            }

            // Update user balance in users.txt
            File usersFile = new File("users.txt");
            List<String> userLines = new ArrayList<>();
            boolean userFound = false;
            double newBalance = 0;

            try (BufferedReader br = new BufferedReader(new FileReader(usersFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(";");
                    if (parts.length >= 8 && parts[0].equals(id)) {
                        userFound = true;
                        // Update balance
                        double currentBalance = Double.parseDouble(parts[7]);
                        newBalance = currentBalance + topupAmount;
                        parts[7] = String.format("%.2f", newBalance);
                        line = String.join(";", parts);
                    }
                    userLines.add(line);
                }
            }

            if (!userFound) {
                JOptionPane.showMessageDialog(null, "User not found!");
                return;
            }

            // Write updated users back to file
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(usersFile))) {
                for (String line : userLines) {
                    bw.write(line);
                    bw.newLine();
                }
            }

            //Record transaction in userTopup.txt
            try (BufferedWriter topupWriter = new BufferedWriter(new FileWriter("userTopup.txt", true))) {
                String transaction = String.format("%s;%s;%.2f;%.2f",
                        id, paymentMethod, topupAmount, newBalance);
                topupWriter.write(transaction);
                topupWriter.newLine();
            }

            cbxSelectId.select(0);
            cbxPaymentMethod.setSelectedIndex(0);
            topUpTxt.setText("");
            JOptionPane.showMessageDialog(null, "Top-up successful!");
            this.refreshTopupData();
            this.refreshData();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_topupBtnActionPerformed

    private void GenerateReceiptBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateReceiptBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_GenerateReceiptBtnActionPerformed

    private void topUpTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_topUpTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_topUpTxtActionPerformed

    private void clearTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearTableBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clearTableBtnActionPerformed

    private void sortRoleCbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortRoleCbxActionPerformed
        String selectedRole = (String) sortRoleCbx.getSelectedItem();
        searchField.setText("");
        filterTableByRole(selectedRole);
    }//GEN-LAST:event_sortRoleCbxActionPerformed

    private void searchtxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtActionPerformed

    }//GEN-LAST:event_searchtxtActionPerformed

    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        try {
            String search = searchField.getText().trim();
            DefaultTableModel model = (DefaultTableModel) userTable.getModel();
            model.setRowCount(0);
            if (search.isEmpty()) {
                refreshData();
                return;
            }

            FileReader fr = new FileReader("users.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                String id = data[0].trim();
                String name = data[1].trim();
                String address = data[2].trim();
                String phoneno = data[3].trim();
                String username = data[4].trim();
                String role = data[6].trim();
                String balance = data[7].trim();

                if (!role.equalsIgnoreCase("Customer")) {
                    balance = "-";
                }

                String searchableText = id + " " + name + " " + address + " " + phoneno + " " + username;

                // Check if the search text matches the order in the searchable text
                if (isOrderedMatch(search.toLowerCase(), searchableText.toLowerCase())) {
                    // Add the matching record to the table
                    model.addRow(new Object[]{id, name, address, phoneno, username, role, balance});
                }
            }
            br.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage());
        }
    }//GEN-LAST:event_searchFieldKeyReleased

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void searchtxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtKeyTyped

    private void clearSearchBtnKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_clearSearchBtnKeyReleased

    }//GEN-LAST:event_clearSearchBtnKeyReleased

    private void clearSearchBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearSearchBtnMouseReleased
        searchField.setText("");
        sortRoleCbx.setSelectedIndex(0);
    }//GEN-LAST:event_clearSearchBtnMouseReleased

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
    private javax.swing.JButton GenerateReceiptBtn;
    private javax.swing.JPanel Navigation;
    private javax.swing.JButton addbtn;
    private javax.swing.JTextField addresstxt;
    private javax.swing.JComboBox<String> cbxPaymentMethod;
    private java.awt.Choice cbxSelectId;
    private javax.swing.JButton clearSearchBtn;
    private javax.swing.JButton clearTableBtn;
    private javax.swing.JButton clearbtn;
    private javax.swing.JPanel editAccount;
    private javax.swing.JPanel home;
    private javax.swing.JTextField idtxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchtxt;
    private javax.swing.JComboBox<String> sortRoleCbx;
    private javax.swing.JLabel tab1;
    private javax.swing.JLabel tab2;
    private javax.swing.JLabel tab3;
    private javax.swing.JLabel tab4;
    private javax.swing.JLabel title_label;
    private javax.swing.JLabel title_label2;
    private javax.swing.JLabel title_label3;
    private javax.swing.JLabel title_label5;
    private javax.swing.JPanel topUp;
    private javax.swing.JTextField topUpTxt;
    private javax.swing.JButton topupBtn;
    private javax.swing.JTable topupTable;
    private javax.swing.JButton updatebtn;
    private javax.swing.JTable userDetailTable;
    private javax.swing.JTable userTable;
    private javax.swing.JTextField usernametxt;
    // End of variables declaration//GEN-END:variables

}
