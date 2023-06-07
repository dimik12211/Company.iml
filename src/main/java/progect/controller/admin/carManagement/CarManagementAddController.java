package progect.controller.admin.carManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Car;
import progect.service.CarService;

@Controller
public class CarManagementAddController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/admin/carManagement/add")
    public ModelAndView setAdminCarManagementMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/carManagement/carAdd.html");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/carManagement/add", params = "carAdd")
    public ModelAndView postAdminCarManagementAddMapping(@RequestParam String model,
                                                         @RequestParam String engine,
                                                         @RequestParam String transmission,
                                                         @RequestParam String carNumber,
                                                         @RequestParam String comment) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/carManagement/add"));
        try {
            Car car = new Car(model, engine, transmission, true, carNumber, comment);
            carService.saveService(car);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
