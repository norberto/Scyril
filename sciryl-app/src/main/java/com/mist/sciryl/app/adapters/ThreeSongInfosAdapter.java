package com.mist.sciryl.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.ThreeSongInfosFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.ArrayList;
import java.util.List;

public class ThreeSongInfosAdapter extends ArrayAdapter<ThreeSongInfosFragment> {

    private Context context;
    private List<SongInfo> songs;

    public ThreeSongInfosAdapter(Context context, List<SongInfo> songs) {
        super(context, R.layout.fragment_three_hor_small_song_info, convertSongsToThreeSongInfosFragment(songs));
        this.context = context;
        this.songs = songs;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_three_hor_small_song_info, LinearLayout.class);

        ThreeSongInfosFragment frag = ThreeSongInfosFragment.fromParent(rowView);
        frag.setNth(0, Helpers.safeGet(songs, position * 3));
        frag.setNth(1, Helpers.safeGet(songs, position * 3 + 1));
        frag.setNth(2, Helpers.safeGet(songs, position * 3 + 2));

		return rowView;
	}

    private static List<ThreeSongInfosFragment> convertSongsToThreeSongInfosFragment(List<SongInfo> songs) {
        List<ThreeSongInfosFragment> list = new ArrayList<>();

        ThreeSongInfosFragment fragment = new ThreeSongInfosFragment();

        for (int i = 0; i < songs.size(); i++) {
            fragment.setNth(i % 3, songs.get(i));
            if (i % 3 == 2) {
                list.add(fragment);
                fragment = new ThreeSongInfosFragment();
            }
        }
        // Last fragment in the list can have 1, 2 or 3 values
        if (!fragment.isEmpty()) {
            list.add(fragment);
        }

        return list;
    }

    public List<SongInfo> getSongs() {
        return songs;
    }
}
