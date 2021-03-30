package access;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

public class AbstractAccess<T> {

    protected static final Logger LOGGER = Logger.getLogger(AbstractAccess.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractAccess() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    protected String createSelectQuery(String field) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM fooddlv.");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" WHERE " + field + "=?");
        return stringBuilder.toString();
    }

    private String createSelectAllQuery() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ");
        stringBuilder.append(" * ");
        stringBuilder.append(" FROM fooddlv.");
        stringBuilder.append(type.getSimpleName());
        return stringBuilder.toString();
    }

    private String createInsertQuery(List<String> fields) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO ");
        stringBuilder.append("fooddlv." + type.getSimpleName());
        stringBuilder.append(" (");
        for(String field : fields) {
            stringBuilder.append(field + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(") ");
        stringBuilder.append(" VALUES (");
        for(@SuppressWarnings("unused") String field : fields) {
            stringBuilder.append("?,");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private String createUpdateQuery(List<String> fields) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append("fooddlv." + type.getSimpleName());
        stringBuilder.append(" SET ");
        for(String field : fields) {
            stringBuilder.append(field + "=" + "?" + ",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(" WHERE ");
        stringBuilder.append(fields.toArray()[0]);
        stringBuilder.append("=?");
        return stringBuilder.toString();
    }

    private String createDeleteQuery(String field) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("UPDATE ");
        stringBuilder.append(type.getSimpleName());
        stringBuilder.append(" SET deleted = true");
        stringBuilder.append(" WHERE ");
        stringBuilder.append(field + "=?");
        return stringBuilder.toString();
    }

    public List<T> findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[0].getName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet == null) return null;
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "Access: findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "Access: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public int insert (T object) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<String> fields = new ArrayList<String>();
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(field.getName());
        }
        String query = createInsertQuery(fields);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(query);
            Object value;
            int i = 1;
            for(Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                value = field.get(object);
                statement.setObject(i, value);
                i++;
            }
            System.out.println(statement.toString());
            statement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    public int update (T objectOld, T objectNew) {
        Connection connection = null;
        PreparedStatement statement = null;
        List<String> fields = new ArrayList<String>();
        for(Field field : type.getDeclaredFields()) {
            field.setAccessible(true);
            fields.add(field.getName());
        }
        String query = createUpdateQuery(fields);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(query);
            Object value;
            int i = 1;
            for(Field field : objectNew.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                value = field.get(objectNew);
                statement.setObject(i, value);
                i++;
            }
            Field field = objectOld.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            value = field.get(objectOld);
            statement.setObject(i, value);
            System.out.println(statement.toString());
            statement.executeUpdate();
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    public int deleteById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        Field field = type.getDeclaredFields()[0];
        field.setAccessible(true);
        String query = createDeleteQuery(field.getName());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareCall(query);
            statement.setInt(1, id);
            statement.executeUpdate();
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return -1;
    }

    public int delete (T object) {
        int id;
        try {
            Field field = object.getClass().getDeclaredFields()[0];
            field.setAccessible(true);
            id = field.getInt(object);
            return deleteById(id);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return -1;
    }

    protected List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        try {
            while(resultSet.next()) {
                T instance = type.getDeclaredConstructor(null).newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (list.isEmpty()) return null;
        return list;
    }
}
