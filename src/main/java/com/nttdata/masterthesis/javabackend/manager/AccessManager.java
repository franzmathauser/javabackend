/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;

/**
 * Access Manager controlls the access to restricted Resources.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class AccessManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( AccessManager.class );

    /**
     * Checks if a user is allowed to access a bankaccount.
     * @param user User Entity
     * @param bankAccount Bankaccount Entity
     * @return true, if user can access ressource, else false
     * @throws ForbiddenException user tries to access an account of another user
     */
    public boolean isAllowed( User user, BankAccount bankAccount ) throws ForbiddenException
    {

        if ( !user.getBankAccount().equals( bankAccount ) )
        {
            String accountNumber = ( bankAccount == null ) ? null : bankAccount.getAccountNumber();

            if ( LOG.isErrorEnabled() )
            {
                LOG.error( "access restricted for user: {} to access bankaccount: {}", user.getUserName(), accountNumber );
            }
            throw new ForbiddenException( "access restricted" );
        }
        return true;
    }
}
