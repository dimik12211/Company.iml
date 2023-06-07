package progect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ManagerController {

    @GetMapping(value = "/manager")
    public ModelAndView getAdminMapping(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("managerHTML/manager.html");
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
