package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.adapters.SearchResultItemsAdapter;
import com.mist.sciryl.app.adapters.TwoSongInfosAdapter;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class SearchResultItemListFragment extends ScirylFragment {

    public List<SongInfo> songInfos;

    public SearchResultItemListFragment() {
        super(R.layout.fragment_search_result_item_list);
    }

    public void updateSongInfos(List<SongInfo> songInfos) {
        this.songInfos = songInfos;
        updateView();
    }

    private void updateView() {
        if (fragmentContainer == null) {
            return;
        }

        SearchResultItemsAdapter adapter = (SearchResultItemsAdapter) ((ListView) fragmentContainer).getAdapter();
        if (adapter == null || !adapter.getSongs().equals(songInfos)) {
            ((ListView)fragmentContainer)
                    .setAdapter(new SearchResultItemsAdapter(fragmentContainer.getContext(), songInfos));
            AndroidHelpers.setListViewHeightBasedOnChildren((ListView) fragmentContainer);
        }
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        if (songInfos != null) {
            updateView();
        }

        return fragmentContainer;
    }
}
