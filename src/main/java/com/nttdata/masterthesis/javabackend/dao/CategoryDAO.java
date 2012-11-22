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
import com.nttdata.masterthesis.javabackend.entities.Category;

/**
 * Data Access Object to receive category informations.
 * @author MATHAF
 */
@Stateless
public class CategoryDAO
{
    @PersistenceContext
    private EntityManager em;

    /**
     * Get full list of categores.
     * @return List of category entities.
     */
    public List<Category> findAll()
    {
        TypedQuery<Category> query = em.createQuery( "SELECT c FROM Category", Category.class );
        return query.getResultList();
    }

    /**
     * Save Category entity.
     * @param category Category entity.
     */
    public void save( Category category )
    {
        em.persist( category );
    }

    /**
     * Update Category entity.
     * @param category category entity.
     */
    public void update( Category category )
    {
        em.merge( category );
    }

    /**
     * Remove Category entity by identifier.
     * @param id category id
     */
    public void remove( Long id )
    {
        Category category = find( id );
        if ( category != null )
        {
            em.remove( category );
        }
    }

    /**
     * Remove Category entity.
     * @param category Category entity.
     */
    public void remove( Category category )
    {
        if ( category != null && em.contains( category ) )
        {
            em.remove( category );
        }
    }

    /**
     * Find Category entity by identifier.
     * @param id category id
     * @return Category entity if found, else null
     */
    public Category find( Long id )
    {
        return em.find( Category.class, id );
    }

    /**
     * Removes Category-Context from Category entity.
     * Changes to the object won't effect the database values.
     * @param category Category entity
     */
    public void detach( Category category )
    {
        em.detach( category );
    }

    /**
     * Get a List of Categories for BankAccount entity.
     * @param bankAccount BankAccount entity.
     * @return List of categories entities if found else null;
     */
    public List<Category> findByBankAccount( BankAccount bankAccount )
    {
        Query query = em.createQuery( "SELECT c FROM Category c WHERE c.bankAccount = :bankAccount" );
        query.setParameter( "bankAccount", bankAccount );
        return (List<Category>) query.getResultList();
    }
}
