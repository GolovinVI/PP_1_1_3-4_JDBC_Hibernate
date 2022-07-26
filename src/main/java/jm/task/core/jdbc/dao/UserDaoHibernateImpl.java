package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = getSessionFactory();
    public UserDaoHibernateImpl() {

    }



    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {transaction = session.beginTransaction();
        session.createNativeQuery("CREATE TABLE IF NOT EXISTS CONSUMER(id mediumint not null auto_increment, name VARCHAR(50), lastname VARCHAR(50), age tinyint, PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }

    }


    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS CONSUMER").executeUpdate();
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session!=null){
                session.close();
            }
        }



    }


    @Override
    public void saveUser(String name, String lastName, byte age) {
            Session session = getSessionFactory().openSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = new User();
                user.setName(name);
                user.setLastName(lastName);
                user.setAge(age);
                session.save(user);
                transaction.commit();
            }catch (Exception e){
                e.printStackTrace();
                if (transaction!=null){
                    transaction.rollback();
                }
            }finally {
                if(session!=null){
                    session.close();
                }
            }


    }


    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createNativeQuery("DELETE FROM CONSUMER WHERE id = ?");
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction!=null){
                transaction.rollback();
            }
        }finally {
            if(session!=null){
                session.close();
            }
        }


    }



    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList = new ArrayList<>();
        try {
            userList = session.createNativeQuery("Select * from CONSUMER", User.class).getResultList();
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
        return userList;
    }


    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

        session.createNativeQuery("DELETE FROM CONSUMER").executeUpdate();
        transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null)
                session.close();
        }
    }
}
