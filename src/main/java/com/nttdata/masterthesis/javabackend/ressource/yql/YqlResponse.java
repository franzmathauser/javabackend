/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.ressource.yql;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Pojo for Yql-Json-Response.
 *
 * @author MATHAF
 */
@XmlRootElement()
@XmlAccessorType( XmlAccessType.FIELD )
@JsonIgnoreProperties( ignoreUnknown = false )
@JsonAutoDetect
public class YqlResponse
{
    private List<YqlResponseItem> item = new ArrayList<YqlResponseItem>();

    public List<YqlResponseItem> getItem()
    {
        return item;
    }

    public void setItem( List<YqlResponseItem> item )
    {
        this.item = item;
    }
}
