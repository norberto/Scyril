package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.model.songs.SongInfo;

public class ThreeSongInfosFragment extends ScirylFragment {

    private SmallSongInfoFragment mFirstSongInfoFragment;
    private SongInfo mFirstSongInfo;

    private SmallSongInfoFragment mSecondSongInfoFragment;
    private SongInfo mSecondSongInfo;

    private SmallSongInfoFragment mThirdSongInfoFragment;
    private SongInfo mThirdSongInfo;

    public ThreeSongInfosFragment() {
        super(R.layout.fragment_three_hor_small_song_info);
    }

    public void setNth(int n, SongInfo song) {
        switch(n) {
            case 0:
                mFirstSongInfo = song;
                if (mFirstSongInfoFragment != null) {
                    mFirstSongInfoFragment.updateSongInfo(song);
                }
                return;
            case 1:
                mSecondSongInfo = song;
                if (mSecondSongInfoFragment != null) {
                    mSecondSongInfoFragment.updateSongInfo(song);
                }
                return;
            case 2:
                mThirdSongInfo = song;
                if (mThirdSongInfoFragment != null) {
                    mThirdSongInfoFragment.updateSongInfo(song);
                }
                return;
            default:
                return;
        }
    }

    public boolean isEmpty() {
        return mFirstSongInfo == null && mSecondSongInfo == null && mThirdSongInfo == null;
    }

    private void extractInfo() {
        if (fragmentContainer == null) {
            return;
        }

        mFirstSongInfoFragment = SmallSongInfoFragment.fromFragmentContainer(
                (GridLayout) fragmentContainer.findViewById(R.id.first_song_info));
        mSecondSongInfoFragment = SmallSongInfoFragment.fromFragmentContainer(
                (GridLayout) fragmentContainer.findViewById(R.id.second_song_info));
        mThirdSongInfoFragment = SmallSongInfoFragment.fromFragmentContainer(
                (GridLayout) fragmentContainer.findViewById(R.id.third_song_info));
    }

    public static ThreeSongInfosFragment fromParent(LinearLayout parent) {
        ThreeSongInfosFragment frag = new ThreeSongInfosFragment();
        frag.fragmentContainer = parent;
        frag.extractInfo();
        return frag;
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        extractInfo();

        return fragmentContainer;
    }
}
