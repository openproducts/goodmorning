package classes;

import android.media.MediaPlayer;

public class SingleMediaPlayer extends MediaPlayer {

	public static class SingletonHolder {
		public static final SingleMediaPlayer HOLDER_INSTANCE = new SingleMediaPlayer();
	}

	public static SingleMediaPlayer getInstance() {
		return SingletonHolder.HOLDER_INSTANCE;
	}
}