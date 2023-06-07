package progect.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;

@Controller
public class ManagerCancelCarRentalController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @GetMapping(value = "/manager/cancelCarRental")
    public ModelAndView getManagerCancelCarRentalMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            List<Car> cars = carService.findAllRentIsNotNullService();
            modelAndView.setViewName("managerHTML/functional/managerCancelCarRental.html");
            modelAndView.addObject("tableCar", cars);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/cancelCarRental")
    public ModelAndView postManagerCancelCarRentalMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/cancelCarRental"));
        try {
            Car car = carService.findIdService(id);
            car.setComment(null);
            car.setOneToOneUser(null);
            carService.updateService(car);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
