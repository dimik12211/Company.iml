package progect.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import progect.model.Role;
import progect.service.RoleService;

import java.util.List;

@Controller
public class RoleManagementController {

    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/admin/roleManagement")
    public ModelAndView getAdminRoleManagementMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/roleManagement.html");
            List<Role> roles = roleService.findAllService();
            modelAndView.addObject("tableRole", roles);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/roleManagement", params = "roleAdd")
    public ModelAndView postAdminRoleManagementRoleAddMapping(@RequestParam String name) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/roleManagement"));
        try {
            Role role = new Role(name);
            roleService.saveService(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/roleManagement", params = "ButtonRow")
    public ModelAndView postAdminRoleManagementRoleDeleteMapping(@RequestParam int id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/roleManagement"));
        try {
            Role role = roleService.findIdService(id);
            roleService.deleteService(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
