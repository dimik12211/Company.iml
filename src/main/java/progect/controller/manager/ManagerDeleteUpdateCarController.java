package progect.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Car;
import progect.service.CarService;

import java.util.List;

@Controller
public class ManagerDeleteUpdateCarController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/manager/deleteUpdateCar")
    public ModelAndView getManagerDeleteUpdateCarMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("managerHTML/functional/managerDeleteUpdateCar.html");
            List<Car> cars = carService.findAllService();
            modelAndView.addObject("tableCar", cars);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/deleteUpdateCar", params = "updateData")
    public ModelAndView postManagerDeleteUpdateCarUpdateDataMapping(@RequestParam int id,
                                                                @RequestParam String model,
                                                                @RequestParam String engine,
                                                                @RequestParam String transmission,
                                                                @RequestParam boolean availability,
                                                                @RequestParam String carNumber,
                                                                @RequestParam String comment){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/deleteUpdateCar"));
        try {
            Car car = carService.findIdService(id);
            car.setModel(model);
            car.setEngine(engine);
            car.setTransmission(transmission);
            car.setAvailability(availability);
            car.setCarNumber(carNumber);
            car.setComment(comment);
            carService.updateService(car);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/deleteUpdateCar", params = "deleteCar")
    public ModelAndView postManagerDeleteUpdateCarDeleteMapping(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/deleteUpdateCar"));
        try {
            Car car = carService.findIdService(id);
            carService.deleteService(car);
        }catch (Exception e){
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
