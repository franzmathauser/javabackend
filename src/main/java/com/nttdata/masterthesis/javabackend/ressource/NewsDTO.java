/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.ressource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.nttdata.masterthesis.javabackend.helper.CustomJsonDateSerializer;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class NewsDTO {
    
    private String message; 
    private Date date;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonSerialize(using=CustomJsonDateSerializer.class)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
}
