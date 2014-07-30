package com.example.goodmorning;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class VideoStream extends Activity implements OnClickListener {
    private Button startRadio;
	private final String proVersion = "com.mxtech.videoplayer.pro";
	private final String adVersion = "com.mxtech.videoplayer.ad";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_stream);
		
		
		startRadio = (Button)findViewById(R.id.buttonVideo);
		startRadio.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.buttonVideo) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			Uri videoUri = Uri.parse("http://195.189.238.39/streaming/ntv/16/tvrec/playlist.m3u8");
			intent.setDataAndType( videoUri, "application/x-mpegURL" );
			intent.setPackage(adVersion );
			startActivity( intent );
		}
		
	}

}

