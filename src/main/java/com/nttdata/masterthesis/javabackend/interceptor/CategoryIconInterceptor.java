/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.interceptor;

import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import java.util.List;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author MATHAF
 */
// @Interceptor //  it's not necessary for @Interceptors() bindings, and in a CDI deployment must specify bindings (as per the spec).
public class CategoryIconInterceptor
{

    static final Logger LOG = LoggerFactory.getLogger( ServicesLoggingInterceptor.class );
    static final String SERVER_URL = "https://pc42366.de.softlab.net:8181/JavaBackend/icons/categories/";

    @AroundInvoke
    public Object log( InvocationContext ctx ) throws Exception
    {

        List<TransactionDTO> ret = ( List<TransactionDTO> ) ctx.proceed();

        for ( TransactionDTO transaction : ret )
        {
            if ( transaction.getCategoryIcon() == null && transaction.getCategory() != null )
            {
                transaction.setCategoryIcon( SERVER_URL + transaction.getCategory() + ".png" );
            }
        }

        return ret;
    }
}
