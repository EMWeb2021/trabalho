/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author enio1
 */
@Local
public interface CategoriaServiceBeanLocal {
       
    public List<Categoria> findAll();
    
    public void save(Categoria categoria);
    
    public void delete(Categoria categoria);
    
    public Categoria findCategoriaById(Long id);
}
