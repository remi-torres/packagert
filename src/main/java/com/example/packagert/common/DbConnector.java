package com.example.packagert.common;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbConnector {

    public static final String DB_PATH = "C:\\Users\\torres\\Documents\\programmation\\packageRT\\packagert.db";
    private static final int NEW_KEY_INDEX = 1;
    private final String dbUrl;

    public DbConnector(final String dbUrl){
        this.dbUrl = dbUrl;
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(String.format("jdbc:sqlite:%s", dbUrl));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public long insert(String query, List<Object> parameters) {
        try (Connection conn = this.connect();
             PreparedStatement statement  = conn.prepareStatement(query)){
            addParamToStatement(statement, parameters);
            statement.executeUpdate();
            return statement.getGeneratedKeys().getLong(NEW_KEY_INDEX);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void addParamToStatement(final PreparedStatement statement, final List<Object> parameters) throws SQLException {
        int index = 1;
        for(Object param :  parameters){
            Class<? extends Object> type = param.getClass();
            if (type == String.class)
                statement.setString(index, param.toString());
            else if (type == Long.class)
                statement.setLong(index, (Long) param);
            else {
                throw new RuntimeException(String.format("Type not found for the param %s", param));
            }
            index ++;
        }
    }

    public <T> List<T> selectAll(final String query, final Class<T> type) {
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(query)){

            List<T> result = new ArrayList<>();
            while (rs.next())
                result.add((T) ((DbConvertion<T>)type.getConstructor().newInstance()).fromDb(rs));

            return result;
        } catch (SQLException | ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> selectAll(final String query, List<Object> parameters, final Class<T> type) {
        try (Connection conn = this.connect();
             PreparedStatement statement  = conn.prepareStatement(query)){
            addParamToStatement(statement, parameters);
            ResultSet rs  = statement.executeQuery();

            List<T> result = new ArrayList<>();
            while (rs.next()) {
                result.add((T) ((DbConvertion<T>)type.getConstructor().newInstance()).fromDb(rs));
            }
            return result;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
