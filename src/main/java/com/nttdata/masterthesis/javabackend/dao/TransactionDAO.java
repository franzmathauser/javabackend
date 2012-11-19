/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author MATHAF
 */
@Stateless
public class TransactionDAO
{

    @PersistenceContext
    private EntityManager em;

    public List<Transaction> findAll()
    {
        TypedQuery<Transaction> query = em.createQuery( "SELECT trx FROM Transaction", Transaction.class );
        return query.getResultList();
    }

    public void save( Transaction transaction )
    {
        em.persist( transaction );
    }

    public void update( Transaction transaction )
    {
        em.merge( transaction );
    }

    public void remove( Long id )
    {
        Transaction transaction = find( id );
        if ( transaction != null )
        {
            em.remove( transaction );
        }
    }

    public void remove( Transaction transaction )
    {
        if ( transaction != null && em.contains( transaction ) )
        {
            em.remove( transaction );
        }
    }

    public Transaction find( Long id )
    {
        return em.find( Transaction.class, id );
    }

    public void detach( Transaction transaction )
    {
        em.detach( transaction );
    }

    public List<Transaction> findByBankAccount( BankAccount bankAccount )
    {
        Query query = em.createQuery( "SELECT t FROM Transaction t WHERE t.bankAccount = :bankAccount" );
        query.setParameter( "bankAccount", bankAccount );
        return ( List<Transaction> ) query.getResultList();
    }
}
