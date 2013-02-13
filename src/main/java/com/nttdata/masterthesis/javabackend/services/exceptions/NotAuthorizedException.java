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

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * A NotAuthorizedException enables to change the http response status
 * if a NotAuthorizedException is thrown.
 *
 * @author MATHAF
 */
public class NotAuthorizedException extends WebApplicationException
{
    /**
     * Creates a HTTP-Response with status 401 als JSON object.
     * @param message error message
     */
    public NotAuthorizedException( String message )
    {
        //TODO change this excepiton to exception mapper.
        super(
        Response.status( HttpStatus.UNAUTHORIZED_401.getStatusCode() ).entity(
        ( new ResponseEnvelope( false ) ).setErrorMsg( message ) ).type( MediaType.APPLICATION_JSON ).build() );


    }
}
