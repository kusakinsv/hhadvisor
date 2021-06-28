package ru.one.hhadvisor.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TableCleaner {
    public void truncate(String tableName) throws SQLException {
        String url = "jdbc:postgresql://35.238.106.186/springdatabase";
        Properties props = new Properties();
        props.setProperty("user","admin");
        props.setProperty("password","123450000");
        props.setProperty("ssl","false");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("Truncating table" + tableName + "...");
        Statement stmt = conn.createStatement();
        String query = "Truncate table " + tableName;
        stmt.execute(query);
        System.out.println("Truncated Complete");
        conn.close();
    }
}

