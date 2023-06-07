package progect.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import progect.model.RegistrationTimeLogin;
import progect.model.User;
import progect.service.RegistrationTimeLoginService;

import java.util.Date;

@Component
public class AuthenticationConfig implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

    @Autowired
    private RegistrationTimeLoginService registrationTimeLoginService;

    @Override
    public void onApplicationEvent(InteractiveAuthenticationSuccessEvent interactiveAuthenticationSuccessEvent) {
        User user = (User) interactiveAuthenticationSuccessEvent.getAuthentication().getPrincipal();
        Date date = new Date();
        RegistrationTimeLogin registrationTimeLogin = new RegistrationTimeLogin(user.getName(), user.getSurName(), user.getPatronymic(), date.toString(), user.getUsername());
        registrationTimeLoginService.saveService(registrationTimeLogin);
    }
}
