/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource.yql;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * Pojo for Yql-Json-Response element.
 *
 * @author MATHAF
 */
@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = false )
@JsonAutoDetect
public class YqlResponseItem
{
    private String title;

    private String description;

    private String link;

    private String pubDate;

    /**
     * Datetime format for YQL-REST-Response.
     * Mon, 11 Feb 2013 23:44:42 -0800
     */
    public static final String YQL_DATETIME_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    private final SimpleDateFormat dateFormat = new SimpleDateFormat( YQL_DATETIME_FORMAT, Locale.ENGLISH );

    /**
     * Logger object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( YqlResponseItem.class );

    public String getTitle()
    {
        return title;
    }

    public void setTitle( String title )
    {
        this.title = title;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription( String description )
    {
        this.description = description;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink( String link )
    {
        this.link = link;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate( String pubDate )
    {
        this.pubDate = pubDate;
    }

    /**
     * Get date as date-object instead of string-value.
     * @return publication date
     */
    public Date getPubDateAsDate()
    {

        try
        {
            return dateFormat.parse( pubDate );
        }
        catch ( ParseException ex )
        {
            LOG.error( "error parsing date format", ex );
        }

        return null;
    }
}
