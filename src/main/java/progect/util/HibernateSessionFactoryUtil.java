package progect.util;

import ch.qos.logback.core.net.server.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import progect.model.Car;
import progect.model.RegistrationTimeLogin;
import progect.model.Role;
import progect.model.User;

public class HibernateSessionFactoryUtil {

    private static SessionFactory sessionFactory;

    //изменил возвращаемый тип с SessionFactory на Session и открываю сессию теперь здесь, в Dao убрал openSession()

    /*Создает SessionFactory и возвращает Session
     *Created SessionFactory and return Session*/

    public static Session getSessionFactory() {
        try {
            if (sessionFactory == null) {
                Configuration configuration = new Configuration().addAnnotatedClass(User.class).addAnnotatedClass(Role.class).addAnnotatedClass(Car.class).addAnnotatedClass(RegistrationTimeLogin.class)
                        .setProperty(Environment.DRIVER, "org.postgresql.Driver")
                        .setProperty(Environment.URL, "jdbc:postgresql://localhost:5432/DifferentiationOfUserAccessRights")
                        .setProperty(Environment.USER, "postgres")
                        .setProperty(Environment.PASS, "123321")
                        .setProperty(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect")
                        .setProperty(Environment.SHOW_SQL, "false")
                        .setProperty(Environment.HBM2DDL_AUTO, "update");
                sessionFactory = configuration.buildSessionFactory();
            }
        } catch (HibernateException e) {
            e.getMessage();
            e.printStackTrace();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return sessionFactory.openSession();
        }
    }
}
