/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pedido;

import Produto.*;
import ItemPedido.*;
import Categoria.Categoria;
import Entidade.JpaEntity;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author enio1
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "produto.findAll",
            query = "select distinct p from Produto p "
                + "left join fetch p.categorias "
                + "order by p.id"
    ),
    @NamedQuery(
            name = "produto.loadProdutoByIdWithCategorias",
            query = "select distinct p from Produto p "
                + "left join fetch p.categorias "
                + "where p.id = :id "
                + "order by p.id"
    )
})
public class Pedido extends JpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Column(nullable = false, length = 150)
    @NotEmpty
    private String endereco;
    
    
    // Data, ValorTotal e Situação, gerado automaticamente
    
    
    
    @Column(nullable = false)
    @OneToMany(mappedBy="pedido")
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido(){
        super();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
       


    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItensPedidos(List<ItemPedido> itens) {
        this.itens = itens;
    }    

     @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pedido other = (Pedido) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "src.Produto[ id=" + getId() + " ]";
    }
    
}
