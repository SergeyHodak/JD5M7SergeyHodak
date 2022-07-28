package ticket;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public interface ITicketDaoService {
    long create(Ticket ticket) throws SQLException, ExecutionException, InterruptedException;
    long getTicketCountToPlanet(Planet planet) throws SQLException;
}