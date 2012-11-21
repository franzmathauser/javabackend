/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Filter redirects a http request to https.
 * @author MATHAF
 */
@WebFilter( filterName = "http2https" )
public class HttpToHttpsFilter implements Filter
{
    /**
     * Logger Object.
     */
    static final Logger LOG = LoggerFactory.getLogger( HttpToHttpsFilter.class );

    @Override
    public void init( FilterConfig config ) throws ServletException
    {
    }

    @Override
    public void doFilter( ServletRequest req, ServletResponse res,
                          FilterChain chain ) throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if ( LOG.isInfoEnabled() )
        {
            LOG.info( "HttpToHttpsFilter: URL requested = {}", request.getRequestURL().toString() );
        }

        if ( !request.isSecure() )
        {
            String url = request.getRequestURL().toString().replaceFirst( "http", "https" );
            url = url.replaceFirst( ":8080/", ":8181/" );  //quick and dirty!!!

            //don't forget to add the parameters
            if ( request.getQueryString() != null )
            {
                url += "?" + request.getQueryString();
            }

            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "HttpToHttpsFilter redirect to: {} ", url );
            }

            response.sendRedirect( url );
        }
        else
        {
            chain.doFilter( req, res ); // we already have a https connection ==> so just continue request
        }
    }

    @Override
    public void destroy()
    {
        // release resources if you have any
    }
}
