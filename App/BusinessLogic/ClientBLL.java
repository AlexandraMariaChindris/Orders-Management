package BusinessLogic;

import BusinessLogic.Validators.EmailValidator;
import BusinessLogic.Validators.Validator;
import DataAccess.ClientDAO;
import Model.Client;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {

    private Validator<Client> validators;
    private ClientDAO clientDAO;

    public ClientBLL() {
        validators = new EmailValidator();
        clientDAO = new ClientDAO();
    }

    public void insertClient(Client client) throws IllegalAccessException {
        validators.validate(client);
        clientDAO.insert(client);
    }

    public Client findClientById(int id) {
        Client st = clientDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    public List<Client> findAllClients(){
        List<Client> allClients = clientDAO.findAll();
        if(allClients.isEmpty())
        {
            throw new NoSuchElementException("0 clients !");
        }
        return allClients;
    }

    public void deleteById(int id){
        clientDAO.deleteById(id);
    }

    public void updateClient(Client client, List<Object> values) throws IllegalAccessException {
        Client c = new Client((String)values.get(0), (String)values.get(1), (String)values.get(2));
        validators.validate(c);
        clientDAO.update(client, values);
    }

    public void populateClientTable(List<Client> clients, JTable tabel){
        clientDAO.populateTable(clients, tabel);
    }
}
