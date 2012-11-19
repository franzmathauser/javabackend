package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class NewsChannelManager
{

    @EJB
    TwitterManager twitterManager;
    @EJB
    YoutubeManager youtubeManager;

    public List<NewsDTO> getNewsChannel()
    {

        List<NewsDTO> newsList = new ArrayList<NewsDTO>();

        newsList.addAll( twitterManager.getNews() );
        newsList.addAll( youtubeManager.getLatestVideoFeeds( "nttdata" ) );

        Collections.sort( newsList );

        return newsList;

    }
}
