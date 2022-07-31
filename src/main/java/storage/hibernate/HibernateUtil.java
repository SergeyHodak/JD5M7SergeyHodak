package storage.hibernate;

import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import passenger.Passenger;
import storage.DatabaseInitService;
import tests.Person;
import tests.PersonInfo;
import tests.Project;
import tests.Workplace;
import ticket.Ticket;

import java.util.Arrays;
import java.util.Collections;
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
                .addAnnotatedClass(Passenger.class) //вказуємо що цей класс аннотований, гібернейт використовуй його
                .addAnnotatedClass(Ticket.class)
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(PersonInfo.class)
                .addAnnotatedClass(Workplace.class)
                .addAnnotatedClass(Project.class)
                .buildSessionFactory();
    }

    public static HibernateUtil getInstance() {
        return INSTANCE;
    }

    public void close() {
        sessionFactory.close();
    }

    public static void main(String[] args) {
        //Init DB using flyway
        new DatabaseInitService().initDb();

        HibernateUtil util = HibernateUtil.getInstance();

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
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Passenger newPassenger = new Passenger();
//                newPassenger.setName("New Passenger Name");
//                newPassenger.setPassport("yy99");
//                session.persist(newPassenger);
//                System.out.println("newPassenger = " + newPassenger);
//            transaction.commit();
//        session.close();

        //Modify existing passenger (модифікація запису в таблиці по айдішці)
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Passenger existing = session.get(Passenger.class, 2L);
//                existing.setName("Modified passenger name");
//                session.persist(existing);
//            transaction.commit();
//        session.close();

        //List all tickets (HQL запит на отримання всіх квитків)
//        Session session = util.getSessionFactory().openSession();
//        List<Ticket> tickets = session.createQuery("from Ticket", Ticket.class).list();
//        System.out.println("tickets = " + tickets);
//        session.close();




        //List all persons
        Session session = util.getSessionFactory().openSession();
        List<Person> persons = session.createQuery("from Person", Person.class).list();
        System.out.println("persons = " + persons);
        session.close();

        //new address
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Person person = new Person();
//                person.setAddressList(Arrays.asList("addressA", "addressB"));
//                session.persist(person);
//            transaction.commit();
//        session.close();

        //new person +info
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Person person = session.get(Person.class, 3L);
//                PersonInfo info = new PersonInfo();
//                info.setPersonId(person.getId());
//                info.setName("Some specific name");
//                person.setPersonInfo(info);
//                session.persist(person);
//            transaction.commit();
//        session.close();

        //add workspace
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Person person = session.get(Person.class, 3L);
//                Workplace workplace = new Workplace();
//                workplace.setPlace("Google");
//                workplace.setPerson(person);
//                person.setWorkplaces(List.of(workplace));
//                session.persist(workplace);
//                session.persist(person);
//            transaction.commit();
//        session.close();

//        Session session = util.getSessionFactory().openSession();
//        Workplace workplace = session.get(Workplace.class, 1);
//        System.out.println("workplace.getPerson() = " + workplace.getPerson());
//        System.out.println("workplace = " + workplace);
//        session.close();

        //Add project to person
//        Session session = util.getSessionFactory().openSession();
//            Transaction transaction = session.beginTransaction();
//                Project project = new Project();
//                project.setName("Save planet");
//                session.persist(project);
//                Person person = session.get(Person.class, 3L);
//                person.setProjects(Collections.singleton(project));
//                session.persist(person);
//            transaction.commit();
//        session.close();
    }
}