package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.CarDao;
import progect.model.Car;
import progect.repository.ServiceRepository;

import java.util.List;

@Service
public class CarService implements ServiceRepository<Car> {

    @Autowired
    private CarDao carDao;

    @Override
    public boolean saveService(Car car) {
        try {
            return carDao.save(car);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteService(Car car) {
        try {
            carDao.delete(car);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(Car car) {
        try {
            carDao.update(car);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public Car findIdService(int id) {
        try {
            return carDao.findId(id);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Car> findAllService() {
        try {
            return carDao.findAll();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public List<Car> findAllRentIsNullService(){
        try {
            return carDao.findAllRentIsNull();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public List<Car> findAllRentIsNotNullService(){
        try {
            return carDao.findAllRentIsNotNull();
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public Car findCarNumber(String carNumber) {
        try {
            return carDao.findCarNumber(carNumber);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
}
