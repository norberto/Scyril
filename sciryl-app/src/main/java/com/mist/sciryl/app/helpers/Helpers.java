package com.mist.sciryl.app.helpers;

import com.google.common.collect.Lists;
import com.mist.sciryl.model.songs.SongInfo;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Helpers {

    public static final String LOG_TAG = "ScirylLogTag";
    public static final String APP_NAME = "Sciryl";

    public static final boolean USE_PICASSO = true;


    public static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        List<Character> resetters = Lists.newArrayList('.', '(', ')');
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || resetters.contains(chars[i])) {
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    public static List<SongInfo> sortSongList(List<SongInfo> songInfo) {
        Collections.sort(songInfo, new Comparator<SongInfo>() {
            @Override
            public int compare(SongInfo first, SongInfo second) {
                int comp = 0;

                comp = first.getAuthor().compareTo(second.getAuthor());
                if (comp != 0) {
                    return comp;
                }

                return first.getTitle().compareTo(second.getTitle());
            }
        });
        return songInfo;
    }

    public static <T> T safeGet(Collection<T> collection, int index) {
        if (collection.size() > index) {
            return (T) collection.toArray()[index];
        }
        return null;
    }
}
