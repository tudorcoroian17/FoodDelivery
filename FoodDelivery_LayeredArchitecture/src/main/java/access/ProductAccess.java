package access;

import connection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ProductAccess extends AbstractAccess<Product> {

    public List<Product> findProductByName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if(resultSet == null) return null;
            List<Product> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<Product> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<Product> findProductById(int id) {
        List<Product> resultRaw = this.findById(id);
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<Product> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }

    public List<Product> findAllProducts() {
        List<Product> resultRaw = this.findAll();
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<Product> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }
}
