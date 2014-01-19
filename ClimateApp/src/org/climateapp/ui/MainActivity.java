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
		final Button okButton = (Button) findViewById(R.id.okButton);
		okButton.setOnClickListener(onClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		((EditText) findViewById(R.id.cityText)).setText(Constants.EMPTY_STRING);
	}
}
