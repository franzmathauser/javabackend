/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import org.slf4j.LoggerFactory;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author MATHAF
 */
@Singleton
@LocalBean
public class TwitterManager {
    
    static final org.slf4j.Logger LOG = LoggerFactory.getLogger(TwitterManager.class);
    
    private final String ACCESS_TOKEN = ConfigurationSingleton.getInstance().getString(ConfigurationConstants.TWITTER_ACCESS_TOKEN);
    private final String ACCESS_TOKEN_SECRET = ConfigurationSingleton.getInstance().getString(ConfigurationConstants.TWITTER_ACCESS_TOKEN_SECURE);
    private final String CUSTOMER_KEY = ConfigurationSingleton.getInstance().getString(ConfigurationConstants.TWITTER_CUSTOMER_KEY);
    private final String CUSTOMER_KEY_SECRET = ConfigurationSingleton.getInstance().getString(ConfigurationConstants.TWITTER_CUSTOMER_KEY_SECURE);
    
    private final Twitter twitter; 
    
    public TwitterManager() {
        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
          .setOAuthConsumerKey(CUSTOMER_KEY)
          .setOAuthConsumerSecret(CUSTOMER_KEY_SECRET)
          .setOAuthAccessToken(ACCESS_TOKEN)
          .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);
        
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
               
        try {
            User user = twitter.verifyCredentials();
             
        } catch (TwitterException ex) {
            LOG.error("could not load home-timeline of twitter"); 
        }
        

    }
    public String getTimeLine(){
        return "";
    }
    
    public List<NewsDTO> getNews() {
        
        List<NewsDTO> newsList = new ArrayList<NewsDTO>();
        
        try {
            ResponseList<Status> responseList = twitter.getUserTimeline();
            
            for(Status status : responseList){
                NewsDTO news = new NewsDTO();
                news.setDate(status.getCreatedAt());
                news.setMessage(status.getText());
                newsList.add(news);
            }

        } catch (TwitterException ex) {
            LOG.error("twitter error durring usertimeline request", ex);
        }
                
        return newsList;
    }
    
}
