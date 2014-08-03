package com.example.goodmorning;


import java.io.IOException;

import models.news.NewsLocation;
import adapters.db.AssetDatabaseOpenHelper;
import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import classes.AsyncTaskCompleteListener;
import classes.AsynchDownloader;
import classes.JsonStringRealization;

public class MainActivity extends Activity implements OnClickListener, AsyncTaskCompleteListener<JsonStringRealization> {
    private Button startRadio;
    private Button startVideo;
    private Button startMusic;
    private Button addAction;
    private AsynchDownloader<NewsLocation, JsonStringRealization> downloader;
	
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
			//String query = "http://ajax.googleapis.com/ajax/services/feed/load?v=1.0&num=8&q=http%3A%2F%2Fnews.google.ru%2Fnews%3Foutput%3Drss";
			//downloader = new AsynchDownloader<NewsLocation, JsonStringRealization>(this, query, NewsLocation.class);
			//downloader.execute();
			
			AssetDatabaseOpenHelper myDbHelper;// = new AssetDatabaseOpenHelper();
	        myDbHelper = new AssetDatabaseOpenHelper(this);
	 
	        try {
	 
	        	myDbHelper.createDataBase();
	 
	 	} catch (IOException ioe) {
	 
	 		throw new Error("Unable to create database");
	 
	 	}
	 
	 	try {
	 		myDbHelper.openDataBase();
	 		myDbHelper.getAllCities();
	 		myDbHelper.getCodeByCityName("Delhi");
	 
	 	}catch(SQLException sqle){
	 
	 		throw sqle;
	 
	 	}
		}
		
	}

	@Override
	public void onTaskComplete(JsonStringRealization result) {
		NewsLocation a  =  (NewsLocation) downloader.getJsonStringRealization();
		a.getResponseDetails();	
	}

}
