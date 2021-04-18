/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author enio1
 */
@Local
public interface ProdutoServiceBeanLocal {
   
    public void save(Produto produto);
    
    public void delete(Produto produto);
    
    public List<Produto> findAll();
    
    public Produto loadProdutoByIdWithCategorias(Long id);
    
}
