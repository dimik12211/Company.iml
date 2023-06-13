package progect.util;

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

    private static SessionFactory sessionFactory; // свойство класса sessionFactory

    /*Создает SessionFactory и возвращает Session
     *Created SessionFactory and return Session*/

    public static Session getSessionFactory() { // метод содержащий в себе параметры для подключения к БД
        try {
            if (sessionFactory == null) { //если сессия пустая/еще не существует
                //Ниже добавляем в конфигурацию параметры подключения и указывает модели (классы) на основе которых строятся таблицы в БД
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
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace();// в случае ошибки пишем в консоли трассировку ошибки
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace();// в случае ошибки пишем в консоли трассировку ошибки
        }
        return sessionFactory.openSession(); //возвращаем сессию
    }
}
