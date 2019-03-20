package com.trevorhalvorson.devjobs.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

import com.google.gson.Gson;
import com.trevorhalvorson.devjobs.R;
import com.trevorhalvorson.devjobs.fragment.EditLocationDialog;
import com.trevorhalvorson.devjobs.fragment.JobListFragment;
import com.trevorhalvorson.devjobs.fragment.SavedSearchesFragment;
import com.trevorhalvorson.devjobs.model.Search;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MainActivity extends AppCompatActivity
        implements EditLocationDialog.EditLocationDialogListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SAVED_SEARCHES_KEY = "saved_searches_key";

    private static SearchListener mSearchListener;
    private static ViewPager mViewPager;

    private DrawerLayout mDrawerLayout;
    private SearchView mSearchView;
    private List<Search> mSavedSearches;
    private String mLocationString;
    private String mQuery;

    public interface SearchListener {
        void search(String query, String location);
    }

    public static void setSearchListener(SearchListener listener) {
        mSearchListener = listener;
    }

    @Override
    public void onFinishEditDialog(String inputText) {
        if (!inputText.isEmpty()) {
            mLocationString = inputText;
            showSnackBar(getString(R.string.sc_loc_set) + mLocationString);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_job);

        EditLocationDialog.setListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpagerxd);
        setupViewPager(mViewPager);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(mViewPager);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int i = item.getItemId();
                if (i == R.id.settings) {
                    Intent intent = new Intent(MainActivity.this, SettingsPreferenceActivity.class);
                    startActivity(intent);

                }
                item.setChecked(true);
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        mSavedSearches = new ArrayList<>();

        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        Gson gson = new Gson();

        for (String json : preferences.getStringSet(SAVED_SEARCHES_KEY, new TreeSet<String>())) {
            Search search = gson.fromJson(json, Search.class);
            mSavedSearches.add(search);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        adapter.addFragment(new JobListFragment(), "Jobs");
        adapter.addFragment(new SavedSearchesFragment(), "Saved Searches");

        viewPager.setAdapter(adapter);
    }

    public static void setCurrentTab(int tabPosition) {
        mViewPager.setCurrentItem(tabPosition);
    }

    public void setCurrentItem(int item, boolean smoothScroll) {
        mViewPager.setCurrentItem(item, smoothScroll);
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        final MenuItem searchItem = menu.findItem(R.id.menu_item_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint(getString(R.string.search_description));
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setCurrentItem(0, true);
                query = query.trim();
                if (!query.isEmpty()) {
                    mSearchView.clearFocus();
                    mQuery = query;
                    mSearchListener.search(mQuery, mLocationString);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == R.id.action_set_location) {
            FragmentManager fm = getSupportFragmentManager();
            EditLocationDialog dialog = EditLocationDialog.newInstance();
            dialog.show(fm, "fragment_edit_location");
            return true;
        } else if (i == R.id.action_save_search) {
            saveSearch();
            return true;
        } else if (i == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void saveSearch() {
        Search search = new Search(mQuery, mLocationString);
        mSavedSearches.add(search);
        savePrefs();
        showSnackBar(search.toString() + getString(R.string.sb_added_search));
    }

    private void savePrefs() {
        SharedPreferences preferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        Set<String> jsonSet = new TreeSet<>();
        Gson gson = new Gson();
        for (Search search : mSavedSearches) {
            jsonSet.add(gson.toJson(search));
        }
        editor.putStringSet(SAVED_SEARCHES_KEY, jsonSet);
        editor.apply();
    }

    private void showSnackBar(String snackBarMessage) {
        Snackbar.make(
                findViewById(R.id.main_content),
                snackBarMessage,
                Snackbar.LENGTH_LONG)
                .show();
    }
}
