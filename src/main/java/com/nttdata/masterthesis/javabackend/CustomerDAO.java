/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author MATHAF
 */
@Stateless
public class CustomerDAO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    @PersistenceContext(unitName="jdbc/backendDS")
    private EntityManager em;
    
    public BankAccount getCustomerById(Long id) {
        return em.find(BankAccount.class, id);
    }

}
