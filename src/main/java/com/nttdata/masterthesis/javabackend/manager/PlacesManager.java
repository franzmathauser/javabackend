/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.services.exceptions.GatewayTimeoutException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class PlacesManager {
    
    public final static String GOOGLE_PLACES_URI = "https://maps.googleapis.com/maps/api/place/search/json?sensor=true";
    
    private final String googleApiKey = ConfigurationSingleton.getInstance().getString(ConfigurationConstants.GOOGLE_API_KEY);
    
    public String getFinancePlaces(String location){
        
        Client client = Client.create();
        
        StringBuilder url = new StringBuilder(GOOGLE_PLACES_URI);
        url.append("&key="+googleApiKey);
        
        if(location != null && !location.isEmpty()){
            url.append("&location="+location);
        }
        url.append("&radius=500");
        url.append("&types=bank%7Cfinance");
        //url.append("&rankby=distance");
        
        WebResource webResource = client.resource(url.toString());
        ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);

        if (response.getStatus() != HttpStatus.OK_200.getStatusCode()) {
           throw new GatewayTimeoutException("Failed : HTTP error code : "
                + response.getStatus());
        }

        String output = response.getEntity(String.class);

        return output;
    }

}
