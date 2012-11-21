/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * A ThrowableExceptionMapper enables to change the http response if a exception occurs.
 * @author MATHAF
 */
//@Provider //TODO is currently turned off
public class ThrowableExceptionMapper implements ExceptionMapper<Throwable>
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( ThrowableExceptionMapper.class );

    private static final Response RESPONSE;

    private static final ResponseEnvelope RESPONSE_ENVELOPE;

    static
    {
        RESPONSE_ENVELOPE = new ResponseEnvelope( false );
        RESPONSE = Response.status( 500 ).entity( RESPONSE_ENVELOPE ).build();
    }

    @Override
    public Response toResponse( Throwable ex )
    {

        if ( LOG.isErrorEnabled() )
        {
            LOG.error( ex.getClass().getName(), ex );
        }

        //usually you don't pass detailed info out (don't do this here in production environments)
        RESPONSE_ENVELOPE.setErrorMsg( ex.getMessage() );

        return RESPONSE;
    }
}
