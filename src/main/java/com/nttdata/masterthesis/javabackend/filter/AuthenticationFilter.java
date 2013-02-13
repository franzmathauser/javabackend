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
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * Filter checks user authentication.
 * If a user is not logged in, the cancels the request and sends back a error message.
 * @author MATHAF
 */
@WebFilter( filterName = "auth" )
public class AuthenticationFilter implements Filter
{
    /**
     * error message of response.
     */
    private static String ERROR_MESSAGE = "No valid session";

    @Override
    public void init( FilterConfig fConfig ) throws ServletException
    {
    }

    @Override
    public void destroy()
    {
    }

    @Override
    public void doFilter( ServletRequest request, ServletResponse response,
                          FilterChain chain ) throws IOException, ServletException
    {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        if ( httpRequest.getUserPrincipal() == null )
        {

            httpResponse.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
            httpResponse.setContentType( MediaType.APPLICATION_JSON );

            ResponseEnvelope responseEnv = new ResponseEnvelope();
            responseEnv.setErrorMsg( ERROR_MESSAGE );

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString( responseEnv );
            httpResponse.getWriter().print( json );

        }
        else
        {
            chain.doFilter( request, response );
        }

    }
}
