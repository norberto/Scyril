package com.mist.sciryl.implementation;

import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.SongFinder;
import com.mist.sciryl.model.SongListServices;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

import static com.mist.sciryl.implementation.SongFinderImpl.containsAuthor;
import static com.mist.sciryl.implementation.SongFinderImpl.containsTitle;

public class SongListServicesImpl implements SongListServices {

    private final ScirylDb db;
    private final SongFinderImpl songFinder;

    public SongListServicesImpl(ScirylDb db) {
        this.db = db;
        this.songFinder = new SongFinderImpl(db);
    }

    @Override
    public List<SongInfo> getAllSongs() {
        return Helpers.sortSongList(db.getAllSongs());
    }

    @Override
    public List<SongInfo> getRecentPicks() {
        return songFinder.filterSongs(containsTitle(
            "Hello", "Sorry", "What do you mean?", "Stitches", "Blank Space"
        ));
    }

    @Override
    public List<SongInfo> getFavourites() {
        return songFinder.filterSongs(containsAuthor(
            "Avenged Sevenfold", "Bon Jovi", "Michael Jackson", "OneRepublic", "The Beatles"
        ));
    }

    @Override
    public List<SongInfo> getTopPicks() {
        return songFinder.filterSongs(containsTitle(
            "Hello", "Rolling in the deep", "Bed of roses", "Shake it off", "Never Say Never",
            "Lost", "Scream", "All The Right Moves", "Mercy"
        ));
    }

    @Override
    public List<SongInfo> getTodaysHits() {
        return songFinder.filterSongs(containsTitle(
            "Save Me", "Lost", "Nightmare", "Baby", "Apologize", "Secrets", "Yesterday"
        ));
    }

    @Override
    public List<SongInfo> getPopularMusic() {
        return songFinder.filterSongs(containsAuthor(
            "Adele", "OneRepublic", "Shawn Mendes", "Taylor Swift", "Justin Bieber"
        ));
    }

    @Override
    public List<SongInfo> getPopMusic() {
        return songFinder.filterSongs(containsAuthor(
            "Adele", "Justin Bieber", "Shawn Mendes", "Taylor Swift"
        ));
    }

    @Override
    public List<SongInfo> getRockMusic() {
        return songFinder.filterSongs(containsAuthor(
            "Avenged Sevenfold", "Bon Jovi"
        ));
    }

    @Override
    public SongFinder getSongFinder() {
        return songFinder;
    }
}
