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
	
	public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";
 
        // Convert total duration into time
           int hours = (int)( milliseconds / (1000*60*60));
           int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
           int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
           // Add hours if there
           if(hours > 0){
               finalTimerString = hours + ":";
           }
 
           // Prepending 0 to seconds if it is one digit
           if(seconds < 10){
               secondsString = "0" + seconds;
           }else{
               secondsString = "" + seconds;}
 
           finalTimerString = finalTimerString + minutes + ":" + secondsString;
 
        // return timer string
        return finalTimerString;
    }
 
    public int getProgressPercentage(long currentDuration, long totalDuration){
        Double percentage = (double) 0;
 
        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);
 
        // calculating percentage
        percentage =(((double)currentSeconds)/totalSeconds)*100;
 
        // return percentage
        return percentage.intValue();
    }
 
    public int progressToTimer(int progress, int totalDuration) {
        int currentDuration = 0;
        totalDuration = (int) (totalDuration / 1000);
        currentDuration = (int) ((((double)progress) / 100) * totalDuration);
 
        // return current duration in milliseconds
        return currentDuration * 1000;
    }
}

