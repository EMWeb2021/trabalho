/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Administrador;

import Entidade.JpaEntity;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
            name = "administrador.findAll",
            query = "select a from Administrador a "
            + "order by a.nome" 
    ),
    @NamedQuery(
            name = "administrador.findAdministradorById",
            query = "select a from Administrador a "
            + "where a.id = :id "
            + "order by a.nome"
    ),
})
public class Administrador extends JpaEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Column(nullable = false, length = 50)
    @NotEmpty
    private String nome;
    
    @Column(nullable = false, length = 50)
    @NotEmpty
    private String usuario;
    
    @Column(nullable = false, length = 50)
    @NotEmpty
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
        final Administrador other = (Administrador) obj;
        return Objects.equals(this.getId(), other.getId());
    }

    @Override
    public String toString() {
        return "src.Administrador[ id=" + getId() + " ]";
    }
    
}
