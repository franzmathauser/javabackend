/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import org.glassfish.grizzly.http.util.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.config.ConfigurationConstants;
import com.nttdata.masterthesis.javabackend.config.ConfigurationSingleton;
import com.nttdata.masterthesis.javabackend.ressource.NewsDTO;
import com.nttdata.masterthesis.javabackend.ressource.yql.YqlResponseItem;
import com.nttdata.masterthesis.javabackend.ressource.yql.YqlResponseWrapper;
import com.nttdata.masterthesis.javabackend.services.exceptions.GatewayTimeoutException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * YQL Manager controls the access to Yahoos Query Language.
 * @author MATHAF
 */
@Singleton
@LocalBean
public class YqlManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( YqlManager.class );

    /**
     * Base Url to Googles REST Service.
     */
    public static final String YQL_URI;

    /**
     * YQL data query.
     */
    public static final String YQL_QUERY;

    /**
     * yql icon url.
     */
    public static final String YQL_ICON;

    /**
     * base application url.
     */
    public static final String APPLICATION_ROOT_URL;

    static
    {
        YQL_URI = "http://query.yahooapis.com/v1/public/yql?format=json&q=";
        YQL_QUERY = "select * from feed where url='http://de.news.search.yahoo.com/rss?ei=UTF-8&p=finance'";

        APPLICATION_ROOT_URL = ConfigurationSingleton.getInstance().getString( ConfigurationConstants.APPLICATION_ROOT_URL );
        YQL_ICON = APPLICATION_ROOT_URL + "icons/newschannel/yahoo.png";


    }

    /**
     * List of Finance-RSS-Feeds from yahoo.
     * @return a list of finance news or an empty list in case of no result.
     */
    public List<NewsDTO> getFinanceFeed()
    {

        Client client = Client.create();

        StringBuilder url = new StringBuilder( YQL_URI );

        try
        {
            String urlEncoded = URLEncoder.encode( YQL_QUERY, "UTF-8" );
            url.append( urlEncoded );
        }
        catch ( UnsupportedEncodingException ex )
        {
            LOG.error( "encoding expection on utf-8", ex );
        }
        LOG.debug( url.toString() );
        WebResource webResource = client.resource( url.toString() );
        ClientResponse response = webResource.accept( "application/json" ).get( ClientResponse.class );

        if ( response.getStatus() != HttpStatus.OK_200.getStatusCode() )
        {
            throw new GatewayTimeoutException( "Failed : HTTP error code : "
            + response.getStatus() );
        }

        YqlResponseWrapper yqlResponse = response.getEntity( YqlResponseWrapper.class );

        return convertYqlToNewsDTO( yqlResponse );


    }

    private List<NewsDTO> convertYqlToNewsDTO( YqlResponseWrapper yql )
    {

        List<NewsDTO> newsList = new ArrayList<NewsDTO>();

        for ( YqlResponseItem yqlResponse : yql.getQuery().getResults().getItem() )
        {
            NewsDTO item = new NewsDTO();
            item.setMessage( yqlResponse.getTitle() );
            item.setDate( yqlResponse.getPubDateAsDate() );
            item.setImage( YQL_ICON );
            newsList.add( item );
        }

        return newsList;
    }
}
