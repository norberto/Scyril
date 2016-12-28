package com.mist.sciryl.app.helpers;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class AndroidHelpers {

    private AndroidHelpers() {}

    public static <T> T findContainerViewById(View parent, @IdRes int id, Class<T> clazz) {
        View view = parent.findViewById(id);
        if (view != null) {
            return clazz.cast(view);
        }
        return null;
    }

    public static <T> T smartInflate(Context context, View view, ViewGroup parent, @LayoutRes int layoutId, Class<T> castTo) {
        LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return smartInflate(inflater, view, parent, layoutId, castTo);
    }

    public static <T> T smartInflate(LayoutInflater inflater, ViewGroup parent, @LayoutRes int layoutId, Class<T> castTo) {
        return smartInflate(inflater, null, parent, layoutId, castTo);
    }

    public static <T> T smartInflate(LayoutInflater inflater, View view, ViewGroup parent, @LayoutRes int layoutId, Class<T> castTo) {
        View result = view;
        if (result == null) {
            result = inflater.inflate(layoutId, parent, false);
        }
        return castTo.cast(result);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
              return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
             View listItem = listAdapter.getView(i, null, listView);
             if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT));
             }
             listItem.measure(0, 0);
             totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
                  listView.setLayoutParams(params);
    }

    public static void hideSoftInput(Activity activity) {
        InputMethodManager imm =  (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
