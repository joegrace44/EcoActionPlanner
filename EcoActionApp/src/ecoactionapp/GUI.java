/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ecoactionapp;

import java.awt.CardLayout;
import java.awt.Color;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.style.CategoryStyler;

/**
 *
 * @author joegr
 */
public class GUI extends javax.swing.JFrame {

    private ArrayList<EcoAction> ecoActions = new ArrayList<>();

    /**
     * Creates new form GUI
     */
    public GUI() {
        initComponents();
        setTitle("Eco Action Planner");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Ensure jPanelBackgroundPanel has CardLayout set in the GUI Designer
        jPanelBackground.setLayout(new CardLayout());

        // Adds panels to jPanelBackgroundPanel with unique identifiers
        jPanelBackground.add(jPanelDash, "jPanelDash");
        jPanelBackground.add(jPanelLogin, "jPanelLogin");
        jPanelBackground.add(jPanelAction, "jPanelAction");
        jPanelBackground.add(jPanelAccount, "jPanelAccount");

        // Show the dashboard panel when the app starts
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelDash");

        // Load data when the app starts
        loadActionsFromFile();

        // Add a WindowListener to save data when the app closes
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                saveActionsToFile();
                System.exit(0);
            }
        });
    }

    private boolean isEditing = false;
    private int editingRowIndex = -1;

    // Add the createImpactChart method here
    public CategoryChart createImpactChart() {
        // Calculate total CO2 reduction from transport actions
        double totalCO2Emission = ecoActions.stream()
                .filter(action -> action instanceof TransportAction)
                .mapToDouble(EcoAction::calculateImpact)
                .sum();

        double totalEnergySaved = ecoActions.stream()
                .filter(action -> action instanceof EnergyAction)
                .mapToDouble(EcoAction::calculateImpact)
                .sum();

        CategoryChart chart = new CategoryChartBuilder()
                .width(800)
                .height(600)
                .title("Environmental Impact Chart")
                .xAxisTitle("Action Type")
                .yAxisTitle("Impact")
                .build();

        List<String> categories = List.of("Transport", "Energy");
        List<Number> values = List.of(totalCO2Emission, totalEnergySaved);

        chart.addSeries("Impact", categories, values);
        chart.getStyler().setSeriesColors(new Color[]{new Color(102, 255, 102), new Color(255, 153, 51)});
        return chart;
    }

    private void saveActionsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ecoActions.dat"))) {
            oos.writeObject(ecoActions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadActionsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ecoActions.dat"))) {
            ecoActions = (ArrayList<EcoAction>) ois.readObject();
            refreshActionTable(); // Refresh the table with loaded data
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Method to refresh the data in actions table
    private void refreshActionTable() {
        // Get the table's data model and cast it to DefaultTableModel
        DefaultTableModel model = (DefaultTableModel) jTableAction.getModel();

        // Clear all existing rows in the table
        model.setRowCount(0);

        // Iterate through the list of ecoActions to populate the table
        for (EcoAction action : ecoActions) {
            // Add a new row to the table with the following columns:
            model.addRow(new Object[]{
                action instanceof TransportAction ? "Transport" : "Energy", // Determine the type of action (Transport or Energy)
                action.getDescription(), // Get the action's description
                action.calculateImpact() + (action instanceof TransportAction ? " kg CO2" : " kWh"), // Calculate the impact and append the appropriate unit
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) // Display the current date
            });
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

        jPanelBackground = new javax.swing.JPanel();
        jPanelDash = new javax.swing.JPanel();
        jLabelWelcome = new javax.swing.JLabel();
        jLabelEcoActionPlanner = new javax.swing.JLabel();
        jButtonDashSignup = new javax.swing.JButton();
        jButtonImpact = new javax.swing.JButton();
        jLabelAuthor = new javax.swing.JLabel();
        jButtonDashLogin = new javax.swing.JButton();
        jPanelAction = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxTransportType = new javax.swing.JComboBox<>();
        jTextFieldDistance = new javax.swing.JTextField();
        jLabelModeTransport = new javax.swing.JLabel();
        jLabelDistanceTravelled = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEnergySaved = new javax.swing.JTextField();
        jLabelEnergySaved = new javax.swing.JLabel();
        jTextFieldActionDescription = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAction = new javax.swing.JTable();
        jButtonSaveTransport = new javax.swing.JButton();
        jButtonClearTransport = new javax.swing.JButton();
        jButtonEnergySave = new javax.swing.JButton();
        jButtonEnergyClear = new javax.swing.JButton();
        jButtonDashImpact = new javax.swing.JButton();
        jButtonActionLogin = new javax.swing.JButton();
        jButtonActionChart = new javax.swing.JButton();
        jButtonTableEdit = new javax.swing.JButton();
        jButtonTableDelete = new javax.swing.JButton();
        jPanelLogin = new javax.swing.JPanel();
        loginLabel = new javax.swing.JLabel();
        usernameLabel7 = new javax.swing.JLabel();
        usernameTF7 = new javax.swing.JTextField();
        passwordLabel7 = new javax.swing.JLabel();
        passwordPF = new javax.swing.JPasswordField();
        loginButton = new javax.swing.JButton();
        orLabel = new javax.swing.JLabel();
        accountButton = new javax.swing.JButton();
        jButtonLoginAction = new javax.swing.JButton();
        jButtonLoginDash = new javax.swing.JButton();
        jPanelAccount = new javax.swing.JPanel();
        accountButton1 = new javax.swing.JButton();
        adPanel7 = new javax.swing.JPanel();
        usernameLabel8 = new javax.swing.JLabel();
        usernameTF8 = new javax.swing.JTextField();
        passwordLabel8 = new javax.swing.JLabel();
        passwordTF7 = new javax.swing.JTextField();
        adLabel = new javax.swing.JLabel();
        pdPanel = new javax.swing.JPanel();
        fnLabel = new javax.swing.JLabel();
        fnTF = new javax.swing.JTextField();
        dobLabel = new javax.swing.JLabel();
        dobTF = new javax.swing.JTextField();
        pdLabel = new javax.swing.JLabel();
        accountLabel = new javax.swing.JLabel();
        jButtonAccountDash = new javax.swing.JButton();
        jButtonAccountAction = new javax.swing.JButton();
        jButtonAccountLogin = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        jPanelBackground.setBackground(new java.awt.Color(0, 153, 153));
        jPanelBackground.setLayout(new java.awt.CardLayout());

        jPanelDash.setBackground(new java.awt.Color(0, 204, 102));

        jLabelWelcome.setBackground(new java.awt.Color(51, 51, 51));
        jLabelWelcome.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelWelcome.setForeground(new java.awt.Color(51, 51, 51));
        jLabelWelcome.setText("Welcome to our");

        jLabelEcoActionPlanner.setBackground(new java.awt.Color(0, 102, 51));
        jLabelEcoActionPlanner.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabelEcoActionPlanner.setForeground(new java.awt.Color(0, 102, 51));
        jLabelEcoActionPlanner.setText("Eco Action Planner");

        jButtonDashSignup.setBackground(new java.awt.Color(204, 204, 204));
        jButtonDashSignup.setForeground(new java.awt.Color(51, 51, 51));
        jButtonDashSignup.setText("Sign up");
        jButtonDashSignup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDashSignupActionPerformed(evt);
            }
        });

        jButtonImpact.setBackground(new java.awt.Color(204, 204, 204));
        jButtonImpact.setForeground(new java.awt.Color(51, 51, 51));
        jButtonImpact.setText("Track your Impact");
        jButtonImpact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImpactActionPerformed(evt);
            }
        });

        jLabelAuthor.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabelAuthor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelAuthor.setText("By Joseph Grace and Dylan Earls");

        jButtonDashLogin.setBackground(new java.awt.Color(204, 204, 204));
        jButtonDashLogin.setForeground(new java.awt.Color(51, 51, 51));
        jButtonDashLogin.setText("Log-in");
        jButtonDashLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDashLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDashLayout = new javax.swing.GroupLayout(jPanelDash);
        jPanelDash.setLayout(jPanelDashLayout);
        jPanelDashLayout.setHorizontalGroup(
            jPanelDashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashLayout.createSequentialGroup()
                .addGroup(jPanelDashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDashLayout.createSequentialGroup()
                        .addGap(263, 263, 263)
                        .addComponent(jLabelWelcome))
                    .addGroup(jPanelDashLayout.createSequentialGroup()
                        .addGap(239, 239, 239)
                        .addComponent(jLabelEcoActionPlanner))
                    .addGroup(jPanelDashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDashLayout.createSequentialGroup()
                            .addGap(218, 218, 218)
                            .addComponent(jButtonDashSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonDashLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelDashLayout.createSequentialGroup()
                            .addGap(231, 231, 231)
                            .addComponent(jLabelAuthor))))
                .addGap(0, 313, Short.MAX_VALUE))
            .addGroup(jPanelDashLayout.createSequentialGroup()
                .addGap(314, 314, 314)
                .addComponent(jButtonImpact, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDashLayout.setVerticalGroup(
            jPanelDashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashLayout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabelWelcome, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelEcoActionPlanner, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAuthor)
                .addGap(157, 157, 157)
                .addGroup(jPanelDashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDashSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonDashLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButtonImpact, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jPanelBackground.add(jPanelDash, "card2");

        jPanelAction.setBackground(new java.awt.Color(0, 204, 102));

        jLabel1.setBackground(new java.awt.Color(51, 51, 51));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 0));
        jLabel1.setText("Transport Actions");

        jComboBoxTransportType.setBackground(new java.awt.Color(204, 204, 204));
        jComboBoxTransportType.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jComboBoxTransportType.setForeground(new java.awt.Color(51, 51, 51));
        jComboBoxTransportType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Car", "Walk", "Bike", "Bus" }));
        jComboBoxTransportType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTransportTypeActionPerformed(evt);
            }
        });

        jTextFieldDistance.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldDistance.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldDistance.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldDistance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDistanceActionPerformed(evt);
            }
        });

        jLabelModeTransport.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelModeTransport.setForeground(new java.awt.Color(51, 51, 51));
        jLabelModeTransport.setText("Select Mode of Transport");

        jLabelDistanceTravelled.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelDistanceTravelled.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDistanceTravelled.setText("Enter Distance Travelled (km)");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("What did you do?");

        jTextFieldEnergySaved.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldEnergySaved.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldEnergySaved.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldEnergySaved.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEnergySavedActionPerformed(evt);
            }
        });

        jLabelEnergySaved.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelEnergySaved.setForeground(new java.awt.Color(51, 51, 51));
        jLabelEnergySaved.setText("Energy Saved (kWh)");

        jTextFieldActionDescription.setBackground(new java.awt.Color(204, 204, 204));
        jTextFieldActionDescription.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTextFieldActionDescription.setForeground(new java.awt.Color(51, 51, 51));
        jTextFieldActionDescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldActionDescriptionActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(51, 51, 51));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 0));
        jLabel6.setText("Energy Saving Actions");

        jLabel5.setBackground(new java.awt.Color(51, 51, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Track your Impact");

        jTableAction.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTableAction.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Action Type", "Description", "Impact Measurement", "Date of Action"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableAction);

        jButtonSaveTransport.setBackground(new java.awt.Color(204, 204, 204));
        jButtonSaveTransport.setForeground(new java.awt.Color(51, 51, 51));
        jButtonSaveTransport.setText("Save");
        jButtonSaveTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveTransportActionPerformed(evt);
            }
        });

        jButtonClearTransport.setBackground(new java.awt.Color(204, 204, 204));
        jButtonClearTransport.setForeground(new java.awt.Color(51, 51, 51));
        jButtonClearTransport.setText("Clear");
        jButtonClearTransport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearTransportActionPerformed(evt);
            }
        });

        jButtonEnergySave.setBackground(new java.awt.Color(204, 204, 204));
        jButtonEnergySave.setForeground(new java.awt.Color(51, 51, 51));
        jButtonEnergySave.setText("Save");
        jButtonEnergySave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnergySaveActionPerformed(evt);
            }
        });

        jButtonEnergyClear.setBackground(new java.awt.Color(204, 204, 204));
        jButtonEnergyClear.setForeground(new java.awt.Color(51, 51, 51));
        jButtonEnergyClear.setText("Clear");
        jButtonEnergyClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnergyClearActionPerformed(evt);
            }
        });

        jButtonDashImpact.setBackground(new java.awt.Color(0, 51, 0));
        jButtonDashImpact.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDashImpact.setText("Dashboard");
        jButtonDashImpact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDashImpactActionPerformed(evt);
            }
        });

        jButtonActionLogin.setBackground(new java.awt.Color(0, 51, 0));
        jButtonActionLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActionLogin.setText("Log-in");
        jButtonActionLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionLoginActionPerformed(evt);
            }
        });

        jButtonActionChart.setBackground(new java.awt.Color(0, 51, 0));
        jButtonActionChart.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButtonActionChart.setForeground(new java.awt.Color(255, 255, 255));
        jButtonActionChart.setText("Click here for your Impact Chart");
        jButtonActionChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActionChartActionPerformed(evt);
            }
        });

        jButtonTableEdit.setBackground(new java.awt.Color(204, 204, 204));
        jButtonTableEdit.setForeground(new java.awt.Color(51, 51, 51));
        jButtonTableEdit.setText("Edit");
        jButtonTableEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTableEditActionPerformed(evt);
            }
        });

        jButtonTableDelete.setBackground(new java.awt.Color(204, 204, 204));
        jButtonTableDelete.setForeground(new java.awt.Color(51, 51, 51));
        jButtonTableDelete.setText("Delete");
        jButtonTableDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTableDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelActionLayout = new javax.swing.GroupLayout(jPanelAction);
        jPanelAction.setLayout(jPanelActionLayout);
        jPanelActionLayout.setHorizontalGroup(
            jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionLayout.createSequentialGroup()
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButtonDashImpact)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActionLogin)
                        .addGap(49, 49, 49)
                        .addComponent(jLabel5))
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGap(484, 484, 484)
                        .addComponent(jLabel6))
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelActionLayout.createSequentialGroup()
                                .addComponent(jButtonTableDelete)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonTableEdit)
                                .addGap(67, 67, 67)
                                .addComponent(jButtonActionChart))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelActionLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jComboBoxTransportType, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelModeTransport)
                                        .addComponent(jLabel1))
                                    .addComponent(jTextFieldDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelActionLayout.createSequentialGroup()
                                        .addComponent(jButtonSaveTransport)
                                        .addGap(48, 48, 48)
                                        .addComponent(jButtonClearTransport))))
                            .addGroup(jPanelActionLayout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jLabelDistanceTravelled)))
                        .addGap(252, 252, 252)
                        .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEnergySaved)
                            .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextFieldEnergySaved)
                                .addComponent(jTextFieldActionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelActionLayout.createSequentialGroup()
                                .addComponent(jButtonEnergySave)
                                .addGap(38, 38, 38)
                                .addComponent(jButtonEnergyClear)))))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jPanelActionLayout.setVerticalGroup(
            jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelActionLayout.createSequentialGroup()
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDashImpact)
                            .addComponent(jButtonActionLogin)))
                    .addComponent(jLabel5))
                .addGap(21, 21, 21)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addGap(12, 12, 12)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelModeTransport, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTransportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldActionDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDistanceTravelled, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelEnergySaved, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDistance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEnergySaved, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSaveTransport)
                    .addComponent(jButtonClearTransport)
                    .addComponent(jButtonEnergySave)
                    .addComponent(jButtonEnergyClear))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(jPanelActionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonTableDelete)
                            .addComponent(jButtonTableEdit)))
                    .addGroup(jPanelActionLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonActionChart, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jPanelBackground.add(jPanelAction, "card3");

        jPanelLogin.setBackground(new java.awt.Color(0, 204, 102));
        jPanelLogin.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanelLogin.setMinimumSize(new java.awt.Dimension(800, 600));
        jPanelLogin.setPreferredSize(new java.awt.Dimension(800, 600));

        loginLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        loginLabel.setForeground(new java.awt.Color(51, 51, 51));
        loginLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loginLabel.setText("Log-in to your Account");

        usernameLabel7.setForeground(new java.awt.Color(51, 51, 51));
        usernameLabel7.setText("Username");

        passwordLabel7.setForeground(new java.awt.Color(51, 51, 51));
        passwordLabel7.setText("Password");

        passwordPF.setText("jPasswordField1");

        loginButton.setText("Log In");

        orLabel.setForeground(new java.awt.Color(51, 51, 51));
        orLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orLabel.setText("or");

        accountButton.setText("Create An Account");
        accountButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButtonActionPerformed(evt);
            }
        });

        jButtonLoginAction.setBackground(new java.awt.Color(0, 51, 0));
        jButtonLoginAction.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLoginAction.setText("Track Impact");
        jButtonLoginAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionActionPerformed(evt);
            }
        });

        jButtonLoginDash.setBackground(new java.awt.Color(0, 51, 0));
        jButtonLoginDash.setForeground(new java.awt.Color(255, 255, 255));
        jButtonLoginDash.setText("Dashboard");
        jButtonLoginDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginDashActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelLoginLayout = new javax.swing.GroupLayout(jPanelLogin);
        jPanelLogin.setLayout(jPanelLoginLayout);
        jPanelLoginLayout.setHorizontalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jButtonLoginDash)
                .addGap(18, 18, 18)
                .addComponent(jButtonLoginAction)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginLayout.createSequentialGroup()
                .addGap(0, 375, Short.MAX_VALUE)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameLabel7)
                        .addComponent(passwordLabel7)
                        .addComponent(passwordPF)
                        .addComponent(usernameTF7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(loginLabel))
                .addGap(251, 251, 251))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLoginLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelLoginLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanelLoginLayout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(accountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(orLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(315, 315, 315))
        );
        jPanelLoginLayout.setVerticalGroup(
            jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLoginLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanelLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLoginDash)
                    .addComponent(jButtonLoginAction))
                .addGap(8, 8, 8)
                .addComponent(loginLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(usernameLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(passwordLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accountButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(137, Short.MAX_VALUE))
        );

        jPanelBackground.add(jPanelLogin, "card5");

        jPanelAccount.setBackground(new java.awt.Color(0, 204, 102));
        jPanelAccount.setForeground(new java.awt.Color(0, 204, 102));

        accountButton1.setBackground(new java.awt.Color(204, 204, 204));
        accountButton1.setForeground(new java.awt.Color(51, 51, 51));
        accountButton1.setText("Create Account");
        accountButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountButton1ActionPerformed(evt);
            }
        });

        adPanel7.setBackground(new java.awt.Color(51, 51, 51));
        adPanel7.setForeground(new java.awt.Color(204, 204, 204));

        usernameLabel8.setBackground(new java.awt.Color(255, 255, 255));
        usernameLabel8.setForeground(new java.awt.Color(255, 255, 255));
        usernameLabel8.setText("Username:");

        passwordLabel8.setBackground(new java.awt.Color(255, 255, 255));
        passwordLabel8.setForeground(new java.awt.Color(255, 255, 255));
        passwordLabel8.setText("Password:");

        adLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        adLabel.setForeground(new java.awt.Color(204, 204, 204));
        adLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        adLabel.setText("Account Details");

        javax.swing.GroupLayout adPanel7Layout = new javax.swing.GroupLayout(adPanel7);
        adPanel7.setLayout(adPanel7Layout);
        adPanel7Layout.setHorizontalGroup(
            adPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adPanel7Layout.createSequentialGroup()
                .addGroup(adPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(adPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(passwordTF7, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, adPanel7Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(adPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameTF8, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, adPanel7Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(adLabel)
                .addGap(95, 95, 95))
        );
        adPanel7Layout.setVerticalGroup(
            adPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(adLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTF8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(passwordLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTF7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(119, Short.MAX_VALUE))
        );

        pdPanel.setBackground(new java.awt.Color(51, 51, 51));
        pdPanel.setForeground(new java.awt.Color(204, 204, 204));

        fnLabel.setBackground(new java.awt.Color(255, 255, 255));
        fnLabel.setForeground(new java.awt.Color(255, 255, 255));
        fnLabel.setText("Full Name:");

        dobLabel.setBackground(new java.awt.Color(255, 255, 255));
        dobLabel.setForeground(new java.awt.Color(255, 255, 255));
        dobLabel.setText("Date of Birth:");

        pdLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        pdLabel.setForeground(new java.awt.Color(204, 204, 204));
        pdLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pdLabel.setText("Personal Details");

        javax.swing.GroupLayout pdPanelLayout = new javax.swing.GroupLayout(pdPanel);
        pdPanel.setLayout(pdPanelLayout);
        pdPanelLayout.setHorizontalGroup(
            pdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pdPanelLayout.createSequentialGroup()
                .addGroup(pdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pdPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fnLabel)
                            .addComponent(fnTF)
                            .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dobTF, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)))
                    .addGroup(pdPanelLayout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addComponent(pdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pdPanelLayout.setVerticalGroup(
            pdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pdLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fnLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fnTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(dobLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dobTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        accountLabel.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        accountLabel.setForeground(new java.awt.Color(51, 51, 51));
        accountLabel.setText("Create An Account");

        jButtonAccountDash.setBackground(new java.awt.Color(0, 51, 0));
        jButtonAccountDash.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAccountDash.setText("Dashboard");
        jButtonAccountDash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountDashActionPerformed(evt);
            }
        });

        jButtonAccountAction.setBackground(new java.awt.Color(0, 51, 0));
        jButtonAccountAction.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAccountAction.setText("Track Impact");
        jButtonAccountAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountActionActionPerformed(evt);
            }
        });

        jButtonAccountLogin.setBackground(new java.awt.Color(0, 51, 0));
        jButtonAccountLogin.setForeground(new java.awt.Color(255, 255, 255));
        jButtonAccountLogin.setText("Log-in");
        jButtonAccountLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAccountLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAccountLayout = new javax.swing.GroupLayout(jPanelAccount);
        jPanelAccount.setLayout(jPanelAccountLayout);
        jPanelAccountLayout.setHorizontalGroup(
            jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccountLayout.createSequentialGroup()
                .addGroup(jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAccountLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAccountLayout.createSequentialGroup()
                                .addComponent(jButtonAccountLogin)
                                .addGap(194, 194, 194)
                                .addComponent(accountLabel))
                            .addGroup(jPanelAccountLayout.createSequentialGroup()
                                .addComponent(pdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(adPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelAccountLayout.createSequentialGroup()
                                .addGap(257, 257, 257)
                                .addComponent(accountButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelAccountLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonAccountDash)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonAccountAction)))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanelAccountLayout.setVerticalGroup(
            jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAccountLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAccountDash)
                    .addComponent(jButtonAccountAction))
                .addGroup(jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAccountLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(accountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAccountLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAccountLogin)))
                .addGap(59, 59, 59)
                .addGroup(jPanelAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(adPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pdPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(accountButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanelBackground.add(jPanelAccount, "card5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void jComboBoxTransportTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTransportTypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTransportTypeActionPerformed

    private void jTextFieldDistanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDistanceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDistanceActionPerformed

    private void jTextFieldEnergySavedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEnergySavedActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEnergySavedActionPerformed

    private void jTextFieldActionDescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldActionDescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldActionDescriptionActionPerformed

    private void jButtonDashSignupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDashSignupActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        // Show the user panel by using its name
        cardLayout.show(jPanelBackground, "jPanelAccount");
    }//GEN-LAST:event_jButtonDashSignupActionPerformed

    private void jButtonImpactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImpactActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelAction");
    }//GEN-LAST:event_jButtonImpactActionPerformed

    private void jButtonDashImpactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDashImpactActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelDash");
    }//GEN-LAST:event_jButtonDashImpactActionPerformed

    private void jButtonActionLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionLoginActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelLogin");
    }//GEN-LAST:event_jButtonActionLoginActionPerformed

    private void jButtonAccountDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountDashActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelDash");
    }//GEN-LAST:event_jButtonAccountDashActionPerformed

    private void jButtonAccountActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountActionActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelAction");
    }//GEN-LAST:event_jButtonAccountActionActionPerformed

    private void accountButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accountButton1ActionPerformed

    private void jButtonAccountLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAccountLoginActionPerformed
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        cardLayout.show(jPanelBackground, "jPanelLogin");
    }//GEN-LAST:event_jButtonAccountLoginActionPerformed

    private void jButtonLoginActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionActionPerformed
        // TODO add your handling code here:
        // Get the layout of the mainPanel, cast it to CardLayout
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        // Show the action panel by using its name
        cardLayout.show(jPanelBackground, "jPanelAction");
    }//GEN-LAST:event_jButtonLoginActionActionPerformed

    private void jButtonLoginDashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginDashActionPerformed
        // TODO add your handling code here:
        // Get the layout of the mainPanel, cast it to CardLayout
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        // Show the action panel by using its name
        cardLayout.show(jPanelBackground, "jPanelDash");
    }//GEN-LAST:event_jButtonLoginDashActionPerformed

    private void jButtonDashLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDashLoginActionPerformed
        // TODO add your handling code here:
        // Get the layout of the mainPanel, cast it to CardLayout
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        // Show the action panel by using its name
        cardLayout.show(jPanelBackground, "jPanelLogin");
    }//GEN-LAST:event_jButtonDashLoginActionPerformed

    private void accountButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountButtonActionPerformed
        // TODO add your handling code here:
        // Get the layout of the mainPanel, cast it to CardLayout
        CardLayout cardLayout = (CardLayout) jPanelBackground.getLayout();
        // Show the action panel by using its name
        cardLayout.show(jPanelBackground, "jPanelAccount");
    }//GEN-LAST:event_accountButtonActionPerformed

    private void jButtonSaveTransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveTransportActionPerformed
        // Get input values from text fields and combo box
        String transportType = (String) jComboBoxTransportType.getSelectedItem();
        String distanceText = jTextFieldDistance.getText();

        try {
            double distance = Double.parseDouble(distanceText);

            if (isEditing) {
                // Update the existing TransportAction object in the list
                TransportAction transportAction = (TransportAction) ecoActions.get(editingRowIndex);
                transportAction.setTransportType(transportType);
                transportAction.setDistanceTravelled(distance);

                // Reset editing mode
                isEditing = false;
                editingRowIndex = -1;

                // Reset the button text
                jButtonSaveTransport.setText("Save");
            } else {
                // Add a new TransportAction object to the list
                ecoActions.add(new TransportAction(transportType, distance));
            }

            // Refresh the table to reflect the changes
            refreshActionTable();

            // Clear input fields
            jTextFieldDistance.setText("");
            jComboBoxTransportType.setSelectedIndex(0);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for distance.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonSaveTransportActionPerformed

    private void jButtonEnergySaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnergySaveActionPerformed
        // Get input values from text fields
        String actionDescription = jTextFieldActionDescription.getText();
        String energySavedText = jTextFieldEnergySaved.getText();

        try {
            double energySaved = Double.parseDouble(energySavedText);

            if (isEditing) {
                // Update the existing EnergyAction object in the list
                EnergyAction energyAction = (EnergyAction) ecoActions.get(editingRowIndex);
                energyAction.setDescription(actionDescription);
                energyAction.setEnergySaved(energySaved);

                // Reset editing mode
                isEditing = false;
                editingRowIndex = -1;

                // Reset the button text
                jButtonEnergySave.setText("Save");
            } else {
                // Add a new EnergyAction object to the list
                ecoActions.add(new EnergyAction(actionDescription, energySaved));
            }

            // Refresh the table to reflect the changes
            refreshActionTable();

            // Clear input fields
            jTextFieldActionDescription.setText("");
            jTextFieldEnergySaved.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for energy saved.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButtonEnergySaveActionPerformed

    private void jButtonTableDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTableDeleteActionPerformed
        int selectedRow = jTableAction.getSelectedRow();
        //Error message if no row selected
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an action to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        } else {
            //removes the selected row from the array and table
            ecoActions.remove(selectedRow);
            refreshActionTable();
        }
    }//GEN-LAST:event_jButtonTableDeleteActionPerformed

    private void jButtonTableEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTableEditActionPerformed
        // Get the index of the selected row in the jTableAction
        int selectedRow = jTableAction.getSelectedRow();

        if (selectedRow == -1) {
            // Tells user no row is selected
            JOptionPane.showMessageDialog(this, "Please select an action to edit.", "No Selection", JOptionPane.WARNING_MESSAGE);
        } else {
            // Retrieves the object corresponding to the selected row
            EcoAction action = ecoActions.get(selectedRow);

            // Track the index of the row being edited
            editingRowIndex = selectedRow;
            // Set the editing mode flag to true
            isEditing = true;

            // Check if the action is a Transport Action
            if (action instanceof TransportAction transportAction) {
                // Set the transport type to the one selected in the combo box
                jComboBoxTransportType.setSelectedItem(transportAction.getTransportType());
                // Set the distance traveled to the one inputted in the text field
                jTextFieldDistance.setText(String.valueOf(transportAction.getDistanceTravelled()));
                // Optionally change the save button's label to indicate editing
                jButtonSaveTransport.setText("Update");
            } // Check if the action is an Energy Action
            else if (action instanceof EnergyAction energyAction) {
                // Set the description of the energy action in the corresponding text field
                jTextFieldActionDescription.setText(energyAction.getDescription());
                // Set the energy saved in the corresponding text field
                jTextFieldEnergySaved.setText(String.valueOf(energyAction.getEnergySaved()));
                // Optionally change the save button's label to indicate editing
                jButtonEnergySave.setText("Update");
            }
        }
    }//GEN-LAST:event_jButtonTableEditActionPerformed

    private void jButtonActionChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActionChartActionPerformed
        // Create the chart
        CategoryChart chart = createImpactChart();

        if (chart != null) {
            System.out.println("Displaying Impact Chart");

            // Create a new JFrame
            JFrame chartFrame = new JFrame("Impact Chart");
            chartFrame.setSize(800, 600);
            chartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Add the chart panel to the frame
            JPanel chartPanel = new XChartPanel<>(chart);
            chartFrame.add(chartPanel);
            chartFrame.setVisible(true);
        } else {
            System.out.println("Chart creation returned null, no data available");
        }
    }//GEN-LAST:event_jButtonActionChartActionPerformed

    private void jButtonEnergyClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnergyClearActionPerformed
        // TODO add your handling code here:
        jTextFieldActionDescription.setText("");
        jTextFieldEnergySaved.setText("");
        isEditing = false; // Exit editing mode
        editingRowIndex = -1; // Clear the editing row index
    }//GEN-LAST:event_jButtonEnergyClearActionPerformed

    private void jButtonClearTransportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearTransportActionPerformed
        jComboBoxTransportType.setSelectedIndex(0);
        jTextFieldDistance.setText("");
        isEditing = false; // Exit editing mode
        editingRowIndex = -1; // Clear the editing row index
    }//GEN-LAST:event_jButtonClearTransportActionPerformed

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
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accountButton;
    private javax.swing.JButton accountButton1;
    private javax.swing.JLabel accountLabel;
    private javax.swing.JLabel adLabel;
    private javax.swing.JPanel adPanel7;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JTextField dobTF;
    private javax.swing.JLabel fnLabel;
    private javax.swing.JTextField fnTF;
    private javax.swing.JButton jButtonAccountAction;
    private javax.swing.JButton jButtonAccountDash;
    private javax.swing.JButton jButtonAccountLogin;
    private javax.swing.JButton jButtonActionChart;
    private javax.swing.JButton jButtonActionLogin;
    private javax.swing.JButton jButtonClearTransport;
    private javax.swing.JButton jButtonDashImpact;
    private javax.swing.JButton jButtonDashLogin;
    private javax.swing.JButton jButtonDashSignup;
    private javax.swing.JButton jButtonEnergyClear;
    private javax.swing.JButton jButtonEnergySave;
    private javax.swing.JButton jButtonImpact;
    private javax.swing.JButton jButtonLoginAction;
    private javax.swing.JButton jButtonLoginDash;
    private javax.swing.JButton jButtonSaveTransport;
    private javax.swing.JButton jButtonTableDelete;
    private javax.swing.JButton jButtonTableEdit;
    private javax.swing.JComboBox<String> jComboBoxTransportType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelAuthor;
    private javax.swing.JLabel jLabelDistanceTravelled;
    private javax.swing.JLabel jLabelEcoActionPlanner;
    private javax.swing.JLabel jLabelEnergySaved;
    private javax.swing.JLabel jLabelModeTransport;
    private javax.swing.JLabel jLabelWelcome;
    private javax.swing.JPanel jPanelAccount;
    private javax.swing.JPanel jPanelAction;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelDash;
    private javax.swing.JPanel jPanelLogin;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAction;
    private javax.swing.JTextField jTextFieldActionDescription;
    private javax.swing.JTextField jTextFieldDistance;
    private javax.swing.JTextField jTextFieldEnergySaved;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel loginLabel;
    private javax.swing.JLabel orLabel;
    private javax.swing.JLabel passwordLabel7;
    private javax.swing.JLabel passwordLabel8;
    private javax.swing.JPasswordField passwordPF;
    private javax.swing.JTextField passwordTF7;
    private javax.swing.JLabel pdLabel;
    private javax.swing.JPanel pdPanel;
    private javax.swing.JLabel usernameLabel7;
    private javax.swing.JLabel usernameLabel8;
    private javax.swing.JTextField usernameTF7;
    private javax.swing.JTextField usernameTF8;
    // End of variables declaration//GEN-END:variables
}
