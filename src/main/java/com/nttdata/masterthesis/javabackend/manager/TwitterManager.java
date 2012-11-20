/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Twitter Manager controlls the access to short message service Twitter.
 * @author MATHAF
 */
@Singleton
@LocalBean
public class TwitterManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( TwitterManager.class );
    /**
     * Url-path to twitter icon.
     */
    public static final String TWITTER_ICON;
    private static final String ACCESS_TOKEN;
    private static final String ACCESS_TOKEN_SECRET;
    private static final String CUSTOMER_KEY;
    private static final String CUSTOMER_KEY_SECRET;
    private static final String APPLICATION_ROOT_URL;

    static
    {
        ACCESS_TOKEN = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.TWITTER_ACCESS_TOKEN );
        ACCESS_TOKEN_SECRET = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.TWITTER_ACCESS_TOKEN_SECURE );
        CUSTOMER_KEY = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.TWITTER_CUSTOMER_KEY );
        CUSTOMER_KEY_SECRET = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.TWITTER_CUSTOMER_KEY_SECURE );
        APPLICATION_ROOT_URL = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.APPLICATION_ROOT_URL );

        TWITTER_ICON = APPLICATION_ROOT_URL + "icons/newschannel/twitter.png";
    }
    private final Twitter twitter;

    /**
     * Default Constructor.
     * Loads Twitter-API-KEYs from config-file and instantiates
     * a Twitter Service Object.
     */
    public TwitterManager()
    {

        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled( true )
        .setOAuthConsumerKey( CUSTOMER_KEY )
        .setOAuthConsumerSecret( CUSTOMER_KEY_SECRET )
        .setOAuthAccessToken( ACCESS_TOKEN )
        .setOAuthAccessTokenSecret( ACCESS_TOKEN_SECRET );

        TwitterFactory tf = new TwitterFactory( cb.build() );
        twitter = tf.getInstance();

        try
        {
            twitter.verifyCredentials();
        }
        catch ( TwitterException ex )
        {
            LOG.error( "could not load home-timeline of twitter" );
        }


    }

    /**
     * List of Tweet-Feeds of configured Twitter-user.
     * @return a list of twitter feeds or an empty list in case of no result.
     */
    public List<NewsDTO> getNews()
    {

        List<NewsDTO> newsList = new ArrayList<NewsDTO>();

        try
        {
            ResponseList<Status> responseList = twitter.getUserTimeline();

            for ( Status status : responseList )
            {
                NewsDTO news = new NewsDTO();
                news.setDate( status.getCreatedAt() );
                news.setMessage( status.getText() );
                news.setImage( TWITTER_ICON );
                newsList.add( news );
            }

        }
        catch ( TwitterException ex )
        {
            LOG.error( "twitter error durring usertimeline request", ex );
        }

        return newsList;
    }
}
