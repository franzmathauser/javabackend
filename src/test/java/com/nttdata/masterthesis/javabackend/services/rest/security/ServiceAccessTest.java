/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.rest.security;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * The ServiceAccessTest checks the access to secure services.
 * @author MATHAF
 */
public class ServiceAccessTest extends AbstractSecureTest
{
    private Cookie cookie;

    /**
     * Creates a Session Context on Server.
     */
    @Before
    public void setUp()
    {
        Client client = Client.create();

        WebResource webResource = client.resource( getLoginURI() );
        ClientResponse response = webResource.accept( "application/json" )
        .type( MediaType.APPLICATION_FORM_URLENCODED )
        .post( ClientResponse.class, getLoginForm() );

        cookie = response.getCookies().get( 0 ).toCookie();
    }

    /**
     * Removes old cookie value.
     */
    @After
    public void tearDown()
    {
        cookie = null;
    }

    /**
     * User want to access a services with correct bankaccount-id.
     */
    @Test
    public void hasAccessToSecureServicesTest()
    {
        List<String> requestUrls = new ArrayList<String>();
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/transactions" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/categories" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byWeekOfYear" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byCategory" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/incomeOutcomeSaldo" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byMonthlyCategory" );

        for ( String requestUri : requestUrls )
        {
            Client client = Client.create();
            WebResource webResource = client.resource( requestUri );

            ClientResponse response = webResource.accept( "application/json" )
            .cookie( cookie )
            .get( ClientResponse.class );

            ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

            assertEquals( "authorized", 200, response.getStatus() );
            assertEquals( "env-success", true, env.isSuccess() );
            assertNull( "env-status", env.getErrorMsg() );
            assertNull( "env-errorMsg", env.getFieldErrors() );
        }

    }

    /**
     * User wants to access a services with correct credentials.
     * The places service does not deliver the standard Response-Envelope.
     * The returned JSON-Format does come from google REST-webservice.
     */
    @Test
    public void hasAccessToSecurePlacesServiceTest()
    {
        String requestUrl = "https://" + getCnName() + ":8181/JavaBackend/rest/secure/places?location=48.13661,11.57709";

        Client client = Client.create();
        WebResource webResource = client.resource( requestUrl );

        ClientResponse response = webResource.accept( "application/json" )
        .cookie( cookie )
        .get( ClientResponse.class );

        assertEquals( "authorized", 200, response.getStatus() );
    }

    /**
     * Forbidden Test.
     * User wants to request a bankaccount id which is not owned.
     */
    @Test
    public void forbiddenAccessToSecureServiceTest()
    {
        List<String> requestUrls = new ArrayList<String>();
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/transactions" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/categories" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/statistic/byWeekOfYear" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/statistic/byCategory" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/statistic/incomeOutcomeSaldo" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/statistic/byMonthlyCategory" );

        for ( String requestUri : requestUrls )
        {
            Client client = Client.create();
            WebResource webResource = client.resource( requestUri );
            ClientResponse response = webResource.accept( "application/json" )
            .cookie( cookie )
            .get( ClientResponse.class );

            ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

            assertEquals( "env-success", false, env.isSuccess() );
            assertNotNull( "env-status", env.getErrorMsg() );
            assertNull( "env-errorMsg", env.getFieldErrors() );

            assertEquals( "forbidden", 403, response.getStatus() );
        }
    }

    /**
     * Unauthorized Test.
     * User wants to request a service without correct credentials.
     */
    @Test
    public void unauthorizedServiceTest()
    {

        List<String> requestUrls = new ArrayList<String>();
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/transactions" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/categories" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byWeekOfYear" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byCategory" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/incomeOutcomeSaldo" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/4/statistic/byMonthlyCategory" );
        requestUrls.add( "https://" + getCnName() + ":8181/JavaBackend/rest/secure/places?location=12,12" );

        for ( String requestUri : requestUrls )
        {
            Client client = Client.create();
            WebResource webResource = client.resource( requestUri );
            ClientResponse response = webResource.accept( "application/json" )
            .get( ClientResponse.class );

            assertEquals( "forbidden", 401, response.getStatus() );
        }

    }
}
