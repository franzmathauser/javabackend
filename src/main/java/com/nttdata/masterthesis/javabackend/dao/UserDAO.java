/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import com.nttdata.masterthesis.javabackend.entities.User;
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
public class UserDAO {
    @PersistenceContext
    private EntityManager em;
     
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("SELECT usr FROM User", User.class);
        return query.getResultList();
    }
  
    public void save(User user) {
        em.persist(user);
    }
  
    public void update(User user) {
        em.merge(user);
    }
  
    public void remove(Long id) {
        User user = find(id);
        if (user != null) {
            em.remove(user);
        }
    }
      
    public void remove(User user) {
        if (user != null && user.getEmail()!=null && em.contains(user)) {
            em.remove(user);
        }
    }
  
    public User find(Long id) {
        return em.find(User.class, id);
    }
     
    public void detach(User user) {
        em.detach(user);
    }

    public User findByName(String userName) {
         Query query = em.createQuery("SELECT u FROM User u WHERE u.userName = :username");
         query.setParameter("username", userName);
         return (User) query.getSingleResult();
    } 
}
