/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.exceptions;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author MATHAF
 */
@XmlRootElement
public class ErrorMessage
{

    private String message;

    ErrorMessage( String message )
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }
}
