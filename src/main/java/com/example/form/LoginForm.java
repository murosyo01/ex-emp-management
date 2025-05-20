package com.example.form;

/**
 * ログイン情報を受け取るフォーム
 */
public class LoginForm {
    /** メアド */
    private String mailAddress;
    /** パスワード */
    private String password;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return "LoginInfo ［ mailAddress = " + mailAddress + ", password = " + password + " ］";
    }
}
