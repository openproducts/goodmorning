package fragments;


import android.support.v4.app.Fragment;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import classes.SingleMediaPlayer;
import classes.Utilities;
import com.example.goodmorning.R;

public class MusicButtonsFragment extends Fragment implements
		OnCompletionListener, OnClickListener, SeekBar.OnSeekBarChangeListener {
	private String path;
	private SingleMediaPlayer mp;
	private Handler mHandler = new Handler();
	private Button buttonPlay;
	private SeekBar songProgressBar;
	private TextView songCurrentDurationLabel;
	private TextView songTotalDurationLabel;
	private Utilities utils;

	public void setPath(String path) {
		this.path = path;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.music_buttons_fragment, null);

		buttonPlay = (Button) v.findViewById(R.id.buttonPlay);
		buttonPlay.setOnClickListener(this);

		songProgressBar = (SeekBar) v.findViewById(R.id.songProgressBar);

		songCurrentDurationLabel = (TextView) v
				.findViewById(R.id.songCurrentDurationLabel);
		songTotalDurationLabel = (TextView) v
				.findViewById(R.id.songTotalDurationLabel);

		mp = new SingleMediaPlayer().getInstance();
		songProgressBar.setOnSeekBarChangeListener(this); // Important
		mp.setOnCompletionListener(this);
		utils = new Utilities();

		playSong(path);

		return v;
	}

	public void playSong(String path) {
		// Play song
		try {
			mp.reset();
			mp.setDataSource(path);
			mp.prepare();
			mp.start();
			// Displaying Song title
			// String songTitle = songsList.get(songIndex).get("songTitle");
			// songTitleLabel.setText(songTitle);

			// Changing Button Image to pause image
			buttonPlay.setText("Play");

			// set Progress bar values
			songProgressBar.setProgress(0);
			songProgressBar.setMax(100);

			// Updating progress bar
			updateProgressBar();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateProgressBar() {
		mHandler.postDelayed(mUpdateTimeTask, 100);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonPlay: {
			if (mp.isPlaying()) {
				if (mp != null) {
					mp.pause();
					// Changing button image to play button
					buttonPlay.setText("Play");
				}
			} else {
				// Resume song
				if (mp != null) {
					mp.start();
					// Changing button image to pause button
					buttonPlay.setText("Stop");
				}
			}
			break;
		}

		}

	}

	private Runnable mUpdateTimeTask = new Runnable() {
		public void run() {
			long totalDuration = mp.getDuration();
			long currentDuration = mp.getCurrentPosition();

			// Displaying Total Duration time
			songTotalDurationLabel.setText(""
					+ utils.milliSecondsToTimer(totalDuration));
			// Displaying time completed playing
			songCurrentDurationLabel.setText(""
					+ utils.milliSecondsToTimer(currentDuration));

			// Updating progress bar
			int progress = (int) (utils.getProgressPercentage(currentDuration,
					totalDuration));
			// Log.d("Progress", ""+progress);
			songProgressBar.setProgress(progress);

			// Running this thread after 100 milliseconds
			mHandler.postDelayed(this, 100);
		}
	};

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		mHandler.removeCallbacks(mUpdateTimeTask);
		int totalDuration = mp.getDuration();
		int currentPosition = utils.progressToTimer(seekBar.getProgress(),
				totalDuration);

		// forward or backward to certain seconds
		mp.seekTo(currentPosition);

		// update timer progress again
		updateProgressBar();

	}

	@Override
	public void onCompletion(MediaPlayer mp) {
		playSong(path);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mp.stop();
		// mp.release();
	}
}
