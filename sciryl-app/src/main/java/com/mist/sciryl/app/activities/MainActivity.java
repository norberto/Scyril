package com.mist.sciryl.app.activities;

import android.content.Context;
import android.os.Bundle;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.hor.FavouritesFragment;
import com.mist.sciryl.app.fragments.vert.MediumSongInfoListFragment;
import com.mist.sciryl.app.fragments.hor.RecentPicksFragment;
import com.mist.sciryl.implementation.ScirylDb;
import com.mist.sciryl.implementation.SongListServicesImpl;
import com.mist.sciryl.model.SongListServices;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;


public class MainActivity extends ScirylActivity {

    private RecentPicksFragment mRecentPicksFragment;
    private FavouritesFragment mFavouritesFragment;
    private MediumSongInfoListFragment mTopPicksFragment;

    // Services
    private SongListServices songListServices;

    public MainActivity() {
//        MockDb db = new MockDb();
//        songListServices = new MockSongListServices(db);
    }

    private void loadDb(Context context) {
        ScirylDb db = new ScirylDb(context);
        songListServices = new SongListServicesImpl(db);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDb(getApplicationContext());
        onCreate(R.layout.activity_main, bundleOrIntentExtras(savedInstanceState));


        mRecentPicksFragment = (RecentPicksFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_recent_picks);
        mRecentPicksFragment.populateSongInfo(songListServices.getRecentPicks());

        mFavouritesFragment = (FavouritesFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_favourites);
        mFavouritesFragment.populateSongInfo(songListServices.getFavourites());

        mTopPicksFragment = (MediumSongInfoListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_two_vert_medium_song_info_list);
        List<SongInfo> topPicks = songListServices.getTopPicks();
        // Always give a x%2==0 amount of songs to have a prettier view
        mTopPicksFragment.updateSongs(topPicks.subList(0, (topPicks.size() / 2) * 2));
    }
}
