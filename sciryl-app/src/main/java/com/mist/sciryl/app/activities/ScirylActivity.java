package com.mist.sciryl.app.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.NavigationDrawerFragment;
import com.mist.sciryl.app.fragments.NavigationDrawerFragment.NavigationDrawerCallbacks;

import static com.mist.sciryl.app.fragments.NavigationDrawerFragment.STATE_SELECTED_POSITION;

public abstract class ScirylActivity extends AppCompatActivity
    implements NavigationDrawerCallbacks {

    private static ScirylActivity CURRENT_ACTIVITY;

    protected NavigationDrawerFragment mNavigationDrawerFragment;
    protected SearchView mSearchView;

    protected Bundle savedInstanceState;

    public ScirylActivity() {
    }

    public static ScirylActivity getCurrentActivity() {
        return CURRENT_ACTIVITY;
    }

    public void onCreate(@LayoutRes int layout, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScirylActivity.CURRENT_ACTIVITY = this;
        this.savedInstanceState = savedInstanceState;
        setContentView(layout);

        setupNavigationDrawer();
    }

    protected Bundle bundleOrIntentExtras(Bundle bundle) {
        if (bundle == null && getIntent() != null) {
            return getIntent().getExtras();
        }
        return bundle;
    }

    protected void setupNavigationDrawer() {
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        if (mNavigationDrawerFragment != null) {
            // Set up the drawer.
            mNavigationDrawerFragment.setUp(
                    R.id.navigation_drawer,
                    (DrawerLayout) findViewById(R.id.drawer_layout));
        }
    }

    protected void startActivityFromActivitiesReg(int index) {
        startActivityFromActivitiesReg(ActivitiesRegistry.getActivitiesRegistry(index));
    }
    protected void startActivityFromActivitiesReg(Class<? extends ScirylActivity> activity) {
        startActivityFromActivitiesReg(ActivitiesRegistry.findFromClass(activity));
    }

    private void startActivityFromActivitiesReg(ActivitiesRegistry actReg) {
        if (actReg.getClazz() != getClass()) {
            Intent intent = new Intent(this, actReg.getClazz());
            intent.putExtra(STATE_SELECTED_POSITION, actReg.getIndex());
            startActivity(intent, intent.getExtras());
            overridePendingTransition(0, 0);
        }
    }

    private @StringRes int getActivityTitle() {
        ActivitiesRegistry actReg = ActivitiesRegistry.findFromClass(getClass());
        return actReg != null ? actReg.getName() : R.string.app_name;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        startActivityFromActivitiesReg(position);
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(getActivityTitle());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        restoreActionBar();
        setupSearch(menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem searchViewMenuItem = menu.findItem(R.id.action_search);
        searchViewMenuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                onSearchRequested();
                return true;
            }
        });
        return super.onPrepareOptionsMenu(menu);
    }

    private void setupSearch(Menu menu) {
        super.onCreateOptionsMenu(menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
               (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView =
                    (SearchView) searchItem.getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getComponentName()));

//            searchView.setSuggestionsAdapter(new CursorAdapter() {
//                @Override
//                public View newView(Context context, Cursor cursor, ViewGroup parent) {
//                    return null;
//                }
//
//                @Override
//                public void bindView(View view, Context context, Cursor cursor) {
//
//                }
//            });
        }
    }
}
