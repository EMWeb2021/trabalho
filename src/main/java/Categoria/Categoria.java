/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Categoria;

import Entidade.JpaEntity;
import Produto.Produto;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author enio1
 */
@Entity
@NamedQueries({
    @NamedQuery(
            name = "categoria.findAll",
            query = "select c from Categoria c "
            + "order by c.nome" 
    ),
    @NamedQuery(
            name = "categoria.findCategoriaById",
            query = "select c from Categoria c "
            + "where c.id = :id "
            + "order by c.nome"
    ),
})
public class Categoria extends JpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Column(nullable = false, length = 50)
    @NotEmpty
    private String nome;

    @Column(nullable = true)
    @ManyToMany(mappedBy = "categorias", cascade = {CascadeType.ALL})
    private List<Produto> produtos;
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.getId());
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
        final Categoria other = (Categoria) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "Categoria.Categoria[ id=" + getId() 
                + ", name=" + getNome()
                + " ]";
    }
    
}
