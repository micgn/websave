package de.mg.websave.web;

import de.mg.websave.service.WebsaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import save.service.DataModel;

@Controller
@Scope("request")
public class SaveController {

    @Autowired
    private WebSession session;

    @Autowired
    private WebsaveService service;


    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String save() {

        DataModel model = session.getDataModel();
        service.persist(model, session.getLoginPassword());

        return "redirect:/success";
    }


}
