/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.interceptor;


import com.nttdata.masterthesis.javabackend.manager.AccessTokenManager;
import com.nttdata.masterthesis.javabackend.services.AbstractAccessTokenService;
import com.nttdata.masterthesis.javabackend.services.exceptions.NotAuthorizedException;
import java.util.Map;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author MATHAF
 */
public class AccessInterceptor {
    
//    @EJB
//    AccessTokenManager accessTokenManager;
    
    @EJB
    AccessTokenManager accessTokenManager;
    
    @AroundInvoke
    public Object checkAccessToken( InvocationContext ctx ) throws Exception
    {
        
        Object ret = null;  
        Map<String,Object> map = ctx.getContextData();
        
        AbstractAccessTokenService  target = (AbstractAccessTokenService) ctx.getTarget();
        UriInfo test = target.getUriInfo();
        MultivaluedMap<String,String> param =  test.getQueryParameters();
        
        // Extract Query Parameter
        String accesstoken = param.getFirst("accesstoken");
        String user = param.getFirst("user");
        
        boolean isValid = accessTokenManager.isValid(user, Long.parseLong(accesstoken));
        
        if(isValid){     
            // do method call
            ret = ctx.proceed(); 
        } else {
            throw new NotAuthorizedException("no valid token found.");
        }
        
        return ret;
    }
    
}
