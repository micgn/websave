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
