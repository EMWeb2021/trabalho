/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import Categoria.*;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author enio1
 */

@FacesConverter(value="administradorConverter", managed=true)
@ApplicationScoped
public class AdministradorConverter implements Converter<Administrador>{
    
    @Inject
    private AdministradorServiceBeanLocal administradorService;
    
    @Override
    public Administrador getAsObject(
            FacesContext context, 
            UIComponent component, 
            String id) {
        if (id == null) {
            return null;
        }
        return administradorService
                .findAdministradorById(Long.parseLong(id));
    }

    @Override
    public String getAsString(
            FacesContext context, 
            UIComponent component, 
            Administrador administrador) {
        if (administrador == null) {
            return null;
        }
        return administrador.getId().toString();
    }
}
