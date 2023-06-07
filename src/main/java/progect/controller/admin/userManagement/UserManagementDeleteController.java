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
public class UserManagementDeleteController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/admin/userManagement/delete")
    public ModelAndView getAdminUserManagementDeleteMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<User> users = userService.findAllService();
            modelAndView.setViewName("adminHTML/functional/userManagement/userDelete.html");
            modelAndView.addObject("tableUser", users);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/userManagement/delete", params = "ButtonRow")
    public ModelAndView postAdminUserManagementDeleteMapping(@RequestParam String id){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/delete"));
        try {
            User user = userService.findIdService(Integer.parseInt(id));
            userService.deleteService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }
}
