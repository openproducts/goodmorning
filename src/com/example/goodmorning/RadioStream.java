package com.example.goodmorning;

import java.io.IOException;

import classes.ParserURL;
import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RadioStream extends Activity implements OnInfoListener,OnPreparedListener,OnErrorListener , OnCompletionListener{

    @SuppressWarnings("unused")
    private Button button_play;
    @SuppressWarnings("unused")
    private Button button_quit;
    private ProgressBar progress_main;
    private TextView text_info;
    MediaPlayer mediaPlayer;
    private Activity rootActivity;
    private boolean isPlaying = false;
    @SuppressWarnings("unused")
    private DigitalClock digitalClock;

    String STREAM_URI;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        Log.i("WakeUpRadio","Radio Starts");
        // get are self
        rootActivity = this;    
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stream);

        // get the UI elemntns
        button_play = (Button) findViewById(R.id.start);
        button_quit = (Button) findViewById(R.id.stop);
        progress_main = (ProgressBar) findViewById(R.id.progressBar);
        text_info = (TextView) findViewById(R.id.textInfo);
        digitalClock = (DigitalClock)findViewById(R.id.digitalClock);

    }

    //Bouton PLay
    public void Start(View v) {
       Runnable runn = new Runnable() {
		
		@Override
		public void run() {
			 STREAM_URI = ParserURL.parse("http://listen.42fm.ru:8000/stealkill.m3u");

		        if (mediaPlayer == null) {
		            mediaPlayer = new MediaPlayer();
		            mediaPlayer.setOnCompletionListener((OnCompletionListener) rootActivity);
		            mediaPlayer.setOnErrorListener((OnErrorListener) rootActivity);
		            mediaPlayer.setOnInfoListener((OnInfoListener) rootActivity);
		            mediaPlayer.setOnPreparedListener((OnPreparedListener) rootActivity);
		            try {
		                mediaPlayer.setDataSource(STREAM_URI);
		            } catch (IllegalArgumentException e) {
		                onError(mediaPlayer, -1, -1);
		                return;
		            } catch (IllegalStateException e) {
		                onError(mediaPlayer, -1, -1);
		                return;
		            } catch (IOException e) {
		                onError(mediaPlayer, -1, -1);
		                return;
		            }   

		            try {
		              //  progress_main.setVisibility(View.VISIBLE);
		                mediaPlayer.prepareAsync();
		              //  text_info.setText("Loading ...");
		                isPlaying = true;
		            } catch (IllegalStateException e) {
		                onError(mediaPlayer, -1, -1);
		            }
		        }
			
		}
	};
       Thread thr = new Thread(runn);
       thr.start();

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        Log.v("FreshAir", "onError (" + what + ")");

        if(mp != null){
            mp.release();
            mp = null;
        }
        isPlaying = false;

        text_info.setText("Error: Please Retry");

        progress_main.setVisibility(View.INVISIBLE);

        return true; 
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        try {
            mediaPlayer.start();
        } catch (IllegalStateException e) {
            onError(mp, -1, -1);
        }
        text_info.setText("Playing ...");
        progress_main.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        // TODO: currently the getMetadata method is not present

        Log.v("FreshAir", "onInfo (" + what + " - " + extra + ")");

        switch (what) {
        case 701: //MediaPlayer.MEDIA_INFO_BUFFERING_START:
            text_info.setText("Buffering ...");
            progress_main.setVisibility(View.VISIBLE);
            isPlaying = true; // ie you cant re set the data sourse without
            // stoping first
            break;

        case 702: //MediaPlayer.MEDIA_INFO_BUFFERING_END:
            text_info.setText("Playing ...");
            progress_main.setVisibility(View.INVISIBLE);
            isPlaying = true;

        default:
            break;
        }

        return true; // return true to stop the OS calling 'onError'
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        if(mp == null)
            return;
        mp.release();
        mp = null;
        isPlaying = false;
        progress_main.setVisibility(View.INVISIBLE);
    }

    // Stop button
    public void Stop(View v){
        if(isPlaying || (mediaPlayer != null && mediaPlayer.isPlaying())){
            if(mediaPlayer.isPlaying()){
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;

            text_info.setText("");
            isPlaying = false;
            progress_main.setVisibility(View.INVISIBLE);
        }
    }

}