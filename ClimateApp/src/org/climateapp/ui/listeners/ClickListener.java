package org.climateapp.ui.listeners;

import org.climateapp.ui.ClimateActivity;

import com.example.climateapp.R;

import android.app.Activity;
import android.content.Intent;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ClickListener implements OnClickListener {
	private Activity activity;

	public ClickListener(Activity activity) {
		super();
		this.activity = activity;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		Button button = (Button) view;
		switch (button.getId()) {
		case R.id.doneButton:
			((ClimateActivity)activity).clickOnDone();
			break;
		case R.id.okButton:
			String cityName = ((EditText) activity.findViewById(R.id.cityText)).getText().toString();
			if(cityName == null || cityName.length() <=0) {
				Toast toast = Toast.makeText(activity, "Please enter the city name.", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.BOTTOM, 0, 0);
				toast.show();
				return;
			}
			Intent climateIntent = new Intent(activity, ClimateActivity.class);
			climateIntent.putExtra("City",cityName);
			activity.startActivity(climateIntent);
			break;
		}
	}

}
