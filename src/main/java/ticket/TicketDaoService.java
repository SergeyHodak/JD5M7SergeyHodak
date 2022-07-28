package ticket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//Сервіс побудований на JDBC
public class TicketDaoService implements ITicketDaoService {
    private PreparedStatement createSt;
    private PreparedStatement getMaxIdSt;
    private PreparedStatement getTicketCountToPlanetSt;

    private ExecutorService insertExecutor;

    public TicketDaoService(Connection connection) throws SQLException {
        insertExecutor = Executors.newSingleThreadScheduledExecutor();

        createSt = connection.prepareStatement(
                "INSERT INTO ticket (passenger_id, from_planet, to_planet) VALUES (?, ?, ?)"
        );

        getMaxIdSt = connection.prepareStatement(
                "SELECT max(id) AS maxId FROM ticket"
        );

        getTicketCountToPlanetSt = connection.prepareStatement(
                "SELECT count(*) AS cnt FROM ticket WHERE to_planet = ?"
        );
    }

    public long create(Ticket ticket) throws SQLException, ExecutionException, InterruptedException {
        Future<Long> ft = insertExecutor.submit(() -> {
            //create
            createSt.setLong(1, ticket.getPassengerId());
            createSt.setString(2, ticket.getFrom().name());
            createSt.setString(3, ticket.getTo().name());
            createSt.executeUpdate();

            //find & return max id
            try(ResultSet rs = getMaxIdSt.executeQuery()) {
                rs.next();

                return rs.getLong("maxId");
            }
        });

        return ft.get();
    }

    public long getTicketCountToPlanet(Planet planet) throws SQLException {
        getTicketCountToPlanetSt.setString(1, planet.name());

        try(ResultSet rs = getTicketCountToPlanetSt.executeQuery()) {
            rs.next();

            return rs.getLong("cnt");
        }
    }

    public static void main(String[] args) throws IOException {
        File img = new File("/home/integer/example.webp");
        InputStream bis = new FileInputStream(img);

        byte[] allByte = bis.readAllBytes();

        String encoded = Base64.getEncoder().encodeToString(allByte);

        byte[] bytes = Base64.getDecoder().decode(encoded);

        System.out.println("encoded = " + encoded);
    }
}