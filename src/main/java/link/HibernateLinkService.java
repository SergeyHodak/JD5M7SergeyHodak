package link;

import org.hibernate.Session;
import org.hibernate.Transaction;
import storage.HibernateUtil;

import java.util.List;

public class HibernateLinkService implements LinkService {
    @Override
    public Link getByShortLink(String shortLink) {
        try(Session session = openSession()) {
            return session.get(Link.class, shortLink);
        }

//        Session session = openSession();
//        Link result = session.get(Link.class, shortLink);
//        session.close();
//        return result;
    }

    @Override
    public void save(Link link) {
        Session session = openSession();
            Transaction transaction = session.beginTransaction();
                session.persist(link);
            transaction.commit();
        session.close();
    }

    @Override
    public void deleteByShortLink(String shortLink) {
        Session session = openSession();
            Transaction transaction = session.beginTransaction();
                session.remove(getByShortLink(shortLink));
            transaction.commit();
        session.close();
    }

    @Override
    public List<Link> listAll() {
        try(Session session = openSession()) {
            return session.createQuery("from Link", Link.class).list();
        }
    }

    @Override
    public List<Link> search(String query) {
        String hql = "from Link where shortLink like '%" + query + "%' or link like '%" + query + "%'";
        try(Session session = openSession()) {
            return session.createQuery(hql, Link.class).list();
        }
    }

    private Session openSession() {
        return HibernateUtil.getInstance().getSessionFactory().openSession();
    }
}