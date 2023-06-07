package progect.controller.admin.carManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Car;
import progect.service.CarService;

import java.util.List;

@Controller
public class CarManagementAllController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/admin/carManagement/search")
    public ModelAndView getAdminCarManagementSearchMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/carManagement/carAll.html");
            List<Car> cars = carService.findAllService();
            modelAndView.addObject("tableCar", cars);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/carManagement/search", params = "updateData")
    public ModelAndView postAdminCarManagementUpdateDataMapping(@RequestParam int id,
                                                                @RequestParam String model,
                                                                @RequestParam String engine,
                                                                @RequestParam String transmission,
                                                                @RequestParam boolean availability,
                                                                @RequestParam String carNumber,
                                                                @RequestParam String comment){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/carManagement/search"));
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

    @PostMapping(value = "/admin/carManagement/search", params = "deleteCar")
    public ModelAndView postAdminCarManagementDeleteMapping(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/carManagement/search"));
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
