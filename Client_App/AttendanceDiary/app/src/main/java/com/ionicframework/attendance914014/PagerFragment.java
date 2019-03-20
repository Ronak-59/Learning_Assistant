package com.ionicframework.attendance914014;


import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PagerFragment extends Fragment {

    private SlidingTabLayout _slidingTabLayout;
    private ViewPager _viewPager;
    private TextView _textView;
    private Toolbar _toolbar;

    public static final String TAG="Debug";


    protected RecyclerView _recyclerView;
    protected RecyclerView.LayoutManager _layoutManager;
    protected RecyclerView.Adapter _adapter;
    protected ArrayList<Subject> _subjects;


    public PagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment,container,false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState) {

        Log.d(TAG,"Position is ");

        _toolbar = (Toolbar) v.findViewById(R.id.action);
        _toolbar.setTitle(R.string.app_name);

        _viewPager = (ViewPager) v.findViewById(R.id.pager);
        _viewPager.setAdapter(new SamplePagerAdapter());

        _slidingTabLayout = (SlidingTabLayout) v.findViewById(R.id.sliding_tabs);
        _slidingTabLayout.setViewPager(_viewPager);
    }

    class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return o==view;
        }

        @Override
        public CharSequence getPageTitle(int position) {
               return position == 0?"Status":"Today";
        }

        @Override
        public Object instantiateItem(ViewGroup container,int position) {

            /*Log.d(TAG,"Position is " + position);

            View v = getActivity().getLayoutInflater().inflate(R.layout.pager,container,false);
            container.addView(v);

            _textView = (TextView) v.findViewById(R.id.text);
            _textView.setText(String.valueOf(position + 1));*/
            View v = getActivity().getLayoutInflater().inflate(R.layout.activity_my,container,false);


            if(_adapter != null)
                _adapter.notifyDataSetChanged();


            if (position == 0) {
                v = getActivity().getLayoutInflater().inflate(R.layout.status,container,false);

                ///////////////////////////////////////


                _recyclerView = (RecyclerView) v.findViewById(R.id.status);

               // _recyclerView.setHasFixedSize(true);

                _layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                _recyclerView.setLayoutManager(_layoutManager);

                _subjects = SubjectLoader.get(getActivity()).getSubjects();

                _adapter = new SubjectAdapter(_subjects);
                _recyclerView.setAdapter(_adapter);

            } else if (position == 1) {
                v = getActivity().getLayoutInflater().inflate(R.layout.status,container,false);

                /////////////////////////////////////////////////////
                _recyclerView = (RecyclerView) v.findViewById(R.id.status);

                _layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                _recyclerView.setLayoutManager(_layoutManager);

                if (_subjects == null) {
                    _subjects = SubjectLoader.get(getActivity()).getSubjects();
                }

                _recyclerView.setAdapter(new TodayAdapter(_subjects));
            }

            container.addView(v);
            return v;

        }
    }


}
