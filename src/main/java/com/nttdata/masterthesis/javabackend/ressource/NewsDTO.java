/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateTimeSerializer;

/**
 * Resource for news content.
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class NewsDTO implements Comparable<NewsDTO>
{
    private String message;

    private Date date;

    private String image;

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    @JsonSerialize( using = CustomJsonDateTimeSerializer.class )
    public Date getDate()
    {
        return (Date) date.clone();
    }

    public void setDate( Date date )
    {
        this.date = (Date) date.clone();
    }

    public String getImage()
    {
        return image;
    }

    public void setImage( String image )
    {
        this.image = image;
    }

    @Override
    public int compareTo( NewsDTO o )
    {
        long diff = o.getDate().getTime() - this.getDate().getTime();

        if ( diff > 0 )
        {
            return 1;
        }
        else if ( diff < 0 )
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + ( this.message != null ? this.message.hashCode() : 0 );
        hash = 71 * hash + ( this.date != null ? this.date.hashCode() : 0 );
        hash = 71 * hash + ( this.image != null ? this.image.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        final NewsDTO other = (NewsDTO) obj;
        if ( ( this.message == null ) ? ( other.message != null ) : !this.message.equals( other.message ) )
        {
            return false;
        }
        if ( this.date != other.date && ( this.date == null || !this.date.equals( other.date ) ) )
        {
            return false;
        }
        if ( ( this.image == null ) ? ( other.image != null ) : !this.image.equals( other.image ) )
        {
            return false;
        }
        return true;
    }
}
