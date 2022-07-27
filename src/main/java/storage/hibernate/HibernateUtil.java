package storage.hibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import passenger.Passenger;

import java.util.List;

public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    @Getter
    private final SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }

    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Passenger.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getINSTANCE() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        HibernateUtil util = HibernateUtil.getINSTANCE();

        //Get single (Достає одного пасажира по айдішці)
//        Session session = util.getSessionFactory().openSession();
//        Passenger passenger = session.get(Passenger.class, 1L);
//        System.out.println("passenger = " + passenger);
//        session.close();

        //List all passengers (HQL запит на отримання всіх пассажирів)
//        Session session = util.getSessionFactory().openSession();
//        List<Passenger> passengers = session.createQuery("from Passenger", Passenger.class).list();
//        System.out.println("passengers = " + passengers);
//        session.close();

        //Save new passenger (Додавання нового запису в таблицю)
        Session session = util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Passenger newPassenger = new Passenger();
                newPassenger.setName("New Passenger Name");
                newPassenger.setPassport("yy99");
                session.persist(newPassenger);
                System.out.println("newPassenger = " + newPassenger);
            transaction.commit();
        session.close();

        //Modify existing passenger (модифікація запису в таблиці по айдішці)
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Passenger existing = session.get(Passenger.class, 2L);
//                existing.setName("Modified passenger name");
//                session.persist(existing);
//            transaction.commit();
//        session.close();
    }
}