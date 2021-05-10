/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

/**
 *
 * @author enio1
 */
@ApplicationScoped
public class DataInitializer {
   @Inject
    AdministradorServiceBeanLocal admService;
   
   public void execute(@Observes @Initialized(ApplicationScoped.class) Object event) {
        if (admService.findAll().isEmpty()) {
            Administrador guisso = new Administrador("Luis Guisso", "guisso", "asdf", "admin");            
                    admService.save(guisso);
            Administrador azacchi = new Administrador("Andrea Zacchi", "azacchi", "asdf", "user");
            admService.save(azacchi);

        }
    }
}
