/**
 * This is the Home UI screen which ask for city name.
 * Creates an Intent and starts ClimateActivity on that Intent
 * 
 * @author Vallabh
 * */
package org.climateapp.ui;

import org.climateapp.Constant.Constants;
import org.climateapp.ui.listeners.ClickListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.climateapp.R;

public class MainActivity extends Activity {
	private OnClickListener onClickListener = new ClickListener(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		addListener();
	}

	/**
	 * Adds listeners to controls
	 **/
	private void addListener() {
		((Button) findViewById(R.id.okButton)).setOnClickListener(onClickListener);
		((Button) findViewById(R.id.exitButton)).setOnClickListener(onClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * Overriding this method so when we say done on Climate Activity screen,
	 * we have to make the city name text view empty on Main Activity
	 **/
	@Override
	protected void onResume() {
		super.onResume();
		((EditText) findViewById(R.id.cityText)).setText(Constants.EMPTY_STRING);
	}
}
