package com.mist.sciryl.app.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.SearchResultItemListFragment;
import com.mist.sciryl.implementation.ScirylDb;
import com.mist.sciryl.implementation.SongListServicesImpl;
import com.mist.sciryl.model.SongListServices;

public class SearchResultsActivity extends ScirylActivity {

    private SearchResultItemListFragment mSearchResultItemListFragment;
    private SongListServices songListServices;

    private void loadDb(Context context) {
        ScirylDb db = new ScirylDb(context);
        songListServices = new SongListServicesImpl(db);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        loadDb(getApplicationContext());
        onCreate(R.layout.activity_search_results, savedInstanceState);

        mSearchResultItemListFragment = (SearchResultItemListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_search_result_item_list);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            mSearchResultItemListFragment.updateSongInfos(
                songListServices.getSongFinder().findBySearchQuery(query));
        }
    }

}
