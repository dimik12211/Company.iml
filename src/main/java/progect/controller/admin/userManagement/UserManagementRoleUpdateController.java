package progect.controller.admin.userManagement;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserManagementRoleUpdateController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private Set<Role> Roles = new HashSet<>();

    @GetMapping(value = "/admin/userManagement/roleUpdate")
    public ModelAndView getUserManagementRoleUpdateMapping() {
        ModelAndView modelAndView = new ModelAndView();
        try {
            modelAndView.setViewName("adminHTML/functional/userManagement/userRoleUpdate.html");
            List<Role> roles = roleService.findAllService();
            modelAndView.addObject("tableRole", roles);
            modelAndView.addObject("tableRoleSelect", Roles);
            if (Roles.size() != 0) {
                modelAndView.addObject("TrueOrFalse", true);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/userManagement/roleUpdate", params = "ButtonRow")
    public ModelAndView postAdminUserManagementAddRoleInUserMapping(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/roleUpdate"));
        try {
            Role role = roleService.findIdService(Integer.parseInt(id));
            if (!Roles.contains(role)) {
                Roles.add(role);
            }
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/userManagement/roleUpdate", params = "ButtonDeleteRow")
    public ModelAndView postAdminUserManagementDeleteRoleInUserMapping(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/roleUpdate"));
        try {
            Role role = roleService.findIdService(Integer.parseInt(id));
            Roles.remove(role);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }

    @PostMapping(value = "/admin/userManagement/roleUpdate", params = "roleUpdateButton")
    public ModelAndView postAdminUserManagementUpdateRoleInUserMapping(@RequestParam String userName){
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/admin/userManagement/roleUpdate"));
        try {
            User user = userService.findUserNameService(userName);
            user.setRoles(Roles);
            for(Role r : Roles){
                r.addUsers(user);
            }
            userService.updateService(user);
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
        return modelAndView;
    }
}
