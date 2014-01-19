package org.climateapp.ui;

import org.climateapp.beans.ClimateInfo;
import org.climateapp.ui.listeners.ClickListener;
import org.climateapp.worker.RetrieveClimateInformation;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
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

	private void addListeners() {
		// TODO Auto-generated method stub
		((Button) findViewById(R.id.doneButton)).setOnClickListener(onClickListener);
	}

	public void setClimateInfo(ClimateInfo climateInfo) {
		this.climateInfo = climateInfo;
	}

	private void getClimateInformation() {
		// TODO Auto-generated method stub
		new RetrieveClimateInformation(this).execute(cityName);
	}

	public void renderClimateDetails() {
		// TODO Auto-generated method stub
		TextView cityTextView = (TextView) findViewById(R.id.cityNameText);
		TextView countryTextView = (TextView) findViewById(R.id.countryNameText);
		TextView tempTextView = (TextView) findViewById(R.id.tempText);
		TextView humidityTextView = (TextView) findViewById(R.id.humidityText);
		TextView lastUpdatedTextView = (TextView) findViewById(R.id.lastUpdatedText);

		Resources resource = getResources();
		cityTextView.setText(climateInfo.getCityName());
		countryTextView.setText(climateInfo.getCountryName());
		tempTextView.setText(resource.getString(R.string.temperature_label) + " is " + climateInfo.getTemperature());
		humidityTextView.setText(resource.getString(R.string.humidity_label) + " is " + climateInfo.getHumidity());
		lastUpdatedTextView.setText(resource.getString(R.string.last_updated) + " on " + climateInfo.getLastUpdate());
	}

	public void clickOnDone() {
		finish();
	}

	public void cityNotExist() {
		Toast toast = Toast.makeText(this, "Didn't find the information for City " + cityName, Toast.LENGTH_SHORT);
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
