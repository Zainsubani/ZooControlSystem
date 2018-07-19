package hibernate.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T> {

    // session/transaction methods
    public Session openCurrentSession();
    public Session openCurrentSessionwithTransaction();
    public void closeCurrentSession();
    public void closeCurrentSessionwithTransaction();
    public Session getCurrentSession();
    public void setCurrentSession(Session currentSession);
    public Transaction getCurrentTransaction();
    public void setCurrentTransaction(Transaction currentTransaction);

    // CRUD methods
    public T get(Class<T> cl, Serializable id);
    public Serializable save(T object);
    public void update(T object);
    public void delete(T object);
    public List<T> query(String hsql, Map<String, Object> params);
}