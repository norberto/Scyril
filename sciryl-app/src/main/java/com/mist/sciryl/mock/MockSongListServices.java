package com.mist.sciryl.mock;

import com.mist.sciryl.model.SongFinder;
import com.mist.sciryl.model.SongListServices;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public class MockSongListServices implements SongListServices {

    private final MockDb db;

    public MockSongListServices(MockDb db) {
        this.db = db;
    }

    @Override
    public List<SongInfo> getAllSongs() {
        return db.getSongs();
    }

    @Override
    public List<SongInfo> getRecentPicks() {
        return db.getSongs();
    }

    @Override
    public List<SongInfo> getFavourites() {
        return db.getSongs();
    }

    @Override
    public List<SongInfo> getTopPicks() {
        return db.getSongs();
    }

    @Override
    public List<SongInfo> getTodaysHits() {
        return null;
    }

    @Override
    public List<SongInfo> getPopularMusic() {
        return null;
    }

    @Override
    public List<SongInfo> getPopMusic() {
        return null;
    }

    @Override
    public List<SongInfo> getRockMusic() {
        return null;
    }

    @Override
    public SongFinder getSongFinder() {
        return null;
    }
}
