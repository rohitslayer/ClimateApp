package org.climateapp.Constant;

public interface Constants {
	// XML Tags, Attributes
	public static String ATTR_NAME = "name";
	public static String ATTR_VALUE = "value";
	public static String TAG_CITY = "city";
	public static String TAG_COUNTRY = "country";
	public static String TAG_TEMPE = "temperature";
	public static String TAG_HUMIDITY = "humidity";
	public static String TAG_LAST_UPDATED = "lastupdate";

	public static String KELVIN = " Kelvin";
	public static String PERCENTAGE = " %";
	public static String CITY_NAME_PLACEHOLDER = ":CITYNAME";
	public static String API_URL = "http://api.openweathermap.org/data/2.5/weather?q=:CITYNAME&mode=xml";
	public static String GET_METHOD = "GET";
	public static CharSequence EMPTY_STRING = "";

	// Warning, Information messages
	public static String FETCH_MSG = "Fetching Climate Information.";
	public static String ENETER_CITY_NAME_MSG = "Please enter the city name.";
	public static String EXIT_APPLICATION_MSG = "Do you wish to Close the Application?";
	public static String EXIT_MESAGE_TITLE = "Exit Application.";
	public static String OK = "Ok";
	public static String CANCEL = "Cancel";
}
