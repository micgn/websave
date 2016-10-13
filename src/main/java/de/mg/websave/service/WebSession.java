/*
 *  Copyright 2016 Michael Gnatz.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package de.mg.websave.service;

import de.mg.lateo.LateoMain;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import save.service.DataModel;
import save.service.PasswordModel;

import javax.annotation.PostConstruct;

@Component
@Scope("session")
public class WebSession {

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

    @PostConstruct
    public void init() {
        lateo = LateoMain.getInstance();
    }
}
