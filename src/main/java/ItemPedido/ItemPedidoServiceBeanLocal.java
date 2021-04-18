/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemPedido;

import Produto.*;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author enio1
 */
@Local
public interface ItemPedidoServiceBeanLocal {
   
    public void save(ItemPedido item);
    
    public void delete(ItemPedido item);
    
        
}
