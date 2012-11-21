/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.config;

/**
 * Configuration constants contains a mapping between property-key in configfiles to Java constants.
 * Those are used to read the configfile.
 *
 * @author MATHAF
 */
public final class ConfigurationConstants
{
    private ConfigurationConstants()
    {
    }
    /**
     * config filename.
     */
    public static final String CONFIG_FILE_NAME = "backend_config.xml";
    /**
     * properties filename.
     */
    public static final String PROPERTIES_FILE_NAME = "backend.properties";
    /**
     * configuration-constant of application url.
     */
    public static final String APPLICATION_ROOT_URL = "com.nttdata.masterthesis.javabackend.applicationRootUrl";
    /**
     * configuration-constant of google api key.
     */
    public static final String GOOGLE_API_KEY = "com.nttdata.masterthesis.javabackend.googleapi.key";
    /**
     * configuration-constant for google dev key.
     */
    public static final String GOOGLE_API_DEV_KEY = "com.nttdata.masterthesis.javabackend.googleapi.dev.key";
    /**
     * configuration-constant for google dev app name.
     */
    public static final String GOOGLE_API_DEV_APP_NAME = "com.nttdata.masterthesis.javabackend.googleapi.dev.appName";
    /**
     * configuration-constant for CN-name of ssl certificate.
     */
    public static final String CN_NAME = "com.nttdata.masterthesis.javabackend.cnName";
    /**
     * configuration-constant for twitter api - access token.
     */
    public static final String TWITTER_ACCESS_TOKEN = "com.nttdata.masterthesis.javabackend.accessToken";
    /**
     * configuration-constant for twitter api - access token secure.
     */
    public static final String TWITTER_ACCESS_TOKEN_SECURE = "com.nttdata.masterthesis.javabackend.accessTokenSecure";
    /**
     * configuration-constant for twitter api - customer key.
     */
    public static final String TWITTER_CUSTOMER_KEY = "com.nttdata.masterthesis.javabackend.customerKey";
    /**
     * configuration-constant for twitter api - customer key secure.
     */
    public static final String TWITTER_CUSTOMER_KEY_SECURE = "com.nttdata.masterthesis.javabackend.customerKeySecure";
    /**
     * configuration-constant for junit test username.
     */
    public static final String JUNIT_TESTUSER1_NAME = "com.nttdata.masterthesis.javabackend.tests.user1.name";
    /**
     * configuration-constant for junit test password.
     */
    public static final String JUNIT_TESTUSER1_PASSWORD = "com.nttdata.masterthesis.javabackend.tests.user1.password";
}
