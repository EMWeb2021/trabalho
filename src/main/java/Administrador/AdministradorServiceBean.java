/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author enio1
 */
@Stateless
public class AdministradorServiceBean implements AdministradorServiceBeanLocal {

    @PersistenceContext
    private EntityManager entityManager;

     @Inject
     Pbkdf2PasswordHash passwordHasher;
    
    @Override
    public List<Administrador> findAll() {
        return entityManager
                .createNamedQuery(
                        "administrador.findAll",
                        Administrador.class)
                .getResultList();
    }

    @Override
    public Administrador save(Administrador administrador) {
        
       System.out.println("Indo salvar o admin: " + administrador.getNome() );
        
        Map<String, String> parameters = new HashMap<>();
        parameters.put("Pbkdf2PasswordHash.Iterations", "3071");
        parameters.put("Pbkdf2PasswordHash.Algorithm", "PBKDF2WithHmacSHA512");
        parameters.put("Pbkdf2PasswordHash.SaltSizeBytes", "64");
        passwordHasher.initialize(parameters);

        Administrador newAdministrador = new Administrador(
                administrador.getNome(),
                administrador.getUsuario(),
                passwordHasher.generate(
                        administrador.getSenha().toCharArray()),
                administrador.getGrupo());
        entityManager.persist(newAdministrador);

        return newAdministrador;
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
                .createNamedQuery("administrador.findAdministradorById", Administrador.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
