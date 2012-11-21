package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;


/**
 * NewsChannel Manager controlls the access to News Service.
 * This manager reunites news from youtube and twitter.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class NewsChannelManager
{

    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( NewsChannelManager.class );

    @EJB
    private TwitterManager twitterManager;
    @EJB
    private YoutubeManager youtubeManager;

    /**
     * List of all current News from youtube and twitter.
     * @return List of News DTO if found else empty list
     */
    public List<NewsDTO> getNewsChannel()
    {

        List<NewsDTO> newsList = new ArrayList<NewsDTO>();

        newsList.addAll( twitterManager.getNews() );
        newsList.addAll( youtubeManager.getLatestVideoFeeds( "nttdata" ) );

        Collections.sort( newsList );

        return newsList;

    }
}
