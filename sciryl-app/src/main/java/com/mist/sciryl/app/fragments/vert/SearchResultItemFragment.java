package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.model.SongInfoOnClickListener;
import com.mist.sciryl.model.songs.SongInfo;

public class SearchResultItemFragment extends ScirylFragment {

    private SongInfo songInfo;
    private int backgroundColor;
    private SongInfoOnClickListener songInfoOnClickListener;

    public SearchResultItemFragment() {
        super(R.layout.fragment_search_result_item);
        this.songInfoOnClickListener = new SongInfoOnClickListener();
    }

    public static SearchResultItemFragment fromParent(LinearLayout parent) {
        SearchResultItemFragment frag = new SearchResultItemFragment();
        frag.fragmentContainer = parent;
        frag.fragmentContainer.setOnClickListener(frag.songInfoOnClickListener);
        frag.updateView();
        return frag;
    }

    public void updateSongInfo(SongInfo songInfo) {
        if (!songInfo.equals(this.songInfo)) {
            this.songInfo = songInfo;
            this.songInfoOnClickListener.setSongInfo(songInfo);
            updateView();
        }
    }

    private void updateView() {
        if (fragmentContainer == null || songInfo == null) {
            return;
        }
        fragmentContainer.setBackgroundColor(backgroundColor);

        TextView mSongName = ((TextView) fragmentContainer.findViewById(R.id.song_name));
        mSongName.setText(songInfo.getFullSongName());
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        fragmentContainer.setOnClickListener(songInfoOnClickListener);
        updateView();

        return fragmentContainer;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        if (fragmentContainer != null) {
            fragmentContainer.setBackgroundColor(backgroundColor);
        }
    }
}
