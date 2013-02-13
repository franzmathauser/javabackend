/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.ressource.BankTransferDTO;
import com.nttdata.masterthesis.javabackend.interceptor.CategoryIconInterceptor;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;

/**
 * Transaction Manager controlls the access to database and paypal transactions.
 * @author MATHAF
 */
@Stateless
@LocalBean
public class TransactionManager
{
    /**
     * Logger Object.
     */
    public static final Logger LOG = LoggerFactory.getLogger( TransactionManager.class );

    @EJB
    private PayPalManager payPalMgr;

    @EJB
    private DbTransactionManager dbTransactionMgr;

    /**
     * List of Transactions received from database and paypal account.
     * @param userName login-username
     * @param bankAccountId account identifier
     * @return a list of transactions or an empty list in case of no result.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @Interceptors( CategoryIconInterceptor.class )
    public List<TransactionDTO> getTransactionList( String userName,
                                                    Long bankAccountId ) throws ForbiddenException
    {

        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        List<TransactionDTO> dbTransactions = dbTransactionMgr.getTransactionList( userName, bankAccountId );
        //List<TransactionDTO> paypalTransactions = payPalMgr.getTransactions();

        transactionList.addAll( dbTransactions );
        //transactionList.addAll( paypalTransactions );

        Collections.reverse( transactionList );

        return transactionList;
    }

    /**
     * Updates the category of a transaction.
     * @param user session username
     * @param transaction transaction transfer object
     * @return Transaction DTO
     * @throws ForbiddenException user tries to access an account of another user
     */
    @Interceptors( CategoryIconInterceptor.class )
    public TransactionDTO updateTransactionCategory( String user,
                                                     TransactionDTO transaction ) throws ForbiddenException
    {
        TransactionDTO retTransaction;

        retTransaction = dbTransactionMgr.updateTransactionCategory( user, transaction );
        if ( retTransaction == null )
        {
            retTransaction = payPalMgr.updateTransactionCategory( user, transaction );
        }
        return retTransaction;
    }

    public void doBankTransfer( String user, Long bankAccountId,
                                BankTransferDTO bankTransfer ) throws ForbiddenException
    {
        dbTransactionMgr.doBankTransfer( user, bankAccountId, bankTransfer );
    }
}
