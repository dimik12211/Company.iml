package progect.controller.admin.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Role;
import progect.model.User;
import progect.service.RoleService;
import progect.service.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserManagementAddController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Set<Role> Roles = new HashSet<>();

    @GetMapping(value = "/admin/userManagement/add")
    public ModelAndView getAdminUserManagementAddMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/userManagement/userAdd.html");
            addParametrsInModelAndView(modelAndView);
            modelAndView.addObject("tableRoleSelect", Roles);
            if (Roles.size() != 0) {
                modelAndView.addObject("TrueOrFalse", true);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    private void addParametrsInModelAndView(ModelAndView modelAndView){
        List<Role> roles = roleService.findAllService();
        modelAndView.addObject("tableRole", roles);
    }

    @PostMapping(value = "/admin/userManagement/add", params = "ButtonRow")
    public ModelAndView postAdminUserManagementAddRoleInUserMapping(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/add"));
        try {
            Role role = roleService.findIdService(Integer.parseInt(id));
            if (!Roles.contains(role)) {
                Roles.add(role);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/userManagement/add", params = "ButtonDeleteRow")
    public ModelAndView postAdminUserManagementDeleteRoleInUserMapping(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/add"));
        try {
            Role role = roleService.findIdService(Integer.parseInt(id));
            Roles.remove(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }

    @PostMapping(value = "/admin/userManagement/add", params = "userAdd")
    public ModelAndView postAdminUserManagementUserAddMapping(@RequestParam String name,
                                                              @RequestParam String surName,
                                                              @RequestParam String patronymic,
                                                              @RequestParam String passportData,
                                                              @RequestParam String address,
                                                              @RequestParam String phoneNumber,
                                                              @RequestParam String userName,
                                                              @RequestParam String password,
                                                              @RequestParam String passwordConfirm) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/add"));
        try {
            if(Roles.size() == 0){
                throw new NullPointerException("Не выбрано ни одной роли");
            }
            if(userService.existFindUsernameService(userName)){
                throw new Exception("Пользователь с таким userName уже существует");
            }
            User user = new User(name, surName, patronymic, passportData, address, phoneNumber, userName, password, passwordConfirm, true);
            user.setRoles(Roles);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            for(Role r : Roles){
                r.addUsers(user);
            }
            userService.saveService(user);
        }catch (NullPointerException e){
            e.getMessage();
            e.printStackTrace();
            modelAndView.addObject("Exception_TrueOrFalse", true);
            modelAndView.addObject("Exception", e.getMessage());
            addParametrsInModelAndView(modelAndView);
        }
        catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        } finally {
            return modelAndView;
        }
    }
}
