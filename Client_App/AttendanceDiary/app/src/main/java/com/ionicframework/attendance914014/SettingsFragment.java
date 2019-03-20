package com.ionicframework.attendance914014;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {


    public static final String requiredPercentage = "Percentage";

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    private ArrayList<Subject> _subjects;

    private Toolbar _toolbar;

    private TextView _textView;
    private SeekBar _seekBar;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        _toolbar = (Toolbar) v.findViewById(R.id.setting_head);
        _toolbar.setTitle("Settings");

        _recyclerView = (RecyclerView) v.findViewById(R.id.recycler_settings);

        _layoutManager = new LinearLayoutManager(v.getContext());
        _recyclerView.setLayoutManager(_layoutManager);

        _subjects = SubjectLoader.get(v.getContext()).getSubjects();

        _adapter = new DeleteAdapter(_subjects);
        _recyclerView.setAdapter(_adapter);

        /*
        for storing desired percentage in shared preferences
         */

        final SharedPreferences settings = v.getContext().getSharedPreferences(requiredPercentage , 0);
        int reqPercentage = settings.getInt("percentage",75);


        _textView = (TextView) v.findViewById(R.id.percentage_text_view);

        _seekBar = (SeekBar) v.findViewById(R.id.percentage_seek_bar);

        _seekBar.setProgress(reqPercentage);
        _textView.setText(" Desired Percentage " + _seekBar.getProgress());

        _seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar,int i, boolean b) {
                _textView.setText("Desired Percentage " + i);

                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("percentage",i);
                editor.commit();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return v;
    }


}
