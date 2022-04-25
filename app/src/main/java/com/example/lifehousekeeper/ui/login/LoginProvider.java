package com.example.lifehousekeeper.ui.login;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginProvider{

    @SerializedName("account")
    @Expose
    private String account;
    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("isLogin")
    @Expose
    private String loginResponse;

    private boolean checked;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getLoginResponse() {
        return loginResponse;
    }

    public void setLoginResponse(String loginResponse) {
        this.loginResponse = loginResponse;
    }
}
