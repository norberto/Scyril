package com.mist.sciryl.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;

public class XMLFragment extends Fragment {

    @Override
    public void onInflate(Context context, AttributeSet attrs,
                          Bundle savedInstanceState) {
        removeThisFromFragmentManager();
        super.onInflate(context, attrs, savedInstanceState);
    }

     @Override
    public void onInflate(Activity activity, AttributeSet attrs,
                          Bundle savedInstanceState) {
        removeThisFromFragmentManager();
        super.onInflate(activity, attrs, savedInstanceState);
    }

    private void removeThisFromFragmentManager() {
        FragmentManager fm = getFragmentManager();
        if (fm != null) {
            fm.beginTransaction().remove(this).commit();
        }
    }

}
