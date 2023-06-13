package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import progect.dao.UserDao;
import progect.model.User;
import progect.repository.ServiceRepository;

import java.util.ArrayList;
import java.util.List;

@Service //Анотация @Service, позволяет SpringFramework сканировать данный класс при компиляции проекта и автоматически подгружать поля с анотацией @Autowired
public class UserService implements UserDetailsService, ServiceRepository<User> { //реализуем интерфейсы ServiceRepository и UserDetailsService

    @Autowired // Анотация @Autowired указывает SpringFramework
    private UserDao userDao; //Поле userDao

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public boolean saveService(User user) {
        try {
            userDao.save(user); // вызываем метод сохранения строки в БД, если ошибок нет метод вернет true
            return true; //возвращаем true
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return false; //возвращаем false
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void deleteService(User user) {
        try {
            userDao.delete(user); // вызываем метод удаления строки в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void updateService(User user) {
        try {
            userDao.update(user); // вызываем метод обновления в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public User findIdService(int id) {
        try {
            return userDao.findId(id); // вызываем метод поиска по id в БД, если ошибок нет метод вернет объект User
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public List<User> findAllService() {
        try {
            return userDao.findAll(); // вызываем метод поиска всех пользователей
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    public User findUserNameService(String userName) {
        try {
            return userDao.findUserName(userName);  // вызываем метод поиска пользователей по userName
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    @Override //Переопределяем метод из интерфейса UserDetailsService
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //данный метод обязателен к переопределению из интерфейса UserDetailsService
        try {
            User user = findUserNameService(username); //вызываем метод для поиска пользователей по userName
            return user;
        } catch (UsernameNotFoundException e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    public boolean existFindUsernameService(String userName) {
        try {
            return userDao.existFindUsername(userName); //вызываем метод проверяющий существует пользователь с указанным userName или не существует
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return true; //возвращаем true
        }
    }

    public User findPassportDataService(String passportData) {
        try {
            return userDao.findPassportData(passportData);  // вызываем метод поиска пользователей по passportData
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return new User();
        }
    }

    public User findPhoneNumberService(String phoneNumber) {
        try {
            return userDao.findPhoneNumber(phoneNumber); // вызываем метод поиска пользователей по phoneNumber
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return new User();
        }
    }

    public List<User> findFullNameService(String name, String surName, String patronymic) {
        try {
            return userDao.findFullName(name, surName, patronymic); // вызываем метод поиска пользователей по ФИО
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return new ArrayList<>();
        }
    }
}
