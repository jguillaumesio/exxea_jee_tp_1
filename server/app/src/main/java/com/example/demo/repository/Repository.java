package com.example.demo.repository;

import com.example.demo.model.Model;

import java.sql.ResultSet;

public interface Repository<T> {

    public T mapResultSetToEntity(ResultSet resultSet);
}
