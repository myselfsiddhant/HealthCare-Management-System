package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean isValidUser(String id, String password) {
        String query = "SELECT * FROM admins WHERE username = ? AND password = ? " +
                       "UNION " +
                       "SELECT * FROM patients WHERE username = ? AND password = ?";
        List<?> results = jdbcTemplate.queryForList(query, id, password, id, password);
        return !results.isEmpty();
    }
}
