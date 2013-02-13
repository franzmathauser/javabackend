/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.config;

/**
 * Configuration implements Singleton-Pattern to request config values.
 * @author MATHAF
 */
public final class ConfigurationSingleton extends BackendConfiguration
{
    private static final ConfigurationSingleton CONFIG = new ConfigurationSingleton();

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
        synchronized ( CONFIG )
        {
            return CONFIG;
        }
    }
}
