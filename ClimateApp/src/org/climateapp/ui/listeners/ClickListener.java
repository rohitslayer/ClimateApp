/**
 * This is OnClickListener class for handling events on different 
 * buttons in the application. 
 * 
 * @author Vallabh
 * */
package org.climateapp.ui.listeners;

import org.climateapp.Constant.Constants;
import org.climateapp.ui.ClimateActivity;

import com.example.climateapp.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
		Button button = (Button) view;
		switch (button.getId()) {
		case R.id.doneButton:
			((ClimateActivity) activity).clickOnDone();
			break;
		case R.id.okButton:
			//Checking for city name is not empty.
			String cityName = ((EditText) activity.findViewById(R.id.cityText))
					.getText().toString();
			if (cityName == null || cityName.length() <= 0) {
				Toast toast = Toast.makeText(activity,
						Constants.ENTER_CITY_NAME_MSG, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.BOTTOM, 0, 0);
				toast.show();
				return;
			}
			
			//All fien create an intent and start climate activity
			Intent climateIntent = new Intent(activity, ClimateActivity.class);
			climateIntent.putExtra("City", cityName);
			activity.startActivity(climateIntent);
			break;
		case R.id.exitButton:
			//Confirmation Dialog
			AlertDialog.Builder alert = new AlertDialog.Builder(activity);
			alert.setTitle(Constants.EXIT_MESAGE_TITLE);
			alert.setMessage(Constants.EXIT_APPLICATION_MSG);

			alert.setNegativeButton(Constants.OK,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							dialog.dismiss();
							activity.finish();
						}
					});
			alert.setNeutralButton(Constants.CANCEL, null);
			alert.show();
			break;
		}
	}

}
