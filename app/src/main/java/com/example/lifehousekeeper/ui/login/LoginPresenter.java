package com.example.lifehousekeeper.ui.login;

import android.util.Log;
import android.widget.Toast;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.ui.base.BasePresenter;
import com.example.lifehousekeeper.util.ApiGet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

public class LoginPresenter <V extends LoginContract.View> extends BasePresenter<V> implements LoginContract.Presenter<V>{
    ApiGet apiGet;

    public Retrofit apiGive(){
        Gson gson = new GsonBuilder()
                .setLenient().create();

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
                .build();

        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl("http://192.168.0.4:8080/lifeHouseKeeper/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Override
    public void SendLoginRequest(LoginProvider loginProvider) {
        getView().showProgressDialog(R.string.hint_login);

        Retrofit retrofit = apiGive();

            apiGet = retrofit.create(ApiGet.class);
        Observable<LoginProvider> loginProviderObservable = apiGet.postLogin(loginProvider);
        loginProviderObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginProvider>() {
                    @Override
                    public void onNext(LoginProvider loginProvider) {
                        getView().dismissProgressDialog();

                        getView().showToast(loginProvider.getLoginResponse());

                        Log.v("login",loginProvider.getLoginResponse()+"登入成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().dismissProgressDialog();
                        getView().showToast(R.string.uploadFail);
                        Log.v("loginfail", String.valueOf(e));
                    }

                    @Override
                    public void onComplete() {
                        getView().dismissProgressDialog();
                        getView().startActivity();
                    }
                });

    }


}
