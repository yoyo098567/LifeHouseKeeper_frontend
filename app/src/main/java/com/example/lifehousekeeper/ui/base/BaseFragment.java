package com.example.lifehousekeeper.ui.base;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.lifehousekeeper.R;
import com.example.lifehousekeeper.util.ToastCreator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public abstract class BaseFragment extends Fragment implements BaseView {
    private ProgressDialog mProgressDialog;
    private Calendar mCalendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public void showProgressDialog(@StringRes int text) {
        dismissProgressDialog();
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setMessage(this.getResources().getString(text));
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
        mProgressDialog.show();
    }

    @Override
    public void dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showSelectDialog(@StringRes int text, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(text)
                .setPositiveButton(R.string.hint_confirm, onClickListener)
                .setNegativeButton(R.string.hint_cancel, onClickListener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void showSelectDialog(String text, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(getActivity())
                .setMessage(text)
                .setPositiveButton(R.string.hint_confirm, onClickListener)
                .setNegativeButton(R.string.hint_cancel, onClickListener)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void showItemDialog(List<String> list, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(getActivity())
                .setItems(list.toArray(new String[list.size()]), onClickListener)
                .create()
                .show();
    }

    @Override
    public void showDatePickerDialog(DatePickerDialog.OnDateSetListener onDateSetListener) {
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),
                onDateSetListener,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();
    }


    @Override
    public String getTodayTime() {
        String dateformat = "yyyyMMdd";
        Calendar mCal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat(dateformat);
        String today = df.format(mCal.getTime());
        return today;
    }

    @Override
    public void showToast(@StringRes int text) {
        ToastCreator.makeText(getActivity(), text, Toast.LENGTH_SHORT);
    }

    @Override
    public void showToast(String text) {
        ToastCreator.makeText(getActivity(), text, Toast.LENGTH_SHORT);
    }

}
