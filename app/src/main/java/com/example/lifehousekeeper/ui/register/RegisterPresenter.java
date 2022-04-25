package com.example.lifehousekeeper.ui.register;

import android.util.Log;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.ui.base.BasePresenter;

import com.example.lifehousekeeper.util.ApiGet;
import com.example.lifehousekeeper.util.api.RegisterUser;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterPresenter <V extends RegisterContract.View> extends BasePresenter<V> implements RegisterContract.Presenter<V>{
    ApiGet apiGet;
    public Retrofit apiGive(){
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.0.4:8080/lifeHouseKeeper/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Override
    public void sendRegister(RegisterUser registerUser) {
        getView().showProgressDialog(R.string.hint_login);

        Retrofit retrofit = apiGive();

        apiGet = retrofit.create(ApiGet.class);

        Observable<RegisterUser>registerUserObservable = apiGet.postRegister(registerUser);
            registerUserObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<RegisterUser>() {
                        @Override
                        public void onNext(RegisterUser registerUser) {
                            getView().dismissProgressDialog();
                            getView().showToast("註冊成功");
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().dismissProgressDialog();
                            getView().showToast("註冊失敗");

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }

    @Override
    public void sendVerifyEmail(RegisterUser registerUser) {
        getView().showProgressDialog(R.string.hint_login);

        Retrofit retrofit = apiGive();

        apiGet = retrofit.create(ApiGet.class);

        Observable<RegisterUser>registerUserObservable = apiGet.postEmail(registerUser);
        registerUserObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<RegisterUser>() {
                    @Override
                    public void onNext(RegisterUser registerUser) {
                        getView().dismissProgressDialog();
                        getView().showToast("發送驗證信成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().dismissProgressDialog();
                        getView().showToast("發送驗證信失敗");

                    }

                    @Override
                    public void onComplete() {
                        getView().dismissProgressDialog();

                    }
                });
    }

}
