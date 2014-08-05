package adapters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import classes.SongInfo;

import com.example.goodmorning.R;

import fragments.MusicButtonsFragment;

@SuppressLint("NewApi")
public class SongAdapter extends ArrayAdapter<SongInfo> {
	private Context context;
	private List<SongInfo> items;
	private FragmentManager fm;
	private HashMap<Integer, Boolean> myChecked;
	private MusicButtonsFragment fr;

	public SongAdapter(Context context, List<SongInfo> items, FragmentManager fm) {
		super(context, R.layout.select_song, items);

		this.context = context;
		this.items = items;
		this.fm = fm;
		myChecked = new HashMap<Integer, Boolean>();

		for (int i = 0; i < items.size(); i++) {
			myChecked.put(i, false);
		}
	}

	public List<Integer> getCheckedItemPositions() {
		List<Integer> checkedItemPositions = new ArrayList<Integer>();

		for (int i = 0; i < myChecked.size(); i++) {
			if (myChecked.get(i)) {
				checkedItemPositions.add(i);
			}
		}

		return checkedItemPositions;
	}

	public List<SongInfo> getCheckedItems() {
		List<SongInfo> checkedItems = new ArrayList<SongInfo>();

		for (int i = 0; i < myChecked.size(); i++) {
			if (myChecked.get(i)) {
				checkedItems.add(items.get(i));
			}
		}

		return checkedItems;
	}

	public void toggleChecked(int position) {
		if (myChecked.get(position)) {
			myChecked.put(position, false);
		} else {
			myChecked.put(position, true);
		}

		notifyDataSetChanged();
	}

	@SuppressLint("NewApi")
	private void addFragment(final SongInfo songInfo) {
		for (int i = 0; i < fm.getBackStackEntryCount(); ++i) {
			fm.popBackStack();
		}
		fr = new MusicButtonsFragment();
		fr.setPath(songInfo.getPath());
		FragmentTransaction ft = fm.beginTransaction();
		ft.replace(R.id.fragment, fr);
		ft.addToBackStack(null);
		ft.commit();
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View rowView = convertView;

		if (rowView == null) {
			LayoutInflater inflater = LayoutInflater.from(context);
			rowView = inflater.inflate(R.layout.select_song, parent, false);
		}

		final SongInfo songInfo = getItem(position);
		if (songInfo != null) {
			TextView checkedTextView = (TextView) rowView
					.findViewById(R.id.checkSong);
			checkedTextView.setText(items.get(position).getTitle());
			checkedTextView.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					addFragment(songInfo);
				}
			});

			CheckBox check = (CheckBox) rowView.findViewById(R.id.checkSongBox);
			check.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					toggleChecked(position);
				}
			});

		}

		return rowView;
	}
}
