package DataAccess;

import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.logging.Logger;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import Connection.ConnectionFactory;
import Model.Bill;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Clasa AbstractDAO este o clasă generică care definește operațiile de bază pentru accesul la date.
 * Această clasă poate fi extinsă de alte clase DAO pentru a implementa operații specifice pentru anumite tipuri de obiecte.
 * @param <T> Tipul obiectelor cu care se lucreaza.
 */

public class AbstractDAO<T> {

    /**
     * Logger utilizat pentru înregistrarea mesajelor de informare, depanare și erori.
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructorul clasei AbstractDAO.
     * Acest constructor determină tipul de obiecte cu care lucrează acest DAO pe baza clasei generice.
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

// --------------- CREARE OBIECTE ---------------------------------

    /**
     * Creează o listă de obiecte de tipul specificat, bazându-se pe datele extrase din resultSet-ul dat.
     * @param resultSet ResultSet-ul care conține datele din baza de date.
     * @return Lista de obiecte create.
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {

                    String fieldName = field.getName();

                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Creează o listă de obiecte de tipul specificat, bazându-se pe datele extrase din ResultSet-ul dat,
     * folosind constructorul de tip record din Java.
     * @param resultSet ResultSet-ul care conține datele din baza de date.
     * @return Lista de obiecte create.
     */
    private List<T> createObjectRecord(ResultSet resultSet) {

        List<T> list = new ArrayList<T>();
        try {
            while (resultSet.next()) {
                Object[] values = new Object[type.getDeclaredFields().length];
                int i = 0;
                for (Field field : type.getDeclaredFields()) {

                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    values[i++] = value;
                }
                Bill b = new Bill(values);
                list.add((T)b);
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


// --------------- FIND BY ID ---------------------------------
    /**
     * Creează și returnează un șir de interogare SELECT pentru a selecta un obiect după un field anume.
     * @param field Field-ul dupa care se realizeaza cautarea.
     * @return Șirul de interogare SELECT.
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    /**
     * Cauta și returnează un obiect din baza de date bazat pe ID-ul specificat.
     * @param id ID-ul obiectului căutat.
     * @return Obiectul găsit sau null dacă nu a fost găsit.
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(getRightId());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

// --------------- FIND ALL ---------------------------------
    /**
     * Cauta și returnează o listă cu toate obiectele din baza de date.
     * @return Lista de obiecte găsite.
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            if(!type.getSimpleName().equals("Bill"))
                return createObjects(resultSet);
            else
                return createObjectRecord(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


    // --------------- INSERT ---------------------------------

    /**
     * Creează și returnează un șir de interogare INSERT pentru a insera un obiect în baza de date.
     * @param t Obiectul de inserat.
     * @return Șirul de interogare INSERT.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se poate accesa un membru al obiectului.
     */
    private String createInsertQuery(T t) throws IllegalAccessException {
        String query = "INSERT INTO " + type.getSimpleName() + " (" ;
        int n = t.getClass().getDeclaredFields().length - 2;
        System.out.println(t.getClass().getName());
        String query_values = "(";
        for (Field field : t.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if(!field.getName().equals(getRightId()))
            {
                query += field.getName();
                if(field.get(t).getClass().getSimpleName().equals("String"))
                    query_values += "'" + field.get(t) + "'";
                else
                    query_values += field.get(t);
                if(n > 0)
                {
                    query += ",";
                    query_values += ",";
                    n--;
                }
            }
        }
        query += ") VALUES " + query_values + ")";
        return query;
    }

    /**
     * Inserează un obiect în baza de date.
     * @param t Obiectul de inserat.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se poate accesa un membru al obiectului.
     */
    public void insert(T t) throws IllegalAccessException {

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);
        try {
            connection = ConnectionFactory.getConnection();
            System.out.println(query);
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


// --------------- FIELD-UL DE ID CORECT ---------------------------------
    /**
     * Returnează numele corect al câmpului ID pentru tipul de obiect gestionat.
     * @return Numele câmpului ID.
     */
    private String getRightId(){
        String id = "";
        if(type.getSimpleName().equals("Client"))
            id = "id_client";
        else
            if(type.getSimpleName().equals("Product"))
                id = "id_product";
            else
                if(type.getSimpleName().equals("Product_Order"))
                    id = "id_order";
                else
                    if(type.getSimpleName().equals("Bill"))
                        id = "id_bill";
        return id;
    }


// --------------- UPDATE ---------------------------------
    /**
     * Creează și returnează un șir de interogare UPDATE pentru a actualiza un obiect în baza de date bazat pe valorile specificate.
     * @param t Obiectul de actualizat.
     * @param values Valorile noi pentru actualizare.
     * @return Șirul de interogare UPDATE.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se poate accesa un membru al obiectului.
     */
    private String createUpdateQuery(T t, List<Object> values) throws IllegalAccessException {
        String query = "UPDATE " + type.getSimpleName() + " SET ";
        int id = -1, i = 0;
        int n = t.getClass().getDeclaredFields().length - 2;
        for (Field field : t.getClass().getDeclaredFields())
        {
            field.setAccessible(true);
            if(field.getName().equals(getRightId())){
                id = (int) field.get(t);
            }
            else
            {
                query += field.getName() + "=";
                if(values.get(i).getClass().getSimpleName().equals("String"))
                    query += "'" + values.get(i++) + "'";
                else
                    query += values.get(i++);
                if(n > 0)
                {
                    query += ",";
                    n--;
                }
            }
        }
        query += " WHERE " + getRightId() + " = " + id;
        return query;
    }

    /**
     * Actualizează un obiect în baza de date bazat pe valorile specificate.
     * @param t Obiectul de actualizat.
     * @param values Valorile noi pentru actualizare.
     * @throws IllegalAccessException Excepție aruncată în cazul în care nu se poate accesa un membru al obiectului.
     */
    public void update(T t, List<Object> values) throws IllegalAccessException {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdateQuery(t, values);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }


    // --------------- DELETE BY ID ---------------------------------
    /**
     * Creează și returnează un șir de interogare DELETE pentru a șterge un obiect din baza de date bazat pe field-ul specificat.
     * @param field Numele câmpului din tabelul dupa care se face stergerea(conditia pentru stergere).
     * @return Șirul de interogare DELETE.
     */
    private String createDeleteQuery(String field){
        String query = "DELETE FROM " + type.getSimpleName() + " WHERE " + field + " =?";
        return query;
    }

    /**
     * Șterge un obiect din baza de date bazat pe ID-ul specificat.
     * @param id ID-ul obiectului de șters.
     */
    public void deleteById(int id){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(getRightId());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            int i = statement.executeUpdate();
            if(i == 0)
                System.out.println("Id-ul = " + id + " nu exista !");

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:deleteById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    //----------------- POPULATE TABLE ---------------------------------
    /**
     * Populează un tabel cu datele din lista specificată de obiecte.
     * @param obj Lista de obiecte de populat în tabel.
     * @param table Tabelul în care vor fi afișate datele.
     */
    public void populateTable(List<T> obj, JTable table) {

        DefaultTableModel model = new DefaultTableModel();

        if (!obj.isEmpty()) {

            Field[] fields = obj.get(0).getClass().getDeclaredFields();

            // adaugam numele coloanelor
            for (Field field : fields) {
                model.addColumn(field.getName());
            }

            // adaugam randurile din tabel
            for (T t : obj)
            {
                Object[] rowData = new Object[fields.length];
                int i = 0;
                for (Field field : fields)
                {
                    field.setAccessible(true);

                        try {
                            rowData[i++] = field.get(t);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                }
                model.addRow(rowData);
            }
        }

        table.setModel(model);
    }
}
