package com.mist.sciryl.mock;

import com.mist.sciryl.model.songs.SongInfo;

import java.util.ArrayList;
import java.util.List;

public class MockDb {

    private List<SongInfo> songs;

    public MockDb() {
        setup();
    }

    private void setup() {
        songs = new ArrayList<>();
//        songs.add(new Builder().setAuthor("Adele").setTitle("Hello").setLyrics("Hi hi hi, hello, ye, hi, hello").createSongInfo());
//        songs.add(new Builder().setAuthor("Justin Bieber").setTitle("Sorry").setLyrics("I am sorry\nNo, really").createSongInfo());
//        songs.add(new Builder().setAuthor("Justin Bieber").setTitle("Baby").setLyrics("Baby baby baby, ohhh, baby noooo").createSongInfo());
//        songs.add(new Builder().setAuthor("Imagine Dragons").setTitle("Radioactive").setLyrics("I'm Radioactive, bitch.").createSongInfo());
//        songs.add(new Builder().setAuthor("Imagine Dragons").setTitle("Warriors").setLyrics("I'm a warrior, bitch.").createSongInfo());
//        songs.add(new Builder().setAuthor("Green Day").setTitle("Wake Me Up When September Ends").setLyrics("Please no").createSongInfo());
    }

    public List<SongInfo> getSongs() {
        return songs;
    }
}
