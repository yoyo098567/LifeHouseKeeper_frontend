package com.example.lifehousekeeper.ui.forgetPassword;

import android.util.Log;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.ui.base.BasePresenter;
import com.example.lifehousekeeper.ui.login.LoginContract;
import com.example.lifehousekeeper.util.ApiGet;
import com.example.lifehousekeeper.util.api.ResetPassword;

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

public class ResetPasswordPresenter <V extends ResetPasswordContract.View> extends BasePresenter<V> implements ResetPasswordContract.Presenter<V>{
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
    public void sendResetPassword(ResetPassword resetPassword) {
        getView().showProgressDialog(R.string.hint_login);
        Retrofit retrofit = apiGive();
        apiGet = retrofit.create(ApiGet.class);
        Observable<ResetPassword> resetPasswordObservable = apiGet.postReset(resetPassword);
            resetPasswordObservable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<ResetPassword>() {
                        @Override
                        public void onNext(ResetPassword resetPassword) {
                            getView().dismissProgressDialog();
                            Log.v("reset",resetPassword.getAccount());
                        }

                        @Override
                        public void onError(Throwable e) {
                            getView().dismissProgressDialog();
                            Log.v("reset","fail"+e);

                        }

                        @Override
                        public void onComplete() {
                            getView().dismissProgressDialog();

                        }
                    });
    }
}
