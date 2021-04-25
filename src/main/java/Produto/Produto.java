/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Produto;

import ItemPedido.ItemPedido;
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
public class Produto extends JpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
  
    @Column(nullable = false, length = 150)
    @NotEmpty
    private String nome;
    
    @Column(nullable = false, length = 300)
    @NotEmpty
    private String descricao;
    
    @Column(precision = 7, scale = 2)
    @DecimalMin(value = "0.00")
    @DecimalMax(value = "99999.00")
    private BigDecimal valor;
    
    @Column(nullable = false)
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name="produtos_categorias",
            joinColumns = @JoinColumn(name="produto_id"),
            inverseJoinColumns = @JoinColumn(name="categoria_id"))
    private List<Categoria> categorias;

    @OneToMany(fetch = FetchType.EAGER,mappedBy="produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itensPedidos;

    public List<ItemPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(List<ItemPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }    
    
    
    public Produto(){
        super();
        valor = BigDecimal.ZERO;
        categorias = new ArrayList<Categoria>();
        itensPedidos = new ArrayList<ItemPedido>();
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }


    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
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
        final Produto other = (Produto) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "src.Produto[ id=" + getId() + ", nome = " + nome + " ]";
    }
    
}
