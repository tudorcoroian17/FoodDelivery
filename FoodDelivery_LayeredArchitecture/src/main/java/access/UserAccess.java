package access;

import connection.ConnectionFactory;
import model.Product;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserAccess extends AbstractAccess<User> {

    public List<User> findUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("username");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<User> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<User> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> findUserByCNP(String cnp) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("cnp");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, cnp);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<User> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<User> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> findUserByIdCard(String idCard) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id_card");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, idCard);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<User> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<User> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> findAllClientsWODiscount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("user_type");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, 1);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<User> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<User> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> findAllClientsWDiscount() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("user_type");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, 2);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            List<User> resultRaw = createObjects(resultSet);
            if (resultRaw == null) return null;
            if(resultRaw.size() == 0) return null;
            List<User> result = new ArrayList<>();
            for(int i = 0; i < resultRaw.size(); i++) {
                if (resultRaw.get(i).getDeleted() == 0) {
                    result.add(resultRaw.get(i));
                }
            }
            return result;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientAccess: findByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<User> findUserById(int id) {
        List<User> resultRaw = this.findById(id);
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<User> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }

    public List<User> findAllUsers() {
        List<User> resultRaw = this.findAll();
        if (resultRaw == null) return null;
        if (resultRaw.size() == 0) return null;
        List<User> result = new ArrayList<>();
        for(int i = 0; i < resultRaw.size(); i++) {
            if (resultRaw.get(i).getDeleted() == 0) {
                result.add(resultRaw.get(i));
            }
        }
        return result;
    }
}
