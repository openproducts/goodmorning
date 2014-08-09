package com.example.goodmorning;

import classes.StaticInformation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelectDayOfWeekActivity extends Activity implements
		OnClickListener {
	private int dayOfWeek = 0;
	private Button saturday, sunday, monday, tuesday, wednesday, thursday,
			friday;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.day_of_week);

	//	initButtons();

		intent = new Intent(this, AddActionActivity.class);
	}

	/*private void initButtons() {
		saturday = (Button) findViewById(R.id.buttonSaturday);
		sunday = (Button) findViewById(R.id.buttonSunday);
		monday = (Button) findViewById(R.id.buttonMonday);
		tuesday = (Button) findViewById(R.id.buttonTuesday);
		wednesday = (Button) findViewById(R.id.buttonWednesday);
		thursday = (Button) findViewById(R.id.buttonThursday);
		friday = (Button) findViewById(R.id.buttonFriday);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.buttonMonday: {
			dayOfWeek = 0
			break;
			}
		case R.id.buttonTuesday:
			dayOfWeek = 1;
			break;
			}
		case R.id.buttonWednesday:
			dayOfWeek = 2;
			break;
			}	 
		case R.id.buttonThursday:
			dayOfWeek = 3
			break;
			}
		case R.id.buttonFriday:
			dayOfWeek = 4;
			break;
			}
		case R.id.buttonSaturday:
			dayOfWeek = 5;
			break;
			}	
		case R.id.buttonSunday:
			dayOfWeek = 6;
			break;
			}	
		}
		intent.putExtra(StaticInformation.dayOfWeek, dayOfWeek);
		startActivity(intent);
	}*/
}