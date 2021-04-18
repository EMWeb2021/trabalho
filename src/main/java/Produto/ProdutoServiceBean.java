/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author enio1
 */
@Stateless
public class ProdutoServiceBean implements ProdutoServiceBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;
    
    
    @Override
    public void save(Produto produto) {
        if (entityManager.contains(produto)) {
            // Update attached -- Ever used??
            System.out.println("ProdutoServiceBean::save[U].task => " + produto);
            entityManager.persist(produto);
        } else if (produto.getId() != null) {
            // Detached entity
            System.out.println("ProdutoServiceBean::save[U'].task => " + produto);
            entityManager.merge(produto);
        } else {
            // Create new
            System.out.println("ProdutoServiceBean::save[S].task => " + produto);
            
            // entityManager.persist(task);
            // Forces the merge to all related entities
            // (CascadeType.ALL) and avoids an exception.
            // However performance degrades. Review required.
            entityManager.merge(produto);
        }
    }

    @Override
    public void delete(Produto produto) {
       entityManager.remove(produto);
    }

    @Override
    public List<Produto> findAll() {
        return entityManager
                .createNamedQuery("produto.findAll", Produto.class)
                .getResultList();
    }

    @Override
    public Produto loadProdutoByIdWithCategorias(Long id) {
        return entityManager
                .createNamedQuery("produto.loadProdutoByIdWithCategorias", Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

   
}
