package com.example.goodmorning;

import java.util.ArrayList;

import adapters.SongAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import classes.SongInfo;
import classes.SongsInspector;

public class SelectDayOfWeekActivity extends Activity implements OnClickListener {
    private Button selectItems;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_music_playlist);
		
		selectItems = (Button)findViewById(R.id.buttonCheckedItemsPlaylist);
		selectItems.setOnClickListener(this);
			
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonCheckedItemsPlaylist) {
		}
	}
}