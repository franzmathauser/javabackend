/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.config;

/**
 * Diese Klasse enthält die Konstanten zum Auslesen aus der Konfiguration.
 * 
 * @author Franz Mathauser, Stefan Wörner
 */
public final class ConfigurationConstants {
    
    private ConfigurationConstants() {
        
    }
    
   /**
   * ConfigFile Name.
   */
   public static final String CONFIG_FILE_NAME = "backend_config.xml";
   
   /**
    * PropertiesFile Name.
    */
   public static final String PROPERTIES_FILE_NAME = "backend.properties";
   
   /**
    * Konfigurations-Konstante für den Google API key.
    */
    public static final String GOOGLE_API_KEY = "com.nttdata.masterthesis.javabackend.googleapi.key";
    
}
