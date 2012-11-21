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
 * Configuration class to load application properties from a file.
 * @author MATHAF
 */
public class BackendConfiguration extends AbstractConfiguration
{

    private Configuration configuration;
    private String configFilePath;

    /**
     * Constructor with filename to load configuration.
     *
     * @param filePath path to configfile
     */
    protected BackendConfiguration( String filePath )
    {
        if ( filePath == null )
        {
            throw new IllegalArgumentException( "invalid parameter filePath: " + null );
        }

        this.configFilePath = filePath;
        setDefaultListDelimiter( ';' );
        loadConfiguration();
    }

    private void loadConfiguration()
    {
        URL configURL;
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
