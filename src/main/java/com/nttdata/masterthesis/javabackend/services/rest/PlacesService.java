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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.PlacesManager;

/**
 * REST-Service for storefinder domain. Available Actions: GET
 * @author MATHAF
 */
@Stateless
@Path( "/secure/places" )
@Interceptors( ServicesLoggingInterceptor.class )
public class PlacesService
{
    @EJB
    private PlacesManager placesMgr;

    /**
     * List of all nearby bank stores to a geo coordinate.
     * The radius of a geo-coord is 500m.
     * @param location lat, lon (example: 48.13661,11.57709)
     * @return google response json
     */
    @GET
    @Produces( "application/json" )
    public String getHtml( @QueryParam( "location" ) String location )
    {
        return placesMgr.getFinancePlaces( location );
    }
}
