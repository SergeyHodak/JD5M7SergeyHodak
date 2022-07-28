package ticket;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import storage.hibernate.HibernateUtil;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class HibernateTicketDaoService implements ITicketDaoService {
    @Override
    public long create(Ticket ticket) throws SQLException, ExecutionException, InterruptedException {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(ticket);
        transaction.commit();
        session.close();

        return ticket.getId();
    }

    @Override
    public long getTicketCountToPlanet(Planet planet) throws SQLException {
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            //HQL варіант
//            Query<Long> query = session.createQuery(
//                    "select count(id) from Ticket t where t.to = :to",
//                    Long.class
//            );
//            query.setParameter("to", planet);
//            return query.getSingleResult();

            //SQL варіант
            NativeQuery<Long> query = session.createNativeQuery(
                    "SELECT count(*) FROM ticket WHERE to_planet = :to",
                    Long.class
            );
            query.setParameter("to", planet.name());
            return query.getSingleResult();
        }
    }
}
