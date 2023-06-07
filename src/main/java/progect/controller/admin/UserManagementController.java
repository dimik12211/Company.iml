package progect.controller.admin;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserManagementController {

    @GetMapping(value = "/admin/userManagement")
    public ModelAndView getAdminUserManagementMapping(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/userManagement.html");
            return modelAndView;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return modelAndView;
        }
    }
}
