package com.mist.sciryl.app.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.vert.SearchResultItemFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.model.songs.SongInfo;

import javax.annotation.Nullable;
import java.util.List;

public class SearchResultItemsAdapter extends ArrayAdapter<SearchResultItemFragment> {

    private Context context;
    private List<SongInfo> songs;

    public SearchResultItemsAdapter(Context context, List<SongInfo> songs) {
        super(context, R.layout.fragment_search_result_item, convertSongsToSearchResultItemFragment(songs));
        this.context = context;
        this.songs = songs;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_search_result_item, LinearLayout.class);

        SearchResultItemFragment frag = SearchResultItemFragment.fromParent(rowView);
        frag.updateSongInfo(songs.get(position));
        if (position % 2 == 1) {
           frag.setBackgroundColor(0xFFE9E9E9);
        }

        return rowView;
	}

    private static List<SearchResultItemFragment> convertSongsToSearchResultItemFragment(List<SongInfo> songs) {
        return Lists.newArrayList(Iterables.transform(songs, new Function<SongInfo, SearchResultItemFragment>() {
            @Override
            public SearchResultItemFragment apply(@Nullable SongInfo songInfo) {
                SearchResultItemFragment frag = new SearchResultItemFragment();
                frag.updateSongInfo(songInfo);
                return frag;
            }
        }));
    }

    public List<SongInfo> getSongs() {
        return songs;
    }
}
