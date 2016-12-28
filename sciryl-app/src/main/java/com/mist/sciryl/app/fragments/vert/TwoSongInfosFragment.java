package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.model.songs.SongInfo;

public class TwoSongInfosFragment extends ScirylFragment {

    private MediumSongInfoFragment mFirstSongInfoFragment;
    private SongInfo mFirstSongInfo;

    private MediumSongInfoFragment mSecondSongInfoFragment;
    private SongInfo mSecondSongInfo;

    public TwoSongInfosFragment() {
        super(R.layout.fragment_two_medium_song_info);
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
            default:
                return;
        }
    }

    public boolean isEmpty() {
        return mFirstSongInfo == null && mSecondSongInfo == null;
    }

    private void extractInfo() {
        if (fragmentContainer == null) {
            return;
        }

        mFirstSongInfoFragment = MediumSongInfoFragment.fromParent(
                (TableLayout) fragmentContainer.findViewById(R.id.first_song_info));
        mSecondSongInfoFragment = MediumSongInfoFragment.fromParent(
                (TableLayout) fragmentContainer.findViewById(R.id.second_song_info));
    }

    public static TwoSongInfosFragment fromParent(LinearLayout parent) {
        TwoSongInfosFragment frag = new TwoSongInfosFragment();
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
