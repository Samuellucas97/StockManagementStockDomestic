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


/// CLASSES PRÓPRIAS
import model.Material;

/**
 * Representa um eletrodoméstico
 * @see Material
 */
@Entity
@Table(name = "Eletrodomestico")
public class Eletrodomestico extends Material implements Serializable {
    
    /// ATRIBUTOS ********************************************************************************

    @Column(nullable = false)
    private String marca;

    @Column(nullable = true)
    private int tempoDeGarantia;
    
    @Column(nullable = true)
    private char eficienciaEnergetica;
    
    /// CONSTRUTOR *******************************************************************************

    Eletrodomestico(){
        super();
    }
    
    /// GETTERS E SETTERS ************************************************************************

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getTempoDeGarantia() {
        return tempoDeGarantia;
    }

    public void setTempoDeGarantia(int tempoDeGarantia) {
        this.tempoDeGarantia = tempoDeGarantia;
    }

    public char getEficienciaEnergetica() {
        return eficienciaEnergetica;
    }

    public void setEficienciaEnergetica(char eficienciaEnergetica) {
        this.eficienciaEnergetica = eficienciaEnergetica;
    }
    
    /// MÉTODOS **********************************************************************************

    @Override
    protected String ImplementYourToString() {
        String myObjectInString = "";

        myObjectInString+= " " + this.marca;
        myObjectInString+= " " + this.eficienciaEnergetica;
        myObjectInString+= " " + this.tempoDeGarantia;

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
        if (!(object instanceof Eletrodomestico)) {
            return false;
        }
        Eletrodomestico other = (Eletrodomestico) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.tiposMaterial.Eletrodomestico[ id=" + id + " ]";
    }

    
}
