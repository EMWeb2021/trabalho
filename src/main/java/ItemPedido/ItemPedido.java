/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ItemPedido;

import Pedido.*;
import Produto.*;
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
import javax.persistence.ManyToOne;
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
public class ItemPedido extends JpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Column
    @NotEmpty
    private int quantidade;
    
    @ManyToOne
    private Pedido pedido;
    
    @ManyToOne
    private Produto produto;

    public ItemPedido(){
        super();
        quantidade = 0;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
        final ItemPedido other = (ItemPedido) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "src.Produto[ id=" + getId() + " ]";
    }
    
}
