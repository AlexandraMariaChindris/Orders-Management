package Model;

public record Bill(int id_bill, String nume_client, String nume_produs, int total) {

    public Bill(String nume_client, String nume_produs, int total){
        this(0, nume_client, nume_produs, total);
    }

    public Bill(){
        this(0, null, null, 0);
    }

    public Bill(Object[] values)
    {
        this((int)values[0], (String)values[1], (String)values[2], (int)values[3]);
    }

    public int isId_bill() {
        return id_bill;
    }



    @Override
    public String nume_client() {
        return nume_client;
    }

    @Override
    public String nume_produs() {
        return nume_produs;
    }

    @Override
    public int total() {
        return total;
    }
}
