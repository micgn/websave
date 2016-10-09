package de.mg.websave.web;

import de.mg.websave.service.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import static de.mg.websave.util.StringUtils.isEmpty;

@Component
@Scope("request")
public class ChangePwValidator implements Validator {

    @Autowired
    private WebSession session;

    public boolean supports(Class<?> clazz) {
        return clazz.equals(ChangePwTO.class);
    }

    public void validate(Object obj, Errors errors) {
        ChangePwTO command = (ChangePwTO) obj;

        if (!session.getPasswordModel().isCorrectPassword(command.getOldPw())) {
            errors.rejectValue("oldPw", "error.invalid", null, "Invalid");
        } else if (isEmpty(command.getHint())) {
            errors.rejectValue("hint", "error.ValueEmpty", null, "Must be filled.");
        } else if (isEmpty(command.getNewPw1())) {
            errors.rejectValue("newPw1", "error.ValueEmpty", null, "Must be filled.");
        } else if (isEmpty(command.getNewPw2())) {
            errors.rejectValue("newPw2", "error.ValueEmpty", null, "Must be filled.");
        } else if (!command.getNewPw1().equals(command.getNewPw2())) {
            errors.rejectValue("newPw2", "error.Invalid", null, "Invalid");
        }
    }


}
