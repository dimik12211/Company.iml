package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.RegistrationTimeLoginDao;
import progect.model.RegistrationTimeLogin;
import progect.repository.ServiceRepository;

import java.util.List;

@Service
public class RegistrationTimeLoginService implements ServiceRepository<RegistrationTimeLogin> {

    @Autowired
    private RegistrationTimeLoginDao registrationTimeLoginDao;

    @Override
    public boolean saveService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.save(registrationTimeLogin);
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.delete(registrationTimeLogin);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(RegistrationTimeLogin registrationTimeLogin) {
        try {
            registrationTimeLoginDao.update(registrationTimeLogin);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public RegistrationTimeLogin findIdService(int id) {
        try {
            return registrationTimeLoginDao.findId(id);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<RegistrationTimeLogin> findAllService() {
        try {
            return registrationTimeLoginDao.findAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
}
