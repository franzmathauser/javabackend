/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource.yql;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pojo for Yql-Json-Response wrapper.
 *
 * @author MATHAF
 */
@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = false )
@JsonAutoDetect
public class YqlResponseWrapper
{
    private YqlQuery query;

    public YqlQuery getQuery()
    {
        return query;
    }

    public void setQuery( YqlQuery query )
    {
        this.query = query;
    }
}
