/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.TransactionManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;

/**
 * REST-Service for transaction domain. Available Actions: GET
 * @author MATHAF
 */
@Stateless
@Path( "secure/bankaccount/{bankAccountId}/transactions" )
@Interceptors( ServicesLoggingInterceptor.class )
public class TransactionService
{
    @Context
    private HttpServletRequest request;
    @EJB
    private TransactionManager transactionMgr;

    /**
     * List of all transactions.
     * @param bankAccountId id of account number
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @GET
    public ResponseEnvelope getUserTransactions(
    @PathParam( "bankAccountId" ) Long bankAccountId ) throws ForbiddenException
    {

        System.out.println( request.getSession().getId() );

        ResponseEnvelope response = new ResponseEnvelope();
        List<TransactionDTO> transactions = null;

        String user = request.getRemoteUser();
        try
        {
            transactions = transactionMgr.getTransactionList( user, bankAccountId );
            response.setSuccess( true );
            response.setBodyData( transactions );

            return response;
        } catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }
}
