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
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nttdata.masterthesis.javabackend.dao.BankAccountDAO;
import com.nttdata.masterthesis.javabackend.dao.CategoryDAO;
import com.nttdata.masterthesis.javabackend.dao.TransactionDAO;
import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import com.nttdata.masterthesis.javabackend.entities.User;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.BankTransferDTO;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;

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

    @EJB
    private CategoryDAO categoryDAO;

    /**
     * List of Transaction Entites.
     * @param userName login-username
     * @param bankAccountId bankaccount identifier
     * @return List of Transaction DTOs
     * @throws ForbiddenException user tries to access an account of another user
     */
    public List<TransactionDTO> getTransactionList( String userName,
                                                    Long bankAccountId ) throws ForbiddenException
    {
        List<TransactionDTO> transactions = new ArrayList<TransactionDTO>();

        if ( userName == null )
        {
            throw new IllegalArgumentException( "user is null" );
        }

        User user = userDAO.findByName( userName );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );
        accessManager.isAllowed( user, bankAccount );
        List<Transaction> transactionEntityList = getTransactionList( bankAccount );

        transactions.addAll( convertTransactionListToTransactionDTOList( transactionEntityList ) );

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

    /**
     * Updates the category of a transaction.
     * @param userName session username
     * @param transactionDTO transaction transfer object
     * @return transaction transfer object
     * @throws ForbiddenException user tries to access an account of another user
     */
    public TransactionDTO updateTransactionCategory( String userName,
                                                     TransactionDTO transactionDTO ) throws ForbiddenException
    {
        try
        {
            long transactionId = Long.parseLong( transactionDTO.getId() );
            Transaction transaction = transactionDAO.find( transactionId );
            User user = userDAO.findByName( userName );

            if ( transaction != null )
            {
                accessManager.isAllowed( user, transaction.getBankAccount() );
                // TODO check if category id is also from user
                Category category = categoryDAO.find( transactionDTO.getCategoryId() );
                transaction.setCategory( category );
                transactionDAO.save( transaction );

                return transactionDTO;
            }
        }
        catch ( NumberFormatException ex )
        {
            LOG.error( "transaction might be a paypal transaction", ex );
        }
        return null;
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
        transactionDto.setBillingDateMillis( transaction.getBillingDate().getTime() );

        Category category = transaction.getCategory();
        if ( category != null )
        {
            transactionDto.setCategory( category.getName() );
            transactionDto.setCategoryId( category.getId() );
        }

        return transactionDto;

    }

    /**
     * Perform a transaction.
     * @param userName session username
     * @param bankAccountId bank account identifier
     * @param bankTransfer bank transfer dto
     * @throws ForbiddenException user tries to access an account of another user
     */
    public void doBankTransfer( String userName, Long bankAccountId,
                                BankTransferDTO bankTransfer ) throws ForbiddenException
    {

        User user = userDAO.findByName( userName );
        BankAccount bankAccount = bankAccountDAO.find( bankAccountId );
        accessManager.isAllowed( user, bankAccount );

        Transaction transaction = new Transaction();

        StringBuilder purpose = new StringBuilder();
        purpose.append( bankTransfer.getPurpose1() );
        purpose.append( bankTransfer.getPurpose2() );
        purpose.append( bankTransfer.getPurpose3() );
        purpose.append( bankTransfer.getPurpose4() );
        purpose.append( bankTransfer.getPurpose5() );

        float amount = Math.abs( Float.parseFloat( bankTransfer.getAmount() ) );


        transaction.setBankAccount( bankAccount );
        transaction.setAmount( -1 * amount );
        transaction.setName( bankTransfer.getName() );
        transaction.setAccount( bankTransfer.getAccountNumber() );
        transaction.setBankCode( bankTransfer.getBankCode() );
        transaction.setBillingDate( new Date() );
        transaction.setValueDate( new Date() );
        transaction.setPurpose( purpose.toString() );

        transactionDAO.save( transaction );

    }
}
