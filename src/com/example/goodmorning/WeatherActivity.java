package com.example.goodmorning;

import java.util.List;

import adapters.db.AssetDatabaseOpenHelper;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import classes.DownloaderProgressDialog;

public class WeatherActivity extends Activity implements OnClickListener {
	private Button save;
	private AutoCompleteTextView actv;
	private AssetDatabaseOpenHelper dbHelper;
	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);

		save = (Button) findViewById(R.id.saveWeatherInformation);
		save.setOnClickListener(this);

		dbHelper = new AssetDatabaseOpenHelper(this);

		actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteCounry);

		new Task().execute();
	}

	private class Task extends AsyncTask<Void, Void, Void> {
		private DownloaderProgressDialog dialog;

		public Task() {
			dialog = new DownloaderProgressDialog(WeatherActivity.this);
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			dialog.dismiss();
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(
					WeatherActivity.this, android.R.layout.simple_list_item_1,
					WeatherActivity.this.list);
			WeatherActivity.this.actv.setAdapter(adapter);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			this.dialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			WeatherActivity.this.list = WeatherActivity.this.dbHelper
					.getAllCitiesWithCodes();
			return null;
		}

	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.saveWeatherInformation) { 
			final String nameCity = actv.getText().toString().split(" ")[0];
			if(nameCity.equals("") || !dbHelper.cityExist(nameCity)) {
				Toast toast = Toast.makeText(getApplicationContext(), 
						   "NONON!", Toast.LENGTH_SHORT); 
				toast.show(); 
			}
			else {
				Toast toast = Toast.makeText(getApplicationContext(), 
						   "YEYEYS!", Toast.LENGTH_SHORT); 
				toast.show(); 
			}
		}
	}

}
