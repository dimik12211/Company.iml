package progect.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Car;
import progect.model.User;
import progect.service.CarService;
import progect.service.UserService;

import java.net.Authenticator;
import java.util.List;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping(value = "/hello")
    public ModelAndView getHelloMapping(){
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("helloHTML/hello.html");
            List<Car> cars = carService.findAllRentIsNullService();
            modelAndView.addObject("tableCar", cars);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/hello", params = "rent")
    public ModelAndView postHelloMapping(@RequestParam String carNumber){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/hello"));
        try {
            Car car = carService.findCarNumber(carNumber);
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserNameService(SecurityContextHolder.getContext().getAuthentication().getName());
            car.setOneToOneUser(user);
            car.setComment("Взят в аренду клиентом: " + user.getPassportData());
            user.setOneToOneCar(car);
            userService.updateService(user);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
