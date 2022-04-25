package com.example.lifehousekeeper.util.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendEmail {
    @SerializedName("email")
    @Expose
    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
