/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.Transaction;

/**
 * Data Access Object to receive transactions informations.
 * @author MATHAF
 */
@Stateless
public class TransactionDAO
{
    @PersistenceContext
    private EntityManager em;

    /**
     * Get full list of transactions.
     * @return List of Transaction entities.
     */
    public List<Transaction> findAll()
    {
        TypedQuery<Transaction> query = em.createQuery( "SELECT trx FROM Transaction", Transaction.class );
        return query.getResultList();
    }

    /**
     * Save Transaction entity.
     * @param transaction Transaction entity.
     */
    public void save( Transaction transaction )
    {
        em.persist( transaction );
    }

    /**
     * Update Transaction entity.
     * @param transaction Transaction entity.
     */
    public void update( Transaction transaction )
    {
        em.merge( transaction );
    }

    /**
     * Remove Transaction entity by identifier.
     * @param id transaction id
     */
    public void remove( Long id )
    {
        Transaction transaction = find( id );
        if ( transaction != null )
        {
            em.remove( transaction );
        }
    }

    /**
     * Remove Transaction entity.
     * @param transaction Transaction entity.
     */
    public void remove( Transaction transaction )
    {
        if ( transaction != null && em.contains( transaction ) )
        {
            em.remove( transaction );
        }
    }

    /**
     * Find Transaction entity by identifier.
     * @param id transaction id
     * @return Transaction entity if found, else null
     */
    public Transaction find( Long id )
    {
        return em.find( Transaction.class, id );
    }

    /**
     * Removes Transactions-Context from Transaction entity.
     * Changes to the object won't effect the database values.
     * @param transaction Transaction entity
     */
    public void detach( Transaction transaction )
    {
        em.detach( transaction );
    }

    /**
     * Get a List of Transactions for BankAccount entity.
     * @param bankAccount BankAccount entity.
     * @return List of Transaction entities if found else null;
     */
    public List<Transaction> findByBankAccount( BankAccount bankAccount )
    {
        Query query = em.createQuery( "SELECT t FROM Transaction t WHERE t.bankAccount = :bankAccount" );
        query.setParameter( "bankAccount", bankAccount );
        return (List<Transaction>) query.getResultList();
    }
}
