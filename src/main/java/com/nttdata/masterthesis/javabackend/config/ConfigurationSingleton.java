/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.config;

/**
 *
 * @author MATHAF
 */
public class ConfigurationSingleton extends BackendConfiguration {
    
        private static ConfigurationSingleton config = new ConfigurationSingleton();

	/**
	 * Konstruktor.
	 */
	private ConfigurationSingleton()
	{
		super( ConfigurationConstants.CONFIG_FILE_NAME );
	}

	/**
	 * Singleton Factory Methode.
	 * 
	 * @return unique Instanz von {@link ConfigurationSingleton}
	 */
	public static ConfigurationSingleton getInstance()
	{
		synchronized (config)
		{
			return config;
		}
	}
    
}
