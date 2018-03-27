package com.cornor.gank.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import com.cornor.gank.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleCalendarDialogFragment#} factory method to
 * create an instance of this fragment.
 */
public class SimpleCalendarDialogFragment extends AppCompatDialogFragment implements OnDateSelectedListener {
    String selectedDate;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();

        //inflate custom layout and get views
        //pass null as parent view because will be in dialog layout
        View view = inflater.inflate(R.layout.fragment_simple_calendar_dialog, null);

        MaterialCalendarView widget = view.findViewById(R.id.calendarView);

        widget.setOnDateChangedListener(this);

        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                    Intent intent = new Intent(getActivity(), DateContentActivity.class);
                    intent.putExtra("date",selectedDate);
                    getActivity().startActivity(intent);
                })
                .create();
    }

    @Override
    public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
        selectedDate = date.getYear() + "-" + (date.getMonth() + 1) + "-" + date.getDay();
    }
}
