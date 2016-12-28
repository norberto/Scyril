package com.mist.sciryl.app.fragments.vert;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.fragments.ScirylFragment;
import com.mist.sciryl.app.helpers.AndroidHelpers;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.SongInfoOnClickListener;
import com.mist.sciryl.model.songs.SongInfo;
import com.squareup.picasso.Picasso;

public class SmallSongInfoFragment extends ScirylFragment {

    private SongInfo songInfo;
    private SongInfoOnClickListener songInfoOnClickListener;

    public SmallSongInfoFragment() {
        super(R.layout.fragment_small_song_info);
    }

    public static SmallSongInfoFragment fromFragmentContainer(GridLayout fragmentContainer) {
        SmallSongInfoFragment frag = new SmallSongInfoFragment();
        frag.fragmentContainer = fragmentContainer;
        frag.extractInfo();
        return frag;
    }

    protected void extractInfo() {
        if (fragmentContainer == null) {
            return;
        }

        TextView mSongTitle = findContainerViewById(R.id.song_title, TextView.class);
        TextView mSongAuthor = findContainerViewById(R.id.song_author, TextView.class);
        ImageView mSongAlbumPicture = findContainerViewById(R.id.song_album_picture, ImageView.class);


        songInfo = new SongInfo.Builder()
                .withTitle(mSongTitle.getText().toString())
                .withAuthor(mSongAuthor.getText().toString())
                .withImageId((Integer) mSongAlbumPicture.getTag())
                .build();

        if (songInfoOnClickListener == null) {
            songInfoOnClickListener = new SongInfoOnClickListener();
        }
        songInfoOnClickListener.setSongInfo(songInfo);
        mSongAlbumPicture.setOnClickListener(songInfoOnClickListener);
    }

    public void updateSongInfo(SongInfo songInfo) {
        if (songInfo != null && !songInfo.equals(this.songInfo)) {
            this.songInfo = songInfo;
            songInfoOnClickListener.setSongInfo(songInfo);
            updateView();
        }
    }

    protected void updateView() {
        if (fragmentContainer == null || songInfo == null) {
            return;
        }

        TextView mSongTitle = findContainerViewById(R.id.song_title, TextView.class);
        TextView mSongAuthor = findContainerViewById(R.id.song_author, TextView.class);

        mSongTitle.setText(songInfo.getTitle());
        mSongAuthor.setText(songInfo.getAuthor());

        if (songInfo.hasImageId()) {
            ImageView mSongAlbumPicture = findContainerViewById(R.id.song_album_picture, ImageView.class);
            if (Helpers.USE_PICASSO) {
                Picasso.with(fragmentContainer.getContext()).load(songInfo.getImageId()).into(mSongAlbumPicture);
            } else {
                mSongAlbumPicture.setImageResource(songInfo.getImageId());
            }
        }
    }

    @Override
    public View onCreateView(Bundle savedInstanceState) {
        if (songInfo != null) {
            updateView();
        }

        return fragmentContainer;
    }

    private <T> T findContainerViewById(@IdRes int id, Class<T> clazz) {
        return AndroidHelpers.findContainerViewById(fragmentContainer, id, clazz);
    }

}
