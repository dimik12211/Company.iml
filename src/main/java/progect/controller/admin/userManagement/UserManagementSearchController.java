package progect.controller.admin.userManagement;

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
public class UserManagementSearchController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/userManagement/search")
    public ModelAndView getAdminUserManagementSearchMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<User> users = userService.findAllService();
            modelAndView.setViewName("adminHTML/functional/userManagement/userSearch.html");
            modelAndView.addObject("tableUser", users);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/userManagement/search", params = "updateData")
    public ModelAndView postAdminUserManagementUpdateDataMapping(@RequestParam int id,
                                                                 @RequestParam String name,
                                                                 @RequestParam String surName,
                                                                 @RequestParam String patronymic,
                                                                 @RequestParam String passportData,
                                                                 @RequestParam String address,
                                                                 @RequestParam String phoneNumber,
                                                                 @RequestParam String userName,
                                                                 @RequestParam String password,
                                                                 @RequestParam boolean active) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/search"));
        try {
            User user = userService.findIdService(id);
            user.setName(name);
            user.setSurName(surName);
            user.setPatronymic(patronymic);
            user.setPassportData(passportData);
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setUserName(userName);
            user.setPassword(password);
            user.setActive(active);
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/userManagement/search", params = "activeUser")
    public ModelAndView postAdminUserManagementActiveUserMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/search"));
        try {
            User user = userService.findIdService(id);
            user.setActive(user.getActive() == true ? false : true);
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }
}
