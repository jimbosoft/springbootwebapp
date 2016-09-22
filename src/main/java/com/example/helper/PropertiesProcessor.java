package com.example.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({
	@PropertySource(value = {"classpath:route.properties", "file:${spring.config.location}rpm.route.properties"}, ignoreResourceNotFound=true),
	@PropertySource(value = {"classpath:spectrumSoapInterface.properties", "file:${spring.config.location}rpm.spectrumSoapInterface.properties"}, ignoreResourceNotFound=true),
})
public class PropertiesProcessor
{
	@Autowired
	private Environment properties;

	
	public String getPropertyAsString(String propertyKey)
	{
		String val = properties.getProperty(propertyKey);

		return val == null ? propertyKey : val;
	}

	public String getPropertyAsString(String propertyKey, String defaultValue)
	{
		String value = properties.getProperty(propertyKey);
		if (value == null)
		{
			value = defaultValue;
		}
		return value;
	}
	public int getPropertyAsInt(String propertyKey, int defaultValue)
	{
		try
		{
			return Integer.parseInt(getPropertyAsString(propertyKey));
		} catch (Exception ex)
		{
			Logger.getLogger(PropertiesProcessor.class).error("Failed to find Integer property " + propertyKey + ex.getMessage());
			return defaultValue;
		}
	}

	public Boolean getPropertyAsBool(String propertyKey, Boolean defaultValue)
	{
		try
		{
			return Boolean.valueOf(getPropertyAsString(propertyKey));
		} catch (Exception ex)
		{
			Logger.getLogger(PropertiesProcessor.class).error("Failed to Boolean property " + propertyKey + ex.getMessage());
			return defaultValue;
		}
	}
}
