package com.openhome.database;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    private String url;
    private String username;
    private String password;

    public MessageDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void add(Message message) {
        try(var conn = DriverManager.getConnection(url, username, password);
            var statement = conn.createStatement()) {

            var sql = String.format("INSERT INTO message(name, email, msg) " +
                    "VALUES ('%s', '%s', '%s');",
                    message.name(), message.email(), message.msg());
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> findAll() {

        List<Message> messages = new ArrayList<>();
        try(var conn = DriverManager.getConnection(url, username, password);
            var statement = conn.createStatement()) {
            var sql = String.format("SELECT * FROM message;");
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Message message = toMessage(rs);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Message> findByCondition(String name, String email) {

        List<Message> messages = new ArrayList<>();
        try(var conn = DriverManager.getConnection(url, username, password);
            var statement = conn.createStatement()) {
            var sql = String.format("SELECT * FROM message where" +
                    " name = '%s' and email = '%s'", name, email);
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Message message = toMessage(rs);
                messages.add(message);
            }
            return messages;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Message toMessage(ResultSet rs) throws SQLException {
        return new Message(
                rs.getString(2),
                rs.getString(3),
                rs.getString(4)
        );
    }
}
