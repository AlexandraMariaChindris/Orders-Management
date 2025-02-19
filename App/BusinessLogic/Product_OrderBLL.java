package BusinessLogic;


import DataAccess.Product_OrderDAO;
import Model.Product;
import Model.Product_Order;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Product_OrderBLL {

    private Product_OrderDAO product_orderDAO;

    public Product_OrderBLL(){
        product_orderDAO = new Product_OrderDAO();
    }
    public int createOrder(Product_Order order) throws IllegalAccessException {
        String produs = order.getProdus();
        //stim produsul, trebuie sa-i aflam id-ul din tabelul product
        //=> find by produs=String
        int id_product = product_orderDAO.findByProductName(produs);//avem id-ul produsului comandat
        //System.out.println(id_product);
        ProductBLL productBLL = new ProductBLL();
        Product p = productBLL.findProductById(id_product);//cautam produsul dupa id-ul sau pentru a-i afla stocul
        if(p.getCantitate_disponibila() >= order.getCantitate_comandata())//daca cantitatea din stoc este in de ajuns pentru efectuarea comenzii
        {
            int stoc_ramas = p.getCantitate_disponibila() - order.getCantitate_comandata();
            //System.out.println("Comanada se poate efectua");
            //adaugam comanda in tabel
            product_orderDAO.insert(order);
            //actualizam stocul
            List<Object> values = new ArrayList<>();
            values.add(p.getNume_produs());
            values.add(stoc_ramas);
            values.add(p.getPret());
            productBLL.updateProduct(p, values);
            return 1;
        }
        else
        {
            //System.out.println("Cantitate indisponibila");
            return 0;
        }
    }

    public List<Product_Order> findAllOrders(){
        List<Product_Order> allProductOrders = product_orderDAO.findAll();
        if(allProductOrders.isEmpty())
        {
            throw new NoSuchElementException("0 orders !");
        }
        return allProductOrders;
    }

    public void populateOrdersTable(List<Product_Order> orders, JTable tabel){
        product_orderDAO.populateTable(orders, tabel);
    }

    public void deleteById(int id){
        product_orderDAO.deleteById(id);
    }

    public int updateOrder(Product_Order order, List<Object> values) throws IllegalAccessException {
        int cantitate_comanda = (int) values.get(2);//la actualizare
        int comanda_update = cantitate_comanda - order.getCantitate_comandata();
        //comanda_update>0 daca la actualizarea comenzii s-a marit numarul de produse comandata
        //comanda_update<0 daca la actualizarea comenzii s-a diminiuat numarul de produse comandate
        String produs = order.getProdus();
        int id_product = product_orderDAO.findByProductName(produs);
        ProductBLL productBLL = new ProductBLL();
        Product p = productBLL.findProductById(id_product);//produsul din stoc ce se doreste a fi comandat
        int stoc_ramas, diferenta_produse;
        if(comanda_update > 0)
        {
            //s-a marit numarul de produse comandate
            //trebuie sa verificam daca diferenta de produse este valabila in stoc
            diferenta_produse = cantitate_comanda - order.getCantitate_comandata();
            if(p.getCantitate_disponibila() >= diferenta_produse)
            {
                product_orderDAO.update(order, values);
                //actualizam stocul in Products
                stoc_ramas = p.getCantitate_disponibila() - diferenta_produse;
                List<Object> v = new ArrayList<>();
                v.add(p.getNume_produs());
                v.add(stoc_ramas);
                v.add(p.getPret());
                productBLL.updateProduct(p, v);
                return 1;
            }
        }
        else
            if(comanda_update < 0)
            {
                //a scazut numarul produselor comandate
                //deci actualizam comanda si crestem stocul produselor
                diferenta_produse = order.getCantitate_comandata() - cantitate_comanda;
                product_orderDAO.update(order, values);
                //actualizam stocul in Products
                stoc_ramas = p.getCantitate_disponibila() + diferenta_produse;
                List<Object> v = new ArrayList<>();
                v.add(p.getNume_produs());
                v.add(stoc_ramas);
                v.add(p.getPret());
                productBLL.updateProduct(p, v);
                return 1;
            }
            else
            {
                product_orderDAO.update(order, values);
                return 1;
            }

        return 0;

    }

    public Product_OrderDAO getProduct_orderDAO() {
        return product_orderDAO;
    }
}
