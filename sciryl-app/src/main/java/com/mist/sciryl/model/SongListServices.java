package com.mist.sciryl.model;

import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public interface SongListServices {
    List<SongInfo> getAllSongs();
    List<SongInfo> getRecentPicks();
    List<SongInfo> getFavourites();
    List<SongInfo> getTopPicks();
    List<SongInfo> getTodaysHits();
    List<SongInfo> getPopularMusic();
    List<SongInfo> getPopMusic();
    List<SongInfo> getRockMusic();


    SongFinder getSongFinder();
}
