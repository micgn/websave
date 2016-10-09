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
public class EditValidator implements Validator {

    @Autowired
    private WebSession session;

    public boolean supports(Class<?> clazz) {
        return clazz.equals(EditTO.class);
    }

    public void validate(Object obj, Errors errors) {
        EditTO to = (EditTO) obj;
        if (isEmpty(to.getEntryName())) {
            errors.rejectValue("entryName", "error.ValueEmpty", null, "Must be filled.");
            return;
        } else if (!to.getOldEntryName().equals(to.getEntryName())
                && session.getDataModel().get(to.getEntryName()) != null) {
            errors.rejectValue("entryName", "error.NameExists", null, "Name already exists");
        }
    }
}
