package dataAccessLayer;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        String query="SELECT * FROM "+type.getSimpleName();
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(query);
            resultSet=statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:findAll "+e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return new ArrayList<>();
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
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

    public T insert(T t) {
        // TODO:
        Connection connection=null;
        PreparedStatement statement=null;
        StringBuilder sb=new StringBuilder();
        sb.append("INSERT INTO ").append(type.getSimpleName()).append(" (");
        Field[] fields=type.getDeclaredFields();
        for(int i=0;i<fields.length;i++) {
            sb.append(fields[i].getName());
            if(i<fields.length-1) sb.append(", ");
        }
        sb.append(") VALUES (");
        for(int i=0;i<fields.length;i++) {
            sb.append("?");
            if (i<fields.length-1) sb.append(", ");
        }
        sb.append(")");
        try {
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(sb.toString());
            for(int i=0; i<fields.length;i++) {
                fields[i].setAccessible(true);
                Object value=fields[i].get(t);
                statement.setObject(i + 1,value);
            }
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:insert "+ e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    public T update(T t) {
        // TODO:
        Connection connection=null;
        PreparedStatement statement=null;
        StringBuilder sb=new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        Field[] fields=type.getDeclaredFields();
        Field idField=null;
        try {
            for(int i=0;i<fields.length;i++) {
                String fieldName=fields[i].getName();
                if(fieldName.equals("id")) {
                    idField=fields[i];
                    continue;
                }
                sb.append(fieldName).append(" = ?");
                if(i<fields.length-1)
                    sb.append(", ");
            }
            if(sb.toString().endsWith(", ")) {
                sb.setLength(sb.length()-2);
            }
            sb.append(" WHERE id = ?");
            connection=ConnectionFactory.getConnection();
            statement=connection.prepareStatement(sb.toString());
            int parameterIndex=1;
            for (Field field:fields) {
                if (field.getName().equals("id"))
                    continue;
                field.setAccessible(true);
                statement.setObject(parameterIndex++,field.get(t));
            }
            if(idField!=null) {
                idField.setAccessible(true);
                statement.setObject(parameterIndex,idField.get(t));
            }
            statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.WARNING,type.getName()+"DAO:update "+e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

}
