package de.mg.websave.web;

import de.mg.websave.service.WebSession;
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
import save.service.PasswordModel;

@Controller
@Scope("request")
public class ChangePwController {

    @Autowired
    private WebsaveService service;

    @Autowired
    private WebSession session;

    @Autowired
    private ChangePwValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/changepw", method = RequestMethod.GET)
    public String enter(Model model) {
        model.addAttribute("changePwForm", new ChangePwTO());
        return "changePw";
    }

    @RequestMapping(value = "/changepw", method = RequestMethod.POST)
    public String change(@ModelAttribute("changePwForm") @Validated ChangePwTO c, BindingResult result) {
        if (result.hasErrors()) {
            return "changePw";
        }
        PasswordModel model = session.getPasswordModel();
        model.setNewPassword(c.getNewPw1());
        model.setHint(c.getHint());
        service.persist(model);
        service.persist(session.getDataModel(), c.getNewPw1());
        return "redirect:/success";
    }


}
