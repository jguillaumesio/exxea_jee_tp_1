package com.example.demo.repository;

import com.example.demo.model.Model;
import com.example.demo.utils.DBConnection;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.persistence.*;

public abstract class AbstractRepository<T> implements Repository<T>{

    protected Class<T> associatedClass;

    public AbstractRepository(Class<T> associatedClass) {
        this.associatedClass = associatedClass;
    }

    private String getTableName() {
        Table annotation = this.associatedClass.getAnnotation(Table.class);
        return annotation.name();
    }

    private List<String> getNonIdColumnNames() {
        List<String> columnNames = new ArrayList<>();
        for (Field field : this.associatedClass.getDeclaredFields()) {
            if (!field.isAnnotationPresent(Id.class)) {
                // If the field is not marked with @Id, add its name to the list
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation != null) {
                    columnNames.add(columnAnnotation.name());
                } else {
                    // If @Column annotation is not present, use the field name as the column name
                    columnNames.add(field.getName());
                }
            }
        }

        return columnNames;
    }

    public T findById(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        T result = null;

        try {
            String sql = "SELECT * FROM " + this.getTableName() + " WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                // Map the resultSet to your entity class (T)
                return this.mapResultSetToEntity(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public List<T> findAll() {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<T> resultList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM " + this.getTableName();
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                resultList.add(mapResultSetToEntity(resultSet));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    public boolean create(T entity) {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            List<String> columnsNames = this.getNonIdColumnNames();
            String params = String.join(",",columnsNames.stream()
                    .map(item -> "?") // Prefix each element with ":"
                    .collect(Collectors.toList()));
            String sql = "INSERT INTO " + this.getTableName() + " (" + String.join(",", columnsNames) + ") VALUES (" + params + ")";
            preparedStatement = connection.prepareStatement(sql);
            Object o = entity;
            Class<?> c = o.getClass();
            for (int i = 0; i < columnsNames.size(); i++) {
                String field = columnsNames.get(i);
                Field f = c.getDeclaredField(field);
                f.setAccessible(true);
                preparedStatement.setObject(i + 1, f.get(o));
            }
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(T entity) {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;

        try {
            List<String> columnsNames = this.getNonIdColumnNames();
            String params = String.join(", ",columnsNames.stream()
                    .map(item -> item+"=?") // Prefix each element with ":"
                    .collect(Collectors.toList()));
            String sql = "UPDATE " + this.getTableName() + " SET " + params + " WHERE id=?";
            System.out.println(sql);
            preparedStatement = connection.prepareStatement(sql);
            Object o = entity;
            Class<?> c = o.getClass();
            for (int i = 0; i < columnsNames.size(); i++) {
                String field = columnsNames.get(i);
                Field f = c.getDeclaredField(field);
                f.setAccessible(true);
                preparedStatement.setObject(i + 1, f.get(o));
            }
            Field f = c.getDeclaredField("id");
            f.setAccessible(true);
            preparedStatement.setObject(columnsNames.size() + 1, f.get(o));
            System.out.println(preparedStatement);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "DELETE FROM " + this.getTableName() + " WHERE id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
