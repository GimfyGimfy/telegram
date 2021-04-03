package telegram.sql;

import lombok.SneakyThrows;

import javax.ws.rs.GET;
import java.sql.*;
import java.util.ArrayList;

import static java.sql.DriverManager.getConnection;
import static sqlRequest.Request.INSERT_INTO_USERS;

public class PostgreSQLJDBC {
    @SneakyThrows
    public Connection connect() {
        Connection c = null;
        c = getConnection("jdbc:postgresql://hostname:port/dbname", "user", "password");
        c.setAutoCommit(false);
        return c;
    }

    @SneakyThrows
    public Statement createStatementFromConnection(Connection connection) {
        Statement stmt = null;
        stmt = connection.createStatement();
        return stmt;
    }

    @SneakyThrows
    public void sendRequest(String sql, Statement statement) {
        statement.executeUpdate(sql);
        statement.close();
        statement.getConnection().commit();
        statement.getConnection().close();
    }

    @SneakyThrows
    public void insertData(int id, String firstname, String lastname) {
        Statement statement = createStatementFromConnection(connect());
        sendRequest(String.format(INSERT_INTO_USERS, id, firstname, lastname), statement);
    }
}