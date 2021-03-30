package access;

import connection.ConnectionFactory;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class OrderAccess extends AbstractAccess<Order>{

    public List<Order> findOrderByDate(String date) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("dateBuy");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, date);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<Order> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<Order> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderAccess: findOrderByDate " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<Order> findOrderByClientId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("client_id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<Order> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<Order> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderAccess: findOrderByClientId " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<Order> findOrderById(int id) {
        List<Order> resultRaw = this.findById(id);
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<Order> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }

    public List<Order> findAllOrders() {
        List<Order> resultRaw = this.findAll();
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<Order> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }
}
