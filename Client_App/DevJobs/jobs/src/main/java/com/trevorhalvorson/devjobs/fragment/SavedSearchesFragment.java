package com.trevorhalvorson.devjobs.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.trevorhalvorson.devjobs.DividerItemDecoration;
import com.trevorhalvorson.devjobs.R;
import com.trevorhalvorson.devjobs.activity.MainActivity;
import com.trevorhalvorson.devjobs.model.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Trevor Halvorson on 9/21/2015.
 */
public class SavedSearchesFragment extends Fragment {
    private static final String TAG = SavedSearchesFragment.class.getSimpleName();
    private static final String SAVED_SEARCHES_KEY = "saved_searches_key";

    public interface SavedSearchSelectedListener {
        void onSearchSelected(Search savedSearch);
    }

    public static void setListener(SavedSearchSelectedListener listener) {
        mListener = listener;
    }

    private static SavedSearchSelectedListener mListener;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SearchAdapter mSearchSearchAdapter;
    private List<Search> mSearchList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_saved_searches, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.searchRecyclerView);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(R.color.primary_dark));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupAdapter();
            }
        });

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final Search searchRemoved = mSearchList.remove(position);
                mSearchSearchAdapter.notifyItemRemoved(position);
                removeSearch(searchRemoved);
                Snackbar.make(
                        getActivity().findViewById(R.id.main_content),
                        searchRemoved.toString() + " removed",
                        Snackbar.LENGTH_LONG)
                        .setAction(R.string.sb_action, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mSearchList.add(searchRemoved);
                                undoRemoveSearch();
                                setupAdapter();
                            }
                        })
                        .show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

        setupAdapter();

        return rootView;
    }

    private void setupAdapter() {
        mSearchList.clear();
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();
        for (String json : preferences.getStringSet(SAVED_SEARCHES_KEY, new TreeSet<String>())) {
            Search search = gson.fromJson(json, Search.class);
            mSearchList.add(search);
        }
        mSearchSearchAdapter = new SearchAdapter(mSearchList);
        mRecyclerView.setAdapter(mSearchSearchAdapter);
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private void removeSearch(Search searchToRemove) {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> jsonSet = new TreeSet<>();
        Gson gson = new Gson();
        for (String search : preferences.getStringSet(SAVED_SEARCHES_KEY, new TreeSet<String>())) {
            if (!search.equals(gson.toJson(searchToRemove))) {
                jsonSet.add(search);
            }
        }
        editor.putStringSet(SAVED_SEARCHES_KEY, jsonSet);
        editor.apply();
    }

    private void undoRemoveSearch() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> jsonSet = new TreeSet<>();
        Gson gson = new Gson();
        for (Search search : mSearchList) {
            jsonSet.add(gson.toJson(search));
        }
        editor.putStringSet(SAVED_SEARCHES_KEY, jsonSet);
        editor.apply();
    }

    private class SearchHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {

        private Search mSearch;
        private TextView mDescTextView;
        private TextView mLocTextView;

        public SearchHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mDescTextView = (TextView) itemView.findViewById(R.id.searchDescriptionTextView);
            mLocTextView = (TextView) itemView.findViewById(R.id.searchLocationTextView);
        }

        public void bindSearch(Search search) {
            mSearch = search;
            mDescTextView.setText(mSearch.getDescription());
            if (mSearch.getLocation() != null) {
                mLocTextView.setText(mSearch.getLocation());
            }

        }

        @Override
        public void onClick(View v) {
            MainActivity.setCurrentTab(0);
            mListener.onSearchSelected(mSearch);
        }
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchHolder> {

        private List<Search> mSearches = new ArrayList<>();

        public SearchAdapter(List<Search> searches) {
            mSearches = searches;
        }

        @Override
        public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.list_item_search, parent, false);
            return new SearchHolder(view);
        }

        @Override
        public void onBindViewHolder(SearchHolder searchHolder, int position) {
            Search search = mSearches.get(position);
            searchHolder.bindSearch(search);
        }

        @Override
        public int getItemCount() {
            return mSearches.size();
        }
    }
}
