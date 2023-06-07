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

@Service
public class UserService implements UserDetailsService, ServiceRepository<User> {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean saveService(User user) {
        try {
            userDao.save(user);
            return true;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void deleteService(User user) {
        try {
            userDao.delete(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public void updateService(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    @Override
    public User findIdService(int id) {
        try {
            return userDao.findId(id);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAllService() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public User findUserNameService(String userName) {
        try {
            return userDao.findUserName(userName);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = findUserNameService(username);
            return user;
        } catch (UsernameNotFoundException e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

    public boolean existFindUsernameService(String userName) {
        try {
            return userDao.existFindUsername(userName);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return true;
        }
    }

    public User findPassportDataService(String passportData) {
        try {
            return userDao.findPassportData(passportData);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new User();
        }
    }

    public User findPhoneNumberService(String phoneNumber) {
        try {
            return userDao.findPhoneNumber(phoneNumber);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new User();
        }
    }

    public List<User> findFullNameService(String name, String surName, String patronymic) {
        try {
            return userDao.findFullName(name, surName, patronymic);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
