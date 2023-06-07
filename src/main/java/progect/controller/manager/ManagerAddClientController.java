package progect.controller.manager;

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
public class ManagerAddClientController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping(value = "/manager/addClient")
    public ModelAndView getManagerAddClientMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("managerHTML/functional/managerAddClient.html");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/addClient")
    public ModelAndView postManagerAddClientMapping(@RequestParam String name,
                                                @RequestParam String surName,
                                                @RequestParam String patronymic,
                                                @RequestParam String passportData,
                                                @RequestParam String address,
                                                @RequestParam String phoneNumber,
                                                @RequestParam String userName,
                                                @RequestParam String password,
                                                @RequestParam String passwordConfirm) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/addClient"));
        try {
            if (userService.existFindUsernameService(userName)) {
                return modelAndView;
            }
            User user = new User(name, surName, patronymic, passportData, address, phoneNumber, userName, password, passwordConfirm, true);
            Role role = roleService.findIdService(1);
            user.setRoles(Collections.singleton(role));
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            role.addUsers(user);
            userService.saveService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
