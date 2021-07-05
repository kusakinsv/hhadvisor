package ru.one.hhadvisor.program;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TableCleaner {
    public void truncate(String tableName) throws SQLException {
        String url = "jdbc:postgresql://springdatabase.cjgtmemkygoe.us-east-2.rds.amazonaws.com/springdatabase";
        Properties props = new Properties();
        props.setProperty("user","administrator");
        props.setProperty("password","123450000");
        props.setProperty("ssl","false");
        Connection conn = DriverManager.getConnection(url, props);
        System.out.println("----------------");
        System.out.println("Truncating table " + tableName + "...");
        Statement stmt = conn.createStatement();
        String query = "Truncate table " + tableName;
        stmt.execute(query);
        System.out.println("Truncation completed");
        System.out.println();
        conn.close();
    }
}
//
//springdatabase.cjgtmemkygoe.us-east-2.rds.amazonaws.com/springdatabase - первая бд
//springdatabase2.cjgtmemkygoe.us-east-2.rds.amazonaws.com/springdatabase2    - вторая бд
// postgresql-group6.group6.svc - postgres - postgres