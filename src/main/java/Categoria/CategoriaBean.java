/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author enio1
 */
@Named(value = "categoriaBean")
@ViewScoped
public class CategoriaBean implements Serializable {

    @Inject
    private CategoriaServiceBeanLocal categoriaService;

    private Categoria selectedCategoria;

    private Long selectedCategoriaId;

    private List<Categoria> categorias;

    private List<Categoria> filteredCategorias;

    public CategoriaBean() {

    }

    @PostConstruct
    public void init() {
        

        if (selectedCategoria == null) {
            selectedCategoria = new Categoria();
        }
    }

    public Categoria getSelectedCategoria() {
        return selectedCategoria;
    }

    public Long getSelectedCategoriaId() {
        return selectedCategoriaId;
    }

    public void setSelectedCategoriaId(Long selectedCategoriaId) {
        if (selectedCategoriaId != null) {
            selectedCategoria = categoriaService.findCategoriaById(selectedCategoriaId);
        }

        if (selectedCategoriaId == null || selectedCategoria == null) {
            selectedCategoria = new Categoria();
        }

        this.selectedCategoriaId = selectedCategoriaId;
    }

    public void setSelectedCategoria(Categoria selectedCategoria) {
        this.selectedCategoria = selectedCategoria;
    }

    public List<Categoria> getCategorias() {

        if (categorias == null) {
            reloadCategorias();
        }

        return categorias;
    }

    public List<Categoria> getFilteredCategorias() {
        return filteredCategorias;
    }

    public void setFilteredCategorias(List<Categoria> filteredCategorias) {
        this.filteredCategorias = filteredCategorias;
    }

    public String save(Categoria categoria){
        categoriaService.save(categoria);
        reloadCategorias();
        reset();
        return null;
    }
    
    public String saveCurrent(){
        save(selectedCategoria);
        return null;
    }
    
    public void reset(){
        selectedCategoria = new Categoria();
    }
    
    private void reloadCategorias(){
        categorias = findAll();
    }
    
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    public Categoria findCategoriaById(Long id) {
        return categoriaService.findCategoriaById(id);
    }
    
    public String remove(Categoria categoria){
        
        System.out.println("EU CHEGUEI AQUI");
        
        categoriaService.delete(categoria);
        reloadCategorias();
        reset();
        return null;
    }
    
     public String removeCurrent(){
        remove(selectedCategoria);
        return null;
    }
     
     public Categoria loadCategoria(Categoria categoria){
        if(categoria != null){
            Categoria fullCategoria = categoriaService.findCategoriaById(categoria.getId());
            selectedCategoria = fullCategoria;
            return selectedCategoria;
        }else{
            return null;
        }
    }
    
}
