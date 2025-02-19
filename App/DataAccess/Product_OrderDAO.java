package DataAccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import Connection.ConnectionFactory;
import Model.Product_Order;

public class Product_OrderDAO extends AbstractDAO<Product_Order>{

    public int findByProductName(String produs) { //returnam id-ul produsului cautat din Product

        int id_product = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT id_product FROM product WHERE nume_produs" + "=?";
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, produs);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                id_product = resultSet.getInt("id_product");
                return id_product;
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,  "Product_OrderDAO:findByProductName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return id_product;
    }
}
