package passenger;

import java.sql.SQLException;

public interface IPassengerDaoService {
    void create(Passenger passenger) throws SQLException;
    Passenger getByPassport(String passport) throws SQLException;
}