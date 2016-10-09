package de.mg.websave.web;

import de.mg.websave.service.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import save.service.EntryModel;

@Controller
@Scope("request")
public class DeleteController {

    @Autowired
    private WebSession session;

    @RequestMapping(value = "/delete/{entryName}", method = RequestMethod.GET)
    public String delete(@PathVariable("entryName") String entryName) {

        EntryModel model = session.getDataModel().get(entryName);
        session.getDataModel().delete(model.getName());

        return "redirect:/list";
    }

}
