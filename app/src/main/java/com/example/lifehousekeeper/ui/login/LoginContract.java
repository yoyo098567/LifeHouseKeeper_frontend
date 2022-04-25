package com.example.lifehousekeeper.ui.login;

import com.example.lifehousekeeper.ui.base.BaseAttachar;
import com.example.lifehousekeeper.ui.base.BaseView;

public interface LoginContract {
    interface View extends BaseView {

        void onRegisterClick();

        void onForgetPasswordClick();

        void startMainActivity();

        String accountDataNotNull(LoginProvider provider);

        void startActivity();
    }
    interface Presenter <V extends View> extends BaseAttachar<V> {
        void SendLoginRequest(LoginProvider loginProvider);
    }
}
