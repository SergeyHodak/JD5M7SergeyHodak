import cli.CliFSM;
import storage.ConnectionProvider;
import storage.DatabaseInitService;

import java.sql.SQLException;

public class App {
    public String getGreeting() {
        return "Hello world.";
    }

    public static void main(String[] args) throws SQLException {
        //Init DB using flyway
        new DatabaseInitService().initDb();

        ConnectionProvider connectionProvider = new ConnectionProvider();

        new CliFSM(connectionProvider);

        connectionProvider.close();
    }
}