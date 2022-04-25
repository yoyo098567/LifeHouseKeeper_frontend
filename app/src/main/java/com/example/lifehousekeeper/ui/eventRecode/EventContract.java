package com.example.lifehousekeeper.ui.eventRecode;

import com.example.lifehousekeeper.ui.base.BaseAttachar;
import com.example.lifehousekeeper.ui.base.BaseView;
import com.example.lifehousekeeper.util.api.EventData;

import java.util.List;

public interface EventContract {
    interface View extends BaseView{
        void addEventClick();


        void getEventList(List<EventData> eventDataList);
    }
    interface Presenter<V extends View> extends BaseAttachar<V> {
        void searchEvent();

    }
    interface Adapter{
        void onItemClick(int position);
    }
}
