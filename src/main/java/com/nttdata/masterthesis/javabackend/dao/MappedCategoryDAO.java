/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nttdata.masterthesis.javabackend.dao;

import com.nttdata.masterthesis.javabackend.entities.BankAccount;
import com.nttdata.masterthesis.javabackend.entities.MappedCategory;
import com.nttdata.masterthesis.javabackend.entities.Transaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author MATHAF
 */
@Stateless
public class MappedCategoryDAO {
    
    @PersistenceContext
    private EntityManager em;
     
    public List<MappedCategory> findAll() {
        TypedQuery<MappedCategory> query = em.createQuery("SELECT trx FROM MappedCategory", MappedCategory.class);
        return query.getResultList();
    }
  
    public void save(MappedCategory mappedCategory) {
        em.persist(mappedCategory);
    }
  
    public void update(MappedCategory mappedCategory) {
        em.merge(mappedCategory);
    }
  
    public void remove(Long id) {
        MappedCategory mappedCategory = find(id);
        if (mappedCategory != null) {
            em.remove(mappedCategory);
        }
    }
      
    public void remove(MappedCategory mappedCategory) {
        if (mappedCategory != null && em.contains(mappedCategory)) {
            em.remove(mappedCategory);
        }
    }
  
    public MappedCategory find(Long id) {
        return em.find(MappedCategory.class, id);
    }
     
    public void detach(MappedCategory mappedCategory) {
        em.detach(mappedCategory);
    }

    public MappedCategory findByMappedId(String mappedId) {
         Query query = em.createQuery("SELECT c FROM MappedCategory c WHERE c.mappedId = :mappedId");
         query.setParameter("mappedId", mappedId);
         try {
             return (MappedCategory) query.getSingleResult();
         }catch(NoResultException ex){
             return null;
         }
         
         
         
    }
}
