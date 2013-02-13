/********************************************
 *       M A S T E R T H E S I S            *
 *                                          *
 * Franz Mathauser                          *
 * Hochschule München                       *
 * Immatrikulationsnummer: 01161608         *
 *                                          *
 ********************************************/
 
package com.nttdata.masterthesis.javabackend.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.nttdata.masterthesis.javabackend.entities.User;

/**
 * Data Access Object to receive user informations.
 * @author MATHAF
 */
@Stateless
public class UserDAO
{
    @PersistenceContext
    private EntityManager em;

    /**
     * Get full list of users.
     * @return List of User entites.
     */
    public List<User> findAll()
    {
        TypedQuery<User> query = em.createQuery( "SELECT usr FROM User", User.class );
        return query.getResultList();
    }

    /**
     * Saves User entity.
     * @param user User entity
     */
    public void save( User user )
    {
        em.persist( user );
    }

    /**
     * Updates User entity.
     * @param user User entity
     */
    public void update( User user )
    {
        em.merge( user );
    }

    /**
     * Removes User by identifier.
     * @param id user id
     */
    public void remove( Long id )
    {
        User user = find( id );
        if ( user != null )
        {
            em.remove( user );
        }
    }

    /**
     * Removes User by User entity.
     * @param user User entity
     */
    public void remove( User user )
    {
        if ( user != null && user.getEmail() != null && em.contains( user ) )
        {
            em.remove( user );
        }
    }

    /**
     * Find User by identifier.
     * @param id user id
     * @return User entity
     */
    public User find( Long id )
    {
        return em.find( User.class, id );
    }

    /**
     * Removes Transactions-Context from User entity.
     * Changes to the object won't effect the database values.
     *
     * @param user User entity
     */
    public void detach( User user )
    {
        em.detach( user );
    }

    /**
     * Find user by username.
     * @param userName username
     * @return User entity if found else null;
     */
    public User findByName( String userName )
    {
        Query query = em.createQuery( "SELECT u FROM User u WHERE u.userName = :username" );
        query.setParameter( "username", userName );
        return (User) query.getSingleResult();
    }
}
