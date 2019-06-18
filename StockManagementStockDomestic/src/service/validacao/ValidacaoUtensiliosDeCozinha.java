/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Material;
import model.tiposMaterial.UtensiliosDeCozinha;

/**
 * Representa uma validacao do material utensiliosDeCozinha
 * @see ValidacaoMaterial
 */
public class ValidacaoUtensiliosDeCozinha extends ValidacaoMaterial{

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(UtensiliosDeCozinha.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        UtensiliosDeCozinha utensiliosDeCozinha = (UtensiliosDeCozinha) material;
                
        if(utensiliosDeCozinha.getMaterialDeFabricao() == null)
            throw new ServiceException("Material de fabricao inválido!");
        
        if(utensiliosDeCozinha.getMaterialDeFabricao().equals(""))
            throw new ServiceException("Material de fabricao inválido!");
        
        if(utensiliosDeCozinha.getTempoDeGarantia() < 0)
            throw new ServiceException("Tempo de garantia inválido!");
        
    }
        
}
