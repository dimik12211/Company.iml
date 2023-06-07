package progect.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import progect.model.RegistrationTimeLogin;
import progect.model.Role;
import progect.repository.DaoRepository;
import progect.util.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class RegistrationTimeLoginDao implements DaoRepository<RegistrationTimeLogin> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public boolean save(RegistrationTimeLogin registrationTimeLogin) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(registrationTimeLogin);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return false;
        }
    }

    @Override
    public void delete(RegistrationTimeLogin registrationTimeLogin) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(registrationTimeLogin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public void update(RegistrationTimeLogin registrationTimeLogin) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(registrationTimeLogin);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public RegistrationTimeLogin findId(int id) {
        try {
            RegistrationTimeLogin registrationTimeLogin = session.get(RegistrationTimeLogin.class, id);
            return registrationTimeLogin;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
    }

    @Override
    public List<RegistrationTimeLogin> findAll() {
        try {
            List<RegistrationTimeLogin> registrationTimeLogins = session.createQuery("select r from RegistrationTimeLogin r", RegistrationTimeLogin.class).getResultList();
            return registrationTimeLogins;
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
            return null;
        }
    }
}
