/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.services.exceptions.ThrowableExceptionMapper;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.eclipse.jetty.util.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author MATHAF
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Interceptors( ServicesLoggingInterceptor.class )
@Stateless
public class UserManagerService {

    static final Logger LOG = LoggerFactory.getLogger(UserManagerService.class);
    
    @EJB
    private UserDAO userDAO;

    @Path("login")
    @POST
    public ResponseEnvelope login(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest req) {
        ResponseEnvelope response = new ResponseEnvelope(); 
        
        // Container does the hashing
        //String hash = DigestUtils.sha512Hex(password);
        
        if (req.getUserPrincipal() != null) {
            if(LOG.isInfoEnabled()){
                LOG.info("Authentication: User has Session! Invalidate now. ",userName);
            }
            try {
                req.logout();
                req.getSession().invalidate();
            } catch (ServletException ex) {
                if(LOG.isErrorEnabled()){
                    LOG.error(ex.getMessage(), ex);
                } 
            }
        }
        
        try {
            req.login(userName, password); 
            response.setSuccess(true);
            
            if(LOG.isInfoEnabled()){
                LOG.info("Authentication: successfully logged in ",userName);
            }
        } catch (ServletException ex) {

            if(LOG.isErrorEnabled()){
                LOG.error(ex.getMessage(), ex);
            }
            response.setErrorMsg("Authentication failed");
            return response;

        }

        //read the user data from db and return to caller
        User user = userDAO.findByName(userName);
        
        if(LOG.isInfoEnabled()){
            LOG.info("Authentication: successfully retrieved User Profile from DB for ",userName);
        }
         
        //we don't want to send the hashed password out in the json response
        userDAO.detach(user);
        user.setPassword(null);
        user.setGroups(null);
        user.setBankAccount(null);
        
        response.setData(user); 
        
        return response;
    }
    
    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEnvelope logout(@Context HttpServletRequest req) {
 
        ResponseEnvelope response = new ResponseEnvelope();
 
        try {
            req.logout();
            req.getSession().invalidate();
            response.setSuccess(true);
        } catch (ServletException e) {
            
            if(LOG.isErrorEnabled()){
                LOG.error(e.getMessage(), e);
            }
            
            response.setErrorMsg("Logout failed.");
        }
        return response;
    }
}
