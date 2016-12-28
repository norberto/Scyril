package com.mist.sciryl.app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mist.sciryl.app.R;
import com.mist.sciryl.app.activities.ActivitiesRegistry;
import com.mist.sciryl.app.helpers.AndroidHelpers;

public class MenuArrayAdapter extends ArrayAdapter<String> {
	private final Context context;

	public MenuArrayAdapter(Context context) {
		super(context, R.layout.fragment_navigation_list_item, ActivitiesRegistry.menuActivitiesNames(context));
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = AndroidHelpers.smartInflate(
                context, convertView, parent,
                R.layout.fragment_navigation_list_item, View.class);

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);

        ActivitiesRegistry actReg = ActivitiesRegistry.getActivitiesRegistry(position);
        textView.setText(actReg.getName());
        imageView.setImageResource(actReg.getIcon());

		return rowView;
	}
}
