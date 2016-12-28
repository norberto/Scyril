package com.mist.sciryl.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.MediumSongInfoFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class MediumSongInfoAdapter extends ArrayAdapter<SongInfo> {

    private Context context;
    private List<SongInfo> songs;

    public MediumSongInfoAdapter(Context context, List<SongInfo> values) {
        super(context, R.layout.fragment_medium_song_info, values);

        this.context = context;
        this.songs = values;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TableLayout rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_medium_song_info, TableLayout.class);

        MediumSongInfoFragment.fromParent(rowView).updateSongInfo(songs.get(position));

		return rowView;
	}
}
