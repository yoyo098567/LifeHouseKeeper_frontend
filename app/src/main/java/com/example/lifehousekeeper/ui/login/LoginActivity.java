package com.example.lifehousekeeper.ui.login;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.lifehousekeeper.MainActivity;
import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.core.ActivityLauncher;
import com.example.lifehousekeeper.databinding.ActivityLoginBinding;
import com.example.lifehousekeeper.ui.base.BaseActivity;
import com.example.lifehousekeeper.ui.eventRecode.EventActivity;
import com.example.lifehousekeeper.ui.forgetPassword.ResetPasswordActivity;
import com.example.lifehousekeeper.ui.register.RegisterActivity;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    LoginContract.Presenter<LoginContract.View> mPresenter = new LoginPresenter<>();

    LoginProvider loginProvider = new LoginProvider() ;

    ActivityLoginBinding activityLoginBinding;
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        mPresenter.onAttached(this);

    }

    @Override
    public void onRegisterClick() {
        ActivityLauncher.go(this, RegisterActivity.class,null);
    }

    @Override
    public void onForgetPasswordClick() {
        ActivityLauncher.go(this, ResetPasswordActivity.class,null);
    }

    @Override
    public void startMainActivity() {

        Log.v("123456",loginProvider.getAccount()+" "+" "+loginProvider.getPassword());
//        startActivity();
        String result = accountDataNotNull(loginProvider);
        if (result.equals("已送出")) {
            remember();
            mPresenter.SendLoginRequest(loginProvider);
        }else{
            showToast(result);
        }
    }
    @Override
    public String accountDataNotNull(LoginProvider provider){
        if (provider.getAccount()==null && provider.getPassword()==null){
            return "帳號密碼必須填寫";
        }else if(provider.getAccount()==null ){
            return "帳號必須填寫";
        }else if (provider.getPassword()==null){
            return "密碼必須填寫";
        }
        return "已送出";
    }

    @Override
    public void startActivity(){
        ActivityLauncher.go(this, EventActivity.class, null);
        finish();
    }

    @Override
    public void init() {
        activityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login);
        activityLoginBinding.setData(loginProvider);
        activityLoginBinding.setView(this);
        if (loginProvider.isChecked()){
            SharedPreferences preferences = getSharedPreferences("mypsd",MODE_PRIVATE);
            loginProvider.setAccount(preferences.getString("account",""));
            loginProvider.setPassword(preferences.getString("psd",""));
        }
    }
    @Override
    protected void onDestroy() {
        mPresenter.onDetached();
        super.onDestroy();
    }
    private void remember(){
        SharedPreferences.Editor edit = getSharedPreferences("mypsd", MODE_PRIVATE).edit();
       //判斷選擇框的狀態 被選中isChecked或……
        if (loginProvider.isChecked()) {
            edit.putString("account", loginProvider.getAccount());
            edit.putString("psd", loginProvider.getPassword());
            edit.putBoolean("isChecked", true);
        }
        edit.commit();
    }
}