package adapters.db;

import java.util.ArrayList;
import java.util.List;

import models.actions.BaseRadioVideoAction;
import models.actions.RadioAction;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ActionsDatabaseOpenHelper extends SQLiteOpenHelper {
	//Static information about DB
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "actionsManager";
	private static final String TABLE_ACTIONS = "actions";
    //Dynamic information about DB
	private final String KEY_ID = "_id";
	private final String NAME = "name";
	private final String PICTURE = "picture";
	private final String BEGIN_TIME = "begin_time";
	private final String END_TIME = "end_time";
	private final String DESCRIPTION = "description";
	private final String DAY_OF_WEAK = "day_weak";
	private final String VOLUME = "volume";
	private final String COUNTRY = "country";
	private final String CITY = "city";
	private final String TEMPERATURE = "temp";
	private final String PRESSURE = "pressure";
	private final String WIND = "wind";
	private final String HUMIDITY = "humidity";
	private final String WEATHER_TYPE = "weather_type";
	private final String URL = "url";
	private final String PLAYLIST = "playlist";
	private final String PATH = "path";
	
	
	public ActionsDatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String create_table = "CREATE TABLE " + TABLE_ACTIONS + " ( " 
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ PICTURE + " BLOB," 
				+ BEGIN_TIME + " TEXT," 
				+ END_TIME + " TEXT,"
				+ NAME + " TEXT,"
				+ DESCRIPTION + " TEXT,"
				+ DAY_OF_WEAK + " TEXT,"
				+ VOLUME + " INTEGER,"
				+ COUNTRY + " TEXT,"
				+ CITY + " TEXT,"
				+ TEMPERATURE + " INTEGER,"
				+ PRESSURE + " INTEGER,"
				+ WIND + " INTEGER,"
				+ HUMIDITY + " INTEGER,"
				+ WEATHER_TYPE + " INTEGER,"
				+ URL + " TEXT,"
				+ PLAYLIST + " INTEGER,"
				+ PATH + " TEXT"
				+ ")";
		 db.execSQL(create_table);
	}

	 @Override
	 public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // Drop older table if existed
	        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACTIONS);
	        onCreate(db);
	    }
	    
	 public void deleteTable() {
	    	SQLiteDatabase db = this.getWritableDatabase();
	    	db.delete(TABLE_ACTIONS, null, null);
	    }
	
	 
	 // BaseRadioVideoAction(int volume, String description,
	//	String beginTime, String endTime, String url, String name) {
	public void addRadioStation(BaseRadioVideoAction radio) {
		ContentValues values = new ContentValues();
		values.put(VOLUME , radio.getVolume());
		values.put(DESCRIPTION , radio.getDescription());
		values.put(BEGIN_TIME, radio.getBeginTime());
		values.put(END_TIME, radio.getEndTime());
		values.put(URL, radio.getUrl());
		values.put(NAME, radio.getName());
		
		insertInDB(values);
	}
	
	public void addVideoChannal(BaseRadioVideoAction video) {
		ContentValues values = new ContentValues();
		values.put(VOLUME , video.getVolume());
		values.put(DESCRIPTION , video.getDescription());
		values.put(BEGIN_TIME, video.getBeginTime());
		values.put(END_TIME, video.getEndTime());
		values.put(URL, video.getUrl());
		values.put(NAME, video.getName());
		
		insertInDB(values);
	}
	
	
	public void getAll() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery("SELECT " + KEY_ID + " , "
					+ BEGIN_TIME + " FROM " + TABLE_ACTIONS + ";", null);
		if (c != null) {
			while (c.moveToNext()) {
				System.out.println(c.getString(0) + c.getString(1));
			}
		}
	}
	
	public List<RadioAction> getAllRadioStations() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<RadioAction> stations = new ArrayList<RadioAction>();
		//Cursor c = db.rawQuery("SELECT " + KEY_NAME + " , "
			//	+ KEY_CODE + " FROM " + DB_NAME + ";", null);
//		if (c != null) {
//			while (c.moveToNext()) {
//				cs.add(new CityNameCode(c.getString(0), c.getString(1)));
//			}
//		}
		return stations;
	}
	
	
	private void insertInDB(ContentValues values) {
		SQLiteDatabase db = this.getWritableDatabase(); 
		db.insert(TABLE_ACTIONS, null, values);
		// Closing database connection
		db.close(); 
	}

}
