package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.RoleDao;
import progect.model.Role;
import progect.repository.ServiceRepository;

import java.util.List;

@Service //Анотация @Service, позволяет SpringFramework сканировать данный класс при компиляции проекта и автоматически подгружать поля с анотацией @Autowired
public class RoleService implements ServiceRepository<Role> { //реализуем интерфейс ServiceRepository

    @Autowired // Анотация @Autowired указывает SpringFramework
    private RoleDao roleDao; //Поле roleDao


    @Override //Переопределяем метод из интерфейса ServiceRepository
    public boolean saveService(Role role) {
        try {
            roleDao.save(role); // вызываем метод сохранения строки в БД, если ошибок нет метод вернет true
            return true; //возвращаем true
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return false; //возвращаем false
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void deleteService(Role role) {
        try {
            roleDao.delete(role); // вызываем метод удаления строки в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void updateService(Role role) {
        try {
            roleDao.update(role); // вызываем метод обновления в БД
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public Role findIdService(int id) {
        try {
            return roleDao.findId(id); // вызываем метод поиска по id в БД, если ошибок нет метод вернет объект Role
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public List<Role> findAllService() {
        try {
            return roleDao.findAll(); // вызываем метод поиска всех ролей
        } catch (Exception e) {
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }
}
