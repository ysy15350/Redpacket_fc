package com.ysy15350.ysyutils.custom_view.dialog;

import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.ysy15350.ysyutils.common.CommFunAndroid;

/**
 * Created by yangshiyou on 2018/3/21.
 */

public class DateDialog extends DialogFragment implements OnDateSetListener {

    private Calendar c = null;

    int string_type = 1;

    private String mDateFormat;

    public DateDialog() {

        mDateFormat = "yyyy-MM-dd";

    }

    public DateDialog(String dateFormat) {
        this.mDateFormat = dateFormat;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), DatePickerDialog.THEME_HOLO_LIGHT, this, year, month, day);

    }

    public void onDateSet(DatePicker dp, int year, int month, int dayOfMonth) {

        c = Calendar.getInstance();
        c.set(year, month, dayOfMonth);
        Date time = c.getTime();
        String dateStr = CommFunAndroid.toDateString(time, mDateFormat);

        if (mDateListener != null) {
            mDateListener.onDateSet(dateStr);
        }

        // dateStringCH = year + "年" + (month + 1) + "月";

        // dateString = year + "-" + ((month + 1) < 10 ? "0" + (month + 1) :
        // (month + 1));

        // String s_date = String.format("%d-%d-%d", year, month + 1,
        // dayOfMonth);

        // String ds = function.GetDateString_sss(s_date);
        // String ds = s_date;

        // if (string_type == 1) {
        // et_date1.setText(s_date);
        // et_date1.setTag(ds);
        // } else {
        // et_date2.setText(s_date);
        // et_date2.setTag(ds);
        // }

    }

    private DateListener mDateListener;

    public void setDateListener(DateListener dateListener) {
        this.mDateListener = dateListener;
    }

    public interface DateListener {
        public void onDateSet(String dateStr);
    }

}
