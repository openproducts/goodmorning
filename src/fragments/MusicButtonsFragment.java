package fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import classes.SingleMediaPlayer;

import com.example.goodmorning.R;

@SuppressLint("NewApi")
public class MusicButtonsFragment extends Fragment implements OnCompletionListener, OnClickListener, SeekBar.OnSeekBarChangeListener{
	private String path;
	private SingleMediaPlayer mp;
    private Button buttonPlay;
    private Button buttonLeftShift;
    private Button buttonRightShift;
    private SeekBar songProgressBar;
	
	public void setPath(String path) {
		this.path = path;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.music_buttons_fragment, null);

		buttonPlay = (Button) v.findViewById(R.id.buttonPlay);
		buttonPlay.setOnClickListener(this);
        
		buttonLeftShift = (Button) v.findViewById(R.id.buttonLeftShift);
		buttonLeftShift.setOnClickListener(this);
		
		buttonRightShift = (Button) v.findViewById(R.id.buttonRightShift);
		buttonRightShift.setOnClickListener(this);
		
	    songProgressBar = (SeekBar) v.findViewById(R.id.songProgressBar);
		
	    mp = new SingleMediaPlayer().getInstance();
	    songProgressBar.setOnSeekBarChangeListener(this); // Important
        mp.setOnCompletionListener(this); // Imp
        
        playSong(path);
        
		return v;
	}
	
	public void playSong(String path){
        // Play song
        try {
            mp.reset();
            mp.setDataSource(path);
            mp.prepare();
            mp.start();
            // Displaying Song title
          //  String songTitle = songsList.get(songIndex).get("songTitle");
            //songTitleLabel.setText(songTitle);
 
            // Changing Button Image to pause image
            //btnPlay.setImageResource(R.drawable.btn_pause);
 
            // set Progress bar values
            songProgressBar.setProgress(0);
            songProgressBar.setMax(100);
 
            // Updating progress bar
            //updateProgressBar();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPlay: {

			break;
		}
		case R.id.buttonLeftShift: {

			break;
		}
		case R.id.buttonRightShift: {

			break;
		}

		}

	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}
}
