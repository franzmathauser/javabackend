/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource for news content.
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
@JsonInclude(Include.NON_NULL)
public class StatisticDTO
{

    private String id;

    @JsonProperty("name")
    private String xValue;

    @JsonProperty("value1")
    private float y1Value;

    @JsonProperty("value2")
    private float y2Value;

    @JsonProperty("value3")
    private float y3Value;

    @JsonProperty("value4")
    private float y4Value;

    @JsonProperty("value5")
    private float y5Value;

    @JsonProperty("value6")
    private float y6Value;

    @JsonProperty("value7")
    private float y7Value;

    @JsonProperty("value8")
    private float y8Value;

    @JsonProperty("value9")
    private float y9Value;

    @JsonProperty("value10")
    private float y10Value;

    public String getId()
    {
        return id;
    }

    public void setId( String id )
    {
        this.id = id;
    }

    public String getxValue()
    {
        return xValue;
    }

    public void setxValue( String xValue )
    {
        this.xValue = xValue;
    }

    public float getY1Value()
    {
        return y1Value;
    }

    public void setY1Value( float y1Value )
    {
        this.y1Value = y1Value;
    }

    public float getY2Value()
    {
        return y2Value;
    }

    public void setY2Value( float y2Value )
    {
        this.y2Value = y2Value;
    }

    public float getY3Value()
    {
        return y3Value;
    }

    public void setY3Value( float y3Value )
    {
        this.y3Value = y3Value;
    }

    public float getY4Value()
    {
        return y4Value;
    }

    public void setY4Value( float y4Value )
    {
        this.y4Value = y4Value;
    }

    public float getY5Value()
    {
        return y5Value;
    }

    public void setY5Value( float y5Value )
    {
        this.y5Value = y5Value;
    }

    public float getY6Value()
    {
        return y6Value;
    }

    public void setY6Value( float y6Value )
    {
        this.y6Value = y6Value;
    }

    public float getY7Value()
    {
        return y7Value;
    }

    public void setY7Value( float y7Value )
    {
        this.y7Value = y7Value;
    }

    public float getY8Value()
    {
        return y8Value;
    }

    public void setY8Value( float y8Value )
    {
        this.y8Value = y8Value;
    }

    public float getY9Value()
    {
        return y9Value;
    }

    public void setY9Value( float y9Value )
    {
        this.y9Value = y9Value;
    }

    public float getY10Value()
    {
        return y10Value;
    }

    public void setY10Value( float y10Value )
    {
        this.y10Value = y10Value;
    }
}
