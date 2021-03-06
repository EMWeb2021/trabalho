/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.security.enterprise.SecurityContext;
import javax.servlet.ServletException;

/**
 *
 * @author enio1
 */
@Named(value = "administradorBean")
@RequestScoped
public class AdministradorBean implements Serializable {

   @Inject
   private AdministradorServiceBeanLocal administradorService;
   
   @Inject
    SecurityContext securityContext;
   
   @Inject
    FacesContext facesContext;
   
   private Administrador selectedAdministrador;
   
   private Long selectedAdministradorId;
   
   private List<Administrador> administradores; 
   
   private List<Administrador> filteredAdministradores;
   
   
   public AdministradorBean (){
       
   }
   
   @PostConstruct
   public void init(){
       if (selectedAdministrador == null) {
            selectedAdministrador = new Administrador();
        }
   }

    public Administrador getSelectedAdministrador() {
        return selectedAdministrador;
    }

    public void setSelectedAdministrador(Administrador selectedAdministrador) {
        this.selectedAdministrador = selectedAdministrador;
    }

    
    
    public Long getSelectedAdministradorId() {
        return selectedAdministradorId;
    }
    

    public void setSelectedAdministradorId(Long selectedAdministradorId) {
        if (selectedAdministradorId != null) {
            selectedAdministrador = administradorService.findAdministradorById(selectedAdministradorId);
        }

        if (selectedAdministradorId == null || selectedAdministrador == null) {
            selectedAdministrador = new Administrador();
        }

        this.selectedAdministradorId = selectedAdministradorId;
    }

    public List<Administrador> getAdministradores() {
        
        if(administradores == null){
            reloadAdministradores();
        }
        return administradores;
    }

  

    public List<Administrador> getFilteredAdministradores() {
        return filteredAdministradores;
    }

    public void setFilteredAdministradores(List<Administrador> filteredAdministradores) {
        this.filteredAdministradores = filteredAdministradores;
    }
   
   

     public String save(Administrador administrador){
        administradorService.save(administrador);
        reloadAdministradores();
        reset();
        return null;
    }
    
    public String saveCurrent(){
        save(selectedAdministrador);
        return null;
    }
    
    public void reset(){
        selectedAdministrador = new Administrador();
    }
    
    private void reloadAdministradores(){
        administradores = findAll();
    }
    
    public List<Administrador> findAll() {
        return administradorService.findAll();
    }

    public Administrador findAdministradorById(Long id) {
        return administradorService.findAdministradorById(id);
    }
    
    public String remove(Administrador administrador){
        
        System.out.println("EU CHEGUEI AQUI");
        
        administradorService.delete(administrador);
        reloadAdministradores();
        reset();
        return null;
    }
    
     public String removeCurrent(){
        remove(selectedAdministrador);
        return null;
    }
     
     public Administrador loadAdministrador(Administrador administrador){
        if(administrador != null){
            Administrador fullAdministrador = administradorService.findAdministradorById(administrador.getId());
            fullAdministrador.setSenha(null);
            selectedAdministrador = fullAdministrador;
            
            return selectedAdministrador;
        }else{
            return null;
        }
    }
     
      public boolean isAuthenticated() {
        return securityContext.getCallerPrincipal() != null;
    }

    public boolean isAllowedToSeeUsers() {
        return securityContext.isCallerInRole("admin");
    }

    public String logout() throws ServletException {
        System.out.println("fazendo logout...");
        facesContext.getExternalContext()
                .invalidateSession();
        return "/login?faces-redirect=true";
    }
}
