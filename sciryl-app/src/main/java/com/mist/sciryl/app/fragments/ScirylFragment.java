package com.mist.sciryl.app.fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mist.sciryl.app.activities.ScirylActivity;
import com.mist.sciryl.app.helpers.AndroidHelpers;

public abstract class ScirylFragment extends Fragment {

    private int layoutId;
    protected View fragmentContainer;
    protected ScirylActivity scirylActivity;

    public ScirylFragment(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        scirylActivity = ScirylActivity.getCurrentActivity();
    }

    public abstract View onCreateView(Bundle savedInstanceState);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentContainer = AndroidHelpers.smartInflate(inflater, container, container, layoutId, View.class);
        return onCreateView(savedInstanceState);
    }

}
