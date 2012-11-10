/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import com.nttdata.masterthesis.javabackend.interceptor.ServicesLoggingInterceptor;
import com.nttdata.masterthesis.javabackend.manager.TransactionManager;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import com.nttdata.masterthesis.javabackend.ressource.ResponseEnvelope;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 *
 * @author MATHAF
 */
@Stateless
@Path("secure/bankaccount/{bankAccountId}/transactions")
@Interceptors( ServicesLoggingInterceptor.class )
public class TransactionService {
    
    @Context
    HttpServletRequest request;
    
    @EJB
    TransactionManager transactionMgr;
    
    //@EJB
    //TransactionDAO transactionDAO;
    
    @GET
    public Response getUserTransactions(@PathParam("bankAccountId") Long bankAccountId) throws ForbiddenException{
        
        //System.out.println(request.getUserPrincipal().getName());
        System.out.println(request.getSession().getId());
        
        ResponseEnvelope response = new ResponseEnvelope();
        List<TransactionDTO> transactions = null;

        String user = request.getRemoteUser();
        //try {
            transactions = transactionMgr.getTransactionList(user, bankAccountId);
            //removeCascading(transactions);
            response.setSuccess(true);
            response.setData(transactions); 
        
        return Response.ok().entity(response).build();
        //}
        //catch(ForbiddenException ex){
        //    throw new com.nttdata.masterthesis.javabackend.services.exceptions.ForbiddenException("Access is restricted.");
        //}
       
        

    }
    
    //private void removeCascading(List<Transaction> transactions){
    //    for(Transaction trx : transactions){
    //        transactionDAO.detach(trx); 
    //        trx.setBankAccount(null);
    //    }
    //}
    
}
