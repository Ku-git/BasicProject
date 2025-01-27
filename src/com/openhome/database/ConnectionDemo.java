package com.openhome.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    public static void main(String[] args) throws SQLException {
        var url = "jdbc:h2:tcp://localhost/C:/Users/asus/TestProject/BasicProject";
        var user = "admin";
        var password = "admin";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.printf("已%s資料庫連線%n",
                    conn.isClosed() ? "關閉" : "開啟");
        }
    }
}
