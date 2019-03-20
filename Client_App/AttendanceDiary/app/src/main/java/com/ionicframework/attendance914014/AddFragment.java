package com.ionicframework.attendance914014;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    private Toolbar _toolbar;
    private Button _add;
    private ArrayList<Subject> _subjects;
    private EditText _newSubject;


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add, container, false);

        _subjects = SubjectLoader.get(getActivity()).getSubjects();

        _toolbar = (Toolbar) v.findViewById(R.id.add_head);
        _toolbar.setTitle("Add Subject");

        _newSubject = (EditText) v.findViewById(R.id.new_subject);

        _add = (Button) v.findViewById(R.id.add_sub_button);
        _add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!_newSubject.getText().toString().isEmpty()) {

                    Log.d("Debug empty",_newSubject.getText().toString() + " jk");
                    Subject s = new Subject();
                    s.setName(_newSubject.getText().toString());
                    s.setPresentClass(0);
                    s.setTotalClass(0);
                    //s.setLastUpdated(Calendar.getInstance().getTime().toString());
                    /*_subjects.add(s);*/

                    DatabaseHandler dh = new DatabaseHandler(view.getContext());
                    dh.addSubject(s);

                    if (_subjects.size() == 0) {
                        s.setId(1);
                        _subjects.add(s);
                    } else {
                        _subjects.add(dh.getSubject(_subjects.get(_subjects.size() - 1).getId() + 1));
                    }

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);

                    getFragmentManager().popBackStackImmediate();
                }
            }
        });

        return v;
    }


}
