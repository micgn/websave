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

package save.service;


import de.mg.websave.util.MD5StringEncrypter;

public class PasswordModel {

    /**
     * hashed password
     */
    private String passwordEncrypted;

    private String hint;

    public boolean isCorrectPassword(String password) {
        String testPw = MD5StringEncrypter.encrypt(password);
        return passwordEncrypted.equals(testPw);
    }

    public void setNewPassword(String password) {
        this.passwordEncrypted = MD5StringEncrypter.encrypt(password);
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

}
