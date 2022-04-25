package com.example.lifehousekeeper.ui.base;

public class BasePresenter<V extends BaseView> implements BaseAttachar<V> {

    private V mView;

    public BasePresenter() {
    }

    public V getView() {
        return mView;
    }

    @Override
    public void onAttached(V view) {
        mView = view;
    }

    @Override
    public void onDetached() {
        mView = null;
    }
}
