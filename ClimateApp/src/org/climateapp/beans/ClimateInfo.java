/**
 * This class is bean, a value object VO, of climate information.
 * When we fetch climate information, we will populate this VO
 * This is read only bean as user will never change the climate information
 * once we fetch it
 * 
 * @author Vallabh
 * */
package org.climateapp.beans;

public class ClimateInfo {
	private String countryName;
	private String cityName;
	private String temperature;
	private String humidity;
	private String lastUpdate;

	public ClimateInfo(String countryName, String cityName, String temperature, String humidity, String lastupdate) {
		super();
		this.countryName = countryName;
		this.cityName = cityName;
		this.temperature = temperature;
		this.humidity = humidity;
		this.lastUpdate = lastupdate;
	}

	public String getCountryName() {
		return countryName;
	}

	public String getCityName() {
		return cityName;
	}

	public String getTemperature() {
		return temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public String getLastUpdate() {
		return lastUpdate;
	}

}
