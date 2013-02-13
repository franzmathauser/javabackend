/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Resource for category content.
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = true )
public class CategoryDTO implements CategoryContainer
{
    private long id;

    private String name;

    private String iconUrl;

    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getIconUrl()
    {
        return iconUrl;
    }

    public void setIconUrl( String iconUrl )
    {
        this.iconUrl = iconUrl;
    }

    @Override
    public String getCategoryName()
    {
        return getName();
    }

    @Override
    public String getCategoryIconUrl()
    {
        return getIconUrl();
    }

    @Override
    public void setCategoryIconUrl( String urlPath )
    {
        setIconUrl( urlPath );
    }
}
