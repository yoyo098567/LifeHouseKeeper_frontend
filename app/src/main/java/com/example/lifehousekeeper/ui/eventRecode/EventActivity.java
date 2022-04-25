package com.example.lifehousekeeper.ui.eventRecode;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;


import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.databinding.ActivityEventBinding;
import com.example.lifehousekeeper.ui.base.BaseActivity;
import com.example.lifehousekeeper.ui.newEvent.AddEventActivity;
import com.example.lifehousekeeper.util.api.EventData;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class EventActivity extends BaseActivity implements EventContract.View {
    private EventAdapter eventAdapter;
    EventContract.Presenter<EventContract.View> mPresenter = new EventPresenter<>();
    ActivityEventBinding activityEventBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mPresenter.onAttached(this);

    }

    @Override
    public void init() {
        activityEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_event);
        mPresenter.searchEvent();
        activityEventBinding.setView(this);

    }

    private void recyclerSet(List<EventData>mList) {
        eventAdapter = new EventAdapter(this, mList);

        activityEventBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        activityEventBinding.recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        activityEventBinding.recyclerView.setAdapter(eventAdapter);
    }

    @Override
    public void addEventClick() {
        Intent intent = new Intent(this, AddEventActivity.class);
        Bundle bundle = new Bundle();

        bundle.putInt("enter_state",0);
        bundle.putString("subject","");
        bundle.putString("cost","");
        bundle.putString("event","");
        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public void getEventList(List<EventData> eventDataList) {
        if (eventDataList!=null) {


            SimpleDateFormat sDateFormat = new SimpleDateFormat("MM-dd");
            Date date = new Date();
            String data = sDateFormat.format(date);
            recyclerSet(eventDataList);
            activityEventBinding.setData("今天日期:"+data);
            eventAdapter.notifyDataSetChanged();
        }
    }
}