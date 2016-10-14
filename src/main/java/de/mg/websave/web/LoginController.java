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
import de.mg.websave.service.WebsaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import save.service.DataModel;
import save.service.PasswordModel;

@Controller
@Scope("request")
public class LoginController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private WebsaveService service;

    @Autowired
    private ApplicationContext applicationContext;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {

        loadPasswordModelIntoSession();

        model.addAttribute("loginForm", new LoginTO());
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") @Validated LoginTO loginTO,
                        BindingResult result) {

        WebSession session = applicationContext.getBean(WebSession.class);
        if (result.hasErrors()) {
            loginTO.setHint(session.getPasswordModel().getHint());
            return "login";
        } else {
            session.setLoginPassword(loginTO.getPw());
            DataModel dataModel = service.getDataModel(loginTO.getPw());
            session.setDataModel(dataModel);
            return "redirect:/list";
        }
    }

    private void loadPasswordModelIntoSession() {
        WebSession session = applicationContext.getBean(WebSession.class);
        if (session.getPasswordModel() == null) {
            PasswordModel passwordModel = service.getPasswordModel();
            session.setPasswordModel(passwordModel);
        }
    }
}
