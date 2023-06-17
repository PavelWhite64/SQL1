package data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    private static QueryRunner runner = new QueryRunner();

    private SqlHelper() {
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }

    @SneakyThrows
    public static Datahelper.VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var conn = getConnection();
        var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new Datahelper.VerificationCode(code);
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnection();
        runner.execute(connection, "DELETE FROM auth_codes");
        runner.execute(connection, "DELETE FROM card_transactions");
        runner.execute(connection, "DELETE FROM cards");
        runner.execute(connection, "DELETE FROM users");
    }
}