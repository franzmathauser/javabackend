/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.dao.MappedCategoryDAO;
import com.nttdata.masterthesis.javabackend.entities.MappedCategory;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import com.nttdata.masterthesis.javabackend.services.rest.PayPalTransactionService;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.xml.parsers.ParserConfigurationException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.xml.sax.SAXException;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.TransactionSearchReq;
import urn.ebay.api.PayPalAPI.TransactionSearchRequestType;
import urn.ebay.api.PayPalAPI.TransactionSearchResponseType;
import urn.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class PayPalManager {
    
    @EJB
    MappedCategoryDAO mappedCategoryDao;
    
    public List<TransactionDTO> getTransactions(){
        
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        
        String fileName = "paypal-sdk-config.properties";
        Class c1 = this.getClass();
        ClassLoader loader = c1.getClassLoader();
        
        PayPalAPIInterfaceServiceService service;
        List<PaymentTransactionSearchResultType> paymentTransactions = null;
        try {

            InputStream is = this.getClass().getClassLoader().getResourceAsStream(fileName);
            service = new PayPalAPIInterfaceServiceService(is); 
            TransactionSearchReq txSearchReq = new TransactionSearchReq();
            TransactionSearchRequestType txReqType = new TransactionSearchRequestType();

            DateTime dt = new DateTime();
            dt = dt.minusDays(100);
            
            txReqType.setStartDate(dt.toString());

            txSearchReq.setTransactionSearchRequest(txReqType);

            TransactionSearchResponseType txSearchRespType = null;
            txSearchRespType = service.transactionSearch(txSearchReq);

            paymentTransactions = txSearchRespType.getPaymentTransactions();
            
            for(PaymentTransactionSearchResultType transaction : paymentTransactions){
                System.out.println(transaction.getPayer()); 
                
                TransactionDTO transactionDto = new TransactionDTO();
                
                transactionDto.setId(transaction.getTransactionID());
                transactionDto.setAmount(Float.valueOf(transaction.getGrossAmount().getValue()));
                
                MappedCategory mappedCategory = mappedCategoryDao.findByMappedId(transaction.getTransactionID());
                if(mappedCategory != null){
                    transactionDto.setCategory(mappedCategory.getCategory().getName());
                }
                
                
                
                
                DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
                dt = fmt.parseDateTime(transaction.getTimestamp());
                transactionDto.setBillingDate(dt.toDate());
                transactionDto.setValueDate(dt.toDate());
                transactionDto.setPurpose(transaction.getStatus());
                transactionDto.setAccount(transaction.getPayer());
                
                transactionList.add(transactionDto);
            }
            
            
        } catch (OAuthException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SSLConfigurationException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidCredentialException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HttpErrorException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidResponseDataException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClientActionRequiredException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingCredentialException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(PayPalTransactionService.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return transactionList;
        
    }

}
