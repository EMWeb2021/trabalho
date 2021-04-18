/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author enio1
 */
@Stateless
public class AdministradorServiceBean implements AdministradorServiceBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Administrador> findAll() {
        return entityManager
                .createNamedQuery(
                        "administrador.findAll",
                        Administrador.class)
                .getResultList();
    }

    @Override
    public void save(Administrador administrador) {
        if (entityManager.contains(administrador)) {
            // Update attached -- Ever used??
            System.out.println("AdministradorServiceBean::save[U].task => " + administrador);
            entityManager.persist(administrador);
        } else if (administrador.getId() != null) {
            // Detached entity
            System.out.println("AdministradorServiceBean::save[U'].task => " + administrador);
            entityManager.merge(administrador);
        } else {
            // Create new
            System.out.println("AdministradorServiceBean::save[S].task => " + administrador);

            // entityManager.persist(task);
            // Forces the merge to all related entities
            // (CascadeType.ALL) and avoids an exception.
            // However performance degrades. Review required.
            entityManager.merge(administrador);
        }
    }

    @Override
    public void delete(Administrador administrador) {
        Administrador toRemove = findAdministradorById(administrador.getId());

        try {
            if (toRemove != null) {
                System.out.println("AdministradorServiceBean::delete[S].categoria => " + toRemove);
                entityManager.remove(toRemove);
            }
        } catch (Exception error) {
            System.out.println("DEU ERRO NESSA BOSTA " + error);
        }
    }

    @Override
    public Administrador findAdministradorById(Long id) {
        return entityManager
                .createNamedQuery("administrador.findCategoriaById", Administrador.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
