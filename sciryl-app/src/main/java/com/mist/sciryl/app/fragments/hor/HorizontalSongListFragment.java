package com.mist.sciryl.app.fragments.hor;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.views.TwoWayView;
import com.mist.sciryl.app.adapters.MediumSongInfoAdapter;
import com.mist.sciryl.app.adapters.SmallSongInfoAdapter;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public abstract class HorizontalSongListFragment extends ScirylFragment {

    public static final int FORMAT_SMALL_SONG_LIST = 0;
    public static final int FORMAT_MEDIUM_SONG_LIST = 1;

    protected TwoWayView horListFragment;
    protected List<SongInfo> songInfoList;
    private int titleResId;
    private int format;

    public HorizontalSongListFragment(@StringRes int titleResId, int format) {
        super(R.layout.fragment_hor_song_list);
        this.titleResId = titleResId;
        this.format = format;
    }

    public void populateSongInfo(List<SongInfo> songInfoList) {
        this.songInfoList = songInfoList;
        updateView();
    }

    protected void updateView() {
        if (horListFragment == null) {
            return;
        }

        ArrayAdapter<SongInfo> adapter;
        if (format == FORMAT_SMALL_SONG_LIST) {
            adapter = new SmallSongInfoAdapter(horListFragment.getContext(), songInfoList);
        } else {
            adapter = new MediumSongInfoAdapter(horListFragment.getContext(), songInfoList);
        }
        horListFragment.setAdapter(adapter);
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        horListFragment = (TwoWayView) fragmentContainer.findViewById(R.id.lvItems);
        TextView fragmentTitle = (TextView) fragmentContainer.findViewById(R.id.fragment_title);

        fragmentTitle.setText(titleResId);

        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());

        horListFragment.setItemMargin(px);

        if (format == FORMAT_MEDIUM_SONG_LIST) {
            ViewGroup.LayoutParams layoutParams = horListFragment.getLayoutParams();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, getResources().getDisplayMetrics());
            horListFragment.setLayoutParams(layoutParams);
        }

        if (songInfoList != null) {
            updateView();
        }

        return fragmentContainer;
    }
}
