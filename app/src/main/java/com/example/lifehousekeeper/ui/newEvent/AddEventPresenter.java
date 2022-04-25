package com.example.lifehousekeeper.ui.newEvent;

import android.util.Log;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.ui.base.BasePresenter;
import com.example.lifehousekeeper.ui.login.LoginContract;
import com.example.lifehousekeeper.util.ApiGet;
import com.example.lifehousekeeper.util.api.EventData;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddEventPresenter <V extends AddEventContract.View> extends BasePresenter<V> implements AddEventContract.Presenter<V>{
    ApiGet apiGet;
    public Retrofit apiGive(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl("http://192.168.0.4:8080/lifeHouseKeeper/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public void addEvent(EventData eventData) {
        getView().showProgressDialog(R.string.hint_login);

        Retrofit retrofit = apiGive();
        apiGet = retrofit.create(ApiGet.class);
        Observable<EventData> eventDataObservable = apiGet.addEvent(eventData);
        eventDataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<EventData>() {
                    @Override
                    public void onNext(EventData eventData) {
                        getView().dismissProgressDialog();
                        getView().showToast("送出成功");
                        Log.v("ADDevent",eventData.getSubject()+"送出成功");

                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().dismissProgressDialog();
                        Log.v("ADDevent","fail"+e);
                    }

                    @Override
                    public void onComplete() {
                        getView().dismissProgressDialog();
                        getView().startActivity();
                    }
                });
    }


}
