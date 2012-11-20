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

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.slf4j.Logger;
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
 * YouTube Manager controlls the access to Googles YouTube Service.
 *
 * @author MATHAF
 */
@Singleton
@LocalBean
public class YoutubeManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( YoutubeManager.class );
    /**
     * Url-path to youtube icon.
     */
    public static final String YOUTUBE_ICON;
    /**
     * Google GDATA Service URL.
     */
    public static final String YOUTUBE_URL = "http://gdata.youtube.com/feeds/api/videos";
    /**
     * Youtube url for embedded videos.
     */
    public static final String YOUTUBE_EMBEDDED_URL = "http://www.youtube.com/v/";
    /**
     * max number of requested video feeds.
     */
    public static final int MAX_RESULTS = 25;
    private static final String DEVELOPER_KEY;
    private static final String DEVELOPER_APP_NAME;
    private static final String APPLICATION_ROOT_URL;
    private final YouTubeService service;

    static
    {
        DEVELOPER_KEY = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.GOOGLE_API_DEV_KEY );
        DEVELOPER_APP_NAME = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.GOOGLE_API_DEV_KEY );
        APPLICATION_ROOT_URL = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.APPLICATION_ROOT_URL );
        YOUTUBE_ICON = APPLICATION_ROOT_URL + "icons/newschannel/youtube.png";
    }

    /**
     * Default Constructor.
     * Loads Google-API-KEYs from config-file and instantiates
     * and instantiates a YouTube-Service Object.
     */
    public YoutubeManager()
    {
        service = new YouTubeService( DEVELOPER_APP_NAME, DEVELOPER_KEY );
    }

    /**
     * List of Video-Feeds to a given query-String.
     * @param queryText Search String for fulltext search.
     * @return a list of found video feeds or an empty list in case of no result.
     */
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

        }
        catch ( IOException ex )
        {
            if ( LOG.isErrorEnabled() )
            {
                LOG.error( "io exception", ex );
            }
        }
        catch ( ServiceException ex )
        {
            if ( LOG.isErrorEnabled() )
            {
                LOG.error( "service exception", ex );
            }
        }

        return convert( videos );

    }

    /**
     * Helper method to convert a List of Googles VideoEntrys to a List of NewsDTO.
     * @param videos List of VideoEntry
     * @return List of NewsDTO
     */
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
