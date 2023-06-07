package progect.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import progect.dao.RoleDao;
import progect.model.Role;
import progect.repository.ServiceRepository;

import java.util.List;

@Service
public class RoleService implements ServiceRepository<Role> {

    @Autowired
    private RoleDao roleDao;


    @Override
    public boolean saveService(Role role) {
        try {
            roleDao.save(role);
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteService(Role role) {
        try {
            roleDao.delete(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(Role role) {
        try {
            roleDao.update(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public Role findIdService(int id) {
        try {
            return roleDao.findId(id);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Role> findAllService() {
        try {
            return roleDao.findAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
}
