/**
 * This is Async Task it fetches the climate information from Web.
 * 
 * @author Vallabh
 * */
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
		//Prepare web URL
		String url = Constants.API_URL.replace(Constants.CITY_NAME_PLACEHOLDER,
				cityNames[0].trim());
		StringBuilder xmlString = new StringBuilder();
		try {
			//Opens the connection
			HttpURLConnection connection = (HttpURLConnection) new URL(url)
					.openConnection();
			connection.setRequestMethod(Constants.GET_METHOD);
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.connect();
			//Reading the response.
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
		//Parsing the XML response and returns Climate Information VO
		XMLParser xmlParser = new XMLParser(xmlString.toString());
		return xmlParser.parseClimateInformation();
	}

	/**
	 * This method shows the progress dialog till thread fetches the information 
	 **/
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		progressDialog.setMessage(Constants.FETCH_MSG);
		progressDialog.show();
		super.onPreExecute();
	}

	/**
	 * This method executes when async task done doing it's background work.
	 * */
	protected void onPostExecute(ClimateInfo climateInfo) {
		if(progressDialog != null) {
			progressDialog.dismiss();
		}
		if (climateInfo != null) {
			//Sets the climate information VO and renders the details
			activity.setClimateInfo(climateInfo);
			activity.renderClimateDetails();
		} else {
			//Couldn't find the city information
			activity.cityNotExist();
		}
	}
}