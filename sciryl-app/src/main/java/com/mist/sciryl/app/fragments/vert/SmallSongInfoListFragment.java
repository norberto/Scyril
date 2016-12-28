package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.adapters.ThreeSongInfosAdapter;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class SmallSongInfoListFragment extends ScirylFragment {

    private List<SongInfo> songs;

    public SmallSongInfoListFragment() {
        super(R.layout.fragment_vert_three_small_song_info_list);
    }

    public void updateSongs(List<SongInfo> songs) {
        this.songs = songs;
        updateView();
    }

    private void updateView() {
        if (fragmentContainer == null) {
            return;
        }
        
        ThreeSongInfosAdapter adapter = (ThreeSongInfosAdapter) ((ListView) fragmentContainer).getAdapter();
        if (adapter == null || !adapter.getSongs().equals(songs)) {
            ((ListView) fragmentContainer)
                    .setAdapter(new ThreeSongInfosAdapter(fragmentContainer.getContext(), songs));
            AndroidHelpers.setListViewHeightBasedOnChildren((ListView) fragmentContainer);
        }
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        if (songs != null) {
            updateView();
        }

        return fragmentContainer;
    }
}
