package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.entities.Category;
import com.nttdata.masterthesis.javabackend.ressource.TransactionDTO;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import com.nttdata.masterthesis.javabackend.manager.exceptions.ForbiddenException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author MATHAF
 */
@Stateless
@LocalBean
public class TransactionManager {
      
    @EJB
    PayPalManager payPalMgr; 
    
    @EJB
    DbTransactionManager dbTransactionMgr;
    
    public List<TransactionDTO> getTransactionList(String userName, Long bankAccountId) throws ForbiddenException{
             
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        
        List<Transaction> dbTransactions = dbTransactionMgr.getTransactionList(userName, bankAccountId);
        
        
        transactionList.addAll(convertTransactionListToTransactionDTOList(dbTransactions));
        //transactionList.addAll( payPalMgr.getTransactions() );
        
        return transactionList;
    }
    
    private List<TransactionDTO> convertTransactionListToTransactionDTOList(List<Transaction> transactions){
        
        List<TransactionDTO> transactionList = new ArrayList<TransactionDTO>();
        
        for(Transaction transaction : transactions){
            transactionList.add(convertTransactionToTransactionDTO(transaction));
        }
        return transactionList;
                
    }

    private TransactionDTO convertTransactionToTransactionDTO(Transaction transaction) {
        TransactionDTO transactionDto = new TransactionDTO();
        
        transactionDto.setId(Long.toString(transaction.getId()));
        transactionDto.setAccount(transaction.getAccount());
        transactionDto.setAmount(transaction.getAmount());
        transactionDto.setBankCode(transaction.getBankCode());
        
        transactionDto.setRevenueType(transaction.getRevenueType());
        transactionDto.setValueDate(transaction.getValueDate());
        transactionDto.setBillingDate(transaction.getBillingDate());
        
        Category category = transaction.getCategory();
        if(category != null){
            transactionDto.setCategory(category.getName());
        }
        
        
        return transactionDto;
        
    }
}
