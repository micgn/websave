package de.mg.websave.web;

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
import save.service.EntryModel;

@Controller
@Scope("request")
public class AddController {


    @Autowired
    private WebSession session;

    @Autowired
    private AddValidator addValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(addValidator);
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String showAdd(Model model) {
        model.addAttribute("entryForm", new EntryModel());
        return "add";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("entryForm") @Validated EntryModel entry,
                      BindingResult result) {

        if (result.hasErrors()) {
            return "add";
        }
        session.getDataModel().getEntries().add(entry);
        return "redirect:/list";
    }

}
