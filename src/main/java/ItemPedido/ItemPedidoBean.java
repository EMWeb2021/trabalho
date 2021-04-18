/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemPedido;

import Produto.*;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author enio1
 */
@Named(value = "itemPedidoBean")
@ViewScoped
public class ItemPedidoBean implements Serializable{
    
    @Inject
    private ProdutoServiceBeanLocal produtoService;
    
    private Produto selectedProduto;
    
    private Long selectedProdutoId;
    
    private List<Produto> allProdutos;
    
    private List<Produto> filteredProdutos;
    
    public ItemPedidoBean(){}
    
    @PostConstruct
    public void init(){
        if(selectedProduto == null){
            selectedProduto = new Produto();
        }
    }


    public Produto getSelectedProduto() {
        return selectedProduto;
    }

    public void setSelectedProduto(Produto selectedProduto) {
        this.selectedProduto = selectedProduto;
    }

    public Long getSelectedProdutoId() {
        return selectedProdutoId;
    }

    public void setSelectedProdutoId(Long selectedProdutoId) {
        
        if(selectedProdutoId != null){
            selectedProduto = produtoService.loadProdutoByIdWithCategorias(selectedProdutoId);
        }
        
        if(selectedProdutoId == null || selectedProduto == null){
            selectedProduto = new Produto();
        }
        
        this.selectedProdutoId = selectedProdutoId;
        
    }

    public List<Produto> getAllProdutos() {
        
        if(allProdutos == null){
            reloadProdutos();
        }
        
        return allProdutos;
    }


    public List<Produto> getFilteredProdutos() {
        return filteredProdutos;
    }

    public void setFilteredProdutos(List<Produto> filteredProdutos) {
        this.filteredProdutos = filteredProdutos;
    }
    
    public String save(Produto produto){
        produtoService.save(produto);
        reloadProdutos();
        reset();
        return null;
    }
    
    public String saveCurrent(){
        save(selectedProduto);
        return null;
    }
    
     public String remove(Produto produto){
        
        System.out.println("EU CHEGUEI AQUI");
        
        produtoService.delete(produto);
        reloadProdutos();
        reset();
        return null;
    }
    
     public String removeCurrent(){
        remove(selectedProduto);
        return null;
    }
    
    public List<Produto> findAll(){
        return produtoService.findAll();
    }
    
    public Produto loadProdutoWithCategorias(Produto produto){
        if(produto != null){
            Produto fullProduto = produtoService.loadProdutoByIdWithCategorias(produto.getId());
            selectedProduto = fullProduto;
            return selectedProduto;
        }else{
            return null;
        }
    }
    
    public void reset(){
        selectedProduto = new Produto();
    }
    
    private void reloadProdutos(){
        allProdutos = findAll();
    }
    
    public void onCategoriaSelected() {
        System.out.println("ProdutoBean::onCategoriaSelected/Produto = > " + selectedProduto);
        System.out.println("ProdutoBean::onCategoriaSelected/Categorias = > " + selectedProduto.getCategorias());
    }
    
}
