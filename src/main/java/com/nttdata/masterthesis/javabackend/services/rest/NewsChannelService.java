/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.NewsChannelManager;
import com.nttdata.masterthesis.javabackend.manager.TwitterManager;
import com.nttdata.masterthesis.javabackend.manager.YoutubeManager;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author MATHAF
 */
@Stateless
@Path( "/newschannel" )
@Interceptors( ServicesLoggingInterceptor.class )
public class NewsChannelService
{

    @EJB
    NewsChannelManager newsChannelManager;
    @EJB
    TwitterManager twitterManager;
    @EJB
    YoutubeManager youtubeManager;

    @GET
    @Produces( "application/json" )
    public ResponseEnvelope getNewsStream()
    {

        ResponseEnvelope response = new ResponseEnvelope( true );

        List<NewsDTO> news = newsChannelManager.getNewsChannel();
        response.setData( news );

        return response;
    }
}
