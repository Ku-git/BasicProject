package com.openhome.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAO2 {

    private String url;
    private String username;
    private String password;

    public MessageDAO2(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void add(Message message) {
        try(var conn = DriverManager.getConnection(url, username, password);
            var statement = conn.prepareStatement(
                    "INSERT INTO message(name, email, msg) values(?,?,?)")) {

            statement.setString(1, message.name());
            statement.setString(2, message.email());
            statement.setString(3, message.msg());
            statement.executeUpdate();
            statement.clearParameters();

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
            var statement = conn.prepareStatement(
                    "SELECT * FROM message WHERE name = ? AND email = ?")) {
            statement.setString(1, name);
            statement.setString(2, email);

            ResultSet rs = statement.executeQuery();
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
