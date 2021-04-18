/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

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
@FacesConverter(value = "produtoConverter", managed = true)
@ApplicationScoped
public class ProdutoConverter implements Converter<Produto>{
    
    @Inject
    private ProdutoServiceBeanLocal produtoService;
     
    @Override
    public Produto getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        return produtoService
                .loadProdutoByIdWithCategorias(Long.parseLong(id));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Produto produto) {
        if (produto == null) {
            return null;
        }
        return produto.getId().toString();
    }
}
