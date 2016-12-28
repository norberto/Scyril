package com.mist.sciryl.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.TwoSongInfosFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.ArrayList;
import java.util.List;

public class TwoSongInfosAdapter extends ArrayAdapter<TwoSongInfosFragment> {

    private final Context context;
    private final List<SongInfo> songs;

    public TwoSongInfosAdapter(Context context, List<SongInfo> songs) {
        super(context, R.layout.fragment_two_medium_song_info, convertSongsToTwoSongInfosFragment(songs));
        this.context = context;
        this.songs = songs;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_two_medium_song_info, LinearLayout.class);

        TwoSongInfosFragment frag = TwoSongInfosFragment.fromParent(rowView);
        frag.setNth(0, Helpers.safeGet(songs, position * 2));
        frag.setNth(1, Helpers.safeGet(songs, position * 2 + 1));

		return rowView;
	}

    private static List<TwoSongInfosFragment> convertSongsToTwoSongInfosFragment(List<SongInfo> songs) {
        List<TwoSongInfosFragment> list = new ArrayList<>();

        TwoSongInfosFragment fragment = new TwoSongInfosFragment();

        for (int i = 0; i < songs.size(); i++) {
            fragment.setNth(i % 2, songs.get(i));
            if (i % 2 == 1) {
                list.add(fragment);
                fragment = new TwoSongInfosFragment();
            }
        }
        // Last fragment in the list can have 1 or 2 values
        if (!fragment.isEmpty()) {
            list.add(fragment);
        }

        return list;
    }

    public List<SongInfo> getSongs() {
        return songs;
    }
}
