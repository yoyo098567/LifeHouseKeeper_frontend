package com.example.lifehousekeeper.ui.forgetPassword;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.databinding.ActivityResetPasswordBinding;
import com.example.lifehousekeeper.ui.base.BaseActivity;
import com.example.lifehousekeeper.util.api.ResetPassword;

public class ResetPasswordActivity extends BaseActivity implements ResetPasswordContract.View {
    ResetPasswordContract.Presenter<ResetPasswordContract.View> mPresenter = new ResetPasswordPresenter<>();
    ResetPassword resetPassword = new ResetPassword();
    ActivityResetPasswordBinding activityResetPasswordBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mPresenter.onAttached(this);
    }

    @Override
    public void init() {
        activityResetPasswordBinding= DataBindingUtil.setContentView(this,R.layout.activity_reset_password);
        activityResetPasswordBinding.setData(resetPassword);
        activityResetPasswordBinding.setView(this);
    }

    @Override
    public void resetPasswordClick() {
        String result = inputNotNull(resetPassword);
        if (result.equals("已送出")){
            mPresenter.sendResetPassword(resetPassword);
        }else{
            showToast(result);
        }
    }
    private String inputNotNull(ResetPassword password){
        if (password.getAccount()==null && password.getPassword()==null && password.getNewPasswd()==null){
            return "帳號密碼必須填寫";
        }else if(password.getAccount()==null){
            return "帳號必須填寫";
        }else if (password.getPassword()==null){
            return "密碼必須填寫";
        }else if (password.getNewPasswd()==null){
            return "新密碼必須填寫";
        }
        return "已送出";
    }
}