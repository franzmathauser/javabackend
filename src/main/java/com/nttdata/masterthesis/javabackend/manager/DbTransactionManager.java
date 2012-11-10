/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.manager;

import com.nttdata.masterthesis.javabackend.dao.BankAccountDAO;
import com.nttdata.masterthesis.javabackend.dao.TransactionDAO;
import com.nttdata.masterthesis.javabackend.dao.UserDAO;
import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import com.nttdata.masterthesis.javabackend.entities.User;
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
public class DbTransactionManager {
    
    @EJB
    TransactionDAO transactionDAO;
    
    @EJB
    UserDAO userDAO;
    
    @EJB 
    BankAccountDAO bankAccountDAO;
    
    @EJB
    AccessManager accessManager; 
    
    public List<Transaction> getTransactionList(BankAccount bankAccount){
        return transactionDAO.findByBankAccount(bankAccount);
    }

    public List<Transaction> getTransactionList(String userName, Long bankAccountId) throws ForbiddenException{
            List<Transaction> transactions = new ArrayList<Transaction>();
        
        if(userName == null){
            throw new IllegalArgumentException("user is null");
        }

        User user = userDAO.findByName(userName);
        BankAccount bankAccount = bankAccountDAO.find(bankAccountId); 
        accessManager.isAllowed(user, bankAccount);
        
        transactions.addAll(getTransactionList(bankAccount));
         
        return transactions;
    }

}
