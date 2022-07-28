package passenger;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;

public class HibernatePassengerDaoService implements IPassengerDaoService {
    @Override
    public void create(Passenger passenger) throws SQLException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(passenger);
        transaction.commit();
        session.close();
    }

    @Override
    public Passenger getByPassport(String passport) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Query<Passenger> query = session.createQuery("from Passenger where passport = :passport", Passenger.class);
            query.setParameter("passport", passport);
            return query.stream().findFirst().orElse(null);
        }
    }
}