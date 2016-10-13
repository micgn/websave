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
