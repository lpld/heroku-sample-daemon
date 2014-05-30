package com.github.lpld.heroku;

import java.sql.*;

/**
 * @author leopold
 * @since 5/30/14
 */
public class DbWriter {

    private final String urlWithCredentials;

    public DbWriter(String urlWithCredentials) {
        this.urlWithCredentials = urlWithCredentials;
        try {
            DriverManager.registerDriver((Driver) Class.forName("org.postgresql.Driver").newInstance());

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void writeContent(String content) {
        try (Connection conn = DriverManager.getConnection(urlWithCredentials)) {
            PreparedStatement statement = conn.prepareStatement("insert into sample_table (content, datetime) values (?, ?)");

            statement.setString(1, content);
            statement.setDate(2, new Date(new java.util.Date().getTime()));

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
