package com.example.lifehousekeeper.ui.newEvent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.lifehousekeeper.MainActivity;
import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.core.ActivityLauncher;
import com.example.lifehousekeeper.databinding.ActivityAddEventBinding;
import com.example.lifehousekeeper.ui.base.BaseActivity;
import com.example.lifehousekeeper.ui.login.LoginContract;
import com.example.lifehousekeeper.ui.login.LoginPresenter;
import com.example.lifehousekeeper.ui.login.LoginProvider;
import com.example.lifehousekeeper.util.api.EventData;

public class AddEventActivity extends BaseActivity implements AddEventContract.View {
    AddEventContract.Presenter<AddEventContract.View> mPresenter = new AddEventPresenter<>();
    ActivityAddEventBinding activityAddEventBinding;
    EventData eventData = new EventData();
    public int enter_state = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mPresenter.onAttached(this);

    }

    @Override
    public void init() {
        activityAddEventBinding = DataBindingUtil.setContentView(this,R.layout.activity_add_event);
        Bundle bundle = this.getIntent().getExtras();
        enter_state = bundle.getInt("enter_state");
        checkEnterState(enter_state,bundle);
        activityAddEventBinding.setData(eventData);
        activityAddEventBinding.setView(this);
    }

    private void checkEnterState(int enter_state,Bundle bundle) {
        if (enter_state == 1){
            String subject = bundle.getString("subject");

            String cost = bundle.getString("cost");
            String event = bundle.getString("event");
            eventData.setSubject(subject);
            eventData.setCostStr(cost);
            eventData.setEvent(event);
            activityAddEventBinding.setData(eventData);
        }
    }

    @Override
    public void onClearClick() {
        eventData.setEvent("");
        eventData.setSubject("");
        eventData.setCostStr("");
        activityAddEventBinding.setData(eventData);
    }

    @Override
    public void onAddEventClick() {
        String result = eventDataNotNull(eventData);
        Log.v("add",result);
        if (result.equals("已送出")) {
            mPresenter.addEvent(eventData);
        }
    }

    public String eventDataNotNull(EventData eventData){
        if (eventData.getSubject()==null && eventData.getCostStr()==null && eventData.getEvent() == null){
            return "主旨、花費、事件內容必須填寫";
        }else if(eventData.getSubject()==null){
            return "主旨必須填寫";
        }else if (eventData.getCostStr()==null){
            return "花費必須填寫";
        }else if (eventData.getEvent()==null){
            return "事件內容必須填寫";
        }
        return "已送出";
    }
    @Override
    public void startActivity(){
        ActivityLauncher.go(this, MainActivity.class, null);
        finish();
    }
}