package progect.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import progect.model.Role;
import progect.repository.DaoRepository;
import progect.util.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class RoleDao implements DaoRepository<Role> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public boolean save(Role role) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(role);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(Role role) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(role);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Role role) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(role);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public Role findId(int id) {
        try {
            Role role = session.get(Role.class, id);
            return role;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> findAll() {
        try {
            List<Role> roles = session.createQuery("select r from Role r", Role.class).getResultList();
            return roles;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
}
