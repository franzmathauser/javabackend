/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.services.exceptions.GatewayTimeoutException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Places Manager controlls the access to Google Places Service.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class PlacesManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( PlacesManager.class );

    /**
     * Base Url to Googles REST Service.
     */
    public static final String GOOGLE_PLACES_URI;

    private static final String GOOGLE_API_KEY;

    static
    {
        GOOGLE_PLACES_URI = "https://maps.googleapis.com/maps/api/place/search/json?sensor=true";
        GOOGLE_API_KEY = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.GOOGLE_API_KEY );
    }

    /**
     * List of all nearby bank stores to a geo coordinate.
     * The radius of a geo-coord is 500m.
     * @param location lat, lon (example: 48.13661,11.57709)
     * @return google response json
     */
    public String getFinancePlaces( String location )
    {

        Client client = Client.create();

        StringBuilder url = new StringBuilder( GOOGLE_PLACES_URI );
        url.append( "&key=" );
        url.append( GOOGLE_API_KEY );

        if ( location != null && !location.isEmpty() )
        {
            url.append( "&location=" );
            url.append( location );
        }
        url.append( "&radius=500" );
        url.append( "&types=bank%7Cfinance" );
        //url.append("&rankby=distance");

        WebResource webResource = client.resource( url.toString() );
        ClientResponse response = webResource.accept( "application/json" ).get( ClientResponse.class );

        if ( response.getStatus() != HttpStatus.OK_200.getStatusCode() )
        {
            throw new GatewayTimeoutException( "Failed : HTTP error code : "
            + response.getStatus() );
        }

        String output = response.getEntity( String.class );

        return output;
    }
}
