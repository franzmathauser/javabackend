/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
package com.nttdata.masterthesis.javabackend.services.rest;

import java.util.Collections;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.TransactionManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;

/**
 * REST-Service for transaction domain. Available Actions: GET, POST
 * @author MATHAF
 */
@Stateless
@Path( "secure/bankaccount/{bankAccountId}/transactions" )
@Interceptors( ServicesLoggingInterceptor.class )
public class BankAccountService
{
    @Context
    private HttpServletRequest request;

    @EJB
    private TransactionManager transactionMgr;

    /**
     * List of all transactions to a bankaccount.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @GET
    public ResponseEnvelope getBankAccountTransactions(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        ResponseEnvelope response = new ResponseEnvelope();
        List<TransactionDTO> transactions;

        String user = request.getRemoteUser();
        try
        {
            transactions = transactionMgr.getTransactionList( user, bankAccountId );
            Collections.sort( transactions );
            response.setSuccess( true );
            response.setBodyData( transactions );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }

    /**
     * Update a transaction from Client.
     * The only possible value for a update is the category-id.
     * @param bankAccountId id of account number
     * @param transaction transaction rest object
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @POST
    public ResponseEnvelope updateBankAccountTransaction(
    @PathParam( "bankAccountId" ) Long bankAccountId, TransactionDTO transaction ) throws ForbiddenException
    {
        ResponseEnvelope response = new ResponseEnvelope();
        TransactionDTO updTransaction;

        String user = request.getRemoteUser();
        try
        {
            updTransaction = transactionMgr.updateTransactionCategory( user, transaction );
            response.setSuccess( true );
            response.setBodyData( updTransaction );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }
}
