/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;

import com.nttdata.masterthesis.javabackend.ressource.BankTransferDTO;
import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.TransactionManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;

/**
 * REST-Service for banktransfer domain. Available Actions: POST
 * @author MATHAF
 */
@Stateless
@Path( "secure/bankaccount/{bankAccountId}/banktransfer" )
@Interceptors( ServicesLoggingInterceptor.class )
public class BankTransferService
{
    @Context
    private HttpServletRequest request;

    @EJB
    private TransactionManager transactionMgr;

    /**
     * Perform a BankTransfer from Client.
     *
     * @param bankAccountId id of account number
     * @param bankTransfer bankTransfer rest object
     * @return Envelope with metadata and data of methodcall.
     * @throws ForbiddenException user tries to access an account of another user
     */
    @POST
    public ResponseEnvelope doBankTransfer(
    @PathParam( "bankAccountId" ) Long bankAccountId,
    BankTransferDTO bankTransfer ) throws ForbiddenException
    {
        ResponseEnvelope response = new ResponseEnvelope();
        String user = request.getRemoteUser();
        try
        {
            transactionMgr.doBankTransfer( user, bankAccountId, bankTransfer );
            response.setSuccess( true );

            return response;
        }
        catch ( ForbiddenException ex )
        {
            throw new ForbiddenException( "Access is restricted." );
        }
    }
}
