package com.example.lifehousekeeper.util.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class EventData {
    @SerializedName("id")
    @Expose
    private long id = 1 ;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("subject")
    @Expose
    private String subject;
    @SerializedName("cost")
    @Expose
    private String costStr = "0";
    @SerializedName("time")
    @Expose
    private String dateTime;

    private String date;

    private int position;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }



    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getCostStr() {
        return costStr;
    }

    public void setCostStr(String costStr) {
        this.costStr = costStr;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
