package de.mg.websave.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import save.service.DataModel;

@Component
@Scope("session")
public class WebSession {

    private DataModel dataModel;



    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }
}
