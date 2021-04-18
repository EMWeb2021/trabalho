/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author enio1
 */
@Stateless
public class CategoriaServiceBean implements CategoriaServiceBeanLocal {

   @PersistenceContext
   private EntityManager entityManager;
   
   @Override
    public void save(Categoria categoria) {
        if (entityManager.contains(categoria)) {
            // Update attached -- Ever used??
            System.out.println("CategoriaServiceBean::save[U].task => " + categoria);
            entityManager.persist(categoria);
        } else if (categoria.getId() != null) {
            // Detached entity
            System.out.println("CategoriaServiceBean::save[U'].task => " + categoria);
            entityManager.merge(categoria);
        } else {
            // Create new
            System.out.println("CategoriaServiceBean::save[S].task => " + categoria);
            
            // entityManager.persist(task);
            // Forces the merge to all related entities
            // (CascadeType.ALL) and avoids an exception.
            // However performance degrades. Review required.
            entityManager.merge(categoria);
        }
    }

    @Override
    public void delete(Categoria categoria) {
       
        Categoria toRemove = findCategoriaById(categoria.getId());
       
        try{
        if(toRemove != null){
            System.out.println("CategoriaServiceBean::delete[S].categoria => " + toRemove);
            entityManager.remove(toRemove);
            
        }
        }catch(Exception error){
            System.out.println("DEU ERRO NESSA BOSTA " + error);
        }
        
        
       
    }
   
    @Override
    public List<Categoria> findAll() {
        return entityManager
                .createNamedQuery(
                        "categoria.findAll",
                        Categoria.class)
                .getResultList();
    }
   
   @Override 
   public Categoria findCategoriaById(Long id){
       return entityManager
               .createNamedQuery("categoria.findCategoriaById", Categoria.class)
               .setParameter("id", id)
               .getSingleResult();
  
   }
}
