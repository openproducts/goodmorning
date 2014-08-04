package adapters.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import classes.CityNameCode;
import classes.StaticInformation;

public class AssetDatabaseOpenHelper extends SQLiteOpenHelper {
	// The Android's default system path of your application database.
	private static final String DB_PATH = "/data/data/com.example.goodmorning/databases/";
	private static final String DB_NAME = StaticInformation.weatherDbName;

	private static final String KEY_NAME = "Name";
	private static final String KEY_LAT = "lat";
	private static final String KEY_LON = "lon";
	private static final String KEY_CODE = "countryCode";

	private SQLiteDatabase dataBaseName;
	private final Context context;

	public AssetDatabaseOpenHelper(Context context) {
		super(context, DB_NAME, null, 1);
		this.context = context;
		try {		 
        	this.createDataBase();
 
 	    } catch (IOException ioe) {
 		throw new Error("Unable to create database");
      	}
 
 	    try {
 		this.openDataBase();
    	}catch(SQLException sqle){
 
 		throw sqle;
 	    }
	}

	// Creates a empty database on the system and rewrites it with your own
	// database.
	public void createDataBase() throws IOException {
		boolean dbExist = checkDataBase();
		if (dbExist) {
			// do nothing - database already exist
		} else {
			this.getReadableDatabase();
			try {
				copyDataBase();

			} catch (IOException e) {
				throw new Error("Error copying database");
			}
		}
	}

	// Check if the database already exist to avoid re-copying the file each
	// time you open the application.
	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READONLY);

		} catch (SQLiteException e) {
			// database does't exist yet.
			e.printStackTrace();
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}

	// Copies your database from your local assets-folder to the just created
	// empty database in the system folder, from where it can be accessed and
	// handled. This is done by transfering bytestream.

	private void copyDataBase() throws IOException {
		// Open your local db as the input stream
		InputStream myInput = context.getAssets().open(DB_NAME);
		// Path to the just created empty db
		String outFileName = DB_PATH + DB_NAME;
		// Open the empty db as the output stream
		OutputStream myOutput = new FileOutputStream(outFileName);
		// transfer bytes from the inputfile to the outputfile
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		// Close the streams
		myOutput.flush();
		myOutput.close();
		myInput.close();

	}

	public void openDataBase() throws SQLException {
		// Open the database
		final String myPath = DB_PATH + DB_NAME;
		dataBaseName = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized void close() {
		if (dataBaseName != null)
			dataBaseName.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
	
	public List<CityNameCode> getCitiesWithCodes(String cityName) {
		List<CityNameCode> cs = new ArrayList<CityNameCode>();
		Cursor c = dataBaseName.rawQuery("SELECT " + KEY_NAME + " , "
				+ KEY_CODE + " FROM "
				+ DB_NAME + " WHERE " + KEY_NAME + " = ?",
				new String[] { cityName });
		if (c != null) {
			while (c.moveToNext()) {
				cs.add(new CityNameCode(c.getString(0), c.getString(1)));
			}
		}
		return cs;
	}

	public List<CityNameCode> getCitiesWithCodes() {
		List<CityNameCode> cs = new ArrayList<CityNameCode>();
		Cursor c = dataBaseName.rawQuery("SELECT " + KEY_NAME + " , "
				+ KEY_CODE + " FROM " + DB_NAME + ";", null);
		if (c != null) {
			while (c.moveToNext()) {
				cs.add(new CityNameCode(c.getString(0), c.getString(1)));
			}
		}
		return cs;
	}

	public List<String> getAllCities() {
		Cursor c = dataBaseName.rawQuery("SELECT DISTINCT " + KEY_NAME
				+ " FROM " + DB_NAME + ";", null);
		List<String> cities = new ArrayList<String>();
		if (c != null) {
			while (c.moveToNext()) {
				cities.add(c.getString(0));
			}
		}
		return cities;
	}
	
	public List<String> getAllCitiesWithCodes() {
		Cursor c = dataBaseName.rawQuery("SELECT DISTINCT " + KEY_NAME + " , " + KEY_CODE
				+ " FROM " + DB_NAME + ";", null);
		List<String> cities = new ArrayList<String>();
		if (c != null) {
			while (c.moveToNext()) {
				cities.add(c.getString(0) + " " + c.getString(1));
			}
		}
		return cities;
	}

	public List<String> getCodeByCityName(String nameCity) {
		List<String> codes = new ArrayList<String>();
		Cursor c = dataBaseName.rawQuery("SELECT " + KEY_CODE + " FROM "
				+ DB_NAME + " WHERE " + KEY_NAME + " = ?",
				new String[] { nameCity });
		if (c != null) {
			while (c.moveToNext()) {
				codes.add(c.getString(0));
			}
		}
		return codes;
	}
	
	public boolean cityExist(String nameCity) {
		Cursor c = dataBaseName.rawQuery("SELECT " + KEY_CODE + " FROM "
				+ DB_NAME + " WHERE " + KEY_NAME + " = ?",
				new String[] { nameCity });
		if (c.getCount() > 0) return true;
		return false;
	}

}