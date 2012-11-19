/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.slf4j.LoggerFactory;

import com.google.gdata.client.youtube.YouTubeQuery;
import com.google.gdata.client.youtube.YouTubeService;
import com.google.gdata.data.youtube.VideoEntry;
import com.google.gdata.data.youtube.VideoFeed;
import com.google.gdata.util.ServiceException;
import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;

/**
 *
 * @author MATHAF
 */
@Singleton
@LocalBean
public class YoutubeManager
{

    static final org.slf4j.Logger LOG = LoggerFactory.getLogger( YoutubeManager.class );
    private final String DEVELOPER_KEY = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.GOOGLE_API_DEV_KEY );
    private final String DEVELOPER_APP_NAME = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.GOOGLE_API_DEV_KEY );
    private final String APPLICATION_ROOT_URL = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.APPLICATION_ROOT_URL );
    private final String YOUTUBE_ICON = APPLICATION_ROOT_URL + "icons/newschannel/youtube.png";
    private static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/videos";
    private static final String YOUTUBE_EMBEDDED_URL = "http://www.youtube.com/v/";
    private final YouTubeService service;
    private final static int MAX_RESULTS = 25;

    public YoutubeManager()
    {

        service = new YouTubeService( DEVELOPER_APP_NAME, DEVELOPER_KEY );

    }

    public List<NewsDTO> getLatestVideoFeeds( String queryText )
    {

        YouTubeQuery query;
        List<VideoEntry> videos = new ArrayList<VideoEntry>();

        try
        {
            query = new YouTubeQuery( new URL( YOUTUBE_URL ) );
            query.setOrderBy( YouTubeQuery.OrderBy.RELEVANCE );
            query.setFullTextQuery( queryText );
            query.setSafeSearch( YouTubeQuery.SafeSearch.NONE );
            query.setMaxResults( MAX_RESULTS );

            VideoFeed videoFeed = service.query( query, VideoFeed.class );
            videos = videoFeed.getEntries();

        } catch ( IOException ex )
        {
            Logger.getLogger( YoutubeManager.class.getName() ).log( Level.SEVERE, null, ex );
        } catch ( ServiceException ex )
        {
            Logger.getLogger( YoutubeManager.class.getName() ).log( Level.SEVERE, null, ex );
        }

        return convert( videos );

    }

    private List<NewsDTO> convert( List<VideoEntry> videos )
    {
        List<NewsDTO> newsList = new ArrayList<NewsDTO>();

        for ( VideoEntry video : videos )
        {

            NewsDTO news = new NewsDTO();

            // video.getMediaGroup().getDescription().getPlainTextContent()
            String message = video.getMediaGroup().getTitle().getPlainTextContent();
            long uploadedDateMillis = video.getMediaGroup().getUploaded().getValue();
            Date uploadedDate = new Date( uploadedDateMillis );

            news.setMessage( message );
            news.setImage( YOUTUBE_ICON );
            news.setDate( uploadedDate );

            newsList.add( news );

        }

        return newsList;
    }
}
