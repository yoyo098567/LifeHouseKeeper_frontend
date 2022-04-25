package com.example.lifehousekeeper.ui.forgetPassword;

import com.example.lifehousekeeper.ui.base.BaseAttachar;
import com.example.lifehousekeeper.ui.base.BaseView;
import com.example.lifehousekeeper.ui.login.LoginContract;
import com.example.lifehousekeeper.util.api.ResetPassword;

public interface ResetPasswordContract {
    interface View extends BaseView{
        void resetPasswordClick();
    }
    interface Presenter<V extends ResetPasswordContract.View> extends BaseAttachar<V>{
        void sendResetPassword(ResetPassword resetPassword);
    }
}
