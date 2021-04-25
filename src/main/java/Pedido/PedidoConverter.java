package Pedido;

import Produto.ProdutoServiceBeanLocal;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author enio1
 */
@FacesConverter(value = "pedidoConverter", managed = true)
@ApplicationScoped
public class PedidoConverter implements Converter<Pedido>{
    @Inject
    private PedidoServiceBeanLocal pedidoService;
     
    @Override
    public Pedido getAsObject(
            FacesContext context,
            UIComponent component,
            String id) {
        if (id == null) {
            return null;
        }
        return pedidoService
                .loadPedidoById(Long.parseLong(id));
    }

    @Override
    public String getAsString(
            FacesContext context,
            UIComponent component,
            Pedido pedido){
        if (pedido == null) {
            return null;
        }
        return pedido.getId().toString();
    }
}
