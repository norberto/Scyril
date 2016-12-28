package com.mist.sciryl.model;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.mist.sciryl.app.activities.ActivitiesRegistry;
import com.mist.sciryl.app.activities.LyricsActivity;
import com.mist.sciryl.app.activities.ScirylActivity;
import com.mist.sciryl.app.fragments.NavigationDrawerFragment;
import com.mist.sciryl.model.songs.SongInfo;

import static com.mist.sciryl.app.activities.LyricsActivity.EXTRA_SONG_INFO;
import static com.mist.sciryl.app.fragments.NavigationDrawerFragment.STATE_SELECTED_POSITION;

public class SongInfoOnClickListener implements View.OnClickListener {

    private ScirylActivity activity;
    private SongInfo songInfo;

    public SongInfoOnClickListener() {
        this(null);
    }

    public SongInfoOnClickListener(SongInfo songInfo) {
        this.songInfo = songInfo;
        activity = ScirylActivity.getCurrentActivity();
    }

    public void setSongInfo(SongInfo songInfo) {
        this.songInfo = songInfo;
    }

    @Override
    public void onClick(View view) {
        if (songInfo == null || activity == null) {
            return;
        }

        ActivitiesRegistry actReg = ActivitiesRegistry.findFromClass(LyricsActivity.class);
        Intent intent = new Intent(activity, LyricsActivity.class);
        intent.putExtra(EXTRA_SONG_INFO, songInfo.getFullSongName());
        intent.putExtra(STATE_SELECTED_POSITION, actReg.getIndex());
        activity.startActivity(intent, intent.getExtras());
        activity.overridePendingTransition(0, 0);
    }

    public void setActivity(ScirylActivity activity) {
        this.activity = activity;
    }
}
