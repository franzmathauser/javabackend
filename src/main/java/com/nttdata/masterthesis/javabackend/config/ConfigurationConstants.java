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
public final class ConfigurationConstants
{

    private ConfigurationConstants()
    {
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
     * Konfigurations-Konstatne für JavaEE - Application
     */
    public static final String APPLICATION_ROOT_URL = "com.nttdata.masterthesis.javabackend.applicationRootUrl";
    /**
     * Konfigurations-Konstante für den Google API key.
     */
    public static final String GOOGLE_API_KEY = "com.nttdata.masterthesis.javabackend.googleapi.key";
    public static final String GOOGLE_API_DEV_KEY = "com.nttdata.masterthesis.javabackend.googleapi.dev.key";
    public static final String GOOGLE_API_DEV_APP_NAME = "com.nttdata.masterthesis.javabackend.googleapi.dev.appName";
    /**
     * Konfigurations-Konstante für CN-Name des SSL-Zertifikats.
     */
    public static final String CN_NAME = "com.nttdata.masterthesis.javabackend.cnName";
    /**
     * Konfigurations-Konstante für Twitter API
     */
    public static final String TWITTER_ACCESS_TOKEN = "com.nttdata.masterthesis.javabackend.accessToken";
    public static final String TWITTER_ACCESS_TOKEN_SECURE = "com.nttdata.masterthesis.javabackend.accessTokenSecure";
    public static final String TWITTER_CUSTOMER_KEY = "com.nttdata.masterthesis.javabackend.customerKey";
    public static final String TWITTER_CUSTOMER_KEY_SECURE = "com.nttdata.masterthesis.javabackend.customerKeySecure";
    /**
     * Konfigurations-Konstante username für JUNIT test.
     */
    public static final String JUNIT_TESTUSER1_NAME = "com.nttdata.masterthesis.javabackend.tests.user1.name";
    /**
     * Konfigurations-Konstante username für JUNIT test.
     */
    public static final String JUNIT_TESTUSER1_PASSWORD = "com.nttdata.masterthesis.javabackend.tests.user1.password";
}
