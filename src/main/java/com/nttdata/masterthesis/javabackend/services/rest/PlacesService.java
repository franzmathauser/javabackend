/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.manager.PlacesManager;
import com.nttdata.masterthesis.javabackend.services.AbstractAccessTokenService;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author MATHAF
 */
@Stateless
@Path("/places")
public class PlacesService extends AbstractAccessTokenService {
    
    @EJB
    PlacesManager placesMgr;
    
    @GET
    @Produces("text/html")
    public String getHtml(@QueryParam("location") String location) {
        return placesMgr.getFinancePlaces(location);
    }

}
