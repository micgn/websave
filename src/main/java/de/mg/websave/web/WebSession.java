package de.mg.websave.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import save.service.DataModel;
import save.service.PasswordModel;

@Component
@Scope("session")
public class WebSession {

    private DataModel dataModel;
    private PasswordModel passwordModel;
    private String loginPassword;


    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public PasswordModel getPasswordModel() {
        return passwordModel;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void logout() {
        // TODO
    }
}
