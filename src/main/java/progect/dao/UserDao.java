package progect.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import progect.model.User;
import progect.repository.DaoRepository;
import progect.util.HibernateSessionFactoryUtil;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao implements DaoRepository<User> {
    private Session session = HibernateSessionFactoryUtil.getSessionFactory();


    @Override
    public boolean save(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        }
        catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }
        catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        }
        catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public User findId(int id) {
        try {
            User user = session.get(User.class, id);
            return user;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        try {
            List<User> users = session.createQuery("select u from User u", User.class).getResultList();
            return users;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public User findUserName(String userName){
        try{
            Query query = session.createQuery("select u from User u where u.userName = :userName");
            query.setParameter("userName", userName);
            User user = (User) query.getSingleResult();
            return user;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public boolean existFindUsername(String userName){
        try {
            Query query = session.createQuery("select u from User u where u.userName = :userName");
            query.setParameter("userName", userName);
            return query.getResultList().size() == 1 ? true : false;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return true;
        }
    }

    public User findPassportData(String passportData){
        try {
            Query query = session.createQuery("select u from User u where u.passportData = :passportData");
            query.setParameter("passportData", passportData);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new User();
        }
    }

    public User findPhoneNumber(String phoneNumber){
        try {
            Query query = session.createQuery("select u from User u where u.phoneNumber = :phoneNumber");
            query.setParameter("phoneNumber", phoneNumber);
            User user = (User) query.getSingleResult();
            return user;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new User();
        }
    }

    public List<User> findFullName(String name, String surName, String patronymic){
        try {
            Query query = session.createQuery("select u from User u where u.name = :name and u.surName = :surName and u.patronymic = :patronymic");
            query.setParameter("name", name);
            query.setParameter("surName", surName);
            query.setParameter("patronymic", patronymic);
            List<User> users = query.getResultList();
            return users;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
