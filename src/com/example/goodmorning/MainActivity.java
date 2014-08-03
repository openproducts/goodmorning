package com.example.goodmorning;


import models.news.NewsLocation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import classes.JsonFastTransformer;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

public class MainActivity extends Activity implements OnClickListener {
    private Button startRadio;
    private Button startVideo;
    private Button startMusic;
    private Button addAction;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		addAction = (Button)findViewById(R.id.addActions);
		addAction.setOnClickListener(this);
		
		startMusic = (Button)findViewById(R.id.buttonMusic);
		startMusic.setOnClickListener(this);
		
		startRadio = (Button)findViewById(R.id.buttonRadio);
		startRadio.setOnClickListener(this);
		
		startVideo = (Button)findViewById(R.id.buttonVideo);
		startVideo.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonRadio) {
			Intent intent = new Intent(this, SelectMusicPlaylistActivity.class);
			startActivity(intent);
		}
		
		if(v.getId() == R.id.buttonVideo) {
			Intent intent = new Intent(this, VideoStream.class);
			startActivity(intent);
		}
		
		if(v.getId() == R.id.buttonMusic) {
			Intent intent = new Intent(this, Music.class);
			startActivity(intent);
		}
		
		if(v.getId() == R.id.addActions) {
			String query = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=8&q=http%3A%2F%2Fnews.google.ru%2Fnews%3Foutput%3Drss";
			AQuery aq = new AQuery(this);
			aq.ajax(query, String.class, new AjaxCallback<String>() {
			        @Override
			        public void callback(String url, String html, AjaxStatus status) {
			        	Log.d("INFO", html);
			        	try {
			        	NewsLocation data = JsonFastTransformer.getObjects(html, NewsLocation.class);
			        	Log.d("fdf", data.getResponseDetails());
			        	data.getResponseData().getFeed().getEntries().get(2).getLink();
			        	}
			        	catch(Exception e) {
			        		Log.d("ErrorMY", e.toString());
			        	}
			        	
			        }
			      });
		  
			//Intent intent = new Intent(this, Music.class);
			//startActivity(intent);
		}
		
	}

}
