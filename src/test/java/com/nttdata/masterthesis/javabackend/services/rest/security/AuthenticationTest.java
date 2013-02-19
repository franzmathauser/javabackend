/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.rest.security;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.representation.Form;

/**
 * The AuthenticationTest checks the Login-Service of the application.
 * @author MATHAF
 */
public class AuthenticationTest extends AbstractSecureTest
{
    private final String requestUri;

    /**
     * Default constructor.
     */
    public AuthenticationTest()
    {
        super();
        requestUri = "https://" + getCnName() + ":8181/JavaBackend/rest/auth/login";
    }

    /**
     * Auth with wrong credentials.
     */
    @Test
    public void authenticationFailTest()
    {
        Client client = Client.create();

        Form form = new Form();
        form.add( "username", getUser() );
        form.add( "password", getPassword() + "false" );

        WebResource webResource = client.resource( requestUri );

        ClientResponse response = webResource.accept( "application/json" )
        .type( MediaType.APPLICATION_FORM_URLENCODED )
        .post( ClientResponse.class, form );

        ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

        assertEquals( "httpstatus", 200, response.getStatus() );
        assertFalse( "authorized", env.isSuccess() );

    }

    /**
     * Checks the login process.
     */
    @Test
    public void authenticationSuccessTest()
    {
        Client client = Client.create();

        Form form = getLoginForm();

        WebResource webResource = client.resource( requestUri );

        ClientResponse response = webResource.accept( "application/json" )
        .type( MediaType.APPLICATION_FORM_URLENCODED )
        .post( ClientResponse.class, form );

        ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

        assertEquals( "httpstatus", 200, response.getStatus() );
        assertTrue( "authorized", env.isSuccess() );

    }

    /**
     * Checks the valid session login process.
     */
    @Test
    public void validSessionAuthTest()
    {
        Client client = Client.create();

        Form form = new Form();
        form.add( "username", getUser() );
        form.add( "password", getPassword() );

        WebResource webResource = client.resource( requestUri );
        ClientResponse response = webResource.accept( "application/json" )
        .type( MediaType.APPLICATION_FORM_URLENCODED )
        .post( ClientResponse.class, form );

        ResponseEnvelope env = response.getEntity( ResponseEnvelope.class );

        assertEquals( "httpstatus", 200, response.getStatus() );
        assertTrue( "authorized", env.isSuccess() );

        NewCookie cookie = response.getCookies().get( 0 );
        String cookieKey = cookie.getName();
        assertEquals( "check session id name", cookieKey, "SESSIONID" );
        assertTrue( "check if cookie is secure", cookie.isSecure() );

        Cookie reqCookie = cookie.toCookie();

        response = webResource.accept( "application/json" )
        .cookie( reqCookie )
        .type( MediaType.APPLICATION_FORM_URLENCODED )
        .post( ClientResponse.class, form );

        NewCookie cookie2 = response.getCookies().get( 0 );
        assertNotSame( "check if new sessionid is sent", cookie.getValue(), cookie2.getValue() );

        env = response.getEntity( ResponseEnvelope.class );

        assertEquals( "httpstatus", 200, response.getStatus() );
        assertTrue( "authorized", env.isSuccess() );

    }
}
