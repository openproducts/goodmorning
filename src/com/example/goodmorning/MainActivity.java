package com.example.goodmorning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {
    private Button startRadio;
    private Button startVideo;
    private Button startMusic;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
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
			Intent intent = new Intent(this, RadioStream.class);
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
		
	}

}
