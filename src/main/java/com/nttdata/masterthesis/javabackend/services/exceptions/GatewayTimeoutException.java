/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 *
 * @author MATHAF
 */
public class GatewayTimeoutException extends WebApplicationException
{

    public GatewayTimeoutException( String message )
    {
        super( Response.status( HttpStatus.GATEWAY_TIMEOUT_504.getStatusCode() ).entity( message ).type( MediaType.APPLICATION_JSON ).build() );
    }
}
