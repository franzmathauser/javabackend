package com.nttdata.masterthesis.javabackend.manager;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
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
        List<Transaction> dbTransactions = dbTransactionMgr.getTransactionList( userName, bankAccountId );

        transactionList.addAll( convertTransactionListToTransactionDTOList( dbTransactions ) );
        transactionList.addAll( payPalMgr.getTransactions() );

        return transactionList;
    }

    /**
     * Convert a List of Transaction Entites to a REST DTO.
     * @param transactions List of Transaction Entities
     * @return List of Transaction DTOs
     */
    private List<TransactionDTO> convertTransactionListToTransactionDTOList(
    List<Transaction> transactions )
    {

        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();

        for ( Transaction transaction : transactions )
        {
            transactionList.add( convertTransactionToTransactionDTO( transaction ) );
        }
        return transactionList;

    }

    /**
     * Convert a Transaction Entity into a REST DTO.
     * @param transaction Transaction Entity
     * @return Transaction DTO
     */
    private TransactionDTO convertTransactionToTransactionDTO(
    Transaction transaction )
    {
        TransactionDTO transactionDto = new TransactionDTO();

        transactionDto.setId( Long.toString( transaction.getId() ) );
        transactionDto.setName( transaction.getName() );
        transactionDto.setPurpose( transaction.getPurpose() );
        transactionDto.setAccount( transaction.getAccount() );
        transactionDto.setAmount( transaction.getAmount() );
        transactionDto.setBankCode( transaction.getBankCode() );

        transactionDto.setRevenueType( transaction.getRevenueType() );
        transactionDto.setValueDate( transaction.getValueDate() );
        transactionDto.setBillingDate( transaction.getBillingDate() );

        Category category = transaction.getCategory();
        if ( category != null )
        {
            transactionDto.setCategory( category.getName() );
        }

        return transactionDto;

    }
}
