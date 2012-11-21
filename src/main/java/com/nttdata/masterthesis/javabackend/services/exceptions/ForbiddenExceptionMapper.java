/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.glassfish.grizzly.http.util.HttpStatus;

import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * A ForbiddenExceptionMapper enables to change the http response status
 * if a ForbiddenException is thrown.
 * @author MATHAF
 */
@Provider
public class ForbiddenExceptionMapper implements ExceptionMapper<ForbiddenException>
{
    private static final Response RESPONSE;

    private static final ResponseEnvelope RESPONSE_ENVELOPE;

    static
    {
        RESPONSE_ENVELOPE = new ResponseEnvelope( false );
        RESPONSE = Response.status( HttpStatus.FORBIDDEN_403.getStatusCode() ).entity( RESPONSE_ENVELOPE ).build();
    }

    @Override
    public Response toResponse( ForbiddenException ex )
    {

        //usually you don't pass detailed info out (don't do this here in production environments)
        RESPONSE_ENVELOPE.setErrorMsg( ex.getMessage() );

        return RESPONSE;
    }
}
