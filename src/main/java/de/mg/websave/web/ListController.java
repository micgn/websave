package de.mg.websave.web;

import de.mg.websave.service.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("request")
public class ListController {


    @Autowired
    private WebSession session;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String showList(Model model) {
        model.addAttribute("list", session.getDataModel());
        return "list";
    }

}
