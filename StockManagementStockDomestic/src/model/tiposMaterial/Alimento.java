/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.tiposMaterial;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/// CLASSES PRÓPRIAS
import model.Data;
import model.Material;

/**
 * Representa um alimento
 * @see Material
 */
@Entity
@Table(name = "Alimento")
public class Alimento extends Material implements Serializable {

    /// ATRIBUTOS ********************************************************************************

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Data dataDeValidade;
    
    @Column(nullable = true)
    private int quantidadeDeCalorias;
    
    /// CONSTRUTOR *******************************************************************************

    Alimento(){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************

    public Data getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(Data dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public int getQuantidadeDeCalorias() {
        return quantidadeDeCalorias;
    }

    public void setQuantidadeDeCalorias(int quantidadeDeCalorias) {
        this.quantidadeDeCalorias = quantidadeDeCalorias;
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " + this.dataDeValidade.toString();
        myObjectInString+= " " + this.quantidadeDeCalorias;

        return myObjectInString;
    }
        
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alimento)) {
            return false;
        }
        Alimento other = (Alimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.tiposMaterial.Alimento[ id=" + id + " ]";
    }

}
