/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedido;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author enio1
 */
@Local
public interface PedidoServiceBeanLocal {
    public void save(Pedido pedido);
    
    public void delete(Pedido pedido);
    
    public List<Pedido> findAll();
    
    public Pedido loadPedidoById(Long id);
}
