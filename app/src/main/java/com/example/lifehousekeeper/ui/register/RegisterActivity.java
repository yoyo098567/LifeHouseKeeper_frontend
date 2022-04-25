package com.example.lifehousekeeper.ui.register;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.databinding.ActivityRegisterBinding;
import com.example.lifehousekeeper.ui.base.BaseActivity;
import com.example.lifehousekeeper.util.api.RegisterUser;

public class RegisterActivity extends BaseActivity implements RegisterContract.View {
    RegisterContract.Presenter<RegisterContract.View> mPresenter = new RegisterPresenter<>();
    RegisterUser registerUser = new RegisterUser();
    ActivityRegisterBinding activityRegisterBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();

        mPresenter.onAttached(this);
    }

    @Override
    public void init() {
        activityRegisterBinding = DataBindingUtil.setContentView(this,R.layout.activity_register);
        activityRegisterBinding.setData(registerUser);
        activityRegisterBinding.setView(this);
    }

    @Override
    public void sendEmailClick() {
        String result = inputNotNull(registerUser);
        if (result.equals("已送出")){
            showToast("驗證信已送出");
            mPresenter.sendVerifyEmail(registerUser);
        }
    }

    @Override
    public void register() {
       String result = inputNotNull(registerUser);
        if (result.equals("已送出")){
            mPresenter.sendRegister(registerUser);
        }else{
            showToast(result);
        }
    }

    private String inputNotNull(RegisterUser registerUser){
        if (registerUser.getAccount()==null && registerUser.getPassword()==null ){
            return "帳號密碼必須填寫";
        }else if(registerUser.getAccount()==null){
            return "帳號必須填寫";
        }else if (registerUser.getPassword()==null){
            return "密碼必須填寫";
        }
        return "已送出";
    }
}