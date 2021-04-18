/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author enio1
 */
@Local
public interface AdministradorServiceBeanLocal {
   public List<Administrador> findAll();
   
   public void save(Administrador administrador);
   
   public void delete(Administrador administrador);
   
   public Administrador findAdministradorById(Long id);
}
