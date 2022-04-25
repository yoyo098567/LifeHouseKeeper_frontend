package com.example.lifehousekeeper.util;

import com.example.lifehousekeeper.ui.login.LoginProvider;
import com.example.lifehousekeeper.util.api.EventData;
import com.example.lifehousekeeper.util.api.MonthDes;
import com.example.lifehousekeeper.util.api.RegisterUser;
import com.example.lifehousekeeper.util.api.ResetPassword;
import com.example.lifehousekeeper.util.api.SendEmail;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiGet {
    @POST("login")
    Observable<LoginProvider> postLogin(@Body LoginProvider loginProvider);

    @POST("user/register")
    Observable<RegisterUser> postRegister(@Body RegisterUser registerUser);

    @POST("email/verifyPassword")
    Observable<RegisterUser>postEmail(@Body RegisterUser RegisterUser);

    @POST("ResetPassword")
    Observable<ResetPassword>postReset(@Body ResetPassword resetPassword);

    @POST("calMonth")
    Observable<MonthDes>addCalMoth(@Body MonthDes monthDes);

    @GET("searchMonthCost")
    Observable<MonthDes>findMonthId();

    @POST("addEvent")
    Observable<EventData>addEvent(@Body EventData data);

    @GET("searchEvent")
    Observable<List<EventData>>searchEvent();

    @DELETE("deleteData")
    Observable<EventData>deleteEvent();
}
