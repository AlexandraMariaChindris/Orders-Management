package Presentation;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

/**
 * Clasa View reprezintă interfața utilizatorului în cadrul aplicației și gestionează
 * afișarea și interacțiunea utilizatorului cu elementele grafice.
 */
public class View extends JFrame {
    //main frame
    private JPanel mainPanel;
    private JButton clientMainButton;
    private JButton managerMainButton;

    //fereastra de autentificare
    private JFrame dialogFrame;
    private JTextField userField;
    private JPasswordField passwordField;

    //tabel clienti
    private JTable clientsMainTable = new JTable(); //tabelul vazut de clienti cu produse
    private JTable clientsManagementTable = new JTable();
    private JTable productsManagementTable = new JTable();
    private JTable ordersManagementTable = new JTable();
    private JTable billsTable = new JTable();

    ///frame-uri
    private JFrame clientsManagementFrame;
    private JFrame productsManagementFrame;
    private JFrame productsOpFrame;
    private JFrame clientsOpFrame;
    private JFrame ordersManagementFrame;
    private JFrame ordersUpdateFrame;
    private JFrame ordersInsertFrame;
    private JFrame billsFrame;

    ///////////////////////////
    private Controller controller = new Controller(this);

    //insert, update clients table
    private JTextField nameField;
    private JTextField addressField;
    private JTextField emailField;

    //insert, update products table
    private JTextField numeProdusField;
    private JTextField cantitateDisponibilaField;
    private JTextField pretField;
    private JTextField numeProdusOrderField;
    private JTextField cantitateComandataField;
    private JComboBox clientsComboBox;
    private JComboBox productsComboBox;

    private JTextField cantitateComandataInsertField;


    /**
     * Construiește o nouă instanță a clasei View cu un nume specificat.
     *
     * @param name Numele ferestrei principale.
     */
    public View(String name) {
        super(name);
        this.prepareGui();
    }

    /**
     * Inițializează interfața grafică a aplicației.
     */
    public void prepareGui(){
        //this.setSize(500, 500);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        BoxLayout mainBoxlayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);
        mainPanel.setLayout(mainBoxlayout);
        mainPanel.setBorder(new EmptyBorder(new Insets(30, 50, 50, 50)));
        mainPanel.setBackground(new Color(255, 150, 255));

        JPanel firstPanel = new JPanel();//panel pentru titlu
        BoxLayout firstBoxlayout = new BoxLayout(firstPanel, BoxLayout.Y_AXIS);
        firstPanel.setLayout(firstBoxlayout);
        firstPanel.setBackground(new Color(255, 150, 255));

        JLabel titleLabel = new JLabel("Orders Management");
        titleLabel.setFont(titleLabel.getFont().deriveFont(16.0f));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        firstPanel.add(titleLabel);

        JPanel secondPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));//panel pentru client_btn si manager_btn
        secondPanel.setBackground(new Color(255, 150, 255));

        //client_btn
        clientMainButton = new JButton("Client");
        clientMainButton.setPreferredSize(new Dimension(150, 30));
        clientMainButton.setBorder(new LineBorder(Color.magenta, 2));
        clientMainButton.setFont(clientMainButton.getFont().deriveFont(13.0f));
        clientMainButton.setBackground(Color.PINK);
        clientMainButton.setFocusPainted(false);
        clientMainButton.setActionCommand("CLIENT_BTN");
        clientMainButton.addActionListener(controller);
        secondPanel.add(clientMainButton);

        //manager_btn
        managerMainButton = new JButton("Manager");
        managerMainButton.setPreferredSize(new Dimension(150, 30));
        managerMainButton.setBorder(new LineBorder(Color.magenta, 2));
        managerMainButton.setFont(managerMainButton.getFont().deriveFont(13.0f));
        managerMainButton.setBackground(Color.PINK);
        managerMainButton.setFocusPainted(false);
        managerMainButton.setActionCommand("MANAGER_BTN");
        managerMainButton.addActionListener(controller);
        secondPanel.add(managerMainButton);

        mainPanel.add(firstPanel);
        mainPanel.add(secondPanel);
        this.setContentPane(mainPanel);
    }


    /**
     * Deschide fereastra de manager.
     */
    public void openManagerWindow(){
        JFrame managerFrame = new JFrame("Manager Window");
        JPanel managerPanel = new JPanel(new GridLayout(4, 2, 2, 10));
        managerPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        managerPanel.setBackground(new Color(170, 100, 255));

        managerFrame.getContentPane().add(managerPanel);
        managerFrame.setSize(300, 180);
        managerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        managerFrame.setLocationRelativeTo(null);
        managerFrame.setVisible(true);

        JButton clientsButton = new JButton("Clients Management");
        clientsButton.setPreferredSize(new Dimension(150, 30));
        clientsButton.setBorder(new LineBorder(Color.magenta, 2));
        clientsButton.setFont(clientsButton.getFont().deriveFont(13.0f));
        clientsButton.setBackground(Color.PINK);
        clientsButton.setFocusPainted(false);
        clientsButton.setActionCommand("CLIENTS_MANAGEMENT");
        clientsButton.addActionListener(controller);

        JButton productsButton = new JButton("Products Management");
        productsButton.setPreferredSize(new Dimension(150, 30));
        productsButton.setBorder(new LineBorder(Color.magenta, 2));
        productsButton.setFont(productsButton.getFont().deriveFont(13.0f));
        productsButton.setBackground(Color.PINK);
        productsButton.setFocusPainted(false);
        productsButton.setActionCommand("PRODUCTS_MANAGEMENT");
        productsButton.addActionListener(controller);

        JButton ordersButton = new JButton("Orders Management");
        ordersButton.setPreferredSize(new Dimension(150, 30));
        ordersButton.setBorder(new LineBorder(Color.magenta, 2));
        ordersButton.setFont(ordersButton.getFont().deriveFont(13.0f));
        ordersButton.setBackground(Color.PINK);
        ordersButton.setFocusPainted(false);
        ordersButton.setActionCommand("ORDERS_MANAGEMENT");
        ordersButton.addActionListener(controller);

        JButton billsButton = new JButton("Bills");
        billsButton.setPreferredSize(new Dimension(150, 30));
        billsButton.setBorder(new LineBorder(Color.magenta, 2));
        billsButton.setFont(ordersButton.getFont().deriveFont(13.0f));
        billsButton.setBackground(Color.PINK);
        billsButton.setFocusPainted(false);
        billsButton.setActionCommand("BILLS");
        billsButton.addActionListener(controller);


        managerPanel.add(clientsButton);
        managerPanel.add(productsButton);
        managerPanel.add(ordersButton);
        managerPanel.add(billsButton);

    }


    /**
     * Setează aspectul butonului pentru client.
     */
    public void setClientMainButton() {
        clientMainButton.setBackground(new Color(170, 100, 255));
        clientMainButton.setForeground(Color.WHITE);
    }


    /**
     * Setează aspectul butonului pentru manager.
     */
    public void setManagerMainButton() {
        managerMainButton.setBackground(new Color(170, 100, 255));
        managerMainButton.setForeground(Color.WHITE);
    }

    /**
     * Deschide fereastra de autentificare.
     */
    public void openAutentificationWindow() {
        dialogFrame = new JFrame("Autentificare Manager");
        JPanel dialogPanel = new JPanel(new GridLayout(3, 2, 2, 10));
        dialogPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        dialogPanel.setBackground(new Color(170, 100, 255));

        JLabel userLabel = new JLabel("   Utilizator:");
        userLabel.setForeground(Color.WHITE);
        userField = new JTextField(20);
        JLabel passwordLabel = new JLabel("   Parolă:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Autentificare");
        loginButton.setActionCommand("LOGIN_BTN");
        loginButton.addActionListener(controller);

        dialogPanel.add(userLabel);
        dialogPanel.add(userField);
        dialogPanel.add(passwordLabel);
        dialogPanel.add(passwordField);
        dialogPanel.add(new JLabel());
        dialogPanel.add(loginButton);

        dialogFrame.getContentPane().add(dialogPanel);
        dialogFrame.setSize(300, 150);
        dialogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        dialogFrame.setLocationRelativeTo(null);
        dialogFrame.setVisible(true);
    }

    /**
     * Deschide fereastra pentru client.
     */
    public void openClientWindow() {
        JFrame clientsFrame = new JFrame("Make-up products");
        clientsFrame.setSize(470, 300);
        clientsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientsFrame.setLocationRelativeTo(null);
        clientsFrame.setVisible(true);
        clientsFrame.getContentPane().setBackground(Color.PINK);

        JPanel panel = new JPanel(new BorderLayout());
        clientsMainTable.setEnabled(false);
        clientsMainTable.setBackground(new Color(255, 150, 255));
        clientsMainTable.getTableHeader().setBackground(new Color(255, 70, 255));
        panel.add(new JScrollPane(clientsMainTable), BorderLayout.CENTER);
        clientsFrame.getContentPane().add(panel);
    }

    /**
     * Deschide fereastra de facturi.
     */
    public void openBillWindow() {
        JFrame billsFrame = new JFrame("Bills");
        billsFrame.setSize(470, 300);
        billsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        billsFrame.setLocationRelativeTo(null);
        billsFrame.setVisible(true);
        billsFrame.getContentPane().setBackground(Color.PINK);

        JPanel panel = new JPanel(new BorderLayout());
        billsTable.setEnabled(false);
        billsTable.setBackground(new Color(255, 150, 255));
        billsTable.getTableHeader().setBackground(new Color(255, 70, 255));
        panel.add(new JScrollPane(billsTable), BorderLayout.CENTER);
        billsFrame.getContentPane().add(panel);
    }

    public JTable getClientsMainTable() {
        return clientsMainTable;
    }

    public JTable getClientsManagementTable() {
        return clientsManagementTable;
    }

    public JTable getProductsManagementTable() {
        return productsManagementTable;
    }

    public JTable getOrdersManagementTable() {
        return ordersManagementTable;
    }

    public JTextField getUserField() {
        return userField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JFrame getDialogFrame() {
        return dialogFrame;
    }

    /**
     * Deschide fereastra de gestionare a clienților.
     */
    public void openClientsManagementWindow() {
        clientsManagementFrame = new JFrame("Clients Management");
        clientsManagementFrame.setSize(600, 430);
        clientsManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientsManagementFrame.setLocationRelativeTo(null);
        clientsManagementFrame.setVisible(true);
        clientsManagementFrame.getContentPane().setBackground(Color.PINK);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setPreferredSize(new Dimension(150, 400));
        panel.setBorder(new EmptyBorder(new Insets(100, 0, 0, 0)));
        panel.setBackground(new Color(255, 150, 255));

        JButton insertButton = new JButton("Insert client");
        JButton updateButton = new JButton("Update client");
        JButton deleteButton = new JButton("Delete client");


        insertButton.setPreferredSize(new Dimension(120, 30));
        insertButton.setBorder(new LineBorder(Color.magenta, 2));
        insertButton.setFont(insertButton.getFont().deriveFont(13.0f));
        insertButton.setBackground(Color.PINK);
        insertButton.setFocusPainted(false);
        insertButton.setActionCommand("INSERT_CLIENT");
        insertButton.addActionListener(controller);

        updateButton.setPreferredSize(new Dimension(120, 30));
        updateButton.setBorder(new LineBorder(Color.magenta, 2));
        updateButton.setFont(updateButton.getFont().deriveFont(13.0f));
        updateButton.setBackground(Color.PINK);
        updateButton.setFocusPainted(false);
        updateButton.setActionCommand("UPDATE_CLIENT");
        updateButton.addActionListener(controller);

        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.setBorder(new LineBorder(Color.magenta, 2));
        deleteButton.setFont(deleteButton.getFont().deriveFont(13.0f));
        deleteButton.setBackground(Color.PINK);
        deleteButton.setFocusPainted(false);
        deleteButton.setActionCommand("DELETE_CLIENT");
        deleteButton.addActionListener(controller);



        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        clientsManagementTable.setBackground(new Color(255, 150, 255));
        clientsManagementTable.getTableHeader().setBackground(new Color(255, 70, 255));

        clientsManagementFrame.getContentPane().setBackground(new Color(255, 150, 255));
        clientsManagementFrame.getContentPane().add(panel, BorderLayout.WEST);
        clientsManagementFrame.getContentPane().add(new JScrollPane(clientsManagementTable), BorderLayout.CENTER);

    }

    /**
     * Deschide fereastra de gestionare a produselor.
     */
    public void openProductsManagementWindow() {
        productsManagementFrame = new JFrame("Products Management");
        productsManagementFrame.setSize(600, 430);
        productsManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productsManagementFrame.setLocationRelativeTo(null);
        productsManagementFrame.setVisible(true);
        productsManagementFrame.getContentPane().setBackground(Color.PINK);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setPreferredSize(new Dimension(150, 400));
        panel.setBorder(new EmptyBorder(new Insets(100, 0, 0, 0)));
        panel.setBackground(new Color(255, 150, 255));

        JButton insertButton = new JButton("Insert product");
        JButton updateButton = new JButton("Update product");
        JButton deleteButton = new JButton("Delete product");


        insertButton.setPreferredSize(new Dimension(120, 30));
        insertButton.setBorder(new LineBorder(Color.magenta, 2));
        insertButton.setFont(insertButton.getFont().deriveFont(13.0f));
        insertButton.setBackground(Color.PINK);
        insertButton.setFocusPainted(false);
        insertButton.setActionCommand("INSERT_PRODUCT");
        insertButton.addActionListener(controller);

        updateButton.setPreferredSize(new Dimension(120, 30));
        updateButton.setBorder(new LineBorder(Color.magenta, 2));
        updateButton.setFont(updateButton.getFont().deriveFont(13.0f));
        updateButton.setBackground(Color.PINK);
        updateButton.setFocusPainted(false);
        updateButton.setActionCommand("UPDATE_PRODUCT");
        updateButton.addActionListener(controller);

        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.setBorder(new LineBorder(Color.magenta, 2));
        deleteButton.setFont(deleteButton.getFont().deriveFont(13.0f));
        deleteButton.setBackground(Color.PINK);
        deleteButton.setFocusPainted(false);
        deleteButton.setActionCommand("DELETE_PRODUCT");
        deleteButton.addActionListener(controller);



        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        productsManagementTable.setBackground(new Color(255, 150, 255));
        productsManagementTable.getTableHeader().setBackground(new Color(255, 70, 255));

        productsManagementFrame.getContentPane().setBackground(new Color(255, 150, 255));
        productsManagementFrame.getContentPane().add(panel, BorderLayout.WEST);
        productsManagementFrame.getContentPane().add(new JScrollPane(productsManagementTable), BorderLayout.CENTER);

    }

    /**
     * Deschide fereastra de gestionare a comenzilor.
     */
    public void openOrdersManagementWindow() {
        ordersManagementFrame = new JFrame("Orders Management");
        ordersManagementFrame.setSize(600, 430);
        ordersManagementFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ordersManagementFrame.setLocationRelativeTo(null);
        ordersManagementFrame.setVisible(true);
        ordersManagementFrame.getContentPane().setBackground(Color.PINK);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel.setPreferredSize(new Dimension(150, 400));
        panel.setBorder(new EmptyBorder(new Insets(100, 0, 0, 0)));
        panel.setBackground(new Color(255, 150, 255));

        JButton insertButton = new JButton("Insert order");
        JButton updateButton = new JButton("Update order");
        JButton deleteButton = new JButton("Delete order");


        insertButton.setPreferredSize(new Dimension(120, 30));
        insertButton.setBorder(new LineBorder(Color.magenta, 2));
        insertButton.setFont(insertButton.getFont().deriveFont(13.0f));
        insertButton.setBackground(Color.PINK);
        insertButton.setFocusPainted(false);
        insertButton.setActionCommand("INSERT_ORDER");
        insertButton.addActionListener(controller);

        updateButton.setPreferredSize(new Dimension(120, 30));
        updateButton.setBorder(new LineBorder(Color.magenta, 2));
        updateButton.setFont(updateButton.getFont().deriveFont(13.0f));
        updateButton.setBackground(Color.PINK);
        updateButton.setFocusPainted(false);
        updateButton.setActionCommand("UPDATE_ORDER");
        updateButton.addActionListener(controller);

        deleteButton.setPreferredSize(new Dimension(120, 30));
        deleteButton.setBorder(new LineBorder(Color.magenta, 2));
        deleteButton.setFont(deleteButton.getFont().deriveFont(13.0f));
        deleteButton.setBackground(Color.PINK);
        deleteButton.setFocusPainted(false);
        deleteButton.setActionCommand("DELETE_ORDER");
        deleteButton.addActionListener(controller);

        panel.add(insertButton);
        panel.add(updateButton);
        panel.add(deleteButton);

        ordersManagementTable.setBackground(new Color(255, 150, 255));
        ordersManagementTable.getTableHeader().setBackground(new Color(255, 70, 255));

        ordersManagementFrame.getContentPane().setBackground(new Color(255, 150, 255));
        ordersManagementFrame.getContentPane().add(panel, BorderLayout.WEST);
        ordersManagementFrame.getContentPane().add(new JScrollPane(ordersManagementTable), BorderLayout.CENTER);

    }

    public JFrame getClientsManagementFrame() {
        return clientsManagementFrame;
    }

    public void setClientsManagementTable(JTable table) {
        clientsManagementTable = table;
    }

    /**
     * Deschide fereastra pentru inserarea sau actualizarea unui client.
     *
     * @param op Operația de efectuat: "insert" pentru inserare, "update" pentru actualizare.
     */
    public void openInsertUpdateClientsWindow(String op) {
        clientsOpFrame = new JFrame();
        JPanel dialogPanel = new JPanel(new GridLayout(4, 2, 2, 10));
        dialogPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        dialogPanel.setBackground(new Color(170, 100, 255));

        JLabel nameLabel = new JLabel("   Nume:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField(20);

        JLabel addressLabel = new JLabel("   Adresă:");
        addressLabel.setForeground(Color.WHITE);
        addressField = new JTextField(20);

        JLabel emailLabel = new JLabel("   Email:");
        emailLabel.setForeground(Color.WHITE);
        emailField = new JTextField(20);

        JButton button = new JButton("Actualizeaza");
        if(Objects.equals(op, "update"))
            button.setActionCommand("UPDATE_CLIENT_BTN");
        else
            button.setActionCommand("INSERT_CLIENT_BTN");
        button.addActionListener(controller);

        dialogPanel.add(nameLabel);
        dialogPanel.add(nameField);
        dialogPanel.add(addressLabel);
        dialogPanel.add(addressField);
        dialogPanel.add(emailLabel);
        dialogPanel.add(emailField);
        dialogPanel.add(new JLabel());
        dialogPanel.add(button);

        clientsOpFrame.getContentPane().add(dialogPanel);
        clientsOpFrame.setSize(300, 180);
        clientsOpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        clientsOpFrame.setLocationRelativeTo(null);
        clientsOpFrame.setVisible(true);
    }

    /**
     * Deschide fereastra pentru inserarea sau actualizarea unui produs.
     *
     * @param op Operația de efectuat: "insert" pentru inserare, "update" pentru actualizare.
     */
    public void openInsertUpdateProductsWindow(String op) {
        productsOpFrame = new JFrame();
        JPanel dialogPanel = new JPanel(new GridLayout(4, 2, 2, 10));
        dialogPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        dialogPanel.setBackground(new Color(170, 100, 255));

        JLabel numeProdusLabel = new JLabel("   Nume produs:");
        numeProdusLabel.setForeground(Color.WHITE);
        numeProdusField = new JTextField(20);

        JLabel cantitateLabel = new JLabel("   Cantitate:");
        cantitateLabel.setForeground(Color.WHITE);
        cantitateDisponibilaField = new JTextField(20);

        JLabel pretLabel = new JLabel("   Pret:");
        pretLabel.setForeground(Color.WHITE);
        pretField = new JTextField(20);

        JButton button = new JButton("Actualizeaza");
        if(Objects.equals(op, "update"))
            button.setActionCommand("UPDATE_PRODUCT_BTN");
        else
            button.setActionCommand("INSERT_PRODUCT_BTN");
        button.addActionListener(controller);

        dialogPanel.add(numeProdusLabel);
        dialogPanel.add(numeProdusField);
        dialogPanel.add(cantitateLabel);
        dialogPanel.add(cantitateDisponibilaField);
        dialogPanel.add(pretLabel);
        dialogPanel.add(pretField);
        dialogPanel.add(new JLabel());
        dialogPanel.add(button);

        productsOpFrame.getContentPane().add(dialogPanel);
        productsOpFrame.setSize(300, 180);
        productsOpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        productsOpFrame.setLocationRelativeTo(null);
        productsOpFrame.setVisible(true);
    }

    /**
     * Deschide fereastra pentru actualizarea unei comenzi existente.
     */
    public void openUpdateOrdersWindow() {
        ordersUpdateFrame = new JFrame();
        JPanel dialogPanel = new JPanel(new GridLayout(3, 2, 2, 10));
        dialogPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        dialogPanel.setBackground(new Color(170, 100, 255));

        JLabel numeProdusLabel = new JLabel("   Nume produs:");
        numeProdusLabel.setForeground(Color.WHITE);
        numeProdusOrderField = new JTextField(20);

        JLabel cantitateLabel = new JLabel("   Cantitate comandata:");
        cantitateLabel.setForeground(Color.WHITE);
        cantitateComandataField = new JTextField(20);

        JButton button = new JButton("Actualizeaza");
        button.setActionCommand("UPDATE_ORDER_BTN");
        button.addActionListener(controller);

        dialogPanel.add(numeProdusLabel);
        dialogPanel.add(numeProdusOrderField);
        dialogPanel.add(cantitateLabel);
        dialogPanel.add(cantitateComandataField);
        dialogPanel.add(new JLabel());
        dialogPanel.add(button);

        ordersUpdateFrame.getContentPane().add(dialogPanel);
        ordersUpdateFrame.setSize(300, 180);
        ordersUpdateFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ordersUpdateFrame.setLocationRelativeTo(null);
        ordersUpdateFrame.setVisible(true);
    }

    /**
     * Deschide fereastra pentru inserarea unei noi comenzi.
     *
     * @param clients Lista de clienți disponibili pentru selecție.
     * @param products Lista de produse disponibile pentru selecție.
     */
    public void openInsertOrderWindow(String[] clients, String[] products) {

        ordersInsertFrame = new JFrame();
        JPanel dialogPanel = new JPanel(new GridLayout(4, 2, 2, 10));
        dialogPanel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
        dialogPanel.setBackground(new Color(170, 100, 255));

        JLabel clientLabel = new JLabel("   Client:");
        clientLabel.setForeground(Color.WHITE);
        String[] c = clients;
        clientsComboBox = new JComboBox(c);

        JLabel numeProdusLabel = new JLabel("   Produs:");
        numeProdusLabel.setForeground(Color.WHITE);
        String[] p = products;
        productsComboBox = new JComboBox(p);

        JLabel cantitateLabel = new JLabel("   Cantitate:");
        cantitateLabel.setForeground(Color.WHITE);
        cantitateComandataInsertField = new JTextField(20);

        JButton button = new JButton("Actualizeaza");
        button.setActionCommand("INSERT_ORDER_BTN");
        button.addActionListener(controller);

        dialogPanel.add(clientLabel);
        dialogPanel.add(clientsComboBox);
        dialogPanel.add(numeProdusLabel);
        dialogPanel.add(productsComboBox);
        dialogPanel.add(cantitateLabel);
        dialogPanel.add(cantitateComandataInsertField);
        dialogPanel.add(new JLabel());
        dialogPanel.add(button);

        ordersInsertFrame.getContentPane().add(dialogPanel);
        ordersInsertFrame.setSize(300, 180);
        ordersInsertFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ordersInsertFrame.setLocationRelativeTo(null);
        ordersInsertFrame.setVisible(true);
    }

    /**
     * Returnează numele introdus în câmpul pentru nume.
     *
     * @return Textul introdus în câmpul pentru nume.
     */
    public JTextField getNameField() {
        return nameField;
    }


    /**
     * Returnează adresa introdusă în câmpul pentru adresă.
     *
     * @return Textul introdus în câmpul pentru adresă.
     */
    public JTextField getAddressField() {
        return addressField;
    }


    /**
     * Returnează adresa de email introdusă în câmpul corespunzător.
     *
     * @return Textul introdus în câmpul pentru adresa de email.
     */
    public JTextField getEmailField() {
        return emailField;
    }

    public Component getProductsManagementFrame() {
        return productsManagementFrame;
    }

    public void setProductsManagementTable(JTable productsManagementTable) {
        this.productsManagementTable = productsManagementTable;
    }

    public JTextField getNumeProdusField() {
        return numeProdusField;
    }

    public JTextField getCantitateDisponibilaField() {
        return cantitateDisponibilaField;
    }

    public JTextField getPretField() {
        return pretField;
    }

    public JFrame getProductsOpFrame() {
        return productsOpFrame;
    }

    public JFrame getClientsOpFrame() {
        return clientsOpFrame;
    }

    public void setOrdersManagementTable(JTable ordersManagementTable) {
        this.ordersManagementTable = ordersManagementTable;
    }

    public JTable getBillsTable() {
        return billsTable;
    }

    public void setBillsTable(JTable billsTable) {
        this.billsTable = billsTable;
    }

    public JFrame getOrdersManagementFrame() {
        return ordersManagementFrame;
    }

    public JTextField getNumeProdusOrderField() {
        return numeProdusOrderField;
    }

    public JTextField getCantitateComandataField() {
        return cantitateComandataField;
    }

    public JFrame getOrdersUpdateFrame() {
        return ordersUpdateFrame;
    }

    public JFrame getOrdersInsertFrame() {
        return ordersInsertFrame;
    }

    public JComboBox getClientsComboBox() {
        return clientsComboBox;
    }

    public JComboBox getProductsComboBox() {
        return productsComboBox;
    }

    public JTextField getCantitateComandataInsertField() {
        return cantitateComandataInsertField;
    }



}

