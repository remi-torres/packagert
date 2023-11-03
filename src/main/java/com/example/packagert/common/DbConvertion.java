package com.example.packagert.common;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DbConvertion<E> {

    E fromDb(ResultSet resultSet) throws SQLException;
}
