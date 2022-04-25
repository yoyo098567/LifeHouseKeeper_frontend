package com.example.lifehousekeeper.ui.register;

import com.example.lifehousekeeper.ui.base.BaseAttachar;
import com.example.lifehousekeeper.ui.base.BaseView;
import com.example.lifehousekeeper.ui.login.LoginContract;
import com.example.lifehousekeeper.util.api.RegisterUser;

public interface RegisterContract {
    interface View extends BaseView{
        void sendEmailClick();
        void register();
    }
    interface Presenter <V extends RegisterContract.View> extends BaseAttachar<V>{
        void sendRegister(RegisterUser registerUser);
        void sendVerifyEmail(RegisterUser registerUser);
    }
}
