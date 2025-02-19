package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa ConnectionFactory este responsabilă pentru crearea și gestionarea conexiunii la baza de date MySQL.
 * Această clasă implementează un șablon Singleton pentru a asigura că există o singură instanță.
 */
public class ConnectionFactory {

    /**
     * Logger pentru înregistrarea mesajelor de informare și erori.
     */
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());
    /**
     * Informații de configurare pentru conexiunea la baza de date.
     */
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/baza_de_date";
    private static final String USER = "root";
    private static final String PASS = "Kiki2003!";

    /**
     * Instanță unică a clasei ConnectionFactory.
     */
    private static ConnectionFactory singleInstance = new ConnectionFactory();

    /**
     * Constructor privat pentru a asigura că nu pot fi create alte instanțe ale clasei.
     */
    private ConnectionFactory() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creează o nouă conexiune la baza de date.
     * @return Obiectul de conexiune creat.
     */
    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }


    /**
     * Returnează o instanță a conexiunii la baza de date.
     * @return Obiectul de conexiune la baza de date.
     */
    public static Connection getConnection() {
        return singleInstance.createConnection();
    }


    /**
     * Închide conexiunea la baza de date dată.
     * @param connection Conexiunea care trebuie închisă.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
            }
        }
    }


    /**
     * Închide obiectul Statement dat.
     * @param statement Obiectul Statement care trebuie închis.
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
            }
        }
    }

    /**
     * Închide ResultSet-ul dat.
     * @param resultSet ResultSet-ul care trebuie închis.
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
            }
        }
    }
}
