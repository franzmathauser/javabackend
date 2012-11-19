/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import static org.junit.Assert.*;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author MATHAF
 */
public class TransactionServiceTest extends AbstractSecureTest
{

    public TransactionServiceTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void hasAccessToTransactionTest()
    {

        String requestUri = "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/1/transactions";

        Client client = Client.create();
        client.addFilter( new HTTPBasicAuthFilter( getUser(), getPassword() ) );

        WebResource webResource = client.resource( requestUri );

        ClientResponse response = webResource.accept( "application/json" )
        .get( ClientResponse.class );

        ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

        //List<Transaction> test = (List<Transaction>) Unmarshaller.unmarshal(env.getData());
        // ElementNSImpl test = (ElementNSImpl) env.getData();
        //test.
        assertEquals( "authorized", 200, response.getStatus() );
        assertEquals( "env-success", true, env.isSuccess() );
        assertEquals( "env-status", "", env.getErrorMsg() );
        assertEquals( "env-errorMsg", 0, env.getFieldErrors().size() );

    }

    @Test
    public void noAccessToTransactions()
    {

        String requestUri = "https://" + getCnName() + ":8181/JavaBackend/rest/secure/bankaccount/2/transactions";

        Client client = Client.create();
        client.addFilter( new HTTPBasicAuthFilter( getUser(), getPassword() ) );

        WebResource webResource = client.resource( requestUri );

        ClientResponse response = webResource.accept( "application/json" )
        .get( ClientResponse.class );

        assertEquals( "forbidden", 403, response.getStatus() );

    }
}
