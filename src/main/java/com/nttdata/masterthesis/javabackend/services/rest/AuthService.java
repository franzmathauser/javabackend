/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.ressource.UserDTO;

/**
 * REST-Service for Authentication. Verfügbare Aktionen: GET, POST
 *
 * @author MATHAF
 */
@Path( "/auth" )
@Interceptors( ServicesLoggingInterceptor.class )
@Produces(
{
    MediaType.APPLICATION_JSON
} )
@Stateless
public class AuthService
{
    private static final Logger LOG = LoggerFactory.getLogger( AuthService.class );

    @EJB
    private UserDAO userDAO;

    /**
     * Creates a new session-context on serverside.
     * The session-context is only created if the user credentials are correct.
     * If a session-context is already established while this method is called,
     * it gets invalidated and creates a new session-context.
     *
     * @param userName
     *            Login-Name
     * @param password
     *            Password
     * @param req HTTP request Object
     *
     * @return Envelope with metadata and data of methodcall.
     */
    @POST
    @Path( "login" )
    @Produces(
    {
        MediaType.APPLICATION_JSON
    } )
    @Consumes(
    {
        MediaType.APPLICATION_FORM_URLENCODED
    } )
    public ResponseEnvelope login( @FormParam( "username" ) String userName,
                                   @FormParam( "password" ) String password,
                                   @Context HttpServletRequest req )
    {
        ResponseEnvelope response = new ResponseEnvelope();

        // Container does the hashing
        //String hash = DigestUtils.sha512Hex(password);

        if ( req.getUserPrincipal() != null )
        {
            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Authentication: User has Session! Invalidate now. ", userName );
            }
            try
            {
                req.logout();
                req.getSession().invalidate();
            }
            catch ( ServletException ex )
            {
                if ( LOG.isErrorEnabled() )
                {
                    LOG.error( ex.getMessage(), ex );
                }
            }
        }

        try
        {
            req.login( userName, password );
            response.setSuccess( true );

            if ( LOG.isInfoEnabled() )
            {
                LOG.info( "Authentication: successfully logged in ", userName );
            }
        }
        catch ( ServletException ex )
        {

            if ( LOG.isErrorEnabled() )
            {
                LOG.error( ex.getMessage(), ex );
            }
            response.setErrorMsg( "Authentication failed" );
            return response;

        }

        //read the user data from db and return to caller
        User dbUser = userDAO.findByName( userName );

        if ( LOG.isInfoEnabled() )
        {
            LOG.info( "Authentication: successfully retrieved User Profile from DB for ", userName );
        }

        UserDTO user = new UserDTO();
        user.setId( dbUser.getId() );
        user.setUserName( dbUser.getUserName() );
        user.setEmail( dbUser.getEmail() );
        user.setLastLogin( dbUser.getLastLogin() );
        user.setCountLoginErrors( dbUser.getCountLoginErrors() );
        user.setAllowedBankAccountId( dbUser.getBankAccount().getId() );

        response.setBodyData( user );

        return response;
    }

    /**
     * Destroys a session-context on serverside.
     * @param req HTTP request Object
     * @return Envelope with metadata and data of methodcall.
     */
    @GET
    @Path( "logout" )
    @Produces( MediaType.APPLICATION_JSON )
    public ResponseEnvelope logout( @Context HttpServletRequest req )
    {

        ResponseEnvelope response = new ResponseEnvelope();

        try
        {
            req.logout();
            req.getSession().invalidate();
            response.setSuccess( true );
        }
        catch ( ServletException e )
        {

            if ( LOG.isErrorEnabled() )
            {
                LOG.error( e.getMessage(), e );
            }

            response.setErrorMsg( "Logout failed." );
        }
        return response;
    }
}
