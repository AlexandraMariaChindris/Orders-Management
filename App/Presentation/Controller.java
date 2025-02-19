package Presentation;

import BusinessLogic.BillBLL;
import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import BusinessLogic.Product_OrderBLL;
import Model.Bill;
import Model.Client;
import Model.Product;
import Model.Product_Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * Clasa Controller acționează ca un controler pentru interacțiunea dintre vizualizare și logica operatiilor.
 * Implementează interfața ActionListener pentru a răspunde la evenimentele acțiunii utilizatorului.
 */
public class Controller implements ActionListener {

    private View view;
    private Client client;
    private Product product;
    private Product_Order order;

    /**
     * Construiește un nou Controller cu vizualizarea specificată.
     *
     * @param v Vizualizarea asociată acestui controler.
     */
    public Controller(View v){
        this.view = v;
    }

    /**
     * Gestionează evenimentele de acțiune generate de interacțiunea utilizatorului.
     *
     * @param e Obiectul ActionEvent care reprezintă acțiunea.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        ClientBLL clientBLL = new ClientBLL();
        ProductBLL productBLL = new ProductBLL();
        Product_OrderBLL product_orderBLL = new Product_OrderBLL();
        BillBLL billBLL = new BillBLL();

        if (Objects.equals(command, "CLIENT_BTN")) {
            //clientii vad doar lista cu produse
            view.setClientMainButton();
            List<Product> products = productBLL.findAllProducts();
            productBLL.populateProductTable(products, view.getClientsMainTable());
            view.openClientWindow();

        } else
            if (Objects.equals(command, "MANAGER_BTN")) {
            view.setManagerMainButton();
            view.openAutentificationWindow();
        } else
            if (Objects.equals(command, "LOGIN_BTN")) {
            String username = view.getUserField().getText();
            String password = new String(view.getPasswordField().getPassword());

            if (username.equals("admin") && password.equals("admin")) {
                view.getDialogFrame().dispose(); // Închidem fereastra de dialog după autentificare
                view.openManagerWindow();
            } else {
                JOptionPane.showMessageDialog(view.getDialogFrame(), "Autentificare eșuată. Vă rugăm să încercați din nou.");
            }
        } else
            if (Objects.equals(command, "CLIENTS_MANAGEMENT")) {
            //lista cu clienti si operatiile pe clienti
            List<Client> clients = clientBLL.findAllClients();
            clientBLL.populateClientTable(clients, view.getClientsManagementTable());
            view.openClientsManagementWindow();
        } else
            if (Objects.equals(command, "PRODUCTS_MANAGEMENT")) {
            //lista cu produse si operatiile pe produse
            List<Product> products = productBLL.findAllProducts();
            productBLL.populateProductTable(products, view.getProductsManagementTable());
            view.openProductsManagementWindow();
        } else
            if (Objects.equals(command, "ORDERS_MANAGEMENT")) {
            List<Product_Order> orders = product_orderBLL.findAllOrders();
            product_orderBLL.populateOrdersTable(orders, view.getOrdersManagementTable());
            view.openOrdersManagementWindow();
        } else
            if (Objects.equals(command, "INSERT_CLIENT")) {
            view.openInsertUpdateClientsWindow("insert");
        } else
            if (Objects.equals(command, "UPDATE_CLIENT")) {
            int selectedRow = view.getClientsManagementTable().getSelectedRow();
            if (selectedRow != -1) {
                int id_client = (int) view.getClientsManagementTable().getValueAt(selectedRow, 0);
                String name = (String) view.getClientsManagementTable().getValueAt(selectedRow, 1);
                String address = (String) view.getClientsManagementTable().getValueAt(selectedRow, 2);
                String email = (String) view.getClientsManagementTable().getValueAt(selectedRow, 3);
                client = new Client(id_client, name, address, email);
                view.openInsertUpdateClientsWindow("update");
            } else {
                JOptionPane.showMessageDialog(view.getClientsManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
            if (Objects.equals(command, "DELETE_CLIENT")) {
            int selectedRow = view.getClientsManagementTable().getSelectedRow();
            if (selectedRow != -1) {
                int id_client = (int) view.getClientsManagementTable().getValueAt(selectedRow, 0);
                clientBLL.deleteById(id_client);
                List<Client> clients = clientBLL.findAllClients();
                clientBLL.populateClientTable(clients, view.getClientsManagementTable());
                view.setClientsManagementTable(view.getClientsManagementTable());

            } else {
                JOptionPane.showMessageDialog(view.getClientsManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
            if (Objects.equals(command, "UPDATE_CLIENT_BTN")) {
            String name = view.getNameField().getText();
            String address = view.getAddressField().getText();
            String email = view.getEmailField().getText();
            if (Objects.equals(name, ""))
                name = client.getNume();
            if (Objects.equals(address, ""))
                address = client.getAdresa();
            if (Objects.equals(email, ""))
                email = client.getEmail();

            List<Object> values = new ArrayList<>();
            values.add(name);
            values.add(address);
            values.add(email);

            int input_ok = 1;
            try {
                clientBLL.updateClient(client, values);
            } catch (IllegalAccessException ex) {
                //todo mesaj pentru email invalid
                input_ok = 0;
                JOptionPane.showMessageDialog(null, "Eroare: " + ex.getMessage(), "Eroare", JOptionPane.ERROR_MESSAGE);
               // System.out.println(ex.getMessage());
                //throw new RuntimeException(ex);
            }
            if(input_ok == 1)
                view.getClientsOpFrame().dispose();
            List<Client> clients = clientBLL.findAllClients();
            clientBLL.populateClientTable(clients, view.getClientsManagementTable());
            view.setClientsManagementTable(view.getClientsManagementTable());
        } else
            if (Objects.equals(command, "INSERT_CLIENT_BTN")) {
            String name = view.getNameField().getText();
            String address = view.getAddressField().getText();
            String email = view.getEmailField().getText();
            int input_ok = 1;
            if (Objects.equals(name, "") || Objects.equals(address, "") || Objects.equals(email, ""))
                JOptionPane.showMessageDialog(view.getClientsManagementFrame(), "Incomplete data!", "Error", JOptionPane.ERROR_MESSAGE);
            else {
                try {
                    clientBLL.insertClient(new Client(name, address, email));
                } catch (IllegalAccessException ex) {
                    ///todo de afisat fereastra de email invalid
                    input_ok = 0;
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(view.getClientsManagementFrame(), "Email invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    //throw new RuntimeException(ex);
                }
            }
            if(input_ok == 1)
                view.getClientsOpFrame().dispose();
            List<Client> clients = clientBLL.findAllClients();
            clientBLL.populateClientTable(clients, view.getClientsManagementTable());
            view.setClientsManagementTable(view.getClientsManagementTable());
        } else
            if (Objects.equals(command, "INSERT_PRODUCT")) {
            view.openInsertUpdateProductsWindow("insert");
        } else
            if (Objects.equals(command, "UPDATE_PRODUCT")) {
            int selectedRow = view.getProductsManagementTable().getSelectedRow();
            if (selectedRow != -1) {
                int id_product = (int) view.getProductsManagementTable().getValueAt(selectedRow, 0);
                String name = (String) view.getProductsManagementTable().getValueAt(selectedRow, 1);
                int cantitate = (int) view.getProductsManagementTable().getValueAt(selectedRow, 2);
                int pret = (int) view.getProductsManagementTable().getValueAt(selectedRow, 3);
                product = new Product(id_product, name, cantitate, pret);
                view.openInsertUpdateProductsWindow("update");
            } else {
                JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
            if (Objects.equals(command, "DELETE_PRODUCT")) {
            int selectedRow = view.getProductsManagementTable().getSelectedRow();
            if (selectedRow != -1) {
                int id_product = (int) view.getProductsManagementTable().getValueAt(selectedRow, 0);
                productBLL.deleteById(id_product);
                List<Product> products = productBLL.findAllProducts();
                productBLL.populateProductTable(products, view.getProductsManagementTable());
                view.setProductsManagementTable(view.getProductsManagementTable());

            } else {
                JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else
            if (Objects.equals(command, "UPDATE_PRODUCT_BTN")) {
                int input_ok = 1;
                String nume_produs = view.getNumeProdusField().getText();
                int cantitate = -1, pret = -1;

                String numbers_pattern = "^(0|[1-9][0-9]*)$";
                Pattern pattern = Pattern.compile(numbers_pattern);

                if (Objects.equals(nume_produs, ""))
                    nume_produs = product.getNume_produs();

                if (Objects.equals(view.getCantitateDisponibilaField().getText(), ""))
                    cantitate = product.getCantitate_disponibila();
                else
                {
                    String input = view.getCantitateDisponibilaField().getText();
                    if(!pattern.matcher(input).matches())
                    {
                        input_ok = 0;
                        JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Cantitate invalida!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                        cantitate = Integer.parseInt(view.getCantitateDisponibilaField().getText());
                }

                if (Objects.equals(view.getPretField().getText(), ""))
                    pret = product.getPret();
                else
                {
                    String input = view.getPretField().getText();
                    if(!pattern.matcher(input).matches())
                    {
                        input_ok = 0;
                        JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Pret invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                        pret = Integer.parseInt(view.getPretField().getText());
                }

                List<Object> values = new ArrayList<>();
                values.add(nume_produs);
                values.add(cantitate);
                values.add(pret);

                try {
                    if(input_ok == 1){
                        productBLL.updateProduct(product, values);
                        view.getProductsOpFrame().dispose();
                    }
                } catch (IllegalAccessException ex) {
                    throw new RuntimeException(ex);
                }
                List<Product> products = productBLL.findAllProducts();
                productBLL.populateProductTable(products, view.getProductsManagementTable());
                view.setProductsManagementTable(view.getProductsManagementTable());
            } else
                if (Objects.equals(command, "INSERT_PRODUCT_BTN")) {
                    String nume_produs = view.getNumeProdusField().getText();
                    int input_ok = 1;
                    if (Objects.equals(nume_produs, "") || Objects.equals(view.getCantitateDisponibilaField().getText(), "") || Objects.equals(view.getPretField().getText(), ""))
                        JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Incomplete data!", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        int cantitate = -1, pret = -1;

                        String numbers_pattern = "^(0|[1-9][0-9]*)$";
                        Pattern pattern = Pattern.compile(numbers_pattern);

                        String input = view.getCantitateDisponibilaField().getText();
                        if(!pattern.matcher(input).matches())
                        {
                            input_ok = 0;
                            JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Cantitate invalida!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                            cantitate = Integer.parseInt(view.getCantitateDisponibilaField().getText());

                        input = view.getPretField().getText();
                        if(!pattern.matcher(input).matches())
                        {
                            input_ok = 0;
                            JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Pret invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        else
                            pret = Integer.parseInt(view.getPretField().getText());


                        try {
                            if(input_ok == 1)
                            {
                                productBLL.insertProduct(new Product(nume_produs, cantitate, pret));
                                view.getProductsOpFrame().dispose();
                            }
                        } catch (IllegalAccessException ex) {
                            ///todo de afisat fereastra de cantitate negativa
                            JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "Cantitate invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                            //throw new RuntimeException(ex);
                        }
                    }
                    List<Product> products = productBLL.findAllProducts();
                    productBLL.populateProductTable(products, view.getProductsManagementTable());
                    view.setProductsManagementTable(view.getProductsManagementTable());
                } else
                    if(Objects.equals(command, "DELETE_ORDER")){
                        int selectedRow = view.getOrdersManagementTable().getSelectedRow();
                        if (selectedRow != -1) {
                            int id_order = (int) view.getOrdersManagementTable().getValueAt(selectedRow, 0);
                            product_orderBLL.deleteById(id_order);
                            List<Product_Order> orders = product_orderBLL.findAllOrders();
                            product_orderBLL.populateOrdersTable(orders, view.getOrdersManagementTable());
                            view.setOrdersManagementTable(view.getOrdersManagementTable());

                        } else {
                            JOptionPane.showMessageDialog(view.getOrdersManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else
                    if(Objects.equals(command, "UPDATE_ORDER")){
                        int selectedRow = view.getOrdersManagementTable().getSelectedRow();
                        if (selectedRow != -1) {
                            int id_order = (int) view.getOrdersManagementTable().getValueAt(selectedRow, 0);
                            String nume_produs = (String) view.getOrdersManagementTable().getValueAt(selectedRow, 1);
                            String nume_client = (String) view.getOrdersManagementTable().getValueAt(selectedRow, 2);
                            int cantitate = (int) view.getOrdersManagementTable().getValueAt(selectedRow, 3);

                            order = new Product_Order(id_order, nume_produs, nume_client, cantitate);
                            view.openUpdateOrdersWindow();
                        } else {
                            JOptionPane.showMessageDialog(view.getProductsManagementFrame(), "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else
                        if(Objects.equals(command, "UPDATE_ORDER_BTN")){
                            int input_ok = 1;
                            String nume_produs = view.getNumeProdusOrderField().getText();
                            int cantitate = -1;

                            String numbers_pattern = "^(0|[1-9][0-9]*)$";
                            Pattern pattern = Pattern.compile(numbers_pattern);

                            if (Objects.equals(nume_produs, ""))
                                nume_produs = order.getProdus();

                            if (Objects.equals(view.getCantitateComandataField().getText(), ""))
                                cantitate = order.getCantitate_comandata();
                            else
                            {
                                String input = view.getCantitateComandataField().getText();
                                if(!pattern.matcher(input).matches())
                                {
                                    input_ok = 0;
                                    JOptionPane.showMessageDialog(view.getOrdersManagementFrame(), "Cantitate invalida!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                                else
                                {
                                    cantitate = Integer.parseInt(view.getCantitateComandataField().getText());
                                    if(cantitate == 0)
                                    {
                                        input_ok = 0;
                                        JOptionPane.showMessageDialog(view.getOrdersManagementFrame(), "Cantitatea comandata nu poate fi 0!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                }
                            }

                            List<Object> values = new ArrayList<>();
                            values.add(nume_produs);
                            values.add(order.getNume_client());
                            values.add(cantitate);

                            try {
                                if(input_ok == 1){
                                    int ok = product_orderBLL.updateOrder(order, values);
                                    if(ok == 1){
                                        view.getOrdersUpdateFrame().dispose();
                                        List<Product> products = productBLL.findAllProducts();
                                        productBLL.populateProductTable(products, view.getProductsManagementTable());
                                        view.setProductsManagementTable(view.getProductsManagementTable());
                                    }
                                    else
                                        JOptionPane.showMessageDialog(view.getOrdersManagementFrame(), "Cantitatea actualizata nu este disponibila in stoc!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } catch (IllegalAccessException ex) {
                                throw new RuntimeException(ex);
                            }

                            List<Product_Order> orders = product_orderBLL.findAllOrders();
                            product_orderBLL.populateOrdersTable(orders, view.getOrdersManagementTable());
                            view.setOrdersManagementTable(view.getOrdersManagementTable());
                        } else
                            if(Objects.equals(command, "INSERT_ORDER")){
                                List<Client> clients = clientBLL.findAllClients();
                                List<Product> products = productBLL.findAllProducts();
                                String[] clienti = new String[clients.size()];
                                String[] produse = new String[products.size()];
                                int nr_clienti = 0;
                                for(Client c : clients)
                                {
                                    String nume_client = c.getNume();
                                    clienti[nr_clienti++] = nume_client;
                                }
                                int nr_produse = 0;
                                for(Product p: products)
                                {
                                    String nume_produs = p.getNume_produs();
                                    produse[nr_produse++] = nume_produs;
                                }
                                view.openInsertOrderWindow(clienti, produse);
                            }
                            else
                                if(Objects.equals(command, "INSERT_ORDER_BTN")){

                                    int input_ok = 1;
                                    String client_name = (String)view.getClientsComboBox().getSelectedItem();
                                    String product_name = (String)view.getProductsComboBox().getSelectedItem();
                                    String cantitate_string = view.getCantitateComandataInsertField().getText();
                                    int cantitate = -1;
                                    if(Objects.equals(cantitate_string, "")){
                                        JOptionPane.showMessageDialog(view.getOrdersInsertFrame(), "Introduceti cantitatea dorita!", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    else
                                    {
                                        String numbers_pattern = "^(0|[1-9][0-9]*)$"; //ne asiguram ca se introduce un numar intreg pozitiv
                                        Pattern pattern = Pattern.compile(numbers_pattern);
                                        if(!pattern.matcher(cantitate_string).matches())
                                        {
                                            input_ok = 0;
                                            JOptionPane.showMessageDialog(view.getOrdersInsertFrame(), "Cantitate invalida!", "Error", JOptionPane.ERROR_MESSAGE);
                                        }
                                        else
                                            cantitate = Integer.parseInt(view.getCantitateComandataInsertField().getText());
                                    }

                                    try {
                                        if(input_ok == 1)
                                        {
                                            int comanda_efectuata = product_orderBLL.createOrder(new Product_Order(product_name, client_name, cantitate));
                                            if(comanda_efectuata == 0)//comanda nu se poate efectua
                                            {
                                                JOptionPane.showMessageDialog(view.getOrdersInsertFrame(), "Cantitate indisponibila in stoc!", "Error", JOptionPane.ERROR_MESSAGE);
                                            }
                                            else {
                                                //avem nevoie de pretul produsului comandat pentru a putea calcula totalul comenzii

                                                int id_produs = product_orderBLL.getProduct_orderDAO().findByProductName(product_name);
                                                int pret_produs = productBLL.findProductById(id_produs).getPret();
                                                //efectuam factura
                                                billBLL.insertBill(new Bill(client_name, product_name, cantitate * pret_produs));
                                                //repopulam tabelul cu produse
                                                List<Product> products = productBLL.findAllProducts();
                                                productBLL.populateProductTable(products, view.getProductsManagementTable());
                                                view.setProductsManagementTable(view.getProductsManagementTable());
                                                //repopulam tabelul cu facturi
                                                List<Bill> bills = billBLL.findAllBills();
                                                billBLL.populateBillTable(bills, view.getBillsTable());
                                                view.setBillsTable(view.getBillsTable());

                                                view.getOrdersInsertFrame().dispose();
                                            }
                                        }
                                    } catch (IllegalAccessException ex) {
                                        JOptionPane.showMessageDialog(view.getOrdersInsertFrame(), "Cantitate invalid!", "Error", JOptionPane.ERROR_MESSAGE);
                                        //throw new RuntimeException(ex);
                                    }
                                    List<Product_Order> orders = product_orderBLL.findAllOrders();
                                    product_orderBLL.populateOrdersTable(orders, view.getOrdersManagementTable());
                                    view.setOrdersManagementTable(view.getOrdersManagementTable());

                                }
                                else
                                    if(Objects.equals(command, "BILLS")){
                                        List<Bill> bills = billBLL.findAllBills();
                                        billBLL.populateBillTable(bills, view.getBillsTable());
                                        view.openBillWindow();
                                    }


    }
}


