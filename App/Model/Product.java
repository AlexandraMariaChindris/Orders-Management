
package Model;
/**
 Clasa Product reprezintă un obiect ce descrie un produs.
 Un produs are un id, nume, cantitatea disponibila pe stoc si un pret.
 */
public class Product {

    private int id_product;
    private String nume_produs;
    private int cantitate_disponibila;
    private int pret;

    /**
     * Constructor implicit pentru un produs.
     */
    public Product(){
    }

    /**
     * Constructor pentru un produs cu toate detaliile specificate.
     * @param id_product Id-ul produsului.
     * @param nume_produs Numele produsului.
     * @param cantitate_disponibila Cantitatea disponibilă în stoc.
     * @param pret Prețul produsului.
     */
    public Product(int id_product, String nume_produs, int cantitate_disponibila, int pret) {
        this.id_product = id_product;
        this.nume_produs = nume_produs;
        this.cantitate_disponibila = cantitate_disponibila;
        this.pret = pret;
    }

    /**
     * Constructor pentru un produs cu detaliile specificate, fără id.
     * @param nume_produs Numele produsului.
     * @param cantitate_disponibila Cantitatea disponibilă în stoc.
     * @param pret Prețul produsului.
     */
    public Product(String nume_produs, int cantitate_disponibila, int pret) {
        this.nume_produs = nume_produs;
        this.cantitate_disponibila = cantitate_disponibila;
        this.pret = pret;
    }

    /**
     * Returnează id-ul produsului.
     * @return Id-ul produsului.
     */
    public int getId_product() {
        return id_product;
    }

    /**
     * Setează id-ul produsului.
     * @param id_product Id-ul produsului.
     */
    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    /**
     * Returnează numele produsului.
     * @return Numele produsului.
     */
    public String getNume_produs() {
        return nume_produs;
    }

    /**
     * Setează numele produsului.
     * @param nume_produs Numele produsului.
     */
    public void setNume_produs(String nume_produs) {
        this.nume_produs = nume_produs;
    }

    /**
     * Returnează cantitatea disponibilă în stoc.
     * @return Cantitatea disponibilă în stoc.
     */
    public int getCantitate_disponibila() {
        return cantitate_disponibila;
    }

    /**
     * Setează cantitatea disponibilă în stoc.
     * @param cantitate_disponibila Cantitatea disponibilă în stoc.
     */
    public void setCantitate_disponibila(int cantitate_disponibila) {
        this.cantitate_disponibila = cantitate_disponibila;
    }

    /**
     * Returnează prețul produsului.
     * @return Prețul produsului.
     */
    public int getPret() {
        return pret;
    }

    /**
     * Setează prețul produsului.
     * @param pret Prețul produsului.
     */
    public void setPret(int pret) {
        this.pret = pret;
    }

}
