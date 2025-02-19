package BusinessLogic;

import DataAccess.BillDAO;
import Model.Bill;
import Model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

public class BillBLL {

    private BillDAO billDAO;

    public BillBLL(){
        billDAO = new BillDAO();
    }

    public void insertBill(Bill bill) throws IllegalAccessException {
        billDAO.insert(bill);
    }

    public List<Bill> findAllBills(){
        List<Bill> allBills = billDAO.findAll();
        if(allBills.isEmpty())
        {
            throw new NoSuchElementException("0 bills !");
        }
        return allBills;
    }

    public void populateBillTable(List<Bill> bills, JTable tabel){
        billDAO.populateTable(bills, tabel);
    }

//TODO sterge metoda asta
    public void deleteById(int id){
        billDAO.deleteById(id);
    }
}
