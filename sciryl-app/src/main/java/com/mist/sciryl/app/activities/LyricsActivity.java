package com.mist.sciryl.app.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.implementation.ScirylDb;
import com.mist.sciryl.implementation.SongListServicesImpl;
import com.mist.sciryl.model.SongListServices;
import com.mist.sciryl.model.songs.SongInfo;

public class LyricsActivity extends ScirylActivity {

    public static final String EXTRA_SONG_INFO = "ExtraSongInfo";

    private TextView mLyricsView;

    // Services
    private SongListServices songListServices;

    private SongInfo songInfo;

    public LyricsActivity() {

    }

    private void loadDb(Context context) {
        ScirylDb db = new ScirylDb(context);
        songListServices = new SongListServicesImpl(db);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        loadDb(getApplicationContext());
        onCreate(R.layout.activity_lyrics, bundleOrIntentExtras(savedInstanceState));

        mLyricsView = ((TextView) findViewById(R.id.lyricsView));

        String fullSongName = this.savedInstanceState.getString(EXTRA_SONG_INFO);
        songInfo = songListServices.getSongFinder().findByFullName(fullSongName).orNull();
        updateLyrics();
    }

    private void updateLyrics() {
        if (songInfo == null || mLyricsView == null) {
            return;
        }

        mLyricsView.setText(songInfo.getLyrics());
    }

    @Override
    public void restoreActionBar() {
        super.restoreActionBar();
        if (songInfo != null) {
            ActionBar actionBar = getSupportActionBar();
            actionBar.setTitle(songInfo.getFullSongName());
        }
    }
}
