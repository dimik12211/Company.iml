package progect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @GetMapping(value = "/admin")
    public ModelAndView getAdminMapping(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/admin.html");
            return modelAndView;
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
            return modelAndView;
        }
    }
}
