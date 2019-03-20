package com.ionicframework.attendance914014;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubjectListFragment extends Fragment {

    private Toolbar _toolbar;

    private RecyclerView _recyclerView;
    private RecyclerView.LayoutManager _layoutManager;
    private RecyclerView.Adapter _adapter;
    private ArrayList<Subject> _subjects;
    private CardView _cardView;
    private ImageButton _add;


    public SubjectListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.sub_list,container,false);

        _toolbar = (Toolbar) v.findViewById(R.id.action);
        _toolbar.setTitle(getActivity().getTitle());
        //getActivity().setSupportActionBar(_toolbar);
        _toolbar.inflateMenu(R.menu.my);

        _toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.d("Debug Settings frag", "M herer");
                int id = item.getItemId();
                if (id == R.id.action_settings) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                    ft.replace(R.id.content_fragment,new SettingsFragment());
                    ft.addToBackStack(null);
                    ft.commit();
                    Log.d("Debug Settings frag", "M herer");
                    return true;
                }
                return false;
            }
        });

        _recyclerView = (RecyclerView) v.findViewById(R.id.status);

        _layoutManager = new LinearLayoutManager(getActivity());
        _recyclerView.setLayoutManager(_layoutManager);

        _subjects = SubjectLoader.get(getActivity()).getSubjects();

        _adapter = new SubjectAdapter(_subjects);
        _recyclerView.setAdapter(_adapter);

        _add = (ImageButton) v.findViewById(R.id.add_button);
        _add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

                ft.replace(R.id.content_fragment,new AddFragment());
                ft.addToBackStack(null);

                ft.commit();

            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        _adapter.notifyDataSetChanged();
        //_subjects = new DatabaseHandler(getActivity()).getSubjects();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        Log.d("Debug Settings frag", "M herer");
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
            ft.replace(R.id.content_fragment,new SettingsFragment());
            ft.addToBackStack(null);
            ft.commit();
            Log.d("Debug Settings frag", "M herer");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
