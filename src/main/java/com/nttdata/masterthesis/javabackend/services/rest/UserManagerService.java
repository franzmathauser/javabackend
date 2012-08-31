/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
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

/**
 *
 * @author MATHAF
 */
@Path("/auth")
@Produces(MediaType.APPLICATION_JSON)
@Interceptors( ServicesLoggingInterceptor.class )
@Stateless
public class UserManagerService {

    @EJB
    private UserDAO userDAO;

    @Path("login")
    @POST
    public Response login(@FormParam("username") String userName, @FormParam("password") String password, @Context HttpServletRequest req) {
        ResponseEnvelope response = new ResponseEnvelope(); 

        if (req.getUserPrincipal() == null) {
            try {
                String hash = DigestUtils.sha512Hex(password);
                req.login(userName, password); 
                req.getServletContext().log("Authentication: successfully logged in " + userName);
            } catch (ServletException e) {
                e.printStackTrace();
                response.setStatus("FAILED");
                response.setErrorMsg("Authentication failed");
                return Response.ok().entity(response).build();
            }
        } else {
            req.getServletContext().log("Skip logged because already logged in: "+ userName);
        }
        
       //read the user data from db and return to caller
        response.setStatus("SUCCESS");
         
        User user = userDAO.findByName(userName);
        req.getServletContext().log("Authentication: successfully retrieved User Profile from DB for " + userName);
        
         
        //we don't want to send the hashed password out in the json response
        userDAO.detach(user);
        user.setPassword(null);
        user.setGroups(null);
        
        response.setData(user); 
        
        return Response.ok().entity(response).build();
    }
    
    @GET
    @Path("logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@Context HttpServletRequest req) {
 
        ResponseEnvelope response = new ResponseEnvelope();
 
        try {
            req.logout();
            response.setStatus("SUCCESS");
            req.getSession().invalidate();
        } catch (ServletException e) {
            e.printStackTrace();
            response.setStatus("FAILED");
            response.setErrorMsg("Logout failed on backend");
        }
        return Response.ok().entity(response).build();
    }
}
