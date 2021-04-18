/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

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

@FacesConverter(value="categoriaConverter", managed=true)
@ApplicationScoped
public class CategoriaConverter implements Converter<Categoria>{
    
    @Inject
    private CategoriaServiceBeanLocal categoriaService;
    
    @Override
    public Categoria getAsObject(
            FacesContext context, 
            UIComponent component, 
            String id) {
        if (id == null) {
            return null;
        }
        return categoriaService
                .findCategoriaById(Long.parseLong(id));
    }

    @Override
    public String getAsString(
            FacesContext context, 
            UIComponent component, 
            Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return categoria.getId().toString();
    }
}
