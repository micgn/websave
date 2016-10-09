package de.mg.websave.web;

public class ChangePwTO {
    private String oldPw;
    private String newPw1;
    private String newPw2;
    private String hint;

    public String getOldPw() {
        return oldPw;
    }

    public void setOldPw(String oldPw) {
        this.oldPw = oldPw;
    }

    public String getNewPw1() {
        return newPw1;
    }

    public void setNewPw1(String newPw1) {
        this.newPw1 = newPw1;
    }

    public String getNewPw2() {
        return newPw2;
    }

    public void setNewPw2(String newPw2) {
        this.newPw2 = newPw2;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
}
