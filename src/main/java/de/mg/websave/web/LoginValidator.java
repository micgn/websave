package de.mg.websave.web;

import de.mg.websave.service.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import save.service.PasswordModel;


@Component
@Scope("request")
public class LoginValidator implements Validator {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LoginTO.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        LoginTO command = (LoginTO) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pw", "error.ValueEmpty", "must be filled");

        WebSession session = applicationContext.getBean(WebSession.class);
        PasswordModel passwordModel = session.getPasswordModel();
        if (!passwordModel.isCorrectPassword(command.getPw())) {
            errors.rejectValue("pw", "error.InvalidLogin", null, "login invalid");
        }
    }

}
