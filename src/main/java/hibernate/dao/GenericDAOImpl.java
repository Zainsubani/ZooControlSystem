package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
        Session session = getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery(hsql);
        if (params != null) {
            for (String i : params.keySet()) {
                query.setParameter(i, params.get(i));
            }
        }

        List<T> result = null;
        if ((hsql.toUpperCase().indexOf("DELETE") == -1)
                && (hsql.toUpperCase().indexOf("UPDATE") == -1)
                && (hsql.toUpperCase().indexOf("INSERT") == -1)) {
            result = query.list();
        } else {
        }
        session.getTransaction().commit();

        return result;
    }

    public GenericDAOImpl(){
        currentSession = getSessionFactory().openSession();
    }
}
