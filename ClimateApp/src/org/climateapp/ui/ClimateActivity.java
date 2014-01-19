/**
 * This is the last UI screen which shows climate details.
 * Starts an async task to fetch climate information.
 * 
 * @author Vallabh
 * */
package org.climateapp.ui;

import org.climateapp.Constant.Constants;
import org.climateapp.beans.ClimateInfo;
import org.climateapp.ui.listeners.ClickListener;
import org.climateapp.worker.RetrieveClimateInformation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.climateapp.R;

public class ClimateActivity extends Activity {
	private String cityName;
	private ClimateInfo climateInfo;
	private OnClickListener onClickListener = new ClickListener(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_climate);
		addListeners();

		Intent intent = getIntent();
		cityName = intent.getStringExtra("City");
		getClimateInformation();
	}

	/**
	 * This method add different listeners to the controls
	 * */
	private void addListeners() {
		((Button) findViewById(R.id.doneButton)).setOnClickListener(onClickListener);
	}

	public void setClimateInfo(ClimateInfo climateInfo) {
		this.climateInfo = climateInfo;
	}

	/**
	 * This method starts async task- a different thread from UI thread
	 **/
	private void getClimateInformation() {
		new RetrieveClimateInformation(this).execute(cityName);
	}

	/**
	 * Renders the UI componenets with values from ClimateInfo VO
	 **/
	public void renderClimateDetails() {
		// Find all the controls
		TextView cityTextView = (TextView) findViewById(R.id.cityNameText);
		TextView countryTextView = (TextView) findViewById(R.id.countryNameText);
		TextView tempTextView = (TextView) findViewById(R.id.tempText);
		TextView humidityTextView = (TextView) findViewById(R.id.humidityText);
		TextView lastUpdatedTextView = (TextView) findViewById(R.id.lastUpdatedText);

		// Populate values of these controls from VO
		cityTextView.setText(climateInfo.getCityName());
		countryTextView.setText(climateInfo.getCountryName());
		tempTextView.setText(Constants.TEMPERATURE + " is " + climateInfo.getTemperature());
		humidityTextView.setText(Constants.HUMIDITY + " is " + climateInfo.getHumidity());
		lastUpdatedTextView.setText(Constants.LAST_UPDATED + " on " + climateInfo.getLastUpdate());
	}

	/**
	 * Finishes this activity and resumes main activity.
	 **/
	public void clickOnDone() {
		finish();
	}

	/**
	 * Method handles when we couldn't find the city's climate information.
	 * Shows a simple Toast message
	 **/
	public void cityNotExist() {
		Toast toast = Toast.makeText(this, Constants.CLIMATE_INFO_NOT_FOUND + cityName, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.BOTTOM, 0, 0);
		toast.show();
		clickOnDone();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

}
