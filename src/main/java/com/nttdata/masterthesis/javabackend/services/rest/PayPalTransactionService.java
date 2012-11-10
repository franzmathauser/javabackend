/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.services.rest;

import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import com.nttdata.masterthesis.javabackend.manager.PayPalManager;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;


/**
 *
 * @author MATHAF
 */
@Stateless
@Path("/secure/paypal")
//@Interceptors( ServicesLoggingInterceptor.class )
public class PayPalTransactionService {
    
    @EJB
    PayPalManager payPalManager;
    
    @Context
    ServletContext context;
    
    @GET
    @Produces("application/json")
    public List<TransactionDTO> getTransactions() {
        return payPalManager.getTransactions();
    }
    
    /*
    @GET
    @Produces("application/json")
    public String getHtml(@QueryParam("transactions") String location) {
        return placesMgr.getFinancePlaces(location);
    }
    */

}
