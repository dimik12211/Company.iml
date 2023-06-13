package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.RegistrationTimeLoginDao;
import progect.model.RegistrationTimeLogin;
import progect.repository.ServiceRepository;

import java.util.List;

@Service //Анотация @Service, позволяет SpringFramework сканировать данный класс при компиляции проекта и автоматически подгружать поля с анотацией @Autowired
public class RegistrationTimeLoginService implements ServiceRepository<RegistrationTimeLogin> { //реализуем интерфейс ServiceRepository

    @Autowired // Анотация @Autowired указывает SpringFramework
    private RegistrationTimeLoginDao registrationTimeLoginDao; //Поле registrationTimeLoginDao

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public boolean saveService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.save(registrationTimeLogin); // вызываем метод сохранения строки в БД, если ошибок нет метод вернет true
            return true; //возвращаем true
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return false; //возвращаем false
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void deleteService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.delete(registrationTimeLogin); // вызываем метод удаления строки в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void updateService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.update(registrationTimeLogin); // вызываем метод обновления в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public RegistrationTimeLogin findIdService(int id) {
        try {
            return registrationTimeLoginDao.findId(id); // вызываем метод поиска по id в БД, если ошибок нет метод вернет объект RegistrationTimeLogin
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем false
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public List<RegistrationTimeLogin> findAllService() {
        try {
            return registrationTimeLoginDao.findAll(); // вызываем метод поиска всех объектов RegistrationTimeLogin
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем false
        }
    }
}
