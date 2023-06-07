package progect.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarManagementController {

    @GetMapping(value = "/admin/carManagement")
    public ModelAndView getAdminCarManagementMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/carManagement.html");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
