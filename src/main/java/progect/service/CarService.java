package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.CarDao;
import progect.model.Car;
import progect.repository.ServiceRepository;

import java.util.List;

@Service //Анотация @Service, позволяет SpringFramework сканировать данный класс при компиляции проекта и автоматически подгружать поля с анотацией @Autowired
public class CarService implements ServiceRepository<Car> { //реализуем интерфейс ServiceRepository

    @Autowired // Анотация @Autowired указывает SpringFramework
    private CarDao carDao; //Поле carDao

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public boolean saveService(Car car) {
        try {
            return carDao.save(car); // вызываем метод сохранения строки в БД, если ошибок нет метод вернет true
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return false; //возвращаем false
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void deleteService(Car car) {
        try {
            carDao.delete(car);// вызываем метод удаления строки в БД
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public void updateService(Car car) {
        try {
            carDao.update(car); // вызываем метод обновления в БД
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public Car findIdService(int id) {
        try {
            return carDao.findId(id);// вызываем метод поиска по id в БД, если ошибок нет метод вернет объект Car
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    @Override //Переопределяем метод из интерфейса ServiceRepository
    public List<Car> findAllService() {
        try {
            return carDao.findAll(); // вызываем метод поиска всех авто в БД, если ошибок нет метод вернет List с объектами Car
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    public List<Car> findAllRentIsNullService(){
        try {
            return carDao.findAllRentIsNull(); // вызываем метод поиска авто которые еще не взяли в аренду
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    public List<Car> findAllRentIsNotNullService(){
        try {
            return carDao.findAllRentIsNotNull(); //вызываем метод поиска авто которые взяли в аренду
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }

    public Car findCarNumber(String carNumber) {
        try {
            return carDao.findCarNumber(carNumber); //вызываем метод поиска авто по номеру машины
        }catch (Exception e){
            e.getMessage(); // в случае ошибки пишем текст ошибки в консоли
            e.printStackTrace(); // в случае ошибки пишем в консоли трассировку ошибки
            return null; //возвращаем null
        }
    }
}
