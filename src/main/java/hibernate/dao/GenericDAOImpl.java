package hibernate.dao;

import hibernate.entity.Animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class GenericDAOImpl<T> implements GenericDAO<T> {
    private Session currentSession;
    private Transaction currentTransaction;

    public Session openCurrentSession() {
        return getSessionFactory().openSession();
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public static SessionFactory getSessionFactory() {
        try {
            SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
            return sessionFactory;
        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public T get(Class<T> cl, Serializable id) {
        T entity = getCurrentSession().get(cl, id);
        return entity;
    }

    public Serializable save(T object) {
        return getCurrentSession().save(object);
    }

    public void update(T object) {
        getCurrentSession().update(object);
    }

    public void delete(T object) {
        getCurrentSession().delete(object);
    }

    public List<T> query(String hsql, Map<String, Object> params) {
        return null;
    }

    public GenericDAOImpl(){
        currentSession = getSessionFactory().openSession();
    }
}
