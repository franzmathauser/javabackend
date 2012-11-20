/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager.exceptions;

/**
 * ForbiddenException is thrown if a user wants to access restricted ressources.
 * @author MATHAF
 */
public class ForbiddenException extends Exception
{
    /**
     * Constructor creates forbidden exception object with message.
     * @param message exception message
     */
    public ForbiddenException( String message )
    {
        super( message );
    }
}
