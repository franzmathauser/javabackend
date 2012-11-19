/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import java.io.IOException;
import java.util.Date;
import javax.activation.MimeType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MATHAF
 */
@WebFilter( filterName = "auth" )
public class AuthenticationFilter implements Filter
{

    public static String ERROR_MESSAGE = "No valid session";

    @Override
    public void init( FilterConfig fConfig ) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException
    {
        HttpServletResponse httpResponse = ( HttpServletResponse ) response;
        HttpServletRequest httpRequest = ( HttpServletRequest ) request;

        if ( httpRequest.getUserPrincipal() == null )
        {
            /*
             ((HttpServletResponse)response).addHeader(
             "Access-Control-Allow-Origin", "http://localhost"
             );

             ((HttpServletResponse)response).addHeader(
             "Access-Control-Allow-Credentials", "true"
             );

             ((HttpServletResponse)response).addHeader(
             "Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, Origin, X-Requested-With"
             );

             ((HttpServletResponse)response).addHeader(
             "Access-Control-Allow-Methods", "GET, POST, OPTIONS"
             );
             */
            httpResponse.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
            httpResponse.setContentType( MediaType.APPLICATION_JSON );

            ResponseEnvelope responseEnv = new ResponseEnvelope();
            responseEnv.setErrorMsg( ERROR_MESSAGE );

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString( responseEnv );
            httpResponse.getWriter().print( json );

        } else
        {
            chain.doFilter( request, response );
        }

    }
}
