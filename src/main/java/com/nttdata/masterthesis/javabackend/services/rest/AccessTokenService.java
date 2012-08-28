/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.ressource.AccessToken;
import com.nttdata.masterthesis.javabackend.manager.AccessTokenManager;
import com.nttdata.masterthesis.javabackend.services.exceptions.NotAuthorizedException;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author MATHAF
 */
@Stateless
@Path("/accesstokens")
@Produces( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
@Consumes( { MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML } )
public class AccessTokenService {
    
    @EJB
    private AccessTokenManager accessTokenManager;

    /**
     *
     * @param user
     * @param password
     * @return
     */
    @POST
    public AccessToken createSession(@QueryParam("user") String user, @QueryParam("password") String password) {
        AccessToken as = accessTokenManager.createAccessToken(user,password); 
        if(as == null){
            throw new NotAuthorizedException("username or password are incorect.");
        }
        return as; 
    }
    
    /**
     *
     * @param user
     * @return
     */
    @DELETE
    public void destroyAccessToken(@QueryParam("user") String user, AccessToken token) {
        accessTokenManager.destroyToken(user, token); 
    }

}
