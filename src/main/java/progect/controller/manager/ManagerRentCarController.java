package progect.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
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
public class ManagerRentCarController {

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    private User user;

    @GetMapping(value = "/manager/rentCar")
    public ModelAndView getManagerRentCarMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("managerHTML/functional/managerRentCar.html");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/rentCar", params = "searchUser")
    public ModelAndView postManagerRentCarSearchMapping(@RequestParam String name,
                                                        @RequestParam String surName,
                                                        @RequestParam String patronymic,
                                                        @RequestParam String passportData,
                                                        @RequestParam String phoneNumber) {
        ModelAndView modelAndView = new ModelAndView("managerHTML/functional/managerRentCar.html");
        try {
            if (passportData.length() > 0) {
                user = userService.findPassportDataService(passportData);
                setModelAndViewParams(modelAndView);
            } else if (phoneNumber.length() > 0) {
                user = userService.findPhoneNumberService(phoneNumber);
                setModelAndViewParams(modelAndView);
            } else if ((name.length() > 0) && (surName.length() > 0) && (patronymic.length() > 0)) {
                List<User> users = userService.findFullNameService(name, surName, patronymic);
                modelAndView.addObject("TrueOrFalse", true);
                modelAndView.addObject("TrueOrFalse_List", true);
                modelAndView.addObject("tableUser", users);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    private void setModelAndViewParams(ModelAndView modelAndView) {
        modelAndView.addObject("TrueOrFalse", true);
        modelAndView.addObject("TrueOrFalseCar", true);
        modelAndView.addObject("tableUser", user);
        List<Car> cars = carService.findAllRentIsNullService();
        modelAndView.addObject("tableCar", cars);
    }

    @PostMapping(value = "/manager/rentCar", params = "define")
    public ModelAndView postManagerRentCarDefineMapping(@RequestParam String passportData) {
        ModelAndView modelAndView = new ModelAndView("managerHTML/functional/managerRentCar.html");
        try {
            user = userService.findPassportDataService(passportData);
            setModelAndViewParams(modelAndView);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/manager/rentCar", params = "rentButton")
    public ModelAndView postManagerRentCarMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/manager/rentCar"));
        try {
            Car car = carService.findIdService(id);
            car.setOneToOneUser(user);
            car.setComment("Взят в аренду клиентом: " + user.getPassportData());
            user.setOneToOneCar(car);
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
