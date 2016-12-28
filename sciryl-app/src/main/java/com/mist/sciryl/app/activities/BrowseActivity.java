package com.mist.sciryl.app.activities;

import android.content.Context;
import android.os.Bundle;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.hor.PopMusicFragment;
import com.mist.sciryl.app.fragments.hor.PopularMusicFragment;
import com.mist.sciryl.app.fragments.hor.RockMusicFragment;
import com.mist.sciryl.app.fragments.hor.TodaysHitsFragment;
import com.mist.sciryl.implementation.ScirylDb;
import com.mist.sciryl.implementation.SongListServicesImpl;
import com.mist.sciryl.model.SongListServices;

public class BrowseActivity extends ScirylActivity {

    private TodaysHitsFragment mTodaysHitsFragment;
    private PopularMusicFragment mPopularMusicFragment;
    private PopMusicFragment mPopMusicFragment;
    private RockMusicFragment mRockMusicFragment;

    // Services
    private SongListServices songListServices;

    private void loadDb(Context context) {
        ScirylDb db = new ScirylDb(context);
        songListServices = new SongListServicesImpl(db);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDb(getApplicationContext());
        onCreate(R.layout.activity_browse, bundleOrIntentExtras(savedInstanceState));

        mTodaysHitsFragment = (TodaysHitsFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_todays_hits);
        mTodaysHitsFragment.populateSongInfo(songListServices.getTodaysHits());

        mPopularMusicFragment = (PopularMusicFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_popular_music);
        mPopularMusicFragment.populateSongInfo(songListServices.getPopularMusic());

        mPopMusicFragment = (PopMusicFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_pop_music);
        mPopMusicFragment.populateSongInfo(songListServices.getPopMusic());

        mRockMusicFragment = (RockMusicFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_rock_music);
        mRockMusicFragment.populateSongInfo(songListServices.getRockMusic());
    }

}
