package de.mg.websave.service;

import de.mg.lateo.LateoMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import save.service.DataModel;
import save.service.PasswordModel;

@Component
@Scope("session")
public class WebSession {

    @Autowired
    private LateoMain lateo;

    private DataModel dataModel;
    private PasswordModel passwordModel;

    private String loginPassword;

    private final static transient String SECRET = String.valueOf(System.currentTimeMillis());


    public DataModel getDataModel() {
        return dataModel;
    }

    public void setDataModel(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public PasswordModel getPasswordModel() {
        return passwordModel;
    }

    public void setPasswordModel(PasswordModel passwordModel) {
        this.passwordModel = passwordModel;
    }

    // in case of session serialization only an encrypted password is to be contained
    public void setLoginPassword(String loginPassword) {
        String encrypted = lateo.encrypt(SECRET, loginPassword);
        this.loginPassword = encrypted;
    }

    public String getLoginPassword() {
        return lateo.decrypt(SECRET, loginPassword);
    }

    public void logout() {
        dataModel = null;
        passwordModel = null;
        loginPassword = null;
    }

    public boolean isLoggedOut() {
        return loginPassword == null;
    }
}
