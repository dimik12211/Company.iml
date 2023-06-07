package progect.dao;

import ch.qos.logback.core.net.server.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import progect.model.Car;
import progect.model.User;
import progect.repository.DaoRepository;
import progect.util.HibernateSessionFactoryUtil;

import java.util.List;

@Repository
public class CarDao implements DaoRepository<Car> {

    private Session session = HibernateSessionFactoryUtil.getSessionFactory();

    @Override
    public boolean save(Car car) {
        try {
            Transaction transaction = session.beginTransaction();
            session.save(car);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(Car car) {
        try {
            Transaction transaction = session.beginTransaction();
            session.delete(car);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void update(Car car) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(car);
            transaction.commit();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public Car findId(int id) {
        try {
            return session.get(Car.class, id);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> findAll() {
        try {
            List<Car> cars = session.createQuery("select c from Car c", Car.class).getResultList();
            return cars;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public List<Car> findAllRentIsNull(){
        try {
            Query query = session.createQuery("select c from Car c where c.OneToOneUser = null");
            List<Car> cars = query.getResultList();
            return cars;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public List<Car> findAllRentIsNotNull(){
        try {
            Query query = session.createQuery("select c from Car c where c.OneToOneUser != null");
            query.setFetchSize(1);
            List<Car> cars = query.getResultList();
            return cars;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public Car findCarNumber(String carNumber){
        try {
            Query query = session.createQuery("select c from Car c where c.carNumber = :carNumber");
            query.setParameter("carNumber", carNumber);
            Car car = (Car) query.getSingleResult();
            return car;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new Car();
        }
    }
}
