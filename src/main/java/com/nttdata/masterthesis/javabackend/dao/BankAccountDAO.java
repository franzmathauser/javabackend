/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;

/**
 * Data Access Object to receive bank account informations.
 * @author MATHAF
 */
@Stateless
public class BankAccountDAO
{

    @PersistenceContext
    private EntityManager em;

    /**
     * Get List of BankAccount entities.
     * @return List of BankAccount entites.
     */
    public List<BankAccount> findAll()
    {
        TypedQuery<BankAccount> query = em.createQuery( "SELECT ba FROM BankAccount", BankAccount.class );
        return query.getResultList();
    }

    /**
     * Save BankAccount entity.
     * @param bankAccount BankAccount entity
     */
    public void save( BankAccount bankAccount )
    {
        em.persist( bankAccount );
    }

    /**
     * Update BankAccount entity.
     * @param bankAccount BankAccount entity
     */
    public void update( BankAccount bankAccount )
    {
        em.merge( bankAccount );
    }

    /**
     * Delete BankAccount entity.
     * @param id BankAccount identifier
     */
    public void remove( Long id )
    {
        BankAccount bankAccount = find( id );
        if ( bankAccount != null )
        {
            em.remove( bankAccount );
        }
    }

    /**
     * Delete BankAccount entity.
     * @param bankAccount BankAccount entity
     */
    public void remove( BankAccount bankAccount )
    {
        if ( bankAccount != null && em.contains( bankAccount ) )
        {
            em.remove( bankAccount );
        }
    }

    /**
     * Search BankAccount from identifier.
     * @param id BankAccount identifier
     * @return BankAccount if found else null
     */
    public BankAccount find( Long id )
    {
        return em.find( BankAccount.class, id );
    }

    /**
     * Removes Transactions-Context from BankAccount Entity.
     * Changes to the object won't effect the database values.
     * @param bankAccount BankAccount entity.
     */
    public void detach( BankAccount bankAccount )
    {
        em.detach( bankAccount );
    }
}
