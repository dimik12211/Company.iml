package progect.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.User;
import progect.service.UserService;

import java.util.List;

@Controller
public class ManagerDeleteUpdateClientController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/manager/deleteUpdateClient")
    public ModelAndView getManagerDeleteUpdateClientMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<User> users = userService.findAllService();
            modelAndView.setViewName("managerHTML/functional/managerDeleteUpdateClient.html");
            modelAndView.addObject("tableUser", users);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/deleteUpdateClient", params = "updateData")
    public ModelAndView postManagerDeleteUpdateClientUpdateDataMapping(@RequestParam int id,
                                                                 @RequestParam String name,
                                                                 @RequestParam String surName,
                                                                 @RequestParam String patronymic,
                                                                 @RequestParam String passportData,
                                                                 @RequestParam String address,
                                                                 @RequestParam String phoneNumber) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/deleteUpdateClient"));
        try {
            User user = userService.findIdService(id);
            user.setName(name);
            user.setSurName(surName);
            user.setPatronymic(patronymic);
            user.setPassportData(passportData);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/manager/deleteUpdateClient", params = "activeUser")
    public ModelAndView postManagerDeleteUpdateClientActiveUserMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/deleteUpdateClient"));
        try {
            User user = userService.findIdService(id);
            user.setActive(user.getActive() == true ? false : true);
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/deleteUpdateClient", params = "deleteUser")
    public ModelAndView postManagerDeleteUpdateClientDeleteUserMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/deleteUpdateClient"));
        try {
            User user = userService.findIdService(id);
            userService.deleteService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
