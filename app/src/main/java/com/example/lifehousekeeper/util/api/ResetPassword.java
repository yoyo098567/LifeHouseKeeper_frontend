package com.example.lifehousekeeper.util.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResetPassword {
    @SerializedName("account")
    @Expose
    private String account = "";

    @SerializedName("password")
    @Expose
    private String password = "";


    private String newPasswd;

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

    public String getNewPasswd() {
        return newPasswd;
    }

    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }
}
