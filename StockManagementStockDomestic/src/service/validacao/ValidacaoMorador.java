/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.validacao;

import exception.ServiceException;
import model.Usuario;
import model.tiposUsuario.Morador;

/**
 * Representa uma validacao do usuario morador
 * @see ValidacaoUsuario
 */
public class ValidacaoMorador extends ValidacaoUsuario{
    
    /// MÉTODOS **********************************************************************************
    
    @Override
    protected void validacaoImplementacao(Usuario usuario) throws ServiceException {
        if ( !usuario.getClass().equals(Morador.class) ) 
            throw new ServiceException("Tipo de usuário inválido!");
        
        Morador morador = (Morador) usuario;
        
        if(morador.getDataNascimento() == null)
            throw new ServiceException("Data de nascimento inválida!");
       
        if(morador.getDataNascimento().equals("") ) 
            throw new ServiceException("Data de nascimento invalida!");
                        
    }
    
}
