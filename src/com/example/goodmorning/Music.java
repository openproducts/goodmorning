package com.example.goodmorning;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import classes.SongInfo;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Music extends Activity implements OnClickListener {
	private MediaPlayer mMediaPlayer;
	ArrayList<SongInfo> songs = new ArrayList<SongInfo>();
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music);

		mMediaPlayer = new MediaPlayer();

		ListView mListView = (ListView) findViewById(R.id.listView1);

		final List<String> mMusicList = new ArrayList<String>();
		for(SongInfo song: getMusic()) {
			mMusicList.add(song.getTitle());
		}

		ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, mMusicList);
		mListView.setAdapter(mAdapter);

		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				try {
					playSong(songs.get(arg2).getPath());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private ArrayList<SongInfo> getMusic() {
		
		final String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

		final String[] projection = { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.DURATION };

		Cursor cursor = this.managedQuery(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
				selection, null, null);

		while (cursor.moveToNext()) {
			songs.add(new SongInfo(cursor.getString(4), cursor.getString(1),
					cursor.getString(3)));
			// songs2.add(cursor.getString(0) + "||" + cursor.getString(1) +
			// "||" + cursor.getString(2) + "||" + cursor.getString(3) + "||" +
			// cursor.getString(4) + "||" + cursor.getString(5));
		}
		cursor.close();

		return songs;
	}

	private void playSong(String path) throws IllegalArgumentException,
			IllegalStateException, IOException {
		String extStorageDirectory = Environment.getExternalStorageDirectory()
				.toString();

		//path = extStorageDirectory + File.separator + path;

		mMediaPlayer.reset();
		mMediaPlayer.setDataSource(path);
		mMediaPlayer.prepare();
		mMediaPlayer.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonRadio) {
			Intent intent = new Intent(this, RadioStream.class);
			startActivity(intent);
		}

		if (v.getId() == R.id.buttonVideo) {
			Intent intent = new Intent(this, VideoStream.class);
			startActivity(intent);
		}

	}

}
