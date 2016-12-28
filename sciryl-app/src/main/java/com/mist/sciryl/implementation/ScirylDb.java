package com.mist.sciryl.implementation;

import android.content.Context;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mist.sciryl.app.R;
import com.mist.sciryl.model.songs.SongInfo;

import javax.annotation.Nullable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mist.sciryl.implementation.SongInfoParser.normalizeName;
import static com.mist.sciryl.implementation.SongInfoParser.SEPARATOR_AUTHOR_FEAT;

public class ScirylDb {

    private static final String SONG_INFO_LIST_FILE = "__list";

    private static Map<String, SongInfo> SONGS = new HashMap<>();
    private static Context CONTEXT;

    public ScirylDb(Context context) {
        if (CONTEXT == null) {
            CONTEXT = context;
        }
        loadDb();
    }

    private void loadDb() {
        if (!SONGS.isEmpty()) {
            return;
        }
        try {
            initializeDb();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeDb() throws IOException {
        Field songInfoField = null;
        for (Field field : R.raw.class.getFields()) {
            try {
                if (field.getName().equalsIgnoreCase(SONG_INFO_LIST_FILE)) {
                    songInfoField = field;
                } else {
                    parseField(field);
                }
            } catch (IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        }
        if (songInfoField != null) {
            try {
                handleSongInfoField(songInfoField);
            } catch (IllegalAccessException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void parseField(Field field) throws IllegalAccessException, IOException {
        String fieldName = field.getName();
        String lyrics = readFromField(field);
        SongInfo songInfo = createSongInfo(fieldName, lyrics);
        if (songInfo != null) {
            SONGS.put(fieldName, songInfo);
        }
    }

    private String readFromField(Field field) throws IllegalAccessException, IOException {
        int fieldId = field.getInt(R.raw.class);
        InputStream stream = CONTEXT.getResources().openRawResource(fieldId);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

    private SongInfo createSongInfo(String fieldName, String lyrics) {
        fieldName = normalizeName(fieldName);
        String[] lyricsData = fieldName.split("-");
        if (lyricsData.length < 2) {
            return null;
        }

        String author = lyricsData[0].split(SEPARATOR_AUTHOR_FEAT)[0].trim();
        String title = lyricsData[1].trim();

        return new SongInfo(author, title, lyrics, null);
    }

    public List<SongInfo> getAllSongs() {
        return new ArrayList<>(SONGS.values());
    }

    private void handleSongInfoField(Field field) throws IllegalAccessException, IOException {
        String contents = readFromField(field);

        for (String line : contents.split("\n")) {
            line = line.replaceAll("(( |\t)( |\t)+)", " ");
            String[] data = line.split(" ");
            String fieldName = data[0];
            String drawableName = data.length > 1 ? data[1] : "";
            if (!drawableName.isEmpty() && SONGS.containsKey(fieldName)) {
                enrichSongInfo(fieldName, drawableName);
            }
        }
    }

    private void enrichSongInfo(String fieldName, String drawableName) {
        SongInfo songInfo = SONGS.get(fieldName);

        int drawableId = getDrawableIdFromString(drawableName);
        if (drawableId != -1) {
            songInfo = new SongInfo.Builder(songInfo)
                                    .withImageId(drawableId)
                                    .build();
        }

        SONGS.put(fieldName, songInfo);
    }

    private int getDrawableIdFromString(final String drawableName) {
        Optional<Field> field = Iterables.tryFind(Lists.newArrayList(R.drawable.class.getFields()), new Predicate<Field>() {
            @Override
            public boolean apply(@Nullable Field input) {
                if (input != null) {
                    String name = input.getName();
                    return name != null && name.equalsIgnoreCase(drawableName);
                }
                return false;
            }
        });
        if (field.isPresent()) {
            try {
                return field.get().getInt(R.drawable.class);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
