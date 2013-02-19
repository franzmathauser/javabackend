/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource.yql;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pojo for Yql-Json-Response query.
 * @author MATHAF
 */
@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = false )
@JsonAutoDetect
public class YqlQuery
{
    private String count;

    private Date created;

    private YqlResponse results;

    public String getCount()
    {
        return count;
    }

    public void setCount( String count )
    {
        this.count = count;
    }

    public Date getCreated()
    {
        return (Date) created.clone();
    }

    public void setCreated( Date created )
    {
        this.created = (Date) created.clone();
    }

    public YqlResponse getResults()
    {
        return results;
    }

    public void setResults( YqlResponse results )
    {
        this.results = results;
    }
}
