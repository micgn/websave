package de.mg.websave.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import save.service.EntryModel;

@Component
@Scope("request")
public class AddValidator implements Validator {

    @Autowired
    private WebSession session;

    public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
        return clazz.equals(EntryModel.class);
    }

    public void validate(Object obj, Errors errors) {
        EntryModel command = (EntryModel) obj;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.ValueEmpty", "must be filled");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "entry", "error.ValueEmpty", "must be filled");

        if (session.getDataModel().get(command.getName()) != null) {
            errors.rejectValue("entry", "error.NameExists", null, "Name already exists");
        }
    }
}
