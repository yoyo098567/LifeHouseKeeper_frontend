package com.example.lifehousekeeper.util.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MonthDes {
    @SerializedName("howMonth")
    @Expose
    private String howMonth;
    @SerializedName("totalCost")
    @Expose
    private long totalCost;

    public String getHowMonth() {
        return howMonth;
    }

    public void setHowMonth(String howMonth) {
        this.howMonth = howMonth;
    }

    public long getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(long totalCost) {
        this.totalCost = totalCost;
    }
}
