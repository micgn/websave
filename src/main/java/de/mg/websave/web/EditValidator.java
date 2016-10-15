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
        } else if (!to.getOldEntryName().equals(to.getEntryName())
                && session.getDataModel().get(to.getEntryName()) != null) {
            errors.rejectValue("entryName", "error.NameExists", null, "Name already exists");
        } else if (isEmpty(to.getHash())) {
            errors.rejectValue("entryText", "error", null, "error calculating hash value");
        }
    }
}
