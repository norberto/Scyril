package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.adapters.TwoSongInfosAdapter;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class MediumSongInfoListFragment extends ScirylFragment {

    private List<SongInfo> favourites;

    public MediumSongInfoListFragment() {
        super(R.layout.fragment_vert_two_medium_song_info_list);
    }

    public void updateSongs(List<SongInfo> songs) {
        this.favourites = songs;
        updateView();
    }

    private void updateView() {
        if (fragmentContainer == null) {
            return;
        }

        TwoSongInfosAdapter adapter = (TwoSongInfosAdapter) ((ListView) fragmentContainer).getAdapter();
        if (adapter == null || !adapter.getSongs().equals(favourites)) {
            ((ListView)fragmentContainer)
                    .setAdapter(new TwoSongInfosAdapter(fragmentContainer.getContext(), favourites));
            AndroidHelpers.setListViewHeightBasedOnChildren((ListView) fragmentContainer);
        }
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        if (favourites != null) {
            updateView();
        }

        return fragmentContainer;
    }
}
