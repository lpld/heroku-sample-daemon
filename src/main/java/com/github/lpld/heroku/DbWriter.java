package com.github.lpld.heroku;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

/**
 * @author leopold
 * @since 5/30/14
 */
public class DbWriter {

    private final String url;
    private final String user;
    private final String pass;

    public DbWriter(String urlWithCredentials) {
        URI dbUri;
        try {
            dbUri = new URI(urlWithCredentials);
        } catch (URISyntaxException ex) {
            throw new RuntimeException(ex);
        }

        String[] userInfo = dbUri.getUserInfo().split(":");
        this.user = userInfo[0];
        this.pass = userInfo[1];

        this.url = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void writeContent(String content) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement statement = conn.prepareStatement("insert into sample_table (content, datetime) values (?, ?)");

            statement.setString(1, content);
            statement.setDate(2, new Date(new java.util.Date().getTime()));

            statement.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
