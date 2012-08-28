/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services;

import com.nttdata.masterthesis.javabackend.interceptor.AccessInterceptor;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author MATHAF
 */
@Interceptors(AccessInterceptor.class)
public class AbstractAccessTokenService {
    
    @Context UriInfo uriInfo;
    
     public UriInfo getUriInfo(){
        return uriInfo;
    }
}
