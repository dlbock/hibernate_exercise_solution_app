package hibernate;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class ContactRepository {
    private SessionFactory sessionFactory;

    public ContactRepository() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Contact.class);
        this.sessionFactory = configuration.configure().buildSessionFactory();
    }

    public void save(Contact contact) {
        Session session = this.sessionFactory.openSession();
        session.beginTransaction();
        session.save(contact);
        session.getTransaction().commit();
        session.close();
    }

    public List<Contact> findAll() {
        Session session = this.sessionFactory.openSession();
        List contacts = session.createQuery("from Contact").list();
        session.close();
        return contacts;
    }

    public void clean() {
        Session session = this.sessionFactory.openSession();
        Query query = session.createQuery("delete from Contact");
        query.executeUpdate();
        session.close();
    }
}
