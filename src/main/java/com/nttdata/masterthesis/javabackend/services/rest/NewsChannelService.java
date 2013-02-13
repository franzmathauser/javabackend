/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.NewsChannelManager;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * REST-Service for news domain. Available Actions: GET
 *
 * @author MATHAF
 */
@Stateless
@Path( "/newschannel" )
@Interceptors( ServicesLoggingInterceptor.class )
public class NewsChannelService
{
    @EJB
    private NewsChannelManager newsChannelManager;

    /**
     * List of all current News.
     * @return Envelope with metadata and data of methodcall.
     */
    @GET
    @Produces( "application/json" )
    public ResponseEnvelope getNewsStream()
    {
        ResponseEnvelope response = new ResponseEnvelope( true );

        List<NewsDTO> news = newsChannelManager.getNewsChannel();
        response.setBodyData( news );

        return response;
    }
}
