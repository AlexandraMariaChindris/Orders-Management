

package Model;
/**
 Clasa reprezinta un client. Un client are un id, nume, adresa si email.
 Clasa contine metode pentru a extrage si a seta toate atributele clientului.
 */
public class Client {

    private int id_client;
    private String nume;
    private String adresa;
    private String email;

    /**
     * Constructor implicit pentru un client.
     */
    public Client(){
    }

    /**
     * Construieste un client cu atributele specificate(transmise prin parametrii).
     * @param id_client id-ul clientului
     * @param nume numele clientului
     * @param adresa adresa clientului
     * @param email email-ul clientului
     */
    public Client(int id_client, String nume, String adresa, String email) {
        this.id_client = id_client;
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }

    /**
     * Construieste un client cu atributele specificate(transmise prin parametrii).
     * @param nume numele clientului
     * @param adresa adresa clientului
     * @param email email-ul clientului
     */
    public Client(String nume, String adresa, String email) {
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
    }

    /**
     * Extrage id-ul clientului
     * @return id-ul clientului
     */
    public int getId_client() {
        return id_client;
    }

    /**
     * Seteaza id-ul clientului
     * @param id_client noul id
     */
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    /**
     * Extrage numele clientului.
     * @return numele clientului
     */
    public String getNume() {
        return nume;
    }

    /**
     * Seteaza numele clientului.
     * @param nume noul nume
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Extrage adresa clientului.
     * @return adresa clientului
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Seteaza adresa clientului.
     * @param adresa noua adresa
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * Extrage email-ul clientului.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Seteaza email-ul clientului.
     * @param email noul email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
