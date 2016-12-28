package com.mist.sciryl.app.activities;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import com.google.common.collect.Iterables;
import com.mist.sciryl.app.R;

import java.util.ArrayList;
import java.util.List;

public enum ActivitiesRegistry {

    HOME(R.string.section_home, R.drawable.ic_home, MainActivity.class),
    FAVOURITES(R.string.section_favourites, R.drawable.ic_heart, FavouritesActivity.class),
    BROWSE(R.string.section_browse, R.drawable.ic_music, BrowseActivity.class),

    LYRICS(R.string.title_lyrics, -1, LyricsActivity.class),
    SEARCH_RESULTS(R.string.title_search_results, -1, SearchResultsActivity.class)
    ;

    private int name;
    private int icon;
    private Class<? extends ScirylActivity> clazz;

    ActivitiesRegistry(@StringRes int name, @DrawableRes int icon, Class<? extends ScirylActivity> clazz) {
        this.name = name;
        this.icon = icon;
        this.clazz = clazz;
    }

    public static List<String> names(Context context) {
        List<String> list = new ArrayList<>();
        for (ActivitiesRegistry act: values()) {
            list.add(context.getString(act.name));
        }
        return list;
    }

    public static List<String> menuActivitiesNames(Context context) {
        List<String> list = new ArrayList<>();
        for (ActivitiesRegistry act: values()) {
            if (act.getIcon() > 0) {
                list.add(context.getString(act.name));
            }
        }
        return list;
    }

    public static ActivitiesRegistry findFromClass(Class<? extends ScirylActivity> clazz) {
        for (ActivitiesRegistry act: values()) {
            if (act.getClazz().equals(clazz)) {
                return act;
            }
        }
        return null;
    }

    public int getIndex() {
        for (int i = 0; i < values().length; i++) {
            if (values()[i] == this) {
                return i;
            }
        }
        return -1;
    }

    public static ActivitiesRegistry getActivitiesRegistry(int index) {
        return values()[index];
    }

    public int getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public Class<? extends ScirylActivity> getClazz() {
        return clazz;
    }
}
