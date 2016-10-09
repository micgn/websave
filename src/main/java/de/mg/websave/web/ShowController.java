package de.mg.websave.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import save.service.EntryModel;

@Controller
@Scope("request")
public class ShowController {

    @Autowired
    private WebSession session;

    @RequestMapping(value = "/show/{entryName}", method = RequestMethod.GET)
    public String show(@PathVariable("entryName") String entryName, Model model) {

        EntryModel entry = session.getDataModel().get(entryName);
        model.addAttribute("entryModel", entry);
        return "show";
    }

}
