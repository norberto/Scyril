package com.mist.sciryl.model.songs;

import android.support.annotation.DrawableRes;

public class SongInfo {

    private final String author;
    private final String title;
    private final String lyrics;
    private final @DrawableRes Integer imageId;

    public SongInfo(String author, String title, String lyrics) {
        this(author, title, lyrics, null);
    }

    public SongInfo(String author, String title, String lyrics, Integer imageId) {
        this.author = author;
        this.title = title;
        this.lyrics = lyrics;
        this.imageId = imageId;
    }

    public static class Builder {
        private String author;
        private String title;
        private String lyrics;
        private Integer imageId;

        public Builder() {
        }

        public Builder(SongInfo songInfo) {
            author = songInfo.getAuthor();
            title = songInfo.getTitle();
            lyrics = songInfo.getLyrics();
            imageId = songInfo.getImageId();
        }

        public Builder withAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withLyrics(String lyrics) {
            this.lyrics = lyrics;
            return this;
        }

        public Builder withImageId(Integer imageId) {
            this.imageId = imageId;
            return this;
        }

        public SongInfo build() {
            return new SongInfo(author, title, lyrics, imageId);
        }
    }

    public String getAuthor() {
        return author.split("(F|f)eat")[0].trim();
    }

    public String getTitle() {
        return title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public Integer getImageId() {
        return imageId;
    }

    public boolean hasImageId() {
        return imageId != null && imageId > 0;
    }

    public boolean equals(SongInfo s) {
        return s != null && (super.equals(s) || (
                author.equals(s.author) &&
                title.equals(s.title) &&
                imageId == s.imageId));
    }

    public boolean testQuery(String query) {
        return (getFullSongName() + " " + lyrics).contains(query);
    }

    public String getFullSongName() {
        return getAuthor() + " - " + title;
    }
}
