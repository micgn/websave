package de.mg.websave.web;

import de.mg.websave.service.WebsaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import save.service.EntryModel;

import java.util.Collections;

@Controller
@Scope("request")
public class EditController {

    @Autowired
    private WebsaveService service;

    @Autowired
    private WebSession session;

    @Autowired
    private EditValidator validator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @RequestMapping(value = "/edit/{entryName}", method = RequestMethod.GET)
    public String enter(@PathVariable("entryName") String entryName, Model model) {

        EntryModel entry = session.getDataModel().get(entryName);

        EditTO to = new EditTO();
        to.setOldEntryName(entry.getName());
        to.setEntryName(entry.getName());
        to.setEntry(entry.getEntry());
        model.addAttribute("editForm", to);

        return "edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@ModelAttribute("editForm") @Validated EditTO to) {

        EntryModel entry = session.getDataModel().get(to.getOldEntryName());
        entry.setName(to.getEntryName());
        entry.setEntry(to.getEntry());
        Collections.sort(session.getDataModel().getEntries());

        return "redirect:/show/" + to.getEntryName();
    }

}
