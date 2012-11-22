/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Resource for news content.
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD )
public class StatisticDTO
{
    @JsonProperty("name")
    private String xValue;

    @JsonProperty("value1")
    private float y1Value;

    private String y2Value;

    private String y3Value;

    private String y4Value;

    private String y5Value;

    private String y6Value;

    private String y7Value;

    private String y8Value;

    private String y9Value;

    private String y10Value;

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

    public String getY2Value()
    {
        return y2Value;
    }

    public void setY2Value( String y2Value )
    {
        this.y2Value = y2Value;
    }

    public String getY3Value()
    {
        return y3Value;
    }

    public void setY3Value( String y3Value )
    {
        this.y3Value = y3Value;
    }

    public String getY4Value()
    {
        return y4Value;
    }

    public void setY4Value( String y4Value )
    {
        this.y4Value = y4Value;
    }

    public String getY5Value()
    {
        return y5Value;
    }

    public void setY5Value( String y5Value )
    {
        this.y5Value = y5Value;
    }

    public String getY6Value()
    {
        return y6Value;
    }

    public void setY6Value( String y6Value )
    {
        this.y6Value = y6Value;
    }

    public String getY7Value()
    {
        return y7Value;
    }

    public void setY7Value( String y7Value )
    {
        this.y7Value = y7Value;
    }

    public String getY8Value()
    {
        return y8Value;
    }

    public void setY8Value( String y8Value )
    {
        this.y8Value = y8Value;
    }

    public String getY9Value()
    {
        return y9Value;
    }

    public void setY9Value( String y9Value )
    {
        this.y9Value = y9Value;
    }

    public String getY10Value()
    {
        return y10Value;
    }

    public void setY10Value( String y10Value )
    {
        this.y10Value = y10Value;
    }
}
