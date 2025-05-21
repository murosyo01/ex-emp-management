package com.example.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

/**
 * 管理者情報を登録するフォーム
 */
public class InsertAdministratorForm {
    /** 名前 */
    @NotNull(message = "名前は必須です。")
    @NotBlank(message = "名前を空文字にはできません。")
    private String name;

    /** メアド */
    @NotBlank(message = "メールアドレスは必須です。")
    @Email(message = "メールアドレスの形式が正しくありません。")
    private String mailAddress;

    /** パスワード */
    @NotBlank(message = "パスワードは必須です。")
    @Length(min = 3, max = 100, message = "パスワードは3文字以上、100文字以内である必要があります。")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
        return "InsertAdministrator ［ name = " + name + ", mailAddress = " + mailAddress + ", password = " + password + " ］";
    }
}
