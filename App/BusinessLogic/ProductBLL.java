package BusinessLogic;

import BusinessLogic.Validators.CantitateDisponibilaValidator;
import BusinessLogic.Validators.Validator;
import DataAccess.ProductDAO;
import Model.Client;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa ProductBLL oferă funcționalități pentru gestionarea produselor în sistem.
 * Această clasă se ocupă de logica operațiunilor legate de produse.
 */
public class ProductBLL {

    private Validator<Product> validators;
    private ProductDAO productDAO;

    /**
     * Constructorul clasei ProductBLL.
     * Inițializează validatorul și obiectul de acces la date pentru produse.
     */
    public ProductBLL() {
        validators = new CantitateDisponibilaValidator();
        productDAO = new ProductDAO();
    }


    /**
     * Inserează un produs în baza de date și validează produsul folosind validatorul asociat.
     * @param product Produsul de inserat.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se pot valida produsul.
     */
    public void insertProduct(Product product) throws IllegalAccessException {
        validators.validate(product);
        productDAO.insert(product);
    }


    /**
     * Găsește un produs din baza de date bazat pe ID-ul specificat.
     * @param id ID-ul produsului de căutat.
     * @return Produsul găsit.
     * @throws NoSuchElementException Excepție aruncată în cazul în care produsul nu este găsit.
     */
    public Product findProductById(int id) {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }


    /**
     * Returnează o listă cu toate produsele din baza de date.
     * @return Lista de produse.
     * @throws NoSuchElementException Excepție aruncată în cazul în care nu există produse în baza de date.
     */
    public List<Product> findAllProducts(){
        List<Product> allProducts = productDAO.findAll();
        if(allProducts.isEmpty())
        {
            throw new NoSuchElementException("0 products !");
        }
        return allProducts;
    }

    /**
     * Șterge un produs din baza de date bazat pe ID-ul specificat.
     * @param id ID-ul produsului de șters.
     */
    public void deleteById(int id){
        productDAO.deleteById(id);
    }


    /**
     * Actualizează un produs în baza de date bazat pe valorile specificate și validează noul produs.
     * @param product Produsul de actualizat.
     * @param values Valorile noi pentru actualizare.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se pot valida valorile noi.
     */
    public void updateProduct(Product product, List<Object> values) throws IllegalAccessException {
       Product p = new Product((String)values.get(0), (int)values.get(1), (int)values.get(2));
       validators.validate(p);
       productDAO.update(product, values);
    }

    /**
     * Populează un tabel cu datele despre produse.
     * @param products Lista de produse de afișat în tabel.
     * @param tabel Tabelul Swing în care vor fi afișate datele.
     */
    public void populateProductTable(List<Product> products, JTable tabel){
        productDAO.populateTable(products, tabel);
    }
}
