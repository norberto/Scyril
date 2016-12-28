package com.mist.sciryl.app.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.SmallSongInfoFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class SmallSongInfoAdapter extends ArrayAdapter<SongInfo> {

    private Context context;
    private List<SongInfo> songs;

    public SmallSongInfoAdapter(Context context, List<SongInfo> values) {
        super(context, R.layout.fragment_small_song_info, values);

        this.context = context;
        this.songs = values;
    }

    private class ViewHolder {
        public ImageView photoImageView;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		GridLayout rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_small_song_info, GridLayout.class);



        SmallSongInfoFragment.fromFragmentContainer(rowView).updateSongInfo(songs.get(position));

		return rowView;
	}
}
