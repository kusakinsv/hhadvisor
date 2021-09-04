package ru.one.hhadvisor.program;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Getter
@Component
public class TableCleaner {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${vacancy.table.name}")
    private String tableName;

    public void truncate() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user",username);
        props.setProperty("password", password);
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

    public TableCleaner() {
    }
}
