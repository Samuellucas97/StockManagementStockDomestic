/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Material;
import model.tiposMaterial.Alimento;

/**
 * Representa uma validacao do material alimento
 * @see ValidacaoMaterial
 */
public class ValidacaoAlimento extends ValidacaoMaterial{

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(Alimento.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        Alimento alimento = (Alimento) material;
                 
        if(alimento.getDataDeValidade() == null)
            throw new ServiceException("Data de validade inválida!");
        
        if(alimento.getDataDeValidade().equals(""))
            throw new ServiceException("Data de validade inválida!");
        
        if(alimento.getQuantidadeDeCalorias() < 0)
            throw new ServiceException("Quantidade de calorias inválida!");
        
    }
        
}
