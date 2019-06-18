/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Material;
import model.tiposMaterial.Eletrodomestico;

/**
 * Representa uma validacao do material eletrodomestico
 * @see ValidacaoMaterial
 */
public class ValidacaoEletrodomestico extends ValidacaoMaterial{

    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Material material) throws ServiceException {
        
        if ( !material.getClass().equals(Eletrodomestico.class) ) 
            throw new ServiceException("Tipo de material inválido!");
        
        Eletrodomestico eletrodomestico = (Eletrodomestico) material;
                
        if(eletrodomestico.getEficienciaEnergetica() != 'A'
            && eletrodomestico.getEficienciaEnergetica() != 'B'
            && eletrodomestico.getEficienciaEnergetica() != 'C'
            && eletrodomestico.getEficienciaEnergetica() != 'D'
            && eletrodomestico.getEficienciaEnergetica() != 'E'
            && eletrodomestico.getEficienciaEnergetica() != 'F'
            && eletrodomestico.getEficienciaEnergetica() != 'G' ){
            throw new ServiceException("Eficiencia energetica inválida!");
        }
        
        if(eletrodomestico.getMarca() == null)
            throw new ServiceException("Marca inválida!");
        
        if(eletrodomestico.getMarca().equals(""))
            throw new ServiceException("Marca inválida!");
        
        if(eletrodomestico.getTempoDeGarantia() < 0)
            throw new ServiceException("Tempo de garantia inválido!");
        
    }
        
}
