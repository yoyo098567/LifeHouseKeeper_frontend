package com.example.lifehousekeeper.ui.eventRecode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifehousekeeper.databinding.EventListBinding;
import com.example.lifehousekeeper.ui.newEvent.AddEventActivity;
import com.example.lifehousekeeper.util.api.EventData;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>
            implements EventContract.Adapter{
    Context activity;
    List<EventData> dBDataList;

    public EventAdapter(Context activity, List<EventData> dBDataList){
        this.activity=activity;
        this.dBDataList=dBDataList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        EventListBinding binding = EventListBinding.inflate(inflater,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventAdapter.ViewHolder holder, int position) {
        EventData eventData = dBDataList.get(position);
        holder.bind(eventData);

    }

    @Override
    public int getItemCount() {
        return dBDataList.size();
    }

    @Override
    public void onItemClick(int position) {
        Log.v("item","111");
        Bundle bundle = new Bundle();
        Intent intent = new Intent(activity, AddEventActivity.class);
            bundle.putInt("enter_state",1);
            bundle.putString("subject",dBDataList.get(position).getSubject());
            bundle.putString("cost",dBDataList.get(position).getCostStr());
            bundle.putString("event",dBDataList.get(position).getEvent());
            intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EventListBinding eventListBinding;
        public ViewHolder(EventListBinding binding) {
            super(binding.getRoot());
            eventListBinding=binding;
        }
        void bind(EventData dbData){
            eventListBinding.setData(dbData);
            eventListBinding.executePendingBindings();
        }
    }
}
