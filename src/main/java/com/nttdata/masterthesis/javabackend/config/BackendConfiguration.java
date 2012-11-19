/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.config;

import java.net.URL;
import java.util.Iterator;

import org.apache.commons.configuration.AbstractConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.DefaultConfigurationBuilder;

/**
 *
 * @author MATHAF
 */
public class BackendConfiguration extends AbstractConfiguration
{

    private Configuration configuration;
    private String configFilePath;

    /**
     * Konstruktor mit Dateiname für die Konfiguration.
     *
     * @param configFilePath zur Konfiguration
     */
    protected BackendConfiguration( String configFilePath )
    {
        if ( configFilePath == null )
        {
            throw new IllegalArgumentException( "Ungueltiger Parameter configFilePath: " + null );
        }

        this.configFilePath = configFilePath;
        setDefaultListDelimiter( ';' );
        loadConfiguration();
    }

    private void loadConfiguration()
    {
        URL configURL = null;
        try
        {
            configURL = getClass().getResource( configFilePath );
            if ( configURL == null )
            {
                configURL = getClass().getResource( "/" + configFilePath );
            }

            DefaultConfigurationBuilder configBuilder = new DefaultConfigurationBuilder();
            configBuilder.setURL( configURL );
            configuration = configBuilder.getConfiguration();

        } catch ( Exception ex )
        {
            //throw new IllegalArgumentException("---------------------------" + configURL.toString() + ex.toString());

            throw new IllegalArgumentException( configFilePath );
        }
    }

    @Override
    public boolean isEmpty()
    {
        return configuration.isEmpty();
    }

    @Override
    public boolean containsKey( String key )
    {
        return configuration.containsKey( key );
    }

    @Override
    public Object getProperty( String key )
    {
        return configuration.getProperty( key );
    }

    @Override
    public Iterator<String> getKeys()
    {
        return configuration.getKeys();
    }

    @Override
    protected void addPropertyDirect( String string, Object o )
    {
        throw new UnsupportedOperationException( "addPropertyDirect is not supported." );
    }
}
