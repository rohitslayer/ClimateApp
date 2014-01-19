package org.climateapp.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.climateapp.Constant.Constants;
import org.climateapp.beans.ClimateInfo;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
	private String xmlString;

	public XMLParser(String xmlString) {
		super();
		this.xmlString = xmlString;
	}

	public ClimateInfo parseClimateInformation() {
		Element rootNode = null;
		ClimateInfo climateInfo;
		try {
			InputStream stream = new ByteArrayInputStream(xmlString.getBytes("UTF-8"));
			SAXBuilder builder = new SAXBuilder();
			Document document = (Document) builder.build(stream);
			rootNode = document.getRootElement();
			Element cityElement = rootNode.getChild(Constants.TAG_CITY);
			String cityName = cityElement.getAttributeValue(Constants.ATTR_NAME);
			String countryName = cityElement.getChildText(Constants.TAG_COUNTRY);
			String temperature = rootNode.getChild(Constants.TAG_TEMPE).getAttributeValue(Constants.ATTR_VALUE)
					+ Constants.KELVIN;
			String humidity = rootNode.getChild(Constants.TAG_HUMIDITY).getAttributeValue(Constants.ATTR_VALUE)
					+ Constants.PERCENTAGE;
			String lastUpdate = rootNode.getChild(Constants.TAG_LAST_UPDATED).getAttributeValue(Constants.ATTR_VALUE);
			climateInfo = new ClimateInfo(countryName, cityName, temperature, humidity, lastUpdate);
			return climateInfo;
		} catch (Exception e) {
			return null;
		}
	}
}
