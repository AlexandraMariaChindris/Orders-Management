package Model;

public class Product_Order {

    private int id_order;
    private String produs;
    private String nume_client;
    private int cantitate_comandata;

    public Product_Order(){
    }

    public Product_Order(int id_order, String produs, String nume_client, int cantitate_comandata) {
        this.id_order = id_order;
        this.produs = produs;
        this.nume_client = nume_client;
        this.cantitate_comandata = cantitate_comandata;
    }

    public Product_Order(String produs, String nume_client, int cantitate_comandata) {
        this.produs = produs;
        this.nume_client = nume_client;
        this.cantitate_comandata = cantitate_comandata;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getProdus() {
        return produs;
    }

    public void setProdus(String produs) {
        this.produs = produs;
    }

    public String getNume_client() {
        return nume_client;
    }

    public void setNume_client(String nume_client) {
        this.nume_client = nume_client;
    }

    public int getCantitate_comandata() {
        return cantitate_comandata;
    }

    public void setCantitate_comandata(int cantitate_comandata) {
        this.cantitate_comandata = cantitate_comandata;
    }
}
