/*
 *  Copyright 2016 Michael Gnatz.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

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
