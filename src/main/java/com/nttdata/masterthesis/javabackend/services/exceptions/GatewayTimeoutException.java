/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.grizzly.http.util.HttpStatus;

/**
 * A GatewayTimeoutException enables to change the http response status
 * if a GatewayTimeoutException is thrown.
 * @author MATHAF
 */
public class GatewayTimeoutException extends WebApplicationException
{
    /**
     * Creates a HTTP-Response with status 504 als JSON object.
     * @param message error message
     */
    public GatewayTimeoutException( String message )
    {
        //TODO change this excepiton to exception mapper.
        super( Response.status( HttpStatus.GATEWAY_TIMEOUT_504.getStatusCode() ).entity( message ).type( MediaType.APPLICATION_JSON ).build() );
    }
}
