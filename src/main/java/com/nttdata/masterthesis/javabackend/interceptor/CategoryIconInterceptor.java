/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.interceptor;

import java.util.List;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.ressource.CategoryContainer;

/**
 * Interceptor adds a icon-url sting into a transaction dto depending on the category.
 * @author MATHAF
 */
// @Interceptor //  it's not necessary for @Interceptors() bindings, and in a CDI deployment must specify bindings (as per the spec).
public class CategoryIconInterceptor
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( ServicesLoggingInterceptor.class );

    /**
     * Application Root URL.
     */
    public static final String APPLICATION_ROOT_URL;

    /**
     * Root-url to icon resources.
     */
    public static final String ICON_ROOT_URL;

    static
    {
        APPLICATION_ROOT_URL = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.APPLICATION_ROOT_URL );
        ICON_ROOT_URL = APPLICATION_ROOT_URL + "icons/categories/";
    }

    /**
     * Adds a icon-url to a Transaction DTO, depending on the category value.
     * @param ctx interception context
     * @return the modified transaction DTO
     * @throws Exception if intercepted method throws an exception
     */
    @AroundInvoke
    public Object log( InvocationContext ctx ) throws Exception
    {

        Object obj = ctx.proceed();

        if ( obj instanceof List<?> )
        {
            List<CategoryContainer> ret = (List<CategoryContainer>) obj;
            return doList( ret );
        }
        else
        {
            CategoryContainer ret = (CategoryContainer) obj;
            return doPlain( ret );
        }

    }

    private List<CategoryContainer> doList( List<CategoryContainer> ret )
    {

        for ( CategoryContainer element : ret )
        {
            doPlain( element );
        }

        return ret;
    }

    private Object doPlain( CategoryContainer element )
    {
        if ( element.getCategoryIconUrl() == null && element.getCategoryName() != null )
        {
            element.setCategoryIconUrl( ICON_ROOT_URL + element.getCategoryName() + ".png" );
        }
        return element;
    }
}
