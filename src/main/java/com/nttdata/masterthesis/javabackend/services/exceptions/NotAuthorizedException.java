/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.grizzly.http.util.HttpStatus;

/**
 *
 * @author MATHAF
 */
public class NotAuthorizedException extends WebApplicationException
{

    public NotAuthorizedException( String message )
    {

        super(
        Response.status( HttpStatus.UNAUTHORIZED_401.getStatusCode() ).entity(
        ( new ResponseEnvelope( false ) ).setErrorMsg( message ) ).type( MediaType.APPLICATION_JSON ).build() );


    }
}