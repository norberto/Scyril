package com.mist.sciryl.implementation;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mist.sciryl.app.helpers.Helpers;
import com.mist.sciryl.model.SongFinder;
import com.mist.sciryl.model.songs.SongInfo;

import javax.annotation.Nullable;
import java.util.List;

public class SongFinderImpl implements SongFinder {

    private ScirylDb db;

    public SongFinderImpl(ScirylDb db) {
        this.db = db;
    }

    @Override
    public Optional<SongInfo> findByFullName(final String fullName) {
        return Iterables.tryFind(db.getAllSongs(), new Predicate<SongInfo>() {
            @Override
            public boolean apply(@Nullable SongInfo input) {
                return input.getFullSongName().equals(fullName);
            }
        });
    }

    @Override
    public List<SongInfo> findBySearchQuery(String query) {
        List<SongInfo> songInfos = Lists.newArrayList();

        for (SongInfo songInfo: db.getAllSongs()) {
            if (songInfo.testQuery(query)) {
                songInfos.add(songInfo);
            }
        }

        return Helpers.sortSongList(songInfos);
    }

    public List<SongInfo> filterSongs(Predicate<SongInfo> predicate) {
        return Helpers.sortSongList(
                    Lists.newArrayList(
                        Iterables.filter(db.getAllSongs(), predicate)));
    }

    public static Predicate<SongInfo> containsAuthor(final String... authors) {
        return new Predicate<SongInfo>() {
            @Override
            public boolean apply(@Nullable SongInfo input) {
                for (String author: authors) {
                    if (input != null && author.equalsIgnoreCase(input.getAuthor())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static Predicate<SongInfo> containsTitle(final String... titles) {
        return new Predicate<SongInfo>() {
            @Override
            public boolean apply(@Nullable SongInfo input) {
                for (String title: titles) {
                    if (input != null && title.equalsIgnoreCase(input.getTitle())) {
                        return true;
                    }
                }
                return false;
            }
        };
    }
}
