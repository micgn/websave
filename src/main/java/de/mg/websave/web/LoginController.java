package de.mg.websave.web;

import de.mg.websave.service.WebsaveService;
import org.springframework.beans.factory.annotation.Autowired;
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

@Controller
@Scope("request")
public class LoginController {

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private WebsaveService service;

    @Autowired
    private WebSession session;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(loginValidator);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String root() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("loginForm", new LoginTO());
        return "login";
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute("loginForm") @Validated LoginTO loginTO,
                        BindingResult result) {


        if (result.hasErrors()) {
            loginTO.setHint(service.getPasswordModel().getHint());
            return "login";
        } else {
            DataModel dataModel = service.getDataModel(loginTO.getPw());
            session.setDataModel(dataModel);
            return "redirect:/list";
        }
    }
}
