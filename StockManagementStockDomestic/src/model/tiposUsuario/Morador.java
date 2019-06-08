/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tiposUsuario;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/// CLASSES PRÓPRIAS
import model.Data;
import model.Usuario;

/**
 * Representa um morador
 * @see Usuario
 * @see Data
 */
@Entity
@Table(name = "Morador")
public class Morador  extends Usuario implements Serializable {
 
    /// ATRIBUTOS ********************************************************************************

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Data dataNascimento;
    
    /// CONSTRUTOR *******************************************************************************

    Morador(){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************

    public Data getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Data dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected String ImplementYourToString() {		
        String myObjectInString = "";

        myObjectInString+= " " + this.dataNascimento.toString();

        return myObjectInString;
    } 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Morador)) {
            return false;
        }
        Morador other = (Morador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Morador[ id=" + id + " ]";
    }

}
