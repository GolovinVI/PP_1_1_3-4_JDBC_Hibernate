package jm.task.core.jdbc.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = getSessionFactory();
    public UserDaoHibernateImpl() {

    }

    Transaction transaction = null;
    @Deprecated
    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS CONSUMER(id mediumint not null auto_increment, name VARCHAR(50), lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        session.close();

    }

    @Deprecated
    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.createNativeQuery("DROP TABLE IF EXISTS CONSUMER").executeUpdate();
        transaction.commit();
        session.close();



    }


    @Deprecated
    @Override
    public void saveUser(String name, String lastName, byte age) {
            Session session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.save(user);
            transaction.commit();


    }

    @Deprecated
    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM CONSUMER WHERE id = ?");
        transaction.commit();
        session.close();


    }


    @Deprecated
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            List<User> userList = session.createNativeQuery("Select * from CONSUMER", User.class).getResultList();
            return userList;
        }catch (Exception e){
            return null;
        }finally {
            session.close();
        }
    }

    @Deprecated
    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.createNativeQuery("DELETE FROM CONSUMER").executeUpdate();
        transaction.commit();
        session.close();

    }
}
