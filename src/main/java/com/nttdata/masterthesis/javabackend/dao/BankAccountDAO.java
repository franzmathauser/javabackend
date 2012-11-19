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
public class BankAccountDAO
{

    @PersistenceContext
    private EntityManager em;

    public List<BankAccount> findAll()
    {
        TypedQuery<BankAccount> query = em.createQuery( "SELECT ba FROM BankAccount", BankAccount.class );
        return query.getResultList();
    }

    public void save( BankAccount bankAccount )
    {
        em.persist( bankAccount );
    }

    public void update( BankAccount bankAccount )
    {
        em.merge( bankAccount );
    }

    public void remove( Long id )
    {
        BankAccount bankAccount = find( id );
        if ( bankAccount != null )
        {
            em.remove( bankAccount );
        }
    }

    public void remove( BankAccount bankAccount )
    {
        if ( bankAccount != null && em.contains( bankAccount ) )
        {
            em.remove( bankAccount );
        }
    }

    public BankAccount find( Long id )
    {
        return em.find( BankAccount.class, id );
    }

    public void detach( BankAccount bankAccount )
    {
        em.detach( bankAccount );
    }
}
