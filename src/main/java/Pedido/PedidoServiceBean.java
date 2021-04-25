/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedido;

import Produto.Produto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author enio1
 */
@Stateless
public class PedidoServiceBean implements PedidoServiceBeanLocal {

   @PersistenceContext
   private EntityManager entityManager;
   
   @Override
    public void save(Pedido pedido) {
        pedido.toString();
        if (entityManager.contains(pedido)) {
            // Update attached -- Ever used??
            System.out.println("PedidoServiceBean::save[U].task => " + pedido);
            entityManager.persist(pedido);
        } else if (pedido.getId() != null) {
            // Detached entity
            System.out.println("PedidoServiceBean::save[U'].task => " + pedido);
            entityManager.merge(pedido);
        } else {
            // Create new
            System.out.println("PedidoServiceBean::save[S].task => " + pedido);
            
            // entityManager.persist(task);
            // Forces the merge to all related entities
            // (CascadeType.ALL) and avoids an exception.
            // However performance degrades. Review required.
            entityManager.merge(pedido);
        }
    }

    @Override
    public void delete(Pedido pedido) {
       entityManager.remove(pedido);
    }

    @Override
    public List<Pedido> findAll() {
        return entityManager
                .createNamedQuery("pedido.findAll", Pedido.class)
                .getResultList();
    }

    @Override
    public Pedido loadPedidoById(Long id) {
        return entityManager
                .createNamedQuery("pedido.loadPedidoById", Pedido.class)
                .setParameter("id", id)
                .getSingleResult();
    }
   
}
