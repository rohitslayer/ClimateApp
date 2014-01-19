package org.climateapp.worker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.climateapp.Constant.Constants;
import org.climateapp.beans.ClimateInfo;
import org.climateapp.ui.ClimateActivity;
import org.climateapp.util.XMLParser;

import android.app.ProgressDialog;
import android.os.AsyncTask;

public class RetrieveClimateInformation extends
		AsyncTask<String, Void, ClimateInfo> {
	private ClimateActivity activity;
	private ProgressDialog progressDialog;

	public RetrieveClimateInformation(ClimateActivity activity) {
		super();
		this.activity = activity;
		progressDialog = new ProgressDialog(activity);
	}

	protected ClimateInfo doInBackground(String... cityNames) {
		String url = Constants.API_URL.replace(Constants.CITY_NAME_PLACEHOLDER,
				cityNames[0].trim());
		StringBuilder xmlString = new StringBuilder();
		try {
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setRequestMethod(Constants.GET_METHOD);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				xmlString.append(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		XMLParser xmlParser = new XMLParser(xmlString.toString());
		return xmlParser.parseClimateInformation();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		progressDialog.setMessage(Constants.FETCH_MSG);
		progressDialog.show();
		super.onPreExecute();
	}

	protected void onPostExecute(ClimateInfo climateInfo) {
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
		if (climateInfo != null) {
			activity.setClimateInfo(climateInfo);
			activity.renderClimateDetails();
		} else {
			activity.cityNotExist();
		}
	}
}