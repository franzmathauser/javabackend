/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.PlacesManager;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author MATHAF
 */
@Stateless
@Path( "/secure/places" )
@Interceptors( ServicesLoggingInterceptor.class )
public class PlacesService
{

    @EJB
    PlacesManager placesMgr;

    @GET
    @Produces( "application/json" )
    public String getHtml( @QueryParam( "location" ) String location )
    {
        return placesMgr.getFinancePlaces( location );
    }
}
