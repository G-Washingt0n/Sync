package com.gnyblecraft.marcul.ideasproject.userProfile.entity;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.R;

import java.util.Calendar;
/**
 * Created by lenovo on 19.10.2017.
 */

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // определяем текущую дату
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // создаем DatePickerDialog и возвращаем его
        Dialog picker = new DatePickerDialog(getActivity(), this,
                year, month, day);

        return picker;
    }
    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int year,
                          int month, int day) {
        try{
            TextView birthday = (TextView) getActivity().findViewById(R.id.edit_birthdayProfile);
            birthday.setText(day + "." + (Integer.valueOf(month)+1) + "." + year);
        } catch(NullPointerException ex){
            TextView hint = (TextView) getActivity().findViewById(R.id.edit_birthdayProfile1);
            hint.setVisibility(View.INVISIBLE);
        }

    }


}
