package classes;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefHolder {
	private final String LOCATION = "currentLocation";
	private SharedPreferences sPref;
	private Editor editor;
	
	public SharedPrefHolder () {
		sPref = getPreferences(Context.MODE_PRIVATE);
		editor = sPref.edit();
	}

	public SharedPreferences getPreferences(int mode) {
		return getPreferences(mode);
	}
	
	public void saveCurrentLocation(final String location) {
		editor.putString(LOCATION, location);
		editor.commit();
	}
	
	public final String getCurrentLocation() {
		return sPref.getString(LOCATION, "");
	}
	
}
