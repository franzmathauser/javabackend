/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.ressource.AccessToken;
import com.nttdata.masterthesis.javabackend.services.exceptions.NotAuthorizedException;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class AccessTokenManager { 
    
    @EJB
    AccessTokenStore accessTokenStore;  
    
    public AccessToken createAccessToken(String user, String password) {
        AccessToken as = null;
        
        if(user.equals(password)){
            as = new AccessToken();
            accessTokenStore.addToken(user, as.getAccessToken());
            System.out.println(accessTokenStore.size()); 
        }

        return as;
    }
    
    public void destroyToken(String user, AccessToken token) {
        if(user != null && !user.isEmpty()){
            Long accessToken = accessTokenStore.getToken(user);
            if(accessToken.equals(token.getAccessToken())){
                accessTokenStore.removeToken(user);
            } else {
                throw new NotAuthorizedException("invalid user or token");
            }
        }
    }
    
    public boolean isValid(String user, Long token){
        
        Long sessToken = accessTokenStore.getToken(user);
        if(sessToken == null || !sessToken.equals(token))
            return false;

        return true;
    }
}
