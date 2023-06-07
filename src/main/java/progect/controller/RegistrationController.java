package progect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Role;
import progect.model.User;
import progect.service.RoleService;
import progect.service.UserService;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/registration")
    public ModelAndView getRegistrationMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("registrationHTML/registration.html");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/registration")
    public ModelAndView postRegistrationMapping(@RequestParam String name,
                                                @RequestParam String surName,
                                                @RequestParam String patronymic,
                                                @RequestParam String passportData,
                                                @RequestParam String address,
                                                @RequestParam String phoneNumber,
                                                @RequestParam String userName,
                                                @RequestParam String password,
                                                @RequestParam String passwordConfirm) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("hello"));
        try {
            User user;
            if (userService.existFindUsernameService(userName)) {
                return modelAndView;
            } else {
                user = new User(name, surName, patronymic, passportData, address, phoneNumber, userName, password, passwordConfirm, true);
            }
            Role role = roleService.findIdService(1);
            user.setRoles(Collections.singleton(role));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            role.addUsers(user);
            userService.saveService(user);
            return modelAndView;
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return new ModelAndView(new RedirectView("registration"));
        }
    }
}
