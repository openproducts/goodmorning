package com.example.goodmorning;

import java.util.List;
import adapters.db.AssetDatabaseOpenHelper;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class WeatherActivity extends Activity implements OnClickListener {
	private Button save;
	private AutoCompleteTextView actv;
	private AssetDatabaseOpenHelper dbHelper;
	private List<String> list;
	private final int MIN_LENGTH_WORD_FOR_SEATCH = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather);

		save = (Button) findViewById(R.id.saveWeatherInformation);
		save.setOnClickListener(this);

		dbHelper = new AssetDatabaseOpenHelper(this);

		actv = (AutoCompleteTextView) findViewById(R.id.autoCompleteCounry);
		actv.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				final String text = actv.getText().toString();
				if (text.length() >= MIN_LENGTH_WORD_FOR_SEATCH) {
					list = dbHelper.getAllCitiesWithCodes(text);
					ArrayAdapter<String> adapter = new ArrayAdapter<String>(
							WeatherActivity.this,
							android.R.layout.simple_list_item_1, list);
					actv.setAdapter(adapter);
				}
			}

			@Override
			public void afterTextChanged(Editable s) {}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {};
		});
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.saveWeatherInformation) {
			final String nameCity = actv.getText().toString().split(" ")[0];
			if (nameCity.equals("") || !dbHelper.cityExist(nameCity)) {
				Toast toast = Toast.makeText(getApplicationContext(), "NONON!",
						Toast.LENGTH_SHORT);
				toast.show();
			} else {
				Toast toast = Toast.makeText(getApplicationContext(),
						"YEYEYS!", Toast.LENGTH_SHORT);
				toast.show();
			}
		}
	}

}
