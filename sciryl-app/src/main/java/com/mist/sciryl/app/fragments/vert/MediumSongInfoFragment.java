package com.mist.sciryl.app.fragments.vert;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.widget.PopupMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.activities.ActivitiesRegistry;
import com.mist.sciryl.app.activities.SearchResultsActivity;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.SongInfoOnClickListener;
import com.mist.sciryl.model.songs.SongInfo;
import com.squareup.picasso.Picasso;

import static com.mist.sciryl.app.fragments.NavigationDrawerFragment.STATE_SELECTED_POSITION;

public class MediumSongInfoFragment extends ScirylFragment {

    private ImageButton mOptions;
    private SongInfo songInfo;

    private OptionsOnClickListener optionsOnClickListener = new OptionsOnClickListener();
    private PopupOnMenuItemClickListener popupOnMenuItemClickListener = new PopupOnMenuItemClickListener();

    public MediumSongInfoFragment() {
        super(R.layout.fragment_medium_song_info);
    }

    public static MediumSongInfoFragment fromParent(TableLayout view) {
        MediumSongInfoFragment frag = new MediumSongInfoFragment();
        frag.fragmentContainer = view;
        frag.extractInfo();
        return frag;
    }

    public void updateSongInfo(SongInfo songInfo) {
        if (!songInfo.equals(this.songInfo)) {
            this.songInfo = songInfo;
            updateView();
        }
    }

    protected void updateView() {
        if (fragmentContainer == null) {
            return;
        }

        TextView mSongTitle = findContainerViewById(R.id.song_title, TextView.class);
        TextView mSongAuthor = findContainerViewById(R.id.song_author, TextView.class);

        mSongTitle.setText(songInfo.getTitle());
        mSongAuthor.setText(songInfo.getAuthor());

        ImageView mSongAlbumPicture = findContainerViewById(R.id.song_album_picture, ImageView.class);
        mSongAlbumPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLyrics();
            }
        });
        if (Helpers.USE_PICASSO) {
            Picasso.with(fragmentContainer.getContext()).load(songInfo.getImageId()).into(mSongAlbumPicture);
        } else {
            mSongAlbumPicture.setImageResource(songInfo.getImageId());
        }
    }

    private void extractInfo() {
        if (fragmentContainer == null) {
            return;
        }

        if (mOptions == null) {
            mOptions = (ImageButton) fragmentContainer.findViewById(R.id.options);
            if (mOptions != null) {
                mOptions.setOnClickListener(optionsOnClickListener);
            }
        }

        if (songInfo != null) {
            updateView();
        }
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        extractInfo();

        return fragmentContainer;
    }

    private <T> T findContainerViewById(@IdRes int id, Class<T> clazz) {
        return AndroidHelpers.findContainerViewById(fragmentContainer, id, clazz);
    }

    private class OptionsOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            PopupMenu popup = new PopupMenu(fragmentContainer.getContext(), mOptions);
            popup.getMenuInflater().inflate(R.menu.medium_song_info_menu, popup.getMenu());
            popup.setOnMenuItemClickListener(popupOnMenuItemClickListener);
            popup.show();
        }
    }

    private class PopupOnMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch(item.getItemId()) {
                case R.id.action_show_author:
                    showAuthor();
                    break;
                case R.id.action_show_lyrics:
                    showLyrics();
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    private void showLyrics() {
        SongInfoOnClickListener listener = new SongInfoOnClickListener();
        listener.setActivity(scirylActivity);
        listener.setSongInfo(songInfo);
        listener.onClick(fragmentContainer);
    }

    private void showAuthor() {
        ActivitiesRegistry actReg = ActivitiesRegistry.findFromClass(SearchResultsActivity.class);
        Intent intent = new Intent(scirylActivity, actReg.getClazz());
        intent.putExtra(STATE_SELECTED_POSITION, actReg.getIndex());
        intent.setAction(Intent.ACTION_SEARCH);
        intent.putExtra(SearchManager.QUERY, songInfo.getAuthor());
        scirylActivity.startActivity(intent, intent.getExtras());
        scirylActivity.overridePendingTransition(0, 0);
    }
}
