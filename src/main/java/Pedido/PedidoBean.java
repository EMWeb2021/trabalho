/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedido;

import Produto.Produto;
import ItemPedido.ItemPedido;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author enio1
 */
@Named(value= "pedidoBean" )
@ViewScoped
public class PedidoBean implements Serializable {

    @Inject
    private PedidoServiceBeanLocal pedidoService;
    
    private Pedido selectedPedido;
    
    private Long selectedPedidoId;
    
    private List<Pedido> allPedidos;
    
    private List<ItemPedido> allItens;
    
    private List<Pedido> filteredPedidos;
    
    public PedidoBean() {}
    
    @PostConstruct
       public void init(){
           if(selectedPedido == null){
               selectedPedido = new Pedido();
           }
           allItens = new ArrayList<ItemPedido>();
       }

    public List<ItemPedido> getAllItens() {
        return allItens;
    }

    public void setAllItens(List<ItemPedido> allItens) {
        this.allItens = allItens;
    }       
    
    public String teste(){
        System.out.println("Entrou aqui");
        return null;
    }
    
    public String addItem(Produto produto){
        
        
        ItemPedido contains = null;        
        
        for (ItemPedido item : selectedPedido.getItens()){
            System.out.println("ENTROU NO FOR");
            if(item.getProduto() == produto){
                System.out.println("TEM PRODUTO");
                contains = item;
            }  
        }
        
        if(contains != null){
            contains.add(1);            
            return null;
        }
        
        contains = new ItemPedido();
         
        contains.setQuantidade(1);
        contains.setProduto(produto);
        contains.setPedido(selectedPedido);
        produto.getItensPedidos().add(contains);
        selectedPedido.getItens().add(contains);
        PrimeFaces.current().ajax().update("totalValue");
        return null;
        
    }

    public String addOneItem(ItemPedido item){
        System.out.println("Entrou");
        item.setQuantidade(item.getQuantidade()+1);
        PrimeFaces.current().ajax().update("totalValue");
        return null;
    }
    
    public String removeItem(ItemPedido item){
        if(item.getQuantidade()>1){
            item.setQuantidade(item.getQuantidade()-1);
        }else{
            selectedPedido.getItens().remove(item);
        }
        
        PrimeFaces.current().ajax().update("totalValue");
        
        return null;
    }
    
    public BigDecimal totalValue(){
    
        BigDecimal total = BigDecimal.ZERO;
        BigDecimal quantidade = BigDecimal.ZERO;
        BigDecimal aux = BigDecimal.ZERO;
        
        for(ItemPedido item : selectedPedido.getItens()){
            quantidade = new BigDecimal(item.getQuantidade());
            System.out.println(quantidade);
            aux =  item.getProduto().getValor().multiply(quantidade);
            System.out.println(aux);
            total = total.add(aux);
        }
    
        System.out.println(total);
        
        return total;
    }
    
    public Pedido getSelectedPedido() {
        return selectedPedido;
    }

    public void setSelectedPedido(Pedido selectedPedido) {
        this.selectedPedido = selectedPedido;
    }

    public Long getSelectedPedidoId() {
        return selectedPedidoId;
    }

    public void setSelectedPedidoId(Long selectedPedidoId) {
        if(selectedPedidoId != null){
            selectedPedido = pedidoService.loadPedidoById(selectedPedidoId);
        }
        
        if(selectedPedidoId == null || selectedPedido == null){
            selectedPedido = new Pedido();
        }
        
        this.selectedPedidoId = selectedPedidoId;
    }

    public List<Pedido> getAllPedidos() {
        if(allPedidos == null){
            reloadPedidos();
        }
        return allPedidos;
    }

    

    public List<Pedido> getFilteredPedidos() {
        return filteredPedidos;
    }

    public void setFilteredPedidos(List<Pedido> filteredPedidos) {
        this.filteredPedidos = filteredPedidos;
    }
       
       public String save(Pedido pedido){
        pedido.setDataPedido(new Date());
        pedidoService.save(pedido);
        reloadPedidos();
        reset();
        return null;
    }
    
    public String saveCurrent(){
        
        save(selectedPedido);
        return null;
    }
    
     public String remove(Pedido pedido){
        
        System.out.println("EU CHEGUEI AQUI");
        
        pedidoService.delete(pedido);
        reloadPedidos();
        reset();
        return null;
    }
    
     public String removeCurrent(){
        remove(selectedPedido);
        return null;
    }
     
     public List<Pedido> findAll(){
        return pedidoService.findAll();
    }
    
      public Pedido loadPedido(Pedido pedido){
        if(pedido != null){
            Pedido fullPedido = pedidoService.loadPedidoById(pedido.getId());
            selectedPedido = fullPedido;
            return selectedPedido;
        }else{
            return null;
        }
    }
      
      public void reset(){
        selectedPedido = new Pedido();
    }
    
    private void reloadPedidos(){
        allPedidos = findAll();
    }
}
