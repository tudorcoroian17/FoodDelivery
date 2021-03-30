package access;

import connection.ConnectionFactory;
import model.OrderItem;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class OrderItemAccess extends AbstractAccess<OrderItem>{

    public List<OrderItem> findOrderItemByOrderId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("order_id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<OrderItem> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<OrderItem> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemAccess: findOrderItemByOrderId " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<OrderItem> findOrderItemByProductName(String name) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("product_name");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<OrderItem> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<OrderItem> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderItemAccess: findOrderItemByProductName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<OrderItem> findOrderItemById(int id) {
        List<OrderItem> resultRaw = this.findById(id);
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<OrderItem> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }

    public List<OrderItem> findAllOrderItems() {
        List<OrderItem> resultRaw = this.findAll();
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<OrderItem> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }
}
