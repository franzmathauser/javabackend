/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import static org.junit.Assert.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.junit.Test;

/**
 *
 * @author MATHAF
 */
public class AuthenticationTest  extends AbstractSecureTest{
    
    
    private final String requestUri;
    
    public AuthenticationTest() {
        super();
        requestUri = "https://"+getCnName()+":8181/JavaBackend/rest/secure/bankaccount/1/transactions";
    }
    
    @Test
    public void authenticationFailTest() {
        Client client = Client.create();
        
        WebResource webResource = client
            .resource(requestUri);

        ClientResponse response = webResource.accept("application/json")
            .get(ClientResponse.class);

        assertEquals("not authorized", 401, response.getStatus());

    }
    
    @Test
    public void authenticationSuccessTest() {
        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter(getUser(), getPassword()));
        
        WebResource webResource = client.resource(requestUri);

        ClientResponse response = webResource.accept("application/json")
            .get(ClientResponse.class);

        assertEquals("authorized", 200, response.getStatus());

    }
}
