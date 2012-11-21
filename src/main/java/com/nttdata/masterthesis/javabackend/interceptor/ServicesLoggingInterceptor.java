/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.interceptor;

import java.util.Arrays;
import java.util.Date;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service Logger logs all requests incoming and response outgoing.
 * @author MATHAF
 */
// @Interceptor //  it's not necessary for @Interceptors() bindings, and in a CDI deployment must specify bindings (as per the spec).
public class ServicesLoggingInterceptor
{
    /**
     * Object Logger.
     */
    public static final Logger LOG = LoggerFactory.getLogger( ServicesLoggingInterceptor.class );

    /**
     * Interceptor method loggs the ingoing parameters, if a parameter exists.
     * In a second step the interceptor chain is called and the starttime is
     * meassured to calculate the duration of the operation. The duration and
     * return objects are written to the log file. A thrown exception will also
     * be logged an rethrown.
     *
     * @param ctx interception context
     * @return proceed object for call chain
     * @throws Exception exception if intercepted method throws an exception
     */
    @AroundInvoke
    public Object log( InvocationContext ctx ) throws Exception
    {
        Class<?> callerClass = ctx.getMethod().getDeclaringClass();

        Object ret = null;
        long duration = -1;

        Object[] parameters = ctx.getParameters();
        String stringParam = Arrays.toString( parameters );

        if ( parameters != null && parameters.length > 0 )
        {
            if ( LOG.isDebugEnabled() )
            {
                Object[] args =
                {
                    callerClass.getName(), ctx.getMethod().getName(), stringParam
                };
                LOG.debug( "Class: {}, Method: {}, Parameters: {}", args );
            }
        }
        try
        {
            long startMethod = new Date().getTime();
            ret = ctx.proceed();
            long endMethod = new Date().getTime();

            duration = endMethod - startMethod;
        }
        catch ( Exception e )
        {
            if ( LOG.isErrorEnabled() )
            {
                Object[] args =
                {
                    callerClass.getName(), ctx.getMethod().getName(), e.getMessage()
                };
                LOG.error( "Class: {}, Method: {}, {}", args );
            }
            throw e;
        }

        Object logRet = ret;
        if ( logRet instanceof Response )
        {
            logRet = ( (Response) ret ).getEntity();
        }

        Object[] args =
        {
            callerClass.getName(), ctx.getMethod().getName(), logRet, new Long( duration )
        };

        if ( LOG.isErrorEnabled() )
        {
            LOG.info( "Class: {}, Method: {}, Return: {}, Durration: {} ms", args );
        }

        return ret;
    }
}
