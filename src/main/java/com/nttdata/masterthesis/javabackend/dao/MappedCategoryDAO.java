/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nttdata.masterthesis.javabackend.entities.MappedCategory;

/**
 * Data Access Object to receive mapped categories informations.
 * @author MATHAF
 */
@Stateless
public class MappedCategoryDAO
{
    @PersistenceContext
    private EntityManager em;

    /**
     * Get full list of mapped categores.
     * @return List of MappedCategory entity
     */
    public List<MappedCategory> findAll()
    {
        TypedQuery<MappedCategory> query = em.createQuery( "SELECT trx FROM MappedCategory", MappedCategory.class );
        return query.getResultList();
    }

    /**
     * Save MappedCategory entity.
     * @param mappedCategory MappedCategory entity
     */
    public void save( MappedCategory mappedCategory )
    {
        em.persist( mappedCategory );
    }

    /**
     * Update MappedCategory entity.
     * @param mappedCategory MappedCategory entity.
     */
    public void update( MappedCategory mappedCategory )
    {
        em.merge( mappedCategory );
    }

    /**
     * Remove MappedCategory entity from identifier.
     * @param id MappedCategory identifier
     */
    public void remove( Long id )
    {
        MappedCategory mappedCategory = find( id );
        if ( mappedCategory != null )
        {
            em.remove( mappedCategory );
        }
    }

    /**
     * Remove MappedCategory entity.
     * @param mappedCategory MappedCategory entity.
     */
    public void remove( MappedCategory mappedCategory )
    {
        if ( mappedCategory != null && em.contains( mappedCategory ) )
        {
            em.remove( mappedCategory );
        }
    }

    /**
     * Find MappedCategory by identifier.
     * @param id identifier
     * @return MappedCategory entity if found else null
     */
    public MappedCategory find( Long id )
    {
        return em.find( MappedCategory.class, id );
    }

    /**
     * Removes Transactions-Context from MappedCategory Entity.
     * Changes to the object won't effect the database values.
     *
     * @param mappedCategory MappedCategory entity
     */
    public void detach( MappedCategory mappedCategory )
    {
        em.detach( mappedCategory );
    }

    /**
     * Find MappedCategory from mapping id.
     * @param mappedId transaction id
     * @return MappedCategory if found else null
     */
    public MappedCategory findByMappedId( String mappedId )
    {
        Query query = em.createQuery( "SELECT c FROM MappedCategory c WHERE c.mappedId = :mappedId" );
        query.setParameter( "mappedId", mappedId );
        try
        {
            return (MappedCategory) query.getSingleResult();
        }
        catch ( NoResultException ex )
        {
            return null;
        }

    }
}
