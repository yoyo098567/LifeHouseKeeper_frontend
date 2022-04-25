package com.example.lifehousekeeper.ui.base;

public interface BaseAttachar<V extends BaseView> {
    void onAttached(V view);
    void onDetached();
}
