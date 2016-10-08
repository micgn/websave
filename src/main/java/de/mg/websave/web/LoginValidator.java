package de.mg.websave.web;

import de.mg.websave.model.PasswordModel;
import de.mg.websave.service.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class LoginValidator implements Validator {

    @Autowired
    private SaveService service;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(LoginTO.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {

        LoginTO command = (LoginTO) obj;
        if (command == null) {
            errors.reject("error.CommandNull", null, "Command object is 'null'.");
            return;
        }
        if (command.getPw() == null || command.getPw().trim().length() == 0) {
            errors.rejectValue("pw", "error.ValueEmpty", null, "must be filled");
            return;
        }
        PasswordModel passwordModel = service.getPassword();
        if (!passwordModel.isCorrectPassword(command.getPw())) {
            errors.rejectValue("pw", "error.InvalidLogin", null, "login invalid");
        }
    }

}
