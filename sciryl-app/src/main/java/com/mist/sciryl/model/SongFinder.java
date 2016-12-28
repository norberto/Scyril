package com.mist.sciryl.model;

import com.google.common.base.Optional;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.List;

public interface SongFinder {
    Optional<SongInfo> findByFullName(String fullName);
    List<SongInfo> findBySearchQuery(String query);
}
