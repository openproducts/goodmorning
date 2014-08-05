package classes;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class SongsInspector {
    private Context context;
    private ArrayList<SongInfo> songs;
    
	public SongsInspector(Context context) {
		this.context = context;
		songs = new ArrayList<SongInfo>();
	}
	
	
	public ArrayList<SongInfo> getMusic() {
		
		final String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0";

		final String[] projection = { MediaStore.Audio.Media._ID,
				MediaStore.Audio.Media.ARTIST, MediaStore.Audio.Media.TITLE,
				MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.DISPLAY_NAME,
				MediaStore.Audio.Media.DURATION };

		Cursor cursor = context.getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection,
				selection, null, null);

		while (cursor.moveToNext()) {
			songs.add(new SongInfo(cursor.getString(4), cursor.getString(1),
					cursor.getString(3)));
		}
		return songs;
	}
}

