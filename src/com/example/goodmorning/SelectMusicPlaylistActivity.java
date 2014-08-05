package com.example.goodmorning;

import java.util.ArrayList;
import java.util.List;
import adapters.SongAdapter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import classes.SongInfo;
import classes.SongsInspector;

public class SelectMusicPlaylistActivity extends FragmentActivity implements
		OnClickListener {
	private Button selectItems;
	private ListView items;
	private SongAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_music_playlist);

		selectItems = (Button) findViewById(R.id.buttonCheckedItemsPlaylist);
		selectItems.setOnClickListener(this);

		items = (ListView) findViewById(R.id.listItemsPlaylist);
		items.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		List<SongInfo> listSongs = new SongsInspector(this).getMusic();
		if (listSongs != null) {
			adapter = new SongAdapter(this, listSongs,
					getSupportFragmentManager());
			items.setAdapter(adapter);
		} else
			finish();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonCheckedItemsPlaylist) {
			ArrayList<SongInfo> songs = (ArrayList<SongInfo>) adapter
					.getCheckedItems();
			finish();
		}
	}

}
