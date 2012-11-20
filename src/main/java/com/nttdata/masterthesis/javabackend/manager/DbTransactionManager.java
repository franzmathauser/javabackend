/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.dao.BankAccountDAO;
import com.nttdata.masterthesis.javabackend.dao.TransactionDAO;
import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;

/**
 * Db Transaction Manager controlls the access to Transactions from database.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class DbTransactionManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( DbTransactionManager.class );

    @EJB
    private TransactionDAO transactionDAO;
    @EJB
    private UserDAO userDAO;
    @EJB
    private BankAccountDAO bankAccountDAO;
    @EJB
    private AccessManager accessManager;

    /**
     * List of Transaction Entites.
     * @param userName login-username
     * @param bankAccountId bankaccount identifier
     * @return Lis of Transaction Entites
     * @throws ForbiddenException user tries to access an account of another user
     */
    public List<Transaction> getTransactionList( String userName,
                                                 Long bankAccountId ) throws ForbiddenException
    {
        List<Transaction> transactions = new ArrayList<Transaction>();

        if ( userName == null )
        {
            throw new IllegalArgumentException( "user is null" );
        }

        User user = userDAO.findByName( userName );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );
        accessManager.isAllowed( user, bankAccount );

        transactions.addAll( getTransactionList( bankAccount ) );

        return transactions;
    }

    /**
     * List of Transaction Entities.
     * @param bankAccount backaccount entity
     * @return List of Transaction Entities
     */
    private List<Transaction> getTransactionList( BankAccount bankAccount )
    {
        return transactionDAO.findByBankAccount( bankAccount );
    }
}
