/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class AccessManager
{

    static final Logger LOG = LoggerFactory.getLogger( AccessManager.class );

    public boolean isAllowed( User user, BankAccount bankAccount ) throws ForbiddenException
    {

        if ( !user.getBankAccount().equals( bankAccount ) )
        {
            String accountNumber = ( bankAccount == null ) ? null : bankAccount.getAccountNumber();

            LOG.error( "access restricted for user: {} to access bankaccount: {}", user.getUserName(), accountNumber );
            throw new ForbiddenException( "access restricted" );
        }
        return true;
    }
}
