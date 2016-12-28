package com.mist.sciryl.app.activities;

import android.content.Context;
import android.os.Bundle;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.SmallSongInfoListFragment;
import com.mist.sciryl.implementation.ScirylDb;
import com.mist.sciryl.implementation.SongListServicesImpl;
import com.mist.sciryl.model.SongListServices;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class FavouritesActivity extends ScirylActivity {

    private SmallSongInfoListFragment mFavouritesFragment;

    // Services
    private SongListServices songListServices;

    public FavouritesActivity() {
    }

    private void loadDb(Context context) {
        ScirylDb db = new ScirylDb(context);
        songListServices = new SongListServicesImpl(db);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDb(getApplicationContext());
        onCreate(R.layout.activity_favourites, bundleOrIntentExtras(savedInstanceState));

        mFavouritesFragment = (SmallSongInfoListFragment)
                getSupportFragmentManager().findFragmentById(R.id.fragment_three_hor_small_song_info);
        List<SongInfo> favourites = songListServices.getFavourites();
        // Always give a x%3==0 amount of songs to have a prettier view
        mFavouritesFragment.updateSongs(favourites.subList(0, (favourites.size() / 3) * 3));
    }
}
