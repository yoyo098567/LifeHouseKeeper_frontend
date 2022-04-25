package com.example.lifehousekeeper.ui.newEvent;

import com.example.lifehousekeeper.ui.base.BaseAttachar;
import com.example.lifehousekeeper.ui.base.BaseView;
import com.example.lifehousekeeper.ui.register.RegisterContract;
import com.example.lifehousekeeper.util.api.EventData;

public interface AddEventContract {
    interface View extends BaseView{
        void onClearClick();
        void onAddEventClick();

        void startActivity();
    }
    interface Presenter <V extends AddEventContract.View> extends BaseAttachar<V>{
        void addEvent(EventData eventData);

    }
}
