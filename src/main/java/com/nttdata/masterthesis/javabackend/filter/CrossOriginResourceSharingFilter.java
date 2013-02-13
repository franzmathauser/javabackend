/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Filter add Header to enable Cross-Origin Requests from a Browser.
 * @author MATHAF
 */
@WebFilter( filterName = "cors" )
public class CrossOriginResourceSharingFilter implements Filter
{
    /**
     * Logger Object.
     */
    static final Logger LOG = LoggerFactory.getLogger( CrossOriginResourceSharingFilter.class );

    @Override
    public void init( FilterConfig fConfig ) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter(
    ServletRequest request, ServletResponse response,
    FilterChain chain ) throws IOException, ServletException
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String origin = httpRequest.getHeader( "Origin" );

        httpResponse.addHeader( "Access-Control-Allow-Origin", origin );

        httpResponse.addHeader( "Access-Control-Allow-Credentials", "true" );

        httpResponse.addHeader( "Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, Origin, X-Requested-With" );

        httpResponse.addHeader( "Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS" );

        chain.doFilter( request, response );
    }
}
