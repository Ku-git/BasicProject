package com.openhome.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StatementDemo {

    public static void main(String[] args) {
        var url = "jdbc:h2:tcp://localhost/C:/Users/asus/TestProject/BasicProject";
        var user = "admin";
        var password = "admin";

        try(var conn = DriverManager.getConnection(url, user, password);
            var statement = conn.createStatement()) {
             statement.executeUpdate(
                     """
                        CREATE TABLE message (
                            id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            name CHAR(20) NOT NULL,
                            email CHAR(40),
                            msg VARCHAR(256) NOT NULL
                        );
                        """
             );
            System.out.println("建立message table");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
